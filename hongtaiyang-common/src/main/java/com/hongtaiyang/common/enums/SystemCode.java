/*
 * Copyright (c) 2019/4/1 3:37:12
 * Created by zhuxj
 */

package com.hongtaiyang.common.enums;


public enum SystemCode {

    SUCCESS("200","成功"),
    RUNTIME_ERROR("System-0001", "系统内部错误"),
    AUTH_ERROR("401","身份认证失败"),
    REQUEST_ERROR("403","重新发起请求"),
    ;

    private String code;
    private String describe;

    SystemCode(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return String.format("Code:[%s], Describe:[%s]", this.code, this.describe);
    }
}
