package com.example.exercise.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exercise.domain.dto.BookDto;
import com.example.exercise.domain.dto.UserDto;
import com.example.exercise.domain.pojo.Book;
import com.example.exercise.domain.pojo.TbUser;
import com.example.exercise.domain.vo.Result;
import com.example.exercise.domain.vo.ResultType;
import com.example.exercise.service.BookService;
import com.example.exercise.service.ITbUserService;
import com.example.exercise.utils.BaseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
@Api(tags = "Book管理接口")
public class BookController {
    private final BookService bookService;
    private final ITbUserService userService;

    @ApiOperation("新增Book类接口")
    @PostMapping
    public Object saveBook(@RequestBody BookDto bookDto){
        if(bookDto.getUserId() == null)
            return new Result<>(ResultType.FAIL.getCode(),"userId不合法",null);
        LambdaQueryWrapper<TbUser> userLambdaQueryWrapper = new LambdaQueryWrapper<TbUser>().eq(TbUser::getId,bookDto.getUserId());
        if(!userService.exists(userLambdaQueryWrapper))
            return new Result<>(ResultType.FAIL.getCode(),"用户不存在",null);
        if(bookDto == null || bookDto.getName() == null || bookDto.getName().equals(""))
            return new Result<>(ResultType.FAIL.getCode(), "书名不合法", null);
        Book book = BeanUtil.copyProperties(bookDto, Book.class);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<Book>()
                .eq(Book::getName,book.getName());
        if(bookService.exists(wrapper)){
            return new Result<>(ResultType.FAIL.getCode(),"书名已经存在",null);
        }
        book.setCreateTime(BaseUtil.getCurrentTime());
        book.setDelFlag(false);
        book.setId(null);
        if(bookService.save(book))
            return new Result<>(ResultType.SUCCESS.getCode(),"插入成功",bookDto);
        else
            return new Result<>(ResultType.FAIL.getCode(),"插入失败",bookDto);
    }

