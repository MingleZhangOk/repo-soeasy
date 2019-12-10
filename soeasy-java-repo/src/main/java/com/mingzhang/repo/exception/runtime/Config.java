package com.mingzhang.repo.exception.runtime;

import java.io.Serializable;

public class Config implements Serializable{

    private static final long serialVersionUID = -1378032670730645555L;

    private KafkaConfig kafka;

    private String  systemNo;

    private ElasticConfig elastic;

    private RedisConfig redis;

    private DatabaseConfig databasePool;

    private HbaseConfig hbase;

    private String cacheType;

    private String nosqlType;

    private FlinkConfig flinkConfig;

    private FtpServerConfig ftpServer;

    private String encode;

    private LicenseInfoConfig license;

    private Scheduler scheduler;

    public String getNosqlType() {
        return nosqlType;
    }

    public void setNosqlType(String nosqlType) {
        this.nosqlType = nosqlType;
    }

    public KafkaConfig getKafka() {
        return kafka;
    }
    public void setKafka(KafkaConfig kafka) {
        this.kafka = kafka;
    }

    public ElasticConfig getElastic() {
        return elastic;
    }

    public void setElastic(ElasticConfig elastic) {
        this.elastic = elastic;
    }

    public RedisConfig getRedis() {
        return redis;
    }

    public void setRedis(RedisConfig redis) {
        this.redis = redis;
    }

    public DatabaseConfig getDatabasePool() {
        return databasePool;
    }

    public void setDatabasePool(DatabaseConfig databasePool) {
        this.databasePool = databasePool;
    }

    public HbaseConfig getHbase() {
        return hbase;
    }

    public void setHbase(HbaseConfig hbase) {
        this.hbase = hbase;
    }



    public String getCacheType() {
        return cacheType;
    }

    public void setCacheType(String cacheType) {
        this.cacheType = cacheType;
    }


    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public LicenseInfoConfig getLicense() {
        return license;
    }

    public void setLicense(LicenseInfoConfig license) {
        this.license = license;
    }

    public FlinkConfig getFlinkConfig() {
        return flinkConfig;
    }

    public void setFlinkConfig(FlinkConfig flinkConfig) {
        this.flinkConfig = flinkConfig;
    }

    public FtpServerConfig getFtpServer() {
        return ftpServer;
    }

    public void setFtpServer(FtpServerConfig ftpServer) {
        this.ftpServer = ftpServer;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\ncacheType=").append(this.getCacheType())
                .append(this.getKafka())
                .append(this.getElastic())
                .append(this.getRedis())
                .append(this.getDatabasePool());
        return stringBuilder.toString();
    }
}


