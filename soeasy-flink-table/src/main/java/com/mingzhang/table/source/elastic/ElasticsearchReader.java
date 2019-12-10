package com.mingzhang.table.source.elastic;

import com.mingzhang.table.common.FlinkBatchInitDemo;
import com.mingzhang.table.impl.elastic.ElasticTable;
import com.mingzhang.table.source.mysql.MySQLReader;
import com.mingzhang.table.util.SchemaUtil;
import org.apache.flink.table.api.TableSchema;
import org.apache.http.HttpHost;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ElasticsearchReader {

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
        MySQLReader.registerMySQLSourceTable();
        registerESSourceTable();

        String sql = "select * from MyStudent a where a.OrgId not in (select f0 as OrgId from testES)";
//        String sql = "select f0 as OrgId ,f1 as TrxTps from testES";
        FlinkBatchInitDemo.printData(sql);
        System.exit(0);
    }

    public static void registerESSourceTable() throws Exception {

        TableSchema tableSchema = SchemaUtil.getTableSchema(FlinkBatchInitDemo.getFieldsList());

        String sql = "";

        final List<HttpHost> httpHosts = Arrays.asList(servers.split(","))
                .stream()
                .map(host -> new HttpHost(host, iport, "http"))
                .collect(Collectors.toList());

        ElasticTable elasticTable = new ElasticTable(httpHosts, "datalog_sectps", FlinkBatchInitDemo.getFieldsList());

        FlinkBatchInitDemo.tableEnvironment.registerTableSource(tableName, elasticTable);

//        FlinkBatchInitDemo.tableEnvironment.registerTableSource(tableName, elasticSource);
        System.out.println("注册es表" + tableName);
        sql = "select * from " + tableName;
        FlinkBatchInitDemo.printData(sql);


    }


}
