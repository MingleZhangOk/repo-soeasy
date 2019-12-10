package com.mingzhang.table.saved;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.KafkaTableSourceSinkFactory;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.api.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.*;
import org.apache.flink.table.factories.StreamTableSinkFactory;
import org.apache.flink.table.factories.TableFactoryService;
import org.apache.flink.table.functions.ScalarFunction;
import org.apache.flink.table.sinks.TableSink;
import org.apache.flink.table.sources.TableSource;
import org.apache.flink.types.Row;

import java.util.*;


/**
 * 莫名其妙的where 条件判断结果不同，
 * 如果是trance1SQL 则不会输出，如果是trance2SQL则会输出。tranceSqlChoose=1 or =2 可以切换sql
 * 另外es的包您自己替换下原版的，
 * import cn.com.yusys.comp.connector.elastic.descriptors.Elasticsearch;
 * 我直接在引擎产品里面写的demo，不好切换了！ ~V V~ ！  谢谢安金大神
 * <!--<dependency>-->
 * <!--<groupId>org.apache.flink</groupId>-->
 * <!--<artifactId>flink-connector-elasticsearch6_2.11</artifactId>-->
 * <!--</dependency>-->
 */
public class FlinkJobDemo {
    private static String kafkaServers = "192.168.56.102:9092";
    private static String kafkaTopic = "nams_cdc_jbk";

    private static String esServers = "192.168.56.102";
    private static String esClusterName = "my-application";
    private static String esIndex = "sd_test";
    private static String esVersion = "642";
    private static int esPort = 9200;


    private static String kafkaTableName = "kafkaSource";
    private static String tranceTableName = "tranceTable";
    private static String esTableName = "esSink";

    private static int tranceSqlChoose = 1;
    //失败的sql
    private static String trance1SQL = "select * from " + kafkaTableName + " where MSGCD='NPS.803.001.01'";
    //成功的sql
    private static String trance2SQL = "select * from " + kafkaTableName + " where isEquals(MSGCD,'NPS.803.001.01')";


    private static String sinkSQL = "insert into " + esTableName + " select MSGID,INSTGBKID,MSGCD,CREDTTM,STSINFTP  from " + tranceTableName;

    //模拟报文：{"MSGID":"123","INSTGBKID":"456","MSGCD":"NPS.803.001.01","CREDTTM":"1922929121","STSINFTP":"asdlfjald"}

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment fsEnv = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings fsSettings = EnvironmentSettings.newInstance().useOldPlanner().inStreamingMode().build();
        StreamTableEnvironment fsTableEnv = StreamTableEnvironment.create(fsEnv, fsSettings);
        fsTableEnv.registerFunction("isEquals", new isEquals());
        registerSource(fsTableEnv);
        registerTrance(fsTableEnv);
        registerSink(fsTableEnv);

