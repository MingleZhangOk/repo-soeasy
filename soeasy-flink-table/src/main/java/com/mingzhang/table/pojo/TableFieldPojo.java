package com.mingzhang.table.pojo;

public class TableFieldPojo {
    private static final long serialVersionUID = -3061098279134113303L;
    /*fieldCode info*/
    private String fieldId;
    private String fieldCode;
    private String fieldName;
    private String tableCode;
    private String mappingFieldCode;
    private String fieldType;
    private String esTabName;
    private Integer fieldOrder;

    private String isKey;
    private String isUpdate;

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public String getMappingFieldCode() {
        return mappingFieldCode;
    }

    public void setMappingFieldCode(String mappingFieldCode) {
        this.mappingFieldCode = mappingFieldCode;
    }

    public String getEsTabName() {
        return esTabName;
    }

    public void setEsTabName(String esTabName) {
        this.esTabName = esTabName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getFieldOrder() {
        return fieldOrder;
    }

    public void setFieldOrder(Integer fieldOrder) {
        this.fieldOrder = fieldOrder;
    }


    public String getIsKey() {
        return isKey;
    }

    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }

    public TableFieldPojo() {
        super();
    }

    @Override
    public String toString() {
        return "{\"fieldCode\":\"" + fieldCode + "\",\"tableCode\":\""
                + tableCode + "\",\"fieldName\":\"" + fieldName
                + "\",\"paramType\":\"" + fieldType + "\",\"esTabName\":\""
                + esTabName + "\"}";
    }

    public TableFieldPojo(String paramCode, String transCode, String fieldName,
                          String paramType, String tabName) {
        super();
        this.fieldCode = paramCode;
        this.tableCode = transCode;
        this.fieldName = fieldName;
        this.fieldType = paramType;
        this.esTabName = tabName;
    }
}
