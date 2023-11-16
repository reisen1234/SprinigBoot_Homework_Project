package com.example.exercise.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author reisen
 * @since 2023-09-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("table_test")
@ApiModel(value="TableTest对象", description="")
public class TableTest implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "column_1", type = IdType.AUTO)
    private Integer column1;

    @TableField("column_3")
    private String column3;

    @TableField("column_2")
    private String column2;

    @TableField("column_4")
    private Double column4;

    @TableField("column_5")
    private LocalDateTime column5;

    @TableField("useful")
    private Boolean useful;


}
