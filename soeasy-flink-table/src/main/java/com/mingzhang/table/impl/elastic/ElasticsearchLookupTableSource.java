package com.mingzhang.table.impl.elastic;

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
import org.elasticsearch.common.settings.Settings;

public class ElasticsearchLookupTableSource implements StreamTableSource<Row>, LookupableTableSource<Row> {

    private final String[] fieldNames;
    private final TypeInformation[] fieldTypes;
    private final String servers;
    private final String index;
    private final String type;
    private final String version;
    private final int port;
    private final String TimeInterval;
    private final String clusterName;
    private final Settings settings;

    public ElasticsearchLookupTableSource(String[] fieldNames, TypeInformation[] fieldTypes, String servers,
                                          String index, String type, String version, int port, String TimeInterval,
                                          String clusterName, Settings settings) {
        this.fieldNames = fieldNames;
        this.fieldTypes = fieldTypes;
        this.servers = servers;
        this.index = index;
        this.type = type;
        this.version = version;
        this.port = port;
        this.TimeInterval = TimeInterval;
        this.clusterName = clusterName;
        this.settings = settings;

    }


    //同步方法
    @Override
    public TableFunction<Row> getLookupFunction(String[] strings) {
        return MyElasticsearchLookupFunction.Builder.getBuilder()
                .withFieldNames(fieldNames)
                .withFieldTypes(fieldTypes)
                .build();
    }

    //异步方法
    @Override
    public AsyncTableFunction<Row> getAsyncLookupFunction(String[] strings) {
        return MyElasticsearchAsyncLookupFunction.Builder.getBuilder()
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
        private String servers;
        private String index;
        private String type;
        private String version;
        private int port;
        private String timeInterval;
        private String clusterName;
        private Settings settings;

        private Builder() {
        }

        public static ElasticsearchLookupTableSource.Builder newBuilder() {
            return new ElasticsearchLookupTableSource.Builder();
        }

        public ElasticsearchLookupTableSource.Builder withFieldNames(String[] fieldNames) {
            this.fieldNames = fieldNames;
            return this;
        }

        public ElasticsearchLookupTableSource.Builder withFieldTypes(TypeInformation[] fieldTypes) {
            this.fieldTypes = fieldTypes;
            return this;
        }

        public ElasticsearchLookupTableSource.Builder setIndex(String index) {
            this.index = index;
            return this;
        }

        public ElasticsearchLookupTableSource.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public ElasticsearchLookupTableSource.Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public ElasticsearchLookupTableSource.Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public ElasticsearchLookupTableSource.Builder setServers(String servers) {
            this.servers = servers;
            return this;
        }

        public ElasticsearchLookupTableSource.Builder setTimeInterval(String timeInterval) {
            this.timeInterval = timeInterval;
            return this;
        }

        public ElasticsearchLookupTableSource.Builder setClusterName(String clusterName) {
            this.clusterName = clusterName;
            return this;
        }

        public ElasticsearchLookupTableSource.Builder setSettings(Settings settings) {
            this.settings = settings;
            return this;
        }

        public ElasticsearchLookupTableSource build() {
            return new ElasticsearchLookupTableSource(fieldNames, fieldTypes, servers, index, type, version, port, timeInterval, clusterName, settings);
        }


    }
}