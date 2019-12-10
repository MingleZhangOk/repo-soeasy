package com.mingzhang.table.enums;

public enum TableType {

    BATCH("BATCH","批量表"),
    REALTTIME("REALTTIME","实时表"),
    SIDE("SIDE","维表");

    private String name;
    private String desc;

    private TableType(String name,String desc){
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
