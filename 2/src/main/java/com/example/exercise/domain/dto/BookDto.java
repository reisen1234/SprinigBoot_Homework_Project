package com.example.exercise.domain.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Time;
@Data
@ApiModel(description = "The dto Book")
public class BookDto {
    @ApiModelProperty("Book`s name")
    private String name;
    @ApiModelProperty("Book`s price")
    private double price;
}
