/*
package cn.com.frms.com.mingzhang.table.tests.mainFile;

import FR_Busirule_Info_Pojo;
import FR_ENG_SQL_Rule_Info_Pojo;
import FR_PD_Alarmdetall_Pojo;
import FR_PD_Alarmmain_Pojo;
import FR_Busirule_Info_FlinkSourceFunction;
import FR_PD_Alarmdetall_FlinkSinkFunction;
import FR_PD_Alarmmain_FlinkSinkFunction;
import CodeString;
import CommonUtil;
import SQL_Connect_DB2;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple10;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MainTest {
    private static final Logger logger = Logger.getLogger(MainTest.class);

    private static Properties properties;
    private static String appName;
    private static Integer flinkDuration;
    private static String flinkCheckPointDir;
    private static Integer flinkCoresMax;
    private static Integer flinkExecutorCores;
    private static Integer flinkExecutorMemory;
    private static String flinkEnable;
    private static boolean replayFlag = true;
    private static Long checkPointTime;
    private static Integer replayTimes;
    private static Integer replaySeconds;
    private static String kafkaTopics;

    private static String zookeeper_host;
    private static String kafka_broker;
    private static String transaction_group;

    static {
        logger.debug("################################################333");
        properties = new Properties();
        try {
            properties.load(CommonUtil.getBasicBipConfig());
            //flink任务相关配置参数
            appName = properties.getProperty(CodeString.FLINK_APP_NAME).trim();
            flinkDuration = Integer.valueOf(properties.get(CodeString.FLINK_DURATIONS).toString().trim());
            flinkCheckPointDir = properties.getProperty(CodeString.FLINK_CHECKPOINT_DIRECTORY).trim();
            flinkCoresMax = Integer.valueOf(properties.getProperty(CodeString.FLINK_CORES_MAX).trim());
            flinkExecutorCores = Integer.valueOf(properties.getProperty(CodeString.FLINK_EXECUTOR_CORES).trim());
            flinkExecutorMemory = Integer.valueOf(properties.getProperty(CodeString.FLINK_EXECUTOR_MEMORY).trim());
            flinkEnable = properties.getProperty(CodeString.FLINK_CONF_ENABLE).trim();
            replayFlag = Boolean.valueOf(properties.getProperty(CodeString.FLINK_REPLAY_FLAG, "true").trim());
            checkPointTime = Long.valueOf(properties.getProperty(CodeString.CHECK_POINT_TIME, "10000").trim());
            replayTimes = Integer.valueOf(properties.getProperty(CodeString.REPLAY_TIMES, "2").trim());
            replaySeconds = Integer.valueOf(properties.getProperty(CodeString.REPLAY_SECONDS, "10").trim());
            //kafka相关配置参数
            kafkaTopics = properties.getProperty(CodeString.KAFKA_TOPICS).trim();
            zookeeper_host = properties.getProperty(CodeString.ZOOKEEPER_HOSTS).trim();
            kafka_broker = properties.getProperty(CodeString.KAFKA_BROKER_LIST).trim();
            transaction_group = properties.getProperty(CodeString.KAFKA_GROUP).trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
        DataSource input = executionEnvironment.createInput(new MyTest_InputFormat());
        //获取flink执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        if (replayFlag) {
            //配置flink检查时间间隔
            env.enableCheckpointing(checkPointTime);
            //配置flink任务错误重启机制
            env.setRestartStrategy(RestartStrategies.fixedDelayRestart(
                    replayTimes, // 重启次数
                    Time.of(replaySeconds, TimeUnit.SECONDS) // 重启时间间隔
            ));
        }
        if (flinkExecutorCores != null) {
            env.setParallelism(flinkExecutorCores);//执行所有核数
        }
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);//基于事件的
        //获取flinkTable执行环境
        StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);

        input.map(new MapFunction() {
            @Override
            public Object map(Object value) throws Exception {

                return null;
            }
        });

        Map<String, FR_ENG_SQL_Rule_Info_Pojo> lsqMap = SQL_Connect_DB2.init();
        //构建kafka接入源配置参数
        Properties props = new Properties();
        props.setProperty("key.deserializer",
                "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.setProperty("value.deserializer",
                "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.setProperty("zookeeper.connect", zookeeper_host);
        props.setProperty("bootstrap.servers", kafka_broker);
        props.setProperty("group.id", transaction_group);
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("transaction.timeout.ms", "35000");
        props.setProperty("zookeeper.connection.timeout.ms", "5000");
        props.setProperty("auto.offset.reset", "earliest"); // read from the beginning. (earliest is kafka 0.11 value)
        props.setProperty("max.partition.fetch.bytes", "256"); // make a lot of fetches (MESSAGES MUST BE SMALLER!)
        //配置多个topic
        List<String> topicName = new ArrayList<String>();
        String[] topics = kafkaTopics.split(",");
        for (int i = 0; i < topics.length; i++) {
            topicName.add(topics[i]);
        }
        //接入kafka数据流
        FlinkKafkaConsumer011 consumer = new FlinkKafkaConsumer011(topicName, new SimpleStringSchema(), props);
        DataStreamSource<String> Kafka_source = env.addSource(consumer);
        DataStream<Tuple10<String, String, String, String, String, String, String, String, String, String>> Kafka_Table_Source =
                Kafka_source.map(k -> new Tuple10<String, String, String, String, String, String, String, String, String, String>()).rebalance();

        tableEnv.registerDataStream("KAFKA_TABLE", Kafka_Table_Source, "CUSTOMLABEL,DTL__CAPXRESTART2," +
                "DTL__CAPXTIMESTAMP,DTL__CAPXUOW,INFA_OP_TYPE,INFA_TABLE_NAME,PASSWORD,FIRSTFLAGE,FIRSTLOGINTIME," +
                "LASTLOGINTIME");

        //注册源表：FR_BUSIRULE_INFO
        DataStreamSource<FR_Busirule_Info_Pojo> FR_Busirule_Info_Source = env.addSource(new FR_Busirule_Info_FlinkSourceFunction());
        tableEnv.registerDataStream("FR_BUSIRULE_INFO", FR_Busirule_Info_Source, "RULE_ID,RULE_NAME,RULE_DESC,RULE_STATE,RULE_TYPE," +
                "RULE_CLASS,RULE_LEVEL,RULE_PRIORITY,OP_TIME,LOGIN_NO,OP_ORG,BAK,RULE_KIND,RULE_REASON,RISK_TYPE,BUIS_TYPE," +
                "APP_CLASS,RULE_RESOURCE,HANDLE_FLOW,WARNING_RESULT,CHECK_PRO,REM_SIGN,REM_STRATEGY,WARNING_SORT,OPER_CYSLE," +
                "WHAT_STRATEGY,INTERVEN_REASON,ORG_ID,PUB_STAT,ID");

        //执行sql语句
        //写入目标表：FR_PD_Alarmmain、FR_PD_Alarmdetall
        //System.out.println(lsqMap);
        //System.out.println(lsqMap.get("FR_PD_ALARMDETALL"));
        if (lsqMap.get("FR_PD_ALARMMAIN") != null) {
            FR_ENG_SQL_Rule_Info_Pojo fr_pd_alarmmain = lsqMap.get("FR_PD_ALARMMAIN");
            Table sqlQuery = tableEnv.sqlQuery(fr_pd_alarmmain.getPROCESS_SQL());
            sqlQuery.printSchema();
            DataStream<FR_PD_Alarmmain_Pojo> fr_pd_alarmmain_pojoDataStream = tableEnv.toAppendStream(sqlQuery, FR_PD_Alarmmain_Pojo.class);
            fr_pd_alarmmain_pojoDataStream.addSink(new FR_PD_Alarmmain_FlinkSinkFunction());
        }

        if (lsqMap.get("FR_PD_ALARMDETALL") != null) {
            FR_ENG_SQL_Rule_Info_Pojo fr_pd_alarmdetall = lsqMap.get("FR_PD_ALARMDETALL");
            String process_sql = fr_pd_alarmdetall.getPROCESS_SQL();
            System.out.println(process_sql);
            Table sqlQuery = tableEnv.sqlQuery(fr_pd_alarmdetall.getPROCESS_SQL());
            sqlQuery.printSchema();
            DataStream<FR_PD_Alarmdetall_Pojo> fr_pd_alarmdetall_pojoDataStream = tableEnv.toAppendStream(sqlQuery, FR_PD_Alarmdetall_Pojo.class);
            fr_pd_alarmdetall_pojoDataStream.addSink(new FR_PD_Alarmdetall_FlinkSinkFunction());
        }

        //sql:select * from KafkaTable,FR_BUSIRULE_INFO
        tableEnv.execEnv();
        env.execute("kafkaTable_Demo");
    }
}
*/
