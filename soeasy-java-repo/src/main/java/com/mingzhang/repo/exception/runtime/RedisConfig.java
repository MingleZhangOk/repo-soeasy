package com.mingzhang.repo.exception.runtime;

import java.io.Serializable;
import java.util.List;

public class RedisConfig implements Serializable {

    private static final long serialVersionUID = -2223040245910509479L;
    private String type;
    private List<String> serverNodeList;
    private Integer port;
    private String password;
    private Integer timeOut;
    private Integer maxTotal;
    private Integer maxIdle;
    private Integer minIdle;
    private Integer maxWaitMillis;
    private Integer maxAttempts;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getServerNodeList() {
        return serverNodeList;
    }

    public void setServerNodeList(List<String> serverNodeList) {
        this.serverNodeList = serverNodeList;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Integer getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Integer getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Integer maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append("\n--------------redis config begin-----------------------")
                 .append("\nredis.type=").append(this.type)
                 .append("\nredis.servers=")
                 .append(serverNodeList.toString())
                 .append("\nredis.port=")
                 .append(this.port)
                 .append("\nredis.password=")
                 .append(this.password)
                 .append("\nredis.timeout=").append(this.timeOut)
                 .append("\nredis.maxTotal=").append(this.maxTotal)
                 .append("\nredis.maxIdle=").append(this.maxIdle)
                 .append("\nredis.minIdle=").append(this.minIdle)
                 .append("\nredis.maxWaitMillis=").append(this.maxWaitMillis)
                 .append("\nredis.maxAttempts=").append(this.maxAttempts)
                .append("\n-----------------redis config end-----------------------");
        return stringBuilder.toString();
    }
}
