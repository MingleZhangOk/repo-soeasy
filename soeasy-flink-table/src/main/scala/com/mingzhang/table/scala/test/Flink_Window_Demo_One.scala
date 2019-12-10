/*
package com.test.flink_Window

import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011

object Flink_Window_Demo_One {
  private val zookeeper_host = "bigdata01:2181,bigdata02:2181,bigdata03:2181"
  private val kafka_broker = "bigdata01:9092,bigdata02:9092,bigdata03:9092"
  private val transaction_group = "kafka_SQL_Test"
  private val topic = "createTest"

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.enableCheckpointing(5000)
    env.setMaxParallelism(2)
    val prop = new Properties
    prop.setProperty("zookeeper.connect", zookeeper_host)
    prop.setProperty("bootstrap.servers", kafka_broker)
    prop.setProperty("group.id", transaction_group)
    val consumer = new FlinkKafkaConsumer011(topic, new SimpleStringSchema, prop)
    val value = env.addSource(consumer)
    val length = value.map(data => {
      val strings = data.split(",")
      val tmpStringi = strings(1)
      tmpStringi + "::::::"
    }).rebalance

    env.execute("flink_window_Test")
  }
}
*/
