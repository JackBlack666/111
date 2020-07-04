
package com.jy.trim.common.web.response;


import lombok.Data;


/**
 * //
 *
 * @author JinChunZhao
 * @version 1.0
 * @date 2020-06-23 22:40
 */
@Data
public class ResponseCode {


    public static final ResponseCode RC00_1000 = new ResponseCode("00", "1000", "success");

    public static final ResponseCode RC00_1001 = new ResponseCode("00", "1001", "fail");



    public String module;

    public String code;

    public String msg;

    public ResponseCode() {

    }

    public ResponseCode(String module) {
        this.module = module;
    }

    public ResponseCode(String module, String code, String msg) {
        this(module);
        this.code = code;
        this.msg = msg;
    }

    public ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }



}
