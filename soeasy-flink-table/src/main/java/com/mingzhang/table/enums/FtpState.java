package com.mingzhang.table.enums;



public enum FtpState {

    YES("Y"),NO("N");
    private String code;
     FtpState(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
