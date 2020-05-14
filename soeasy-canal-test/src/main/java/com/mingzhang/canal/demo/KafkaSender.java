package com.mingzhang.canal.demo;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-05-08 9:38
 */
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import java.util.Properties;

/**
 * Kafka生产消息工具类
 */
public class KafkaSender {
    private String topic;

    public KafkaSender(String topic){
        super();
        this.topic = topic;
    }

    /**
     * 发送消息到Kafka指定topic
     *
     * @param topic topic名字
     * @param key 键值
     * @param data 数据
     */
    public static void sendMessage(String topic , String key , String data){
        Producer<String, String> producer = createProducer();
        producer.send(new KeyedMessage<String , String>(topic , key , data));
    }

    /**
     * 创建生产者实例
     * @return
     */
    private static Producer<String , String> createProducer(){
        Properties properties = new Properties();

        properties.put("metadata.broker.list" , GlobalConfigUtil.kafkaBootstrap);
        properties.put("zookeeper.connect" , GlobalConfigUtil.kafkaZookeeper);
        properties.put("serializer.class" , StringEncoder.class.getName());

        return new Producer<String, String>(new ProducerConfig(properties));
    }
}
