/*
package com.mingzhang.table.tests.flin_datasetTotable;

import org.apache.calcite.adapter.enumerable.JavaRowFormat;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.operators.PartitionOperator;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.Types;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;

public class FlinkReadFile_ToTable {

    public static final RowTypeInfo rowTypeInfo =
            new RowTypeInfo(Types.STRING(), Types.STRING(), Types.STRING(), Types.STRING(), Types.STRING());
    private static String fileOne = "e:\\a.txt";
    private static String fileTwo = "e:\\b.txt";
    private static String wirteFile = "e:\\outputFile";
    private static String tableOneName = "tableOne";
    private static String tableTwoName = "tableTwo";
    private static String sql1 = "select * from tableOne";
    private static String split = "\\s+";

    public static void main(String[] args) {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        try {
            env.registerType(Class.forName("org.apache.flink.api.java.typeutils.RowTypeInfo"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        DataSource<String> sourceOne1 = env.readTextFile(fileOne);
        MapOperator<String, Row> sourceOne2 = sourceOne1.map(new MapFunction_StringToRow(split));
        PartitionOperator<Row> sourceOne3 = sourceOne2.rebalance();

        DataSource<String> sourceTwo1 = env.readTextFile(fileTwo);
        MapOperator<String, Row> sourceTwo2 = sourceTwo1.map(new MapFunction_StringToRow(split));
        PartitionOperator<Row> sourceTwo3 = sourceTwo2.rebalance();

        */
/*String[] fieldNames = {"name", "age", "sex", "addr", "job"};
        TypeInformation[] fieldTypes = {Types.STRING(), Types.INT(), Types.STRING(), Types.STRING(), Types.STRING()};
        RowTypeInfo rowTypeInfo = new RowTypeInfo(fieldTypes);*//*


        BatchTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);
        Table tableOne = tableEnv.fromDataSet(sourceOne3);//, "name,age,sex,addr,job"
        Table tableTwo = tableEnv.fromDataSet(sourceTwo3);//, "age,tre,dic"

        // tableEnv.registerDataSet(tableOneName, sourceOne2, "name,age,sex,addr,job");
        tableEnv.registerTable(tableOneName, tableOne);
        tableEnv.registerTable(tableTwoName, tableTwo);

        Table resultTable = tableEnv.sqlQuery(sql1);
        resultTable.printSchema();

        DataSet<Row> rowDataSet = tableEnv.toDataSet(resultTable, Row.class);
        try {
            rowDataSet.print();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        tableEnv.execEnv();
    }
}
*/
