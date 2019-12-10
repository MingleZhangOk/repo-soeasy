package com.mingzhang.table.source.file;

import com.mingzhang.table.common.FlinkStreamInitDemo;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.table.api.Table;
import org.apache.flink.types.Row;

public class HDFSConnectDemo {

    public static void main(String[] args) {

        final ParameterTool params = ParameterTool.fromArgs(args);
        String fileName;
        if (params.get("f") != null)
            fileName = params.get("f");
        else
            fileName = "/flink/userClick_Random_100W";

        DataStream<String> source = FlinkStreamInitDemo.streamEnv.readTextFile("hdfs://172.16.44.28:8020" + fileName, "UTF-8");

        TypeInformation[] types = new TypeInformation[]{Types.STRING, Types.STRING, Types.LONG};
        String[] fields = new String[]{"id", "user_click", "time"};
        RowTypeInfo typeInformation = new RowTypeInfo(types, fields);

        DataStream<Row> stream = source.map(new MapFunction<String, Row>() {
            private static final long serialVersionUID = 2349572544179673349L;

            @Override
            public Row map(String s) {
                String[] split = s.split(",");
                Row row = new Row(split.length);
                for (int i = 0; i < split.length; i++) {
                    Object value = split[i];
                    if (types[i].equals(Types.STRING)) {
                        value = split[i];
                    }
                    if (types[i].equals(Types.LONG)) {
                        value = Long.valueOf(split[i]);
                    }
                    row.setField(i, value);
                }
                return row;
            }
        }).returns(typeInformation);

        FlinkStreamInitDemo.flinkTableEnv.registerDataStream("user_click_name", stream, String.join(",", typeInformation.getFieldNames()) + ",proctime.proctime");
        String sql = "select * from user_click_name ";
        Table table = FlinkStreamInitDemo.flinkTableEnv.sqlQuery(sql);
        table.printSchema();
        FlinkStreamInitDemo.printData(sql);
    }
}
