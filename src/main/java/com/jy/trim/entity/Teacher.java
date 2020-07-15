package com.jy.trim.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 老师
 *
 * @author jinchunzhao
 * @version 1.0
 * @date 2020-05-09 13:59
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "老师模型")
public class Teacher {



    @ApiModelProperty(value = "老师姓名")
    private String teacherName;


    @ApiModelProperty(value = "老师年龄")
    private Integer teacherAge;


    @ApiModelProperty(value = "是否是班主任")
    private Boolean isLeader ;


    @ApiModelProperty(value = "学生")
    private Student student;
    @ApiModelProperty(value = "测试老师姓名集合")
    private List<String> names;
    @ApiModelProperty(value = "测试老师年龄数组")
    private String[] ages;

    @ApiModelProperty(value = "测试学生list集合")
    private List<Student> students;

    @ApiModelProperty(value = "测试学生数组")
    private Student[] studentArry;

    @ApiModelProperty(value = "测试学生set集合")
    private Set<Student> studentSets;

    @ApiModelProperty(value = "测试学生map集合")
    private Map<String,Object> map;

    @ApiModelProperty(value = "测试学生map集合")
    private Map<String, Student> mapStu;



}