    @ApiOperation("返回所有结果")
    @GetMapping("/list")
    public Object getAllBookList(){

        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<Book>().eq(Book::isDelFlag,false);
        List<Book> books = bookService.list(wrapper);
        if(books.isEmpty())
            return new Result<>(ResultType.FAIL.getCode(), "没有更多图书信息",null);
        List<BookDto> bookDtoS = new ArrayList<>();
        books.forEach(book -> {bookDtoS.add(BeanUtil.copyProperties(book,BookDto.class));});
        return new Result<>(ResultType.SUCCESS.getCode(), "查找成功",bookDtoS);
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings("NAB_NEEDLESS_BOOLEAN_CONSTANT_CONVERSION")
    @ApiOperation("根据Id查找Book接口")
    @GetMapping("")
    public Object getBookById(@ApiParam("Book id") @RequestParam("id") Integer id){
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<Book>()
                .eq(Book::isDelFlag, false)
                .eq(Book::getId,id);
        Book book = bookService.getOne(wrapper);
        BookDto bookDto = BeanUtil.copyProperties(book,BookDto.class);
        if(book == null)return new Result<>(ResultType.FAIL.getCode(),"该书不存在或被删除",null);
        return new Result<>(ResultType.SUCCESS.getCode(), "查找成功", bookDto);
    }

    @ApiOperation("根据id删除Book接口")
    @DeleteMapping("id/{id}")
    public Object deleteBookById(@ApiParam("Book id") @PathVariable("id") Integer id){
        if(id == null)return new Result<>(ResultType.FAIL.getCode(),"非法Id",null);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<Book>()
                .eq(Book::isDelFlag, false)
                .eq(Book::getId,id);
        Book book = bookService.getOne(wrapper);
        if(book == null)return new Result<>(ResultType.FAIL.getCode(),"该书不存在或已被删除",null);
        book.setDelFlag(true);
        if(!bookService.updateById(book)){
            return new Result<>(ResultType.FAIL.getCode(),"删除失败!",null);
        }
        return new Result<>(ResultType.SUCCESS.getCode(),"删除成功",null);
    }

    @ApiOperation("模糊查询书名接口")
    @GetMapping("/search")
    public Object getBookByName(@ApiParam("Book`s name") @RequestParam("name") String name){
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<Book>()
                .like(Book::getName,name)
                .eq(Book::isDelFlag, false);
        List<Book> books = bookService.list(wrapper);
        List<BookDto> bookDtoS = new ArrayList<>();
        books.forEach(book -> bookDtoS.add(BeanUtil.copyProperties(book, BookDto.class)));
        if(books.isEmpty())return new Result<>(ResultType.FAIL.getCode(),"未找到类似书籍",null);
        return new Result<>(ResultType.SUCCESS.getCode(), "查找成功", bookDtoS);
    }

    @ApiOperation("修改Book信息接口")
    @PutMapping()
    public Object updateBookMessageById(@RequestBody BookDto bookDto){
        Book book = BeanUtil.copyProperties(bookDto,Book.class);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<Book>()
                .eq(Book::getId,book.getId());
        if(!bookService.exists(wrapper))
            return new Result<>(ResultType.FAIL.getCode(),"该书不存在或已被删除",null);
        if(!bookService.updateById(book)){
            return new Result<>(ResultType.FAIL.getCode(),"更新失败!",null);
        }
        return new Result<>(ResultType.SUCCESS.getCode(),"更新成功",null);
    }

    @ApiOperation("根据Book id更改Is_Delete")
    @PutMapping("id={id}/useful/{useful}")
    public Object updateUsefulById(@ApiParam("Book`s Id") @PathVariable("id") Integer id,
                                   @ApiParam("Book`s Is_Delete") @PathVariable("useful") String useful){
        if(id == null)return new Result<>(ResultType.FAIL.getCode(),"非法Id",null);
        Book book = bookService.getById(id);
        if(book == null)return new Result<>(ResultType.FAIL.getCode(),"该书不存在或已被删除",null);
        boolean flag = useful.equals("true") ? true : false;
        book.setDelFlag(flag);
        if(!bookService.updateById(book)){
            return new Result<>(ResultType.FAIL.getCode(),"更新失败!",null);
        }
        return new Result<>(ResultType.SUCCESS.getCode(),"更新成功",null);
    }

    @ApiOperation("分页查询接口")
    @GetMapping("/page")
    public Object getBookPage(@ApiParam("PageIndex") @RequestParam("index")Integer index,
                              @ApiParam("PageSize") @RequestParam("size") Integer size){
        Page<Book> page = Page.of(index,size);
        page.addOrder(new OrderItem("id",true));
        Page<Book> bookPage = bookService.page(page);
        return new Result<>(ResultType.SUCCESS.getCode(), "查找分页成功", bookPage);
    }
    @ApiOperation("批量删除接口")
    @DeleteMapping("/deleteByIds")
    public Object deleteByIds(@ApiParam("Remove Book`s id") @RequestParam("ids") List<Integer> ids){
        if(ids == null || ids.isEmpty())
            return new Result<>(ResultType.FAIL.getCode(),"非法Id",null);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<Book>()
                .in(Book::getId,ids)
                .eq(Book::isDelFlag, false);
        if(!bookService.removeBookByIds(wrapper,true)){
            return new Result<>(ResultType.FAIL.getCode(), "删除失败!", null);
        }
        return new Result<>(ResultType.SUCCESS.getCode(),"删除成功",null);
    }

    @ApiOperation("根据username获取分页接口")
    @GetMapping("/page/username")
    public Object pageByUsername(@ApiParam("Book username") @RequestParam("username") String username) {
        LambdaQueryWrapper<TbUser> userLambdaQueryWrapper = new LambdaQueryWrapper<TbUser>().eq(TbUser::getName, username);
        TbUser user = userService.getOne(userLambdaQueryWrapper);
        if(user == null)
            return new Result<>(ResultType.FAIL.getCode(), "没有这名用户",null);
        UserDto userDto = BeanUtil.copyProperties(user,UserDto.class);
        List<BookDto> bookDtoPage = bookService.pageByUsername(user.getId(),userDto);
        return new Result<>(ResultType.SUCCESS.getCode(), "查找成功" ,bookDtoPage);
    }

}
