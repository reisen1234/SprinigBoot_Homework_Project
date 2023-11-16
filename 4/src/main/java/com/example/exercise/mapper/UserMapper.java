package com.example.exercise.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.exercise.domain.pojo.Book;
import com.example.exercise.domain.pojo.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    int updateBalance(@Param("ew") LambdaQueryWrapper<User> wrapper, @Param("money") Integer money);
}
