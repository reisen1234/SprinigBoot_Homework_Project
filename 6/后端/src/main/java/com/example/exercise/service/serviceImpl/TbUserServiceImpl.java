package com.example.exercise.service.serviceImpl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exercise.domain.dto.UserDto;
import com.example.exercise.domain.pojo.TableTest;
import com.example.exercise.domain.pojo.TbUser;
import com.example.exercise.mapper.TbUserMapper;
import com.example.exercise.service.ITbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.exercise.utils.BaseUtil;
import com.example.exercise.utils.Constant;
import lombok.AllArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author reisen
 * @since 2023-10-22
 */
@Service
@AllArgsConstructor
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {

    private final RedisTemplate<String,Object> redisTemplate;
    private final TbUserMapper tbUserMapper;
    @Override
    public Map<Object, Object> login(TbUser user) {
        //被删除用户 0 输入不合法 1 账密错误 2 成功 3 未知错误 4
        Map<Object,Object>map = new HashMap<>();
        if(!BaseUtil.check(user)){
            map.put("flag",Constant.ILLEGAL);
            return map;
        }
        LambdaQueryWrapper<TbUser> wrapper = new LambdaQueryWrapper<TbUser>()
                .eq(TbUser::getName,user.getName());
        TbUser tbUser = tbUserMapper.selectOne(wrapper);
        if(tbUser != null && tbUser.getDelFlag() == true){
            map.put("flag",Constant.DELETED);
        }else {
            if(tbUser != null && tbUser.getPassword().equals(user.getPassword())){
                String token = UUID.randomUUID().toString();
                redisTemplate.opsForValue().set(token,tbUser.getId().toString(), Duration.ofMinutes(Constant.TOKEN_EXPIRE_TIME));
                map.put("flag",Constant.SUCCESS);
                map.put("token",token);
                map.put("user",tbUser);
            }else {
                map.put("flag",Constant.ERROR);
            }
        }
        return map;
    }

    @Override
    public UserDto checkLogin(String token) {
        String userId = (String) redisTemplate.opsForValue().get(token);
        TbUser user;
        UserDto userDto = null;
        if(userId != null){
            redisTemplate.expire(token, Constant.TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
            user = tbUserMapper.selectById(Integer.valueOf(userId));
            userDto = BeanUtil.copyProperties(user,UserDto.class);
        }
        return userDto;
    }

    @Override
    public int register(TbUser user) {
        //1.输入不合法 2.已存在username 3.成功
        LambdaQueryWrapper<TbUser> wrapper = new LambdaQueryWrapper<TbUser>().eq(TbUser::getName,user.getName());
        if(!BaseUtil.check(user))return Constant.ILLEGAL;
        else if(tbUserMapper.exists(wrapper))return Constant.EXIST;
        else {
            user.setCreateTime(BaseUtil.getCurrentTime());
            tbUserMapper.insert(user);
            return Constant.SUCCESS;
        }
    }

    @Override
    public Page<TbUser> page() {
        Page<TbUser> page = new Page<>(0,10);
        return lambdaQuery()
                .page(page);
    }

    @Override
    public boolean removeUserByIds(LambdaQueryWrapper<TbUser> wrapper, boolean delFlag) {
        if(tbUserMapper.removeBookByIds(wrapper,delFlag) == 0)
            return false;
        return true;
    }
}
