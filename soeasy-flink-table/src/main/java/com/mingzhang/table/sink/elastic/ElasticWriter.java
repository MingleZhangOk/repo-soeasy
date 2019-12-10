package com.mingzhang.table.sink.elastic;

import com.mingzhang.table.common.FlinkStreamInitDemo;
import com.mingzhang.table.side.mysql.MySQLConnectDemo;
import com.mingzhang.table.source.kafka.KafkaConnectDemo;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.table.descriptors.Descriptor;
import org.apache.flink.table.descriptors.Elasticsearch;
import org.apache.flink.table.descriptors.Json;
import org.apache.flink.table.factories.StreamTableSinkFactory;
import org.apache.flink.table.factories.TableFactoryService;
import org.apache.flink.table.sinks.StreamTableSink;
import org.apache.flink.types.Row;

import java.util.Map;

public class ElasticWriter {

    static String version = "6";
    static String servers = "192.168.56.102";
    static String port = "9200";
    static String index = "test";
    static Integer iport = Integer.valueOf(port);
    static String updateIndexFlag = "";
    /*提交记录数*/
    static String records = "10000";
    /*提交妙数*/
    static String secendsNum = "1";
    /*提交记录总的大小*/
    static String size = "10";
    /*重试次数*/
    static String retryTimes = "2";
    static String updateFields = "testES";
    static String[] hostsList = servers.split(",");
    public static String tableName = "testES";

    public static void main(String[] args) throws Exception {

        KafkaConnectDemo.registerKafkaSourceTable();
        MySQLConnectDemo.registerFunction();
        ElasticWriter.registerESSinkTable();
        String querySQL = " select id,name1,age1,stat from " + KafkaConnectDemo.tableName + ", LATERAL TABLE(" + MySQLConnectDemo.funcName + "(id)) AS S(id1 ,name1,age1,year1)";
        FlinkStreamInitDemo.printData(querySQL);
        String sql = "insert into " + tableName + querySQL;
        FlinkStreamInitDemo.flinkTableEnv.sqlUpdate(sql);
        FlinkStreamInitDemo.streamEnv.execute("MyES_testJob");
    }

    static void registerESSinkTable() {
        final Map<String, String> propertiesMap = getUpsertMode().toProperties();
        StreamTableSink<Tuple2<Boolean, Row>> actualSink = TableFactoryService.find(StreamTableSinkFactory.class, propertiesMap).createStreamTableSink(propertiesMap);
        FlinkStreamInitDemo.flinkTableEnv.registerTableSink(tableName, actualSink);
    }

    static Descriptor getUpsertMode() {
        return FlinkStreamInitDemo.flinkTableEnv.connect(getElasticsearch()).withFormat(
                new Json().deriveSchema())
                .withSchema(SchemaUtil.getSchema(FlinkStreamInitDemo.getFieldsList()))
                .inUpsertMode();
    }

    static Descriptor getRetractMode() {
        return FlinkStreamInitDemo.flinkTableEnv.connect(getElasticsearch()).withFormat(
                new Json().deriveSchema())
                .withSchema(SchemaUtil.getSchema(FlinkStreamInitDemo.getFieldsList()))
                .inRetractMode();
    }

    static Descriptor getAppendMode() {
        return FlinkStreamInitDemo.flinkTableEnv.connect(getElasticsearch()).withFormat(
                new Json().deriveSchema())
                .withSchema(SchemaUtil.getSchema(FlinkStreamInitDemo.getFieldsList()))
                .inAppendMode();
    }

    private static Elasticsearch getElasticsearch() {
        Elasticsearch tmpElasticsearch = new Elasticsearch();
        for (int i = 0; i < hostsList.length; i++) {
            tmpElasticsearch = tmpElasticsearch.host(hostsList[i], iport, "http");
        }
        // String busiKey = SchemaUtil.getTableFieldSKey(list);
        final Elasticsearch elasticsearch = tmpElasticsearch
                .version(version)
                // .host(servers, iport, "http")
                .index(index.toLowerCase())
                .documentType("_doc")
                // .dynamicIndexFlag(updateIndexFlag)
                // .busiKeys(busiKey != null ? busiKey : "")
                // .updateFieldMap(updateFields != null ? updateFields : "")
                .failureHandlerIgnore()
                .bulkFlushMaxActions(Integer.valueOf(records))
                .bulkFlushInterval(Integer.valueOf(secendsNum) * 1000)
                .bulkFlushMaxSize(size + " MB")
                .bulkFlushBackoffMaxRetries(Integer.valueOf(retryTimes));
        return elasticsearch;
    }

    private void doc() {
        new Elasticsearch()
                .version("6")                      // 必需：有效的连接器版本为“6”
                .host("localhost", 9200, "http")   // 必需：要连接到的一个或多个Elasticearch主机
                .index("MyUsers")                  // 必需：ElasticSearch索引
                .documentType("user")              // 必需: Elasticsearch document type

                .keyDelimiter("$")        // 可选：组合键的分隔符(默认为“_”)
                //  例如，“$”将导致ID“KEY 1$KEY 2$KEY 3”
                .keyNullLiteral("n/a")    // 可选：在键中表示空字段(默认为“空”)

                // 可选：如果对Elasticearch的请求失败(默认情况下失败)，则执行失败处理策略。
                .failureHandlerFail()          // 可选：如果请求失败并导致作业失败，则引发异常。
                .failureHandlerIgnore()        //   或者忽略失败并删除请求。
                .failureHandlerRetryRejected() //   或者重新添加由于队列容量饱和而失败的请求。
                //  .failureHandlerCustom(...)     //   或使用ActionRequestFailureHandler子类处理自定义故障

                // 可选：为提高效率，在将元素大容量发送到集群之前，配置如何缓冲这些元素
                .disableFlushOnCheckpoint()    // 可选：禁用检查点上的冲洗(见下面的说明！)
                .bulkFlushMaxActions(42)       // 可选：为每个大容量请求缓冲区的最大操作数
                .bulkFlushMaxSize("42 mb")     // 可选：每个大容量请求的缓冲操作的最大大小(以字节为单位)
                //   (只支持MB粒度)
                .bulkFlushInterval(60000L)     // 可选：大容量刷新间隔(毫秒)

                .bulkFlushBackoffConstant()    // 可选：使用常量退避类型
                .bulkFlushBackoffExponential() //   或者使用指数退避类型。
                .bulkFlushBackoffMaxRetries(3) // 可选：最大重试次数
                .bulkFlushBackoffDelay(30000L) // 可选：每次退避尝试之间的延迟(以毫秒为单位)

                // 可选：在REST与Elasticearch通信期间使用的连接属性
                .connectionMaxRetryTimeout(3)  // 可选：重试之间的最大超时时间(以毫秒为单位)
                .connectionPathPrefix("/v1");   // 可选：要添加到每个REST通信中的前缀字符串
    }

}
