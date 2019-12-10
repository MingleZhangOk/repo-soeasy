package com.mingzhang.repo.exception.runtime;

import java.io.Serializable;
import java.util.List;

/**
 * elastic配置文件信息
 */
public class ElasticConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> serverList;
    private int port;
    private String clusterName;
    private Boolean transportSniff;
    private String index;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<String> getServerList() {
        return serverList;
    }

    public void setServerList(List<String> serverList) {
        this.serverList = serverList;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public Boolean getTransportSniff() {
        return transportSniff;
    }

    public void setTransportSniff(Boolean transportSniff) {
        this.transportSniff = transportSniff;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {

        return "\n--------------elastic config begin--------------------" +
                "\nelastic.serverList=" + this.getServerList()  +
                "\nelastic.port=" + this.getPort() +
                "\nelastic.clusterName=" + this.getClusterName() +
                "\nelastic.transportSniff=" + this.getTransportSniff() +
                "\nelastic.index=" + this.getIndex() +
                "\n----------elastic config end---------------------------";
    }
}
