package com.main.recipebook.constant;

import lombok.Getter;

public enum ErrorCodeEnum {
    GENERIC_EXCEPTION("20001","Something Went Wrong Please Try Later"),
    USER_EXIST_ALREADY("20002","User Already Exist With This Username"),
    USER_NOT_FOUND("20003","Invalid Username OR Password"),
    VALIDATION_EXCEPTION("20004");


    @Getter
    private String errorCode;

    @Getter
    private String errorMessage;

    ErrorCodeEnum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    ErrorCodeEnum(String errorCode) {
        this.errorCode = errorCode;
    }
}
