
package com.jy.trim.common.web.response;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * //
 *
 * @author JinChunZhao
 * @version 1.0
 * @date 2020-06-23 22:40
 */
@Data
public class ResponseBean<T>{

    @ApiModelProperty(value = "编码：1000, 1001，5001")
    private String code;

    @ApiModelProperty(value = "消息")
    private String msg;

    @ApiModelProperty(value = "模块")
    private String module;

    @ApiModelProperty(value = "时间戳")
    private Date timestamp;

    @ApiModelProperty(value = "数据")
    private T data;

    public ResponseBean() {
        this.timestamp = new Date();
    }

    public ResponseBean(String module, String code, String msg) {
        this.timestamp = new Date();
        this.code = code;
        this.msg = msg;
        this.module = module;
    }

    public ResponseBean(String code, String msg) {
        this.timestamp = new Date();
        this.code = code;
        this.msg = msg;
    }

    public ResponseBean(ResponseCode responseCode,String msg, T data) {
        this.code = responseCode.getCode();
        this.msg = msg;
        this.module = responseCode.getModule();
        this.timestamp = new Date();
        this.data = data;
    }

    public ResponseBean(ResponseCode responseCode,String msg) {
        this.code = responseCode.getCode();
        this.msg = msg;
        this.module = responseCode.getModule();
        this.timestamp = new Date();
        this.data = null;
    }

    public ResponseBean(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.module = responseCode.getModule();
        this.timestamp = new Date();
        this.data = data;
    }

    public static <T> ResponseBean<T> success(T data) {
        return  new ResponseBean(ResponseCode.RC00_1000, data);
    }

    public static <T> ResponseBean<T> success(ResponseCode responseCode,String msg, T data) {
        return  new ResponseBean(responseCode,msg, data);
    }

    public static <T> ResponseBean<T> success() {
        return new ResponseBean(ResponseCode.RC00_1000);
    }
    public ResponseBean(ResponseCode responseCode) {
        this(responseCode.getModule(), responseCode.getCode(), responseCode.getMsg());
    }

    public static <T> ResponseBean<T> failed() {
        return new ResponseBean(ResponseCode.RC00_1001, null);
    }

    public static <T> ResponseBean<T> failed(ResponseCode responseCode,String msg) {
        return new ResponseBean(responseCode, msg);
    }

    public static <T> ResponseBean<T> failed(ResponseCode responseCode,String msg, T data) {
        return new ResponseBean(responseCode, msg,data);
    }
}
