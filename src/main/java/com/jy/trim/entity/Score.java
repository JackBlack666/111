package com.jy.trim.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 成绩
 *
 * @author JinChunZhao
 * @version 1.0
 * @date 2020-07-02 23:02
 */
@Data
public class Score {
    @ApiModelProperty(value = "分数")
    private String score;

    @ApiModelProperty(value = "分数数组")
    private String[] scoreArry;

}
