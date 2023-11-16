package com.example.exercise.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.exercise.domain.pojo.User;

public interface UserService extends IService<User> {
    void transfer(Integer transferor,Integer receiver, Integer money);
}
