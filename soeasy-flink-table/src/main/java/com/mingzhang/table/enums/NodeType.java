package com.mingzhang.table.enums;

public enum NodeType {

    SOURCE_NODE("01"),SINK_NODE("02"),WINDOWS_NODE("03"),TRANSFER_NODE("04"),SIDE_NODE("05");

    private String nodeType;
    public String getNodeType() {
        return nodeType;
    }
    NodeType(String nodeType){
        this.nodeType = nodeType;
    }


}
