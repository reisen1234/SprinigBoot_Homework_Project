package com.example.exercise.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("The dto user")
public class UserDto {
    @ApiModelProperty("User name")
    private String name;
    @ApiModelProperty("User balance")
    private double balance;
}
