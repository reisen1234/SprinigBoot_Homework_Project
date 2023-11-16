package com.example.exercise.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.exercise.domain.dto.BookDto;
import com.example.exercise.domain.dto.UserDto;
import com.example.exercise.domain.pojo.Book;

import java.util.List;

public interface BookService extends IService<Book> {
    boolean removeBookByIds(LambdaQueryWrapper<Book> wrapper, boolean delFlag);
    List<BookDto> pageByUsername(Integer userId, UserDto userDto);
}
