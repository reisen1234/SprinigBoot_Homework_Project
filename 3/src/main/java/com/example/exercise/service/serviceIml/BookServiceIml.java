package com.example.exercise.service.serviceIml;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.exercise.domain.pojo.Book;
import com.example.exercise.mapper.BookMapper;
import com.example.exercise.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceIml extends ServiceImpl<BookMapper, Book> implements BookService {
    private final BookMapper bookMapper;
    @Override
    public boolean removeBookByIds(LambdaQueryWrapper<Book> wrapper, boolean delFlag) {
        if(bookMapper.removeBookByIds(wrapper,delFlag) == 0)
            return false;
        return true;
    }
}
