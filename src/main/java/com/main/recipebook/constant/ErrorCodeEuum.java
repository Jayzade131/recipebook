package com.main.recipebook.constant;

import lombok.Getter;

public enum ErrorCodeEuum {
    GENERIC_EXCEPTION("20001","Something Went Wrong Please Try Later"),
    USER_EXIST_ALREADY("20002","User Already Exist With This Username");


    @Getter
    private String errorCode;

    @Getter
    private String errorMessage;

    ErrorCodeEuum(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
