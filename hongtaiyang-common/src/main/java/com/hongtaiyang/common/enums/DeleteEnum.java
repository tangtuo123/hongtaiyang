package com.hongtaiyang.common.enums;


public enum DeleteEnum {

    NOT_DELETE(0, "未删除"),
    HAS_DELETE(1, "已删除"),
    ;

    private Integer code;
    private String desc;

    DeleteEnum(int code, String desc) {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
