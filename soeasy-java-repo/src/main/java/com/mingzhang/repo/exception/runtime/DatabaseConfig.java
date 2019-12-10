package com.mingzhang.repo.exception.runtime;

import java.io.Serializable;

public class DatabaseConfig  implements   Serializable{

    private static final long serialVersionUID = -3209869288418017872L;
    private String databaseName;
    private String dbType;
    private String dataSourceClassName;
    private String driverClassName;
    private String  jdbcUrl;
    private String username;
    private String password;
    private Integer minimumIdle;
    private Integer maximumPoolSize;
    private Long connectionTimeout;
    private String connectionTestQuery;

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Integer getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(Integer maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }


    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public Long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getMinimumIdle() {
        return minimumIdle;
    }

    public void setMinimumIdle(Integer minimumIdle) {
        this.minimumIdle = minimumIdle;
    }

    public String getConnectionTestQuery() {
        return connectionTestQuery;
    }

    public void setConnectionTestQuery(String connectionTestQuery) {
        this.connectionTestQuery = connectionTestQuery;
    }

    public String getDataSourceClassName() {
        return dataSourceClassName;
    }

    public void setDataSourceClassName(String dataSourceClassName) {
        this.dataSourceClassName = dataSourceClassName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n---------------databasePool config begin----------------");
        stringBuilder.append("\ndatabasePool.databaseName=").append(this.getDatabaseName());
        stringBuilder.append("\ndatabasePool.dataSourceClassName=").append(this.getDataSourceClassName());
        stringBuilder.append("\ndatabasePool.driverClass=").append(this.getDriverClassName())
                .append("\ndatabasePool.jdbcUrl=").append(this.getJdbcUrl())
                .append("\ndatabasePool.username=").append(this.getUsername())
                .append("\ndatebasePool.password=").append(this.getPassword())
                .append("\ndatebasePool.connectionTimeout=").append(this.getConnectionTimeout())
                .append("\ndatebasePool.minimumIdle=").append(this.getMinimumIdle())
                .append("\ndatabasePool.maximumPoolSize=").append(this.getMaximumPoolSize())
                .append("\ndatabasePool.connectionTestQuery=").append(this.getConnectionTestQuery())
                .append("\n---------------databasepool config eng-------------------");

        return stringBuilder.toString();
    }
}
