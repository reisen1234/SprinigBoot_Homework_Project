package com.example.exercise.service.serviceIml;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.exercise.domain.dto.BookDto;
import com.example.exercise.domain.dto.UserDto;
import com.example.exercise.domain.pojo.Book;
import com.example.exercise.mapper.BookMapper;
import com.example.exercise.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Page<BookDto> pageByUsername(Integer pageNum, Integer pageSize, Integer userId, UserDto userDto) {
        Page<Book> bookPage = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<Book>().eq(Book::getUserId,userId);
        bookMapper.selectPage(bookPage,wrapper);
        List<Book> books = bookPage.getRecords();
        List<BookDto> bookDtoS = new ArrayList<>();
        books.forEach(book -> {
            BookDto bookDto = BeanUtil.copyProperties(book,BookDto.class);
            bookDto.setUserDto(userDto);
            bookDtoS.add(bookDto);
        });
        Page<BookDto> bookDtoPage = new Page<>(bookPage.getCurrent(),bookPage.getSize(),bookPage.getTotal());
        bookDtoPage = bookDtoPage.setRecords(bookDtoS);
        return bookDtoPage;
    }
}
