package com.example.exercise.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.exercise.domain.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
