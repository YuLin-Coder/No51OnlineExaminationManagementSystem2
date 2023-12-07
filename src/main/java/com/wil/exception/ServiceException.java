package com.wil.exception;

/**
 * Created by wil on 2018/5/5.
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable th) {
        super(th);
    }

    public ServiceException(String message, Throwable th) {
        super(message, th);
    }

}
