/*
 * Copyright (c) 2019/4/1 3:37:12
 * Created by zhuxj
 */

package com.hongtaiyang.common.enums;


public enum SystemCode {

    SUCCESS("200","成功"),
    RUNTIME_ERROR("System-0001", "系统内部错误"),
    PARAMETER_ERROR("System-0002", "参数错误"),
    REFLEX_ERROR("System-0003", "反射异常"),
    CELLPHONENUM_ERROR("System-0004", "手机号格式不正确"),
    ID_CARD_ERROR("System-0034", "身份证号格式不正确"),
    FIXEDPHONENUM_ERROR("System-0005", "座机号格式不正确"),
    EMAIL_ERROR("System-0006", "邮箱账号格式不正确"),
    REGISTRY_ERROR("System-0007", "您已注册,请勿重复注册"),
    USER_ERROR("System-0008", "找不到此用户的个人信息"),
    NOT_EXISTS_ERROR("System-0009", "找不到此对象"),
    SERVICE_REQUEST_ERROR("System-0010", "接口服务调用异常"),
    SIGN_ERROR("System-0011", "签名参数异常"),
    AUTH_ERROR("System-0012", "权限不足"),
    ROLENAME_ERROR("System-0013", "此角色名已存在"),
    PASSWORD_ERROR("System-0014", "密码错误"),
    REGISTER_ERROR("System-0015", "您还没有注册,请先注册"),
    TOKEN_INVALID_ERROR("System-0016", "token已失效"),
    LOGIN_ERROR("System-0017", "您还未登录,请先登录"),
    TOKEN_ERROR("System-0018", "您的账号已在别处登录"),
    LOGIN_AGAIN_ERROR("System-0019", "请重新登录"),
    REQUEST_ERROR("System-0020", "请重新发起请求"),
    WRITE_MODE_ERROR("System-0021", "写入方式错误"),
    UNSURPPORT("System-0021", "尚不支持的功能"),
    SYSTEM_0022("System-0022","接口调用失败"),
    ACCOUNT_IS_ERROR("System-0023","账号密码错误！"),
    AUTH_UNACCESS("401", "授权未通过！"),
    OLD_PASSWORD_IS_ERROR("System-0024", "旧密码错误！"),
    IMPORT_ERROR("System-0025", "Excel导入到数据库失败"),
    ENTERPRISE_IS_ERROR("System-0026", "企业信息错误,无法注册！"),
    UPLOAD_FILE_ERROR("System-0027", "没有找到文件"),
    ACCOUNT_ALREADY_EXISTS_ERROR("System-0028", "账号已存在,无法注册!"),
    ADD_USER_ERROR("System-0029", "添加职工失败！"),
    MODIFY_USER_ERROR("System-0030", "修改职工失败！"),
    DELETE_USER_ERROR("System-0031", "删除职工失败！"),
    SOCIALCREDITCODE_IS_EXIST("System-0032", "企业社会信用代码已存在！")
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
