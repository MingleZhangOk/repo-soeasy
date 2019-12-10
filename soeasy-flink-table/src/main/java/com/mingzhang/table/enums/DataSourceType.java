package com.mingzhang.table.enums;

public enum DataSourceType {

    FILE("FILE","纯文本文件","包括本地文件与网络文件"),
    RDB("RDB","关系数据库","包括所有的关系数据库(ORACLE,DB2,MYSQL...)"),
    NRDB("NRDB","非关系型数据库","包括所有的非关系型数据库（redis,elastic,MONGDB...）"),
    MQ("MQ","消息中间件","类似于kafka,rabitMQ....");

    ;



    private String sourceCode;
    private String sourceName;
    private String sourceDesc;

    private DataSourceType(String sourceCode,String sourceName,String sourceDesc){
        this.sourceCode = sourceCode;
        this.sourceName = sourceName;
        this.sourceDesc = sourceDesc;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getSourceDesc() {
        return sourceDesc;
    }
}
