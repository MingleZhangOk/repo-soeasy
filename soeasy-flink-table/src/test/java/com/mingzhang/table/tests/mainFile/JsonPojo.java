package com.mingzhang.table.tests.mainFile;

public class JsonPojo{
    private String sourceTable;
    private String srouceParam;
    private String sinkTable;
    private String sinkParam;
    private String sqlExecute;

    public JsonPojo() {
    }

    public JsonPojo(String sourceTable, String srouceParam, String sinkTable, String sinkParam, String sqlExecute) {
        this.sourceTable = sourceTable;
        this.srouceParam = srouceParam;
        this.sinkTable = sinkTable;
        this.sinkParam = sinkParam;
        this.sqlExecute = sqlExecute;
    }

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }

    public String getSrouceParam() {
        return srouceParam;
    }

    public void setSrouceParam(String srouceParam) {
        this.srouceParam = srouceParam;
    }

    public String getSinkTable() {
        return sinkTable;
    }

    public void setSinkTable(String sinkTable) {
        this.sinkTable = sinkTable;
    }

    public String getSinkParam() {
        return sinkParam;
    }

    public void setSinkParam(String sinkParam) {
        this.sinkParam = sinkParam;
    }

    public String getSqlExecute() {
        return sqlExecute;
    }

    public void setSqlExecute(String sqlExecute) {
        this.sqlExecute = sqlExecute;
    }

    @Override
    public String toString() {
        return "JsonPojo{" +
                "sourceTable='" + sourceTable + '\'' +
                ", srouceParam='" + srouceParam + '\'' +
                ", sinkTable='" + sinkTable + '\'' +
                ", sinkParam='" + sinkParam + '\'' +
                ", sqlExecute='" + sqlExecute + '\'' +
                '}';
    }

}
