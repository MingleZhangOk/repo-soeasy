package com.mingzhang.table.tests.flin_datasetTotable;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.api.Types;
import org.apache.flink.types.Row;

public class MapFunction_StringToRow implements MapFunction<String, Row> {

    private String split = null;
    private int[] fieldMapping = {0, 1, 2, 3, 4};
    private String[] fieldNames = {"name", "age", "sex", "addr", "job"};
    private TypeInformation[] fieldTypes = {Types.STRING(), Types.INT(), Types.STRING(), Types.STRING(), Types.STRING()};

    public MapFunction_StringToRow(String split) {
        this.split = split;
    }

    @Override
    public Row map(String value) throws Exception {
        String[] fields = value.split(split);
        RowTypeInfo rowTypeInfo = new RowTypeInfo(fieldTypes, fieldNames);
        RowTypeInfo rowTypeInfo1 = RowTypeInfo.projectFields(rowTypeInfo, fieldMapping);
        Row row = new Row(fields.length);

        row.setField(fields.length,value);
        for (int i = 0; i < fields.length; i++) {
            row.setField(i, fields[i]);
        }
        return row;
    }
}
