package com.mingzhang.repo.exception.runtime;


public class CompCommonException extends RuntimeException {

    private static final long serialVersionUID = -1010658412328256483L;

    private String errorCode;

    private Object[] params;

    public CompCommonException(Exception e){
        super(e);
    }

    public CompCommonException(String errorCode){
        super(MessageParser.getMessage(errorCode));
        this.errorCode = errorCode;
    }

    public CompCommonException(String errorCode, Object... obejects){
        super(MessageParser.getMessage(errorCode,obejects));
        this.errorCode = errorCode;
        this.params = obejects;

    }

    public CompCommonException(String errorCode, Throwable throwable, Object... obejects){
        super(MessageParser.getMessage(errorCode,obejects),throwable);
        this.errorCode = errorCode;
        this.params = obejects;

    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object[] getParams() {
        return params;
    }

}
