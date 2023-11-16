package com.example.exercise.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@TableName("tb_user")
@Data
@ApiModel("The entity of User")
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("User id")
    private Integer id;

    @ApiModelProperty("User name")
    private String name;

    @ApiModelProperty("User balance")
    private double balance;

    @ApiModelProperty("The createTime of user data")
    private Timestamp createTime;
}
