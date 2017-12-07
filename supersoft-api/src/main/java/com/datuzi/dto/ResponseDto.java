package com.datuzi.dto;

import java.io.Serializable;


public class ResponseDto<T> implements Serializable {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;

    public ResponseDto(){
    }

    public ResponseDto(Boolean success,Integer code,String message,T data){
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
