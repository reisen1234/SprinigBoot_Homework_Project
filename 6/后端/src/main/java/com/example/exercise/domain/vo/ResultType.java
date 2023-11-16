package com.example.exercise.domain.vo;

import io.swagger.annotations.ApiModelProperty;

public enum ResultType {
    @ApiModelProperty("成功")
    SUCCESS("200","操作成功"),
    FAIL("400","操作失败"),
    UNAUTHORIZED("401","权限不足"),
    NOT_FOUND("404","接口不存在"),
    INTERNAL_SERVER_ERROR("500","服务器内部错误"),
    AGAIN_LOGIN("600","请重新登入");
    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
