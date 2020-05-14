package com.mingzhang.canal.demo;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-05-08 9:37
 */
import java.util.ResourceBundle;

/**
 * 配置文件的公共类
 */
public class GlobalConfigUtil {

    //读取application.properties文件
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");

    public static String canalHost= resourceBundle.getString("canal.host");
    public static String canalPort = resourceBundle.getString("canal.port");
    public static String canalInstance = resourceBundle.getString("canal.instance");
    public static String mysqlUsername = resourceBundle.getString("mysql.username");
    public static String mysqlPassword=  resourceBundle.getString("mysql.password");
    public static String kafkaBootstrap= resourceBundle.getString("kafka.bootstrap.servers");
    public static String kafkaZookeeper= resourceBundle.getString("kafka.zookeeper.connect");
    public static String kafkaInput = resourceBundle.getString("kafka.input.topic");

    public static void main(String[] args) {
        System.out.println(canalHost);
    }
}
