package com.mingzhang.table.scala.test

object Flink_Table {
  val zookeeper_host = "bigdata01:2181,bigdata02:2181,bigdata03:2181";
  val kafka_broker = "bigdata01:9092,bigdata02:9092,bigdata03:9092";
  val transaction_group = "kafka_SQL_Test";
  val topic = "";
  def main(args: Array[String]): Unit = {
   /* val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
    val tenv = TableEnvironment.getTableEnvironment(env)
    val stream: DataStream[(Long, String)] = null
    // register the DataStream as Table "myTable" with fields "f0", "f1"
    tenv.registerDataStream("myTable", stream)
    val prop = new Properties();
    prop.setProperty("zookeeper.connect", zookeeper_host);
    prop.setProperty("bootstrap.servers", kafka_broker);
    prop.setProperty("group.id", transaction_group);
    val consumer = new FlinkKafkaConsumer011(topic, new SimpleStringSchema(), prop)
    val source = env.addSource(consumer)

    val streamSource = env.fromElements(source)

    env.execute();*/
  }
}
