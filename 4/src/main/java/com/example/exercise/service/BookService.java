package com.example.exercise.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.exercise.domain.dto.BookDto;
import com.example.exercise.domain.dto.UserDto;
import com.example.exercise.domain.pojo.Book;

public interface BookService extends IService<Book> {
    boolean removeBookByIds(LambdaQueryWrapper<Book> wrapper, boolean delFlag);
    Page<BookDto> pageByUsername(Integer pageNum, Integer pageSize, Integer userId, UserDto userDto);
}
