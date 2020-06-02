package com.mingzhang.table.source.udfsocket;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.typeutils.GenericTypeInfo;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.functions.AsyncTableFunction;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.table.sources.LookupableTableSource;
import org.apache.flink.table.sources.StreamTableSource;
import org.apache.flink.table.types.DataType;
import org.apache.flink.table.types.logical.RowType;
import org.apache.flink.table.types.utils.TypeConversions;
import org.apache.flink.types.Row;

import java.io.Serializable;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @Description
 * @Classname UserDefAPISource
 * @date 2020-06-01 16:04
 */
public class UserDefAPISource implements StreamTableSource<Row>, LookupableTableSource<Row>, Serializable {

    private static final String[] fields = new String[]{"id", "name", "age"};
    private static final TypeInformation[] typeInformations = new TypeInformation[]{Types.STRING, Types.STRING, Types.INT};

    @Override
    public DataType getProducedDataType() {
        TypeInformation<RowType.RowField> fieldTypeInformation = GenericTypeInfo.of(RowType.RowField.class);

//        GenericTypeInfo genericTypeInfo = new GenericTypeInfo(Row.of("").getClass());
//        GenericTypeComparator genericTypeComparator = new GenericTypeComparator(true, new TypeSerializer<Row>(), Row.class);


//        GenericTypeInfo genericTypeInfo = new GenericTypeInfo(new RowTypeInfo(typeInformations, fields).getFieldTypes().getClass());

//        BasicTypeInfo

//        GenericTypeInfo genericTypeInfo = new GenericTypeInfo(Row.class);


        return TypeConversions.fromLegacyInfoToDataType(new RowTypeInfo(typeInformations, fields));
//        return TypeConversions.fromLegacyInfoToDataType(genericTypeInfo);
    }

    @Override
    public TypeInformation<Row> getReturnType() {
        return null;
    }

    @Override
    public TableSchema getTableSchema() {
        return TableSchema.builder()
                .fields(fields, TypeConversions.fromLegacyInfoToDataType(typeInformations))
                .build();
//        return new TableSchema(fields, typeInformations);
    }

    @Override
    public String explainSource() {
        return "User_Def_API_Source";
    }

    @Override
    public boolean isBounded() {
        return true;
    }

    @Override
    public DataStream<Row> getDataStream(StreamExecutionEnvironment streamExecutionEnvironment) {
        return streamExecutionEnvironment.addSource(new SouceParaSocket(), new RowTypeInfo(typeInformations, fields));
    }


    @Override
    public TableFunction<Row> getLookupFunction(String[] strings) {
        return null;
    }

    @Override
    public AsyncTableFunction<Row> getAsyncLookupFunction(String[] strings) {
        return null;
    }

    @Override
    public boolean isAsyncEnabled() {
        return true;
    }
}