package com.mingzhang.java.spark.firstdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class FileClientStarter {

    private static final Logger LOG = LoggerFactory.getLogger(FileClientStarter.class);

    public static String delimiter = null;

    public static void main(String[] args) {
        try {
            propInit();
        } catch (Exception e) {
            LOG.error("配置文件加载错误", e);
        }

        ClientStart();
    }

    private static void propInit() throws Exception {

        Properties properties = new Properties();
        InputStream resourceAsStream = FileClientStarter.class.getClassLoader().getResourceAsStream("conf.properties");
        properties.load(resourceAsStream);
        delimiter = properties.getProperty("delimiter");
    }

    private static void ClientStart() {
        try {
            LOG.info("启动客户端程序.");
            new FileClientListener().start();
        } catch (Exception e) {
            LOG.error("服务启动失败：", e);
        }
    }
}
