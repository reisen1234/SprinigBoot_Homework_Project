package com.example.exercise.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.exercise.domain.pojo.Book;

public interface BookService extends IService<Book> {
    boolean removeBookByIds(LambdaQueryWrapper<Book> wrapper, boolean delFlag);
}