        fsEnv.execute();
    }

    private static void registerSource(StreamTableEnvironment tableEnvironment) {
        Properties props = new Properties();
        props.setProperty("key.deserializer",
                "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        props.setProperty("value.deserializer",
                "org.apache.kafka.comñmon.serialization.ByteArrayDeserializer");
        props.setProperty("bootstrap.servers", kafkaServers);
        props.setProperty("group.id", "testgroup");
        props.setProperty("auto.offset.reset", "latest"); // read from the beginning. (earliest is kafka 0.11 value)
        Descriptor sourceDescriptor = tableEnvironment.connect(
                new Kafka().version("universal")
                        .topic(kafkaTopic)
                        .properties(props)
        ).withFormat(new Json().deriveSchema())
                .withSchema(new Schema().schema(getTableSchema1(getFieldList())).proctime())
                .inAppendMode();

        System.err.println(sourceDescriptor.toProperties());

        final TableSource<Row> actualSource = TableFactoryService.find(KafkaTableSourceSinkFactory.class, sourceDescriptor)
                .createStreamTableSource(sourceDescriptor.toProperties());

        tableEnvironment.registerTableSource(kafkaTableName, actualSource);

        //TODO 20191129测试
        printStreamTableData(tableEnvironment, "kafkaSource表", kafkaTableName);
    }

    private static void registerTrance(StreamTableEnvironment tableEnvironment) {
        String sql = "";
        if (tranceSqlChoose == 1) sql = trance1SQL;
        if (tranceSqlChoose == 2) sql = trance2SQL;
        Table newUppertable = tableEnvironment.sqlQuery(sql);
        tableEnvironment.registerTable(tranceTableName, newUppertable);
        //TODO 20191129测试
        printStreamTableData(tableEnvironment, "中间事务表", tranceTableName);
    }

    private static void registerSink(StreamTableEnvironment tableEnvironment) {
        String[] hostsList = esServers.split(",");
        Elasticsearch tmpElasticsearch = new Elasticsearch();
        for (int i = 0; i < hostsList.length; i++) {
            tmpElasticsearch = tmpElasticsearch.host(hostsList[i], esPort, "http");
        }
        final Elasticsearch elasticsearch = tmpElasticsearch
                .version(esVersion)
                .index(esIndex)
                .documentType("_doc")
//                .busiKeys("")
//                .updateFieldMap("")
                .bulkFlushMaxActions(1);
//                .dynamicIndexFlag("N");
        Descriptor targetDesc = tableEnvironment.connect(elasticsearch).withFormat(
                new Json().deriveSchema())
                .withSchema(getSchema(getFieldList()))
                .inUpsertMode();
//                .inAppendMode();
        final Map<String, String> propertiesMap = targetDesc.toProperties();
        final TableSink<Row> actualSink = TableFactoryService.find(StreamTableSinkFactory.class, propertiesMap)
                .createStreamTableSink(propertiesMap);
        tableEnvironment.registerTableSink(esTableName, actualSink);
        tableEnvironment.sqlUpdate(sinkSQL);
        //TODO 20191115测试
        printSchema(tableEnvironment, "esSink表", esTableName);
    }

    private static TableSchema getTableSchema1(List<String> fieldList) {
        TypeInformation[] typeInformations = new TypeInformation[fieldList.size() + 1];
        String[] fieldNames = new String[fieldList.size() + 1];
        for (int i = 0; i < fieldList.size(); i++) {
            typeInformations[i] = Types.STRING;
            fieldNames[i] = fieldList.get(i);
        }
        fieldNames[fieldList.size()] = "proctime";
        typeInformations[fieldList.size()] = Types.LOCAL_DATE_TIME;
        return new TableSchema(fieldNames, typeInformations);
    }

    private static Schema getSchema(List<String> fieldList) {
        final Schema schema = new Schema();
        fieldList.forEach(i -> schema.field(i, Types.STRING));
        return schema;
    }

    private static List<String> getFieldList() {
        List<String> list = new ArrayList<>();
        list.add("MSGID");
        list.add("INSTGBKID");
        list.add("MSGCD");
        list.add("CREDTTM");
        list.add("STSINFTP");
        return list;

    }

    public static class isEquals extends ScalarFunction {
        private static final long serialVersionUID = -3226793638567956384L;

        public boolean eval(String MSGCD, String MSGCDCODE) {
            boolean flag = false;
            if (MSGCD.hashCode() == MSGCDCODE.hashCode() || MSGCD.toLowerCase().hashCode() == MSGCDCODE.toLowerCase().hashCode()) {
                flag = true;
            }
            System.out.println("MSGCD=" + MSGCD);
            System.out.println("MSGCDCODE=" + MSGCDCODE);
            System.out.println(flag);
            return flag;
        }
    }

    private static void printStreamTableData(StreamTableEnvironment tableEnvironment, String tableType, String tableName) {
        System.out.println("注册" + tableType + "，表名:" + tableName);
        Table sqlQuery = tableEnvironment.sqlQuery("select * from " + tableName);
        sqlQuery.printSchema();
        DataStream<Row> rowDataStream = tableEnvironment.toAppendStream(sqlQuery, Row.class);
        rowDataStream.print();
    }

    private static void printSchema(StreamTableEnvironment tableEnvironment, String tableType, String tableName) {
        System.out.println("注册" + tableType + "，表名:" + tableName);
        Table sqlQuery = tableEnvironment.sqlQuery("select * from " + tableName);
        sqlQuery.printSchema();
    }

}
