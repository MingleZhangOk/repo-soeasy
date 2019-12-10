package com.mingzhang.table.parameter;

import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * 初始化配置
 *
 * @author luoyuan
 */
public class CommonParameter {

    public static String delimiter = null;//分隔符
    public static String tmpPath = null;//中间临时文件路径
    public static String userPrincipal = null;
    public static String userKeytabPath = null;
    public static String krb5ConfPath = null;
    public static String confFilePath = "/app/des/conf/";
    //	public static String confFilePath = "E:\\Project_Development_Space\\HA_ZZ_YuXin_数据交换平台_201904\\开发文档和资料\\晋商银行POC_20190731-2019080\\des_code_20190731\\bdp-etl-new\\conf\\";
    public static String sparkSubmitPath = null;
    public static String numExecutors = null;
    public static String executorMemory = null;
    public static String executorCores = null;
    public static String driverCores = null;
    public static String driverMemory = null;

    public static String url;
    public static String driverClassName;
    public static String username;
    public static String password;

    public static String checkPath;
    public static boolean isCheck = false;

    public static int jdbcBatchSize = 5000; //commonDialect 批量入库size

    public static boolean isTrim = true;//卸数字段是否都做去空格操作，默认true

    public static String ELK_CODE = "GBK"; //ELK字符集

    public static void initProperties() throws Exception {

//        confFilePath = System.getProperty("user.dir") + File.separator + "conf" + File.separator;
        File confFile = new File(confFilePath + "conf.properties");
        System.out.println("System.getProperty('user.dir'):" + System.getProperty("user.dir"));
        System.out.println("File.separator:" + File.separator);
        System.out.println("confFilePath:" + confFilePath);
        if (confFile.exists()) {
            Properties confProps = new Properties();
            confProps.load(new FileInputStream(confFilePath + "conf.properties"));
            delimiter = confProps.getProperty("delimiter");
            tmpPath = confProps.getProperty("tmp_path");
            userPrincipal = confProps.getProperty("userPrincipal");
            userKeytabPath = confFilePath + "user.keytab";
            krb5ConfPath = confFilePath + "krb5.conf";
            sparkSubmitPath = confProps.getProperty("sparkSubmitPath");

            numExecutors = confProps.getProperty("numExecutors");
            executorCores = confProps.getProperty("executorCores");
            executorMemory = confProps.getProperty("executorMemory");
            driverCores = confProps.getProperty("driverCores");
            driverMemory = confProps.getProperty("driverMemory");
            checkPath = confProps.getProperty("checkPath");
            isCheck = Boolean.valueOf(confProps.getProperty("isCheck", "false").trim());
            jdbcBatchSize = Integer.valueOf(confProps.getProperty("jdbcBatchSize", "5000").trim());
            isTrim = Boolean.valueOf(confProps.getProperty("isTrime", "true"));
            ELK_CODE = confProps.getProperty("elk_code", "GBK");
        }

        File dbFile = new File(confFilePath + "db.properties");

        if (dbFile.exists()) {
            Properties dbProps = new Properties();
            dbProps.load(new FileInputStream(confFilePath + "db.properties"));
            url = dbProps.getProperty("url");
            driverClassName = dbProps.getProperty("driverClassName");
            username = dbProps.getProperty("username");
            password = dbProps.getProperty("password");
        }

        File logFile = new File(confFilePath + "log4j.properties");

        if (logFile.exists()) {
            PropertyConfigurator.configure(confFilePath + "log4j.properties");
        }
    }

}
