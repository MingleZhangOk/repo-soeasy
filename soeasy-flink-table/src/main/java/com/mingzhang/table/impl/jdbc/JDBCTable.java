package com.mingzhang.table.impl.jdbc;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.sources.BatchTableSource;

public class JDBCTable implements BatchTableSource {
    private final JDBCInputFormat inputFormat;
    private final TableSchema tableSchema;

    public JDBCTable(JDBCInputFormat inputFormat, TableSchema tableSchema) {
        this.inputFormat = inputFormat;
        this.tableSchema = tableSchema;
    }


    @Override
    public DataSet getDataSet(ExecutionEnvironment execEnv) {
        return execEnv.createInput(this.inputFormat);
    }

    @Override
    public TypeInformation getReturnType() {
        return new RowTypeInfo(tableSchema.getFieldTypes(), tableSchema.getFieldNames());
    }

    @Override
    public TableSchema getTableSchema() {
        return this.tableSchema;
    }

    @Override
    public String explainSource() {
        return "RDB source";
    }
}
