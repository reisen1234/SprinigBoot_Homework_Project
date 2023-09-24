package com.example.exercise.service.serviceIml;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.exercise.domain.pojo.Book;
import com.example.exercise.mapper.BookMapper;
import com.example.exercise.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceIml extends ServiceImpl<BookMapper, Book> implements BookService {
}
