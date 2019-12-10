package com.mingzhang.repo.exception.runtime;

import java.io.Serializable;

public class FtpServerConfig implements Serializable {

    private static final long serialVersionUID = 4679677673922013469L;
    private String ftpUrl;
    private int port;
    private String system;
    private String userName;
    private String password;
    private int timeOut;
    private String fileFinishFlg;
    private String encode;
    private Integer sleepMinites;
    private String startTime; //开始时间
    private String endTime;  //结束时间

    public String getFtpUrl() {
        return ftpUrl;
    }

    public void setFtpUrl(String ftpUrl) {
        this.ftpUrl = ftpUrl;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getFileFinishFlg() {
        return fileFinishFlg;
    }

    public void setFileFinishFlg(String fileFinishFlg) {
        this.fileFinishFlg = fileFinishFlg;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSleepMinites() {
        return sleepMinites;
    }

    public void setSleepMinites(Integer sleepMinites) {
        this.sleepMinites = sleepMinites;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }
}
