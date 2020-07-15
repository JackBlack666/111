package com.jy.trim.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 学生
 *
 * @author JinChunZhao
 * @version 1.0
 * @date 2020-06-23 22:08
 */
@Data
@ApiModel(value = "学生模型")
public class Student {
    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    @ApiModelProperty(value = "学生年龄")
    private Integer studentAge;

    @ApiModelProperty(value = "学生成绩")
    private Score score;
}
