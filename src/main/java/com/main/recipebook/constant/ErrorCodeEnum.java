package com.main.recipebook.constant;

import lombok.Getter;

public enum ErrorCodeEnum {
    GENERIC_EXCEPTION("20001","Something Went Wrong Please Try Later"),
    USER_EXIST_ALREADY("20002","User Already Exist With This Username"),
    USER_NOT_FOUND("20003","Invalid Username OR Password"),
    USERNAME_INVALID("20004","User Not Found With This Username :: {0}"),
    VALIDATION_EXCEPTION("20005"),
    OLD_PASSWORD_INVALID("20006","Old Password Invalid"),
    EMAIL_NOT_FOUND("20007","User  Not Found with this Email"),
    OTP_INVALID("20008","OTP Invalid"),
    USER_NOT_EXIST("20009","User Not Exist In Our Database"),
    PASSWORD_SAME("20010","current password and new password cannot be same"),;


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
