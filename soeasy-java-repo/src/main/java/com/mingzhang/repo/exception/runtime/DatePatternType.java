package com.mingzhang.repo.exception.runtime;

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
