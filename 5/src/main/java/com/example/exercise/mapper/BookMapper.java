package com.example.exercise.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.exercise.domain.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    int removeBookByIds(@Param("ew")LambdaQueryWrapper<Book>wrapper,@Param("delFlag") boolean delFlag);
}
