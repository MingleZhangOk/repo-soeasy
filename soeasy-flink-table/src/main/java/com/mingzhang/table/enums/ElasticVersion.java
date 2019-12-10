package com.mingzhang.table.enums;

public enum ElasticVersion {
   SIX("642"),FIVE("5");
   private String code;

    public String getCode() {
        return code;
    }

    private ElasticVersion(String code ){
       this.code = code;
   }

}
