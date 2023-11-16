package com.example.exercise.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exercise.domain.dto.UserDto;
import com.example.exercise.domain.pojo.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author reisen
 * @since 2023-10-22
 */
public interface ITbUserService extends IService<TbUser> {
    public Map<Object, Object> login(TbUser user);
    public UserDto checkLogin(String token);
    public int register(TbUser tbUser);
    public Page<TbUser>page();

    boolean removeUserByIds(LambdaQueryWrapper<TbUser> wrapper, boolean delFlag);
}
