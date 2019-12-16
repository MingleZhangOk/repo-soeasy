package com.mingzhang.table.impl.elastic;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.table.functions.FunctionContext;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.Map;
import java.util.Set;

public class MyElasticsearchLookupFunction extends TableFunction<Row> {

    TransportClient transportClient;
    private final String[] fieldNames;
    private final TypeInformation[] fieldTypes;
    private final String servers = "192.168.56.102";
    private final String index = "test";
    private final String type = "_doc";
    private final String version;
    private final int port = 9200;
    private final String TimeInterval;
    private final String clusterName;
    private final Settings settings;


    public MyElasticsearchLookupFunction(String[] fieldNames, TypeInformation[] fieldTypes, String servers,
                                         String index, String type, String version, int port, String TimeInterval,
                                         String clusterName, Settings settings) {
        this.fieldNames = fieldNames;
        this.fieldTypes = fieldTypes;
//        this.servers = servers;
//        this.index = index;
//        this.type = type;
        this.version = version;
//        this.port = port;
        this.TimeInterval = TimeInterval;
        this.clusterName = clusterName;
        this.settings = settings;
    }

    @Override
    public void open(FunctionContext context) throws Exception {
        Settings build = Settings.builder().build();
        Settings settings = Settings.builder()
                .put(build)
                .build();
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(servers), port);
        transportClient = new PreBuiltTransportClient(settings).addTransportAddresses(transportAddress);
    }

    public void eval(Object... paramas) {
        String id = paramas[0].toString();
        String key = paramas[1].toString();
        StringBuilder value = new StringBuilder();
        GetRequestBuilder getRequestBuilder = transportClient.prepareGet(index, type, id);
        Map<String, Object> source = getRequestBuilder.get().getSource();
        Set<Map.Entry<String, Object>> entries = source.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            value.append(entry.getKey()).append(":").append(entry.getValue()).append("|");
        }
        collect(Row.of(value.substring(0, value.length() - 1)));
    }

    public static final class Builder {
        private String[] fieldNames;
        private TypeInformation[] fieldTypes;
        private String servers;
        private String index;
        private String type;
        private String version;
        private int port;
        private String TimeInterval;
        private String clusterName;
        private Settings settings;

        private Builder() {
        }

        public static MyElasticsearchLookupFunction.Builder getBuilder() {
            return new MyElasticsearchLookupFunction.Builder();
        }

        public MyElasticsearchLookupFunction.Builder withFieldNames(String[] fieldNames) {
            this.fieldNames = fieldNames;
            return this;
        }

        public MyElasticsearchLookupFunction.Builder withFieldTypes(TypeInformation[] fieldTypes) {
            this.fieldTypes = fieldTypes;
            return this;
        }


        public MyElasticsearchLookupFunction.Builder setIndex(String index) {
            this.index = index;
            return this;
        }

        public MyElasticsearchLookupFunction.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public MyElasticsearchLookupFunction.Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public MyElasticsearchLookupFunction.Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public MyElasticsearchLookupFunction.Builder setServers(String servers) {
            this.servers = servers;
            return this;
        }

        public MyElasticsearchLookupFunction.Builder setTimeInterval(String timeInterval) {
            TimeInterval = timeInterval;
            return this;
        }

        public MyElasticsearchLookupFunction.Builder setClusterName(String clusterName) {
            this.clusterName = clusterName;
            return this;
        }

        public MyElasticsearchLookupFunction.Builder setSettings(Settings settings) {
            this.settings = settings;
            return this;
        }

        public MyElasticsearchLookupFunction build() {
            return new MyElasticsearchLookupFunction(fieldNames, fieldTypes, servers, index, type, version, port, TimeInterval, clusterName, settings);
        }
    }
}
