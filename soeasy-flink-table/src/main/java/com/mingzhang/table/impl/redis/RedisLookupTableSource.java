package com.mingzhang.table.impl.redis;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.functions.AsyncTableFunction;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.table.sources.LookupableTableSource;
import org.apache.flink.table.sources.StreamTableSource;
import org.apache.flink.table.types.DataType;
import org.apache.flink.table.types.utils.TypeConversions;
import org.apache.flink.types.Row;

public class RedisLookupTableSource implements StreamTableSource<Row>, LookupableTableSource<Row> {

    private final String[] fieldNames;
    private final TypeInformation[] fieldTypes;

    public RedisLookupTableSource(String[] fieldNames, TypeInformation[] fieldTypes) {
        this.fieldNames = fieldNames;
        this.fieldTypes = fieldTypes;
    }

    //同步方法
    @Override
    public TableFunction<Row> getLookupFunction(String[] strings) {
        return null;
    }

    //异步方法
    @Override
    public AsyncTableFunction<Row> getAsyncLookupFunction(String[] strings) {
        return MyRedisAsyncLookupFunction.Builder.getBuilder()
                .withFieldNames(fieldNames)
                .withFieldTypes(fieldTypes)
                .build();
    }

    //开启异步
    @Override
    public boolean isAsyncEnabled() {
        return true;
    }

    @Override
    public DataType getProducedDataType() {
        return TypeConversions.fromLegacyInfoToDataType(new RowTypeInfo(fieldTypes, fieldNames));
    }

    @Override
    public TableSchema getTableSchema() {
        return TableSchema.builder()
                .fields(fieldNames, TypeConversions.fromLegacyInfoToDataType(fieldTypes))
                .build();
    }

    @Override
    public DataStream<Row> getDataStream(StreamExecutionEnvironment environment) {
        throw new UnsupportedOperationException("do not support getDataStream");
    }

    public static final class Builder {
        private String[] fieldNames;
        private TypeInformation[] fieldTypes;

        private Builder() {
        }

        public static RedisLookupTableSource.Builder newBuilder() {
            return new RedisLookupTableSource.Builder();
        }

        public RedisLookupTableSource.Builder withFieldNames(String[] fieldNames) {
            this.fieldNames = fieldNames;
            return this;
        }

        public RedisLookupTableSource.Builder withFieldTypes(TypeInformation[] fieldTypes) {
            this.fieldTypes = fieldTypes;
            return this;
        }

        public RedisLookupTableSource build() {
            return new RedisLookupTableSource(fieldNames, fieldTypes);
        }
    }
}
