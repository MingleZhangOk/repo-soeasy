package com.mingzhang.table.enums;

public enum JdbcDriver {

    ORACLE_DIVER("oracle.jdbc.driver.OracleDriver"),
    DB2_DRIVER(""),
    MYSQL_DRIVER("com.mysql.jdbc.Driver");

    private String driver;

     JdbcDriver(String driver){
        this.driver =driver;
    }

    public String getDriver() {
        return driver;
    }
}
