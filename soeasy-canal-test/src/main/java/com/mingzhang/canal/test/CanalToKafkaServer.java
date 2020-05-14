//package com.mingzhang.canal.test;
//
///**
// * File Description:
// *
// * @author MingZhang                      --Variety is the spice of life.
// * @date 2020-05-08 10:00
// */
//public class CanalToKafkaServer {
//    public static void execute() {
//        SimpleCanalClient simpleCanalClient = new SimpleCanalClient(GetProperties.getValue("MYSQL_HOST"),
//                GetProperties.getValue("MTSQL_PORT"), GetProperties.getValue("INSTANCE"));
//        try {
//            simpleCanalClient.execute(1,CanalKafkaProducer.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
