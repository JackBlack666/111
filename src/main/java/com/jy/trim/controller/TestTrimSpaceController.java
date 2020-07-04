
package com.jy.trim.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.jy.trim.common.annotation.TrimSpace;
import com.jy.trim.common.web.response.ResponseBean;
import com.jy.trim.entity.Teacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试TrimSpace注解的可用性
 *
 * @author JinChunZhao
 * @version 1.0
 * @date 2020-06-22 8:41
 */
@RestController
@RequestMapping(value = "/test/trim")
@Api(tags = "测试TrimSpace注解的可用性")
@Slf4j
public class TestTrimSpaceController {

    @TrimSpace
    @ApiOperation(value = "新增Teacher--POST，json传值", nickname = "新增组织机构POST")
    @ApiOperationSupport(order=1)
    @PostMapping("/add/post")
    public ResponseBean<Teacher> addPost(@RequestBody Teacher teacher) throws Exception{
        log.error("新增Teacher--POST，@RequestBody---》》"+teacher.toString());

        return ResponseBean.success(teacher);
    }

//    @TrimSpace
//    @ApiOperation(value = "新增Teacher--离散传值", notes = "新增组织机构POST")
//    @PostMapping("/add/post/discrete")
//    public ResponseBean<Teacher> addPostDiscrete(Teacher teacher) throws Exception{
//        log.error("新增Teacher--POST，离散传值---》》"+teacher.toString());
////        ResponseBean<OdcDicOrg> responseBean = null;
//
//        return ResponseBean.success();
//    }

//    @TrimSpace
//    @ApiOperation(value = "新增Teacher--离散传值1", notes = "新增组织机构POST1")
//    @PostMapping("/add/post/discrete/param")
//    public ResponseBean<Teacher> addPostDiscrete1(@RequestParam("teacherName") String teacherName,@RequestParam("teacherAge") Integer teacherAge,@RequestParam("isLeader") Boolean isLeader) throws Exception{
//        Teacher teacher = Teacher.builder().teacherAge(teacherAge).teacherName(teacherName).isLeader(isLeader).build();
//
//        log.error("新增Teacher--POST，RequestParam---》》"+teacher.toString());
//
//        return ResponseBean.success(teacher);
//    }



    @TrimSpace
    @ApiOperation(value = "新增Teacher--Get", notes = "新增组织机构Get")
    @ApiOperationSupport(order=2)
    @GetMapping("/add/get")
    public ResponseBean<Teacher> addGet(Teacher teacher) throws Exception{
        log.error("新增Teacher--Get，对象传值---》》"+teacher.toString());
        return ResponseBean.success(teacher);
    }

    @TrimSpace
    @ApiOperation(value = "新增Teacher--Get离散", notes = "新增组织机构Get")
    @ApiOperationSupport(order=3)
    @GetMapping("/add/get/discrete")
    public ResponseBean<Teacher> addGetDiscrete(@RequestParam("teacherName") String teacherName,@RequestParam("teacherAge") Integer teacherAge,@RequestParam("isLeader") Boolean isLeader) throws Exception{
        Teacher teacher = Teacher.builder().teacherAge(teacherAge).teacherName(teacherName).build();
        log.error("新增Teacher--Get，RequestParam---》》"+teacher.toString());
        return ResponseBean.success(teacher);
    }


    @TrimSpace
    @ApiOperation(value = "新增Teacher--Get离散path", notes = "新增组织机构Get")
    @ApiOperationSupport(order=4)
    @GetMapping("/add/get/discrete/path/{teacherName}/{teacherAge}")
    public ResponseBean<Teacher> addGetDiscrete(@PathVariable("teacherName") String teacherName, @PathVariable("teacherAge") Integer teacherAge) throws Exception{
        Teacher teacher = Teacher.builder().teacherAge(teacherAge).teacherName(teacherName).build();

        log.error("新增Teacher--Get，PathVariable---》》"+teacher.toString());
        return ResponseBean.success(teacher);
    }



}
