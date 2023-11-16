package com.example.exercise.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exercise.domain.dto.UserDto;
import com.example.exercise.domain.pojo.Book;
import com.example.exercise.domain.pojo.TbUser;
import com.example.exercise.domain.vo.Result;
import com.example.exercise.domain.vo.ResultType;
import com.example.exercise.service.ITbUserService;
import com.example.exercise.utils.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author reisen
 * @since 2023-10-22
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
@Api(tags = "User管理接口")
public class TbUserController {
    private final ITbUserService userService;
    @PostMapping(value = "/login")
    @ApiOperation("登入")
    public Object login(@RequestBody TbUser user){
        Map<Object,Object> result = userService.login(user);
        Map<Object,Object> ans = new HashMap<>();
        if(result.get("flag").equals(Constant.DELETED))return new Result<>(ResultType.FAIL.getCode(),"该用户被删除",null);
        else if(result.get("flag").equals(Constant.ILLEGAL))return new Result<>(ResultType.FAIL.getCode(),"输入不合法",null);
        else if(result.get("flag").equals(Constant.ERROR))return new Result<>(ResultType.FAIL.getCode(),"账号或者密码错误",null);
        else if(result.get("flag").equals(Constant.SUCCESS)){
            ans.put("token",result.get("token"));
            ans.put("user", result.get("user"));
            return new Result<>(ResultType.SUCCESS.getCode(),"成功",ans);
        }
        return new Result<>(ResultType.INTERNAL_SERVER_ERROR.getCode(),"未知错误",null);
    }
    @PostMapping(value = "/checkLogin")
    @ApiOperation("查看是否登入")
    public Object checkLogin(@RequestBody Map<String, String> map){
        UserDto userDto = userService.checkLogin(map.get("token"));
        if(userDto == null)return new Result<>(ResultType.FAIL.getCode(),"已过期",null);
        return new Result<UserDto>(ResultType.SUCCESS.getCode(),"成功",userDto);
    }
    @PostMapping(value = "/register")
    @ApiOperation("注册")
    public Object register(@RequestBody TbUser user){
        System.out.println(user);
        int flag = userService.register(user);
        if(flag == Constant.ILLEGAL)return new Result<>(ResultType.FAIL.getCode(),"输入不合法",null);
        else if(flag == Constant.EXIST)return new Result<>(ResultType.FAIL.getCode(),"该用户已存在",null);
        return new Result<>(ResultType.SUCCESS.getCode(),"注册成功",null);
    }
    @PostMapping(value = "/page")
    @ApiOperation("Get Page")
    public Object page(){
        Page<TbUser> page = userService.page();
        return new Result<>(ResultType.SUCCESS.getCode(),"success",page);
    }
    @PostMapping(value = "/list")
    @ApiOperation("Get List")
    public Object list(){
        List<TbUser> tbUsers = userService.list();
        return new Result<>(ResultType.SUCCESS.getCode(),"success",tbUsers);
    }
    @DeleteMapping("/deleteByIds")
    public Object deleteByIds(@ApiParam("Remove Book`s id") @RequestParam("ids") List<Integer> ids){
        if(ids == null || ids.isEmpty())
            return new Result<>(ResultType.FAIL.getCode(),"非法Id",null);
        LambdaQueryWrapper<TbUser> wrapper = new LambdaQueryWrapper<TbUser>()
                .in(TbUser::getId,ids)
                .eq(TbUser::getDelFlag, false);
        if(!userService.removeUserByIds(wrapper,true)){
            return new Result<>(ResultType.FAIL.getCode(), "删除失败!", null);
        }
        return new Result<>(ResultType.SUCCESS.getCode(),"删除成功",null);
    }
}
