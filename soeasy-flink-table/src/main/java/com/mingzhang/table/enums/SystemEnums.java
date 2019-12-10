package com.mingzhang.table.enums;

public enum SystemEnums {

    WINDOWS("WINDOWS"),UNIX("UNIX");

     private String systemName;

    private SystemEnums(String systemName){
      this.systemName = systemName;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}
