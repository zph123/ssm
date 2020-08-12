package com.zph.exception;

public class MyLoginException extends Exception{
    public MyLoginException() {
    }

    public MyLoginException(String message) {
        super(message);
    }
}
