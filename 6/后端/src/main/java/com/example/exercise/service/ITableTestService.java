package com.example.exercise.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exercise.domain.pojo.TableTest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author reisen
 * @since 2023-09-25
 */
public interface ITableTestService extends IService<TableTest> {
    Page<TableTest> page(Integer index, Integer pageSize);
    List<TableTest> list(String column2);
    int save_(TableTest tableTest);
    boolean update_(TableTest tableTest);
    int deleteByIds(List<Integer> ids);


}
