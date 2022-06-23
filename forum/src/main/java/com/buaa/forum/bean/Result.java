package com.buaa.forum.bean;

public class Result<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMassage(String massage) {
        this.message = massage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(int code, String massage, T data) {
        this.code = code;
        this.message = massage;
        this.data = data;
    }

    public Result(int code, String massage) {
        this.code = code;
        this.message = massage;
    }
}
