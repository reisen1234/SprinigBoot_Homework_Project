package com.example.exercise.service.serviceImpl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.exercise.domain.pojo.TableTest;
import com.example.exercise.mapper.TableTestMapper;
import com.example.exercise.service.ITableTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author reisen
 * @since 2023-09-25
 */
@Service
@RequiredArgsConstructor
public class TableTestServiceImpl extends ServiceImpl<TableTestMapper, TableTest> implements ITableTestService {
    private TableTestMapper tableTestMapper;
    @Override
    public Page<TableTest> page(Integer index, Integer pageSize) {
        Page<TableTest> page = new Page<>(index,pageSize);
        return lambdaQuery()
                .page(page);
    }

    @Override
    public List<TableTest> list(String column2) {
        return lambdaQuery()
                .like(column2 != null, TableTest::getColumn2, column2)
                .list();
    }

    @Override
    public int save_(TableTest tableTest) {
        return tableTestMapper.insert(tableTest);
    }

    @Override
    public boolean update_(TableTest tableTest) {
        System.out.println(tableTest);
        return lambdaUpdate()
                .eq(tableTest.getColumn1() != null,TableTest::getColumn1,tableTest.getColumn1())
                .update(tableTest);
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        return tableTestMapper.deleteBatchIds(ids);
    }

}
