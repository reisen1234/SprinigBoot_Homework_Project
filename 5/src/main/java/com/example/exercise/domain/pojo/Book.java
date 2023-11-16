package com.example.exercise.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.sql.Timestamp;

@TableName("tb_book")
@Data
@ApiModel(description = "The entity of Book")
public class Book {
    @ApiModelProperty("Book`s id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty("User id")
    private Integer userId;
    @ApiModelProperty("Book`s name")
    private String name;
    @ApiModelProperty("Is delete")
    private boolean delFlag;
    @ApiModelProperty("Create time of Book")
    private Timestamp createTime;
    @ApiModelProperty("Book`s price")
    private double price;
}
