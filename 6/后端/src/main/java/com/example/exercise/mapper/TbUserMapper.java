package com.example.exercise.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.exercise.domain.pojo.TbUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author reisen
 * @since 2023-10-22
 */
@Mapper
public interface TbUserMapper extends BaseMapper<TbUser> {
    int removeBookByIds(@Param("ew") LambdaQueryWrapper<TbUser> wrapper, @Param("delFlag") boolean delFlag);

}
