package com.demo.springbootdemo1.shared.error;

public class GenericException extends RuntimeException {

    public GenericException(ErrorCode errorCode) {
        super(errorCode.message());
    }

}
