/*
 * Copyright (c) 2019/3/27 2:38:33
 * Created by zhuxj
 */

package com.hongtaiyang.common.entity;

import com.alibaba.fastjson.JSON;
import com.hongtaiyang.common.enums.SystemCode;
import com.hongtaiyang.common.exception.SysException;
import lombok.Data;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @author zhuxj
 * @description 统一的接口返回信息体包括 【返回码】、【提示信息】、【数据】
 */
@Data
public class HttpResponse {

    private String code;
    private String message;
    private Object data;


    private HttpResponse(SystemCode sysCode, Object data) {
        this.code = sysCode.getCode();
        this.message = sysCode.getDescribe();
        this.data = data;
    }

    private HttpResponse(SysException e, Object data) {
        this.code = e.getErrorCode().getCode();
        this.message = e.getMessage();
        if (data != null) {
            this.data = data;
        } else {
            this.data = e.getMessage().replace(e.getErrorCode().getCode() + "-" + e.getErrorCode().getDescribe() + "-", "");
        }
    }



    public static HttpResponse success(Object data) {
        return new HttpResponse(SystemCode.SUCCESS, data);
    }

    public static HttpResponse info(SystemCode code, Object data) {
        return new HttpResponse(code, data);
    }

    public static HttpResponse error(SysException e) {
        return new HttpResponse(e, e.getData());
    }

    public static HttpResponse error(Exception e) {
        return new HttpResponse(SystemCode.RUNTIME_ERROR, e.getMessage());
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public static HttpResponse error(SysException e, Object data) {
        return new HttpResponse(e, data);
    }

    public static HttpResponse error(SystemCode e, Object data) {
        return new HttpResponse(e, data);
    }
}
