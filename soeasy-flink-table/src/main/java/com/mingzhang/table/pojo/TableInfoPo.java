package com.mingzhang.table.pojo;

import java.io.Serializable;
import java.util.List;

public class TableInfoPo extends DataSourceInfoPo implements Serializable {

    private static final long serialVersionUID = -7604821366495456626L;
    private String tableId;
    private String tableName;
    private String tableCode;
    private String tableAppType;
    private String tableSrcType;
    private String dataFormat;
    private String esDocType;
    private String tableStatus;
    private String keyVal;
    private String queryType;
    private String udfSql;
    private String rdbTable;
    private String cacheType;
    private Integer loadSize;
    private String loadType;
    private String fileName;
    private String fieldDelimiter;
    private String lineDelimiter;
    private String sourceType;
    private String sourceCode;
    private String dynamicIndexFlag;
    private DataSourceInfoPo dataSourcePo;
    private List<TableFieldPojo> tableFieldPoList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public String getTableAppType() {
        return tableAppType;
    }

    public void setTableAppType(String tableAppType) {
        this.tableAppType = tableAppType;
    }

    public String getTableSrcType() {
        return tableSrcType;
    }

    public void setTableSrcType(String tableSrcType) {
        this.tableSrcType = tableSrcType;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public DataSourceInfoPo getDataSourcePo() {
        return dataSourcePo;
    }

    public void setDataSourcePo(DataSourceInfoPo dataSourcePo) {
        this.dataSourcePo = dataSourcePo;
    }

    public List<TableFieldPojo> getTableFieldPoList() {
        return tableFieldPoList;
    }

    public void setTableFieldPoList(List<TableFieldPojo> tableFieldPoList) {
        this.tableFieldPoList = tableFieldPoList;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }


    public String getEsDocType() {
        return esDocType;
    }

    public void setEsDocType(String esDocType) {
        this.esDocType = esDocType;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    public String getKeyVal() {
        return keyVal;
    }

    public void setKeyVal(String keyVal) {
        this.keyVal = keyVal;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public String getUdfSql() {
        return udfSql;
    }

    public void setUdfSql(String udfSql) {
        this.udfSql = udfSql;
    }

    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }

    public Integer getLoadSize() {
        return loadSize;
    }

    public void setLoadSize(Integer loadSize) {
        this.loadSize = loadSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getLoadType() {
        return loadType;
    }

    public void setLoadType(String loadType) {
        this.loadType = loadType;
    }

    public String getFieldDelimiter() {
        return fieldDelimiter;
    }


    public void setFieldDelimiter(String fieldDelimiter) {
        this.fieldDelimiter = fieldDelimiter;
    }

    public String getRdbTable() {
        return rdbTable;
    }

    public void setRdbTable(String rdbTable) {
        this.rdbTable = rdbTable;
    }

    public String getLineDelimiter() {
        return lineDelimiter;
    }

    public void setLineDelimiter(String lineDelimiter) {
        this.lineDelimiter = lineDelimiter;
    }


    public String getDynamicIndexFlag() {
        return dynamicIndexFlag;
    }

    public void setDynamicIndexFlag(String dynamicIndexFlag) {
        this.dynamicIndexFlag = dynamicIndexFlag;
    }
}
