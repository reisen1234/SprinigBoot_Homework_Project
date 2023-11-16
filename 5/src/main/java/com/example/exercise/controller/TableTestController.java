package com.example.exercise.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exercise.domain.pojo.TableTest;
import com.example.exercise.service.ITableTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author reisen
 * @since 2023-09-25
 */
@RestController
@RequestMapping("/table-test")
@RequiredArgsConstructor
@Api(tags = "Test")
public class TableTestController {
    private final ITableTestService iTableTestService;
    @ApiOperation("返回page接口")
    @GetMapping("/page")
    public Object page(@ApiParam("PageIndex") @RequestParam("index")Integer index,
                       @ApiParam("PageSize") @RequestParam("size") Integer size){
        return iTableTestService.page(index,size);
    }
    @ApiOperation("保存接口")
    @PostMapping("/save")
    public Object save(@RequestBody TableTest tableTest){
        return iTableTestService.save(tableTest);
    }
    @ApiOperation("返回list接口")
    @GetMapping("/list")
    public Object list(@ApiParam("list name") @RequestParam("list")String list){
        return iTableTestService.list(list);
    }
    @ApiOperation("返回page接口")
    @PutMapping("/update")
    public Object update(@RequestBody TableTest tableTest){
        return iTableTestService.update_(tableTest);
    }
    @ApiOperation("返回page接口")
    @DeleteMapping("deleteByIds")
    public Object deleteByIds(@ApiParam("ids") @RequestParam("ids") List<Integer> ids){
        return iTableTestService.removeBatchByIds(ids);
    }
    @ApiOperation("返回page接口")
    @PutMapping("updateUseful")
    public Object updateUsefulByIds(@ApiParam("ids") @RequestParam("ids") List<Integer> ids,@RequestParam("use")boolean useful){
        List<TableTest> lists = iTableTestService.listByIds(ids);
        lists.forEach(list->{list.setUseful(useful);});
        return iTableTestService.updateBatchById(lists);
    }
    @ApiOperation("返回所有结果")
    @GetMapping("/all")
    public Object getAllData(){
        return iTableTestService.list();
    }
}
