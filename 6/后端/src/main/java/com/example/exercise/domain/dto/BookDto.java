package com.example.exercise.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(description = "The dto Book")
public class BookDto {
    @ApiModelProperty("Book`s id")
    private Integer id;
    @ApiModelProperty("Book`s name")
    private String name;
    @ApiModelProperty("Book`s price")
    private double price;
    @ApiModelProperty("Create time of Book")
    private Timestamp createTime;
    @ApiModelProperty("User data")
    private UserDto userDto;
    @ApiModelProperty("User id")
    private Integer userId;
}
