package com.mingzhang.table.common;

import com.mingzhang.table.pojo.TableFieldPojo;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;

import java.util.ArrayList;
import java.util.List;

public class FlinkBatchInitDemo {
    public static final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
    public static final BatchTableEnvironment tableEnvironment = BatchTableEnvironment.create(env);

    public static void printData(String sql) throws Exception {
        //env.setParallelism(1);
        Table table = FlinkBatchInitDemo.tableEnvironment.sqlQuery(sql);
        table.printSchema();
        DataSet<Row> rowDataSet = FlinkBatchInitDemo.tableEnvironment.toDataSet(table, Row.class);
        rowDataSet.print();

    }

    public static List<TableFieldPojo> getFieldsList() {
        List<TableFieldPojo> tableFieldPos = new ArrayList<>();

        TableFieldPojo tableFieldPo1 = new TableFieldPojo();
        tableFieldPo1.setFieldCode("OrgId");
        tableFieldPo1.setTableCode("log_tsp_source");
        tableFieldPo1.setFieldName("业务类型");
        tableFieldPo1.setFieldType("VARCHAR");
        tableFieldPos.add(tableFieldPo1);

        TableFieldPojo tableFieldPo2 = new TableFieldPojo();
        tableFieldPo2.setFieldCode("TrxTps");
        tableFieldPo2.setTableCode("log_tsp_source");
        tableFieldPo2.setFieldName("TPS");
        tableFieldPo2.setFieldType("LONG");
        tableFieldPos.add(tableFieldPo2);

        return tableFieldPos;
    }
}
