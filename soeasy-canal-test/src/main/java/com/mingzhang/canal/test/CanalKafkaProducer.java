//package com.mingzhang.canal.test;
//
///**
// * File Description:
// *
// * @author MingZhang                      --Variety is the spice of life.
// * @date 2020-05-08 9:58
// */
//
//import java.io.IOException;
//import java.util.Properties;
//
//import org.apache.kafka.clients.producer.KafkaProducer;
//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.alibaba.otter.canal.protocol.Message;
//
///**
// * @Title: CanalKafkaProducer.java
// * @Package com.unigroup.kafka.producer
// * @Description:
// * @author 桃花惜春风
// * @date 2018年9月3日 上午11:53:35
// * @version V1.0
// */
//public class CanalKafkaProducer {
//
//    private static final Logger logger = LoggerFactory.getLogger(CanalKafkaProducer.class);
//
//    private Producer<String, Message> producer;
//
//    public void init(KafkaProperties kafkaProperties) {
//        Properties properties = new Properties();
//        properties.put("bootstrap.servers", kafkaProperties.getServers());
//        properties.put("acks", "all");
//        properties.put("retries", kafkaProperties.getRetries());
//        properties.put("batch.size", kafkaProperties.getBatchSize());
//        properties.put("linger.ms", kafkaProperties.getLingerMs());
//        properties.put("buffer.memory", kafkaProperties.getBufferMemory());
//        properties.put("key.serializer", StringSerializer.class.getName());
//        properties.put("value.serializer", MessageSerializer.class.getName());
//        producer = new KafkaProducer<String, Message>(properties);
//    }
//
//    public void stop() {
//        try {
//            logger.info("## stop the kafka producer");
//            producer.close();
//        } catch (Throwable e) {
//            logger.warn("##something goes wrong when stopping kafka producer:", e);
//        } finally {
//            logger.info("## kafka producer is down.");
//        }
//    }
//
//    public void send(Topic topic, Message message) throws IOException {
//
//        ProducerRecord<String, Message> record;
//        if (topic.getPartition() != null) {
//            record = new ProducerRecord<String, Message>(topic.getTopic(), topic.getPartition(), null, message);
//        } else {
//            record = new ProducerRecord<String, Message>(topic.getTopic(), message);
//        }
//        producer.send(record);
//        if (logger.isDebugEnabled()) {
//            logger.debug("send message to kafka topic: {} \n {}", topic.getTopic(), message.toString());
//        }
//    }
//}
