/*
package cn.com.frms.com.mingzhang.table.tests.mainFile;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.Types;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.sinks.CsvTableSink;
import org.apache.flink.table.sources.CsvTableSource;
import org.apache.flink.table.sources.TableSource;
import org.apache.flink.types.Row;

public class DataSetInput {
    private static String sourcePath = "e:\\a.txt";
    private static String sourcePath1 = "e:\\a.txt";
    private static String sinkPath = "e:\\outPutFile.csv";
    private static String sourceTableName = "Flink_Source";
    private static String sourceTableName_1 = "Flink_Source_1";
    private static String sinkTableName = "Flink_Sink";
    private static String split_r = ",";
    private static String sqlQuery = "select fs.name,fs.age,fs.sex,fs.address,fs.job from Flink_Source fs where fs.age>=15 ";
    private static String sqlQuery1 = "select fs.name,fs.age,fs.sex,fs.address,fs.job,sf.tree,sf.cound from Flink_Source fs full join Flink_Source_1 sf ON fs.age = sf.age ";
    private static String sqlInsert = "";

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);

        env.setParallelism(1);

        DataSource<String> dataSource1 = env.readTextFile(sourcePath);
        DataSource<String> dataSource_1 = env.readTextFile(sourcePath1);
        */
/*MapOperator<String, String[]> dataSource2 = dataSource1.map(new MapFunction<String, String[]>() {
            @Override
            public String[] map(String value) throws Exception {
                String[] split = value.split("\\s+");
                return split;
            }
        });
        MapOperator<String, String[]> dataSource_2 = dataSource_1.map(new MapFunction<String, String[]>() {
            @Override
            public String[] map(String value) throws Exception {
                String[] split = value.split("\\s+");
                return split;
            }
        });
        FilterOperator<String[]> dataSource3 = dataSource2.filter(new FilterFunction<String[]>() {
            @Override
            public boolean filter(String[] value) throws Exception {
                if (value == null) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        FilterOperator<String[]> dataSource_3 = dataSource_2.filter(new FilterFunction<String[]>() {
            @Override
            public boolean filter(String[] value) throws Exception {
                if (value == null) {
                    return false;
                } else {
                    return true;
                }
            }
        });
*//*


        MapOperator<String, Tuple5<String, String, String, String, String>> dataSource2
                = dataSource1.map(new MapFunction<String, Tuple5<String, String, String, String, String>>() {
            @Override
            public Tuple5<String, String, String, String, String> map(String value) throws Exception {
                String[] split = value.split("\\s+");
                return new Tuple5<String, String, String, String, String>(split[0], split[1], split[2], split[3], split[4]);
            }
        });

        MapOperator<String, Tuple3<String, String, String>> dataSource_2
                = dataSource_1.map(new MapFunction<String, Tuple3<String, String, String>>() {
            @Override
            public Tuple3<String, String, String> map(String value) throws Exception {
                String[] split = value.split("\\s+");
                return new Tuple3<String, String, String>(split[0], split[1], split[2]);
            }
        });

        TableSource csvTableSource = CsvTableSource
                .builder()
                .path("file:///f:/person.csv")//文件路径
                .field("id", Types.INT())//第一列
                .field("name", Types.STRING())//第二列
                .field("age", Types.INT())//第三列
                .fieldDelimiter(",")//列分隔符，默认是逗号
                .lineDelimiter("\n")//行分隔符，回车
                .ignoreFirstLine()//忽略第一行
                .ignoreParseErrors()//忽略解析错误
                .build();

        CsvTableSink csvTableSink = new CsvTableSink(sinkPath, "---");

        Table table1 = tableEnv.fromDataSet(dataSource2, "name,age,sex,address,job");
        Table table2 = tableEnv.fromDataSet(dataSource_2, "age,tree,cound");

        tableEnv.registerTable(sourceTableName, table1);
        tableEnv.registerTable(sourceTableName_1, table2);
        Table sqlQueryResult = tableEnv.sqlQuery(DataSetInput.sqlQuery);
        sqlQueryResult.printSchema();
        sqlQueryResult.writeToSink(csvTableSink);
        //sqlQueryResult.insertInto(sinkTableName);
        DataSet<Row> rowDataSet = tableEnv.toDataSet(sqlQueryResult, Row.class);
        rowDataSet.print();
        // sqlQueryResult.insertInto(sinkTableName);

        tableEnv.execEnv();
    }
}
*/
