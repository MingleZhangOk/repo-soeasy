package com.mingzhang.table.tests.mainFile;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;

import java.lang.reflect.Field;

public class Flink_SQL_Test {
    private static String tableName = "Flink_Table";
    private static String sqlExecute = "select * from " + tableName;

    public static void main(String[] args) throws Exception {

      /*  if (args == null || args.length != 5) {
            throw new Exception("The required parameter was passed in incorrectly or not. Please pass in the parameter");
        }*/
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.createRemoteEnvironment("192.168.10.66", 6123);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(3);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
//        StreamTableEnvironment tableEnv = StreamTableEnvironment.getTableEnvironment(env);
        EnvironmentSettings environmentSettings = EnvironmentSettings.newInstance().useBlinkPlanner().inStreamingMode().build();
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, environmentSettings);


        String filePath = "E:\\JsonPojo777.java";
        String packagePath = "cn.com.frms.com.mingzhang.table.tests.mainFile.";
        String className = "JsonPojo777";
        String s = Demo.fileRead(filePath);
        MemoryClassLoader.getInstrance().registerJava(className, s);
        Class<?> aClass = MemoryClassLoader.getInstrance().findClass(packagePath + className);

        //Class<?> aClass = MemoryClassLoader.getInstrance().findClass(className);
        //System.out.println(Arrays.toString(aClass.getDeclaredMethods()));

        // System.out.println():

        //System.out.println(Arrays.toString(aClass.getDeclaredFields()));

        DataStreamSource<String> socketStream = env.socketTextStream("192.168.10.66", 9000);
        SingleOutputStreamOperator<SomePojo> stream1 = socketStream.map(new MapFunction<String, SomePojo>() {
            @Override
            public SomePojo map(String value) throws Exception {
                String[] split = value.split("\\s+");
                if (split.length != 5) {
                    return null;
                } else {
                    SomePojo o = (SomePojo) aClass.newInstance();
                    Field[] declaredFields = aClass.getDeclaredFields();
                    for (int i = 0; i < declaredFields.length; i++) {
                        declaredFields[i].setAccessible(true);
                        declaredFields[i].set(o, split[i]);
                    }
                    return o;
                }
            }
        });

        SingleOutputStreamOperator<SomePojo> stream2 = stream1.filter(new FilterFunction<SomePojo>() {
            @Override
            public boolean filter(SomePojo value) throws Exception {
                if (value == null) {
                    return false;
                } else {
                    return true;
                }
            }
        });

        DataStream<SomePojo> stream3 = stream2.rebalance();

        tableEnv.registerDataStream(tableName, stream3);
        Table sqlQuery = tableEnv.sqlQuery(sqlExecute);

        DataStream dataStream = tableEnv.toAppendStream(sqlQuery, aClass);
        dataStream.print();
        System.out.println(sqlQuery.toString());
        sqlQuery.printSchema();

        env.execute("Flink_Table_Test");
    }
}
