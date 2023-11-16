package com.example.exercise.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.exercise.domain.pojo.User;
import com.example.exercise.mapper.UserMapper;
import com.example.exercise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceIml extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;
    @Override
    @Transactional
    public void transfer(Integer transferor, Integer receiver, Integer money) {
        LambdaQueryWrapper<User> transferorWrapper = new LambdaQueryWrapper<User>().eq(User::getId,transferor);
        LambdaQueryWrapper<User> receiverWrapper = new LambdaQueryWrapper<User>().eq(User::getId,receiver);
        userMapper.updateBalance(transferorWrapper,-money);
        userMapper.updateBalance(receiverWrapper,money);
    }
}
