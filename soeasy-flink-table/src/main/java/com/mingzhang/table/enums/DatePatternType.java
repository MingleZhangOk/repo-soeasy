package com.mingzhang.table.enums;

public enum DatePatternType {

    YEAR("${yyyy}"), MONTH("${mm}"),DATE("${dd}"),HOUR("${hh}"),MINITES("${mi}");


    private String patternCode;

      DatePatternType(String patternCode){
        this.patternCode = patternCode;
    }

    public String getPatternCode() {
        return patternCode;
    }


}
