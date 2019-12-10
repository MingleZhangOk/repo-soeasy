package com.mingzhang.table.impl.elastic;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.table.functions.AsyncTableFunction;
import org.apache.flink.table.functions.FunctionContext;
import org.apache.flink.types.Row;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MyElasticsearchAsyncLookupFunction extends AsyncTableFunction<Row> {

    TransportClient transportClient;
    String doc = "_doc";
    String hosts = "192.168.56.102";
    Integer port = 9300;
    String index = "test";
    private final String[] fieldNames;
    private final TypeInformation[] fieldTypes;

    public MyElasticsearchAsyncLookupFunction(String[] fieldNames, TypeInformation[] fieldTypes) {
        this.fieldNames = fieldNames;
        this.fieldTypes = fieldTypes;
    }

    @Override
    public void open(FunctionContext context) throws Exception {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", true) //增加嗅探机制，找到ES集群
                .put("cluster.name", "my-application")  // 设置集群名称
                .put("thread_pool.search.size", 20)// 增加线程池个数，暂时设为20
                .build();
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(hosts), port);
        transportClient = new PreBuiltTransportClient(settings).addTransportAddresses(transportAddress);
    }

    public void eval(CompletableFuture<Collection<Row>> future, Object... paramas) {
        String id = paramas[0].toString();
        String key = paramas[1].toString();
        StringBuilder value = new StringBuilder();
        GetRequestBuilder getRequestBuilder = transportClient.prepareGet(index, doc, id);
        Map<String, Object> source = getRequestBuilder.get().getSource();
        Set<Map.Entry<String, Object>> entries = source.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            value.append(entry.getKey()).append(":").append(entry.getValue()).append("|");
            
        }
        future.complete(Collections.singletonList(Row.of(value.substring(0, value.length() - 1))));
    }

    public static final class Builder {
        private String[] fieldNames;
        private TypeInformation[] fieldTypes;

        private Builder() {
        }

        public static MyElasticsearchAsyncLookupFunction.Builder getBuilder() {
            return new MyElasticsearchAsyncLookupFunction.Builder();
        }

        public MyElasticsearchAsyncLookupFunction.Builder withFieldNames(String[] fieldNames) {
            this.fieldNames = fieldNames;
            return this;
        }

        public MyElasticsearchAsyncLookupFunction.Builder withFieldTypes(TypeInformation[] fieldTypes) {
            this.fieldTypes = fieldTypes;
            return this;
        }

        public MyElasticsearchAsyncLookupFunction build() {
            return new MyElasticsearchAsyncLookupFunction(fieldNames, fieldTypes);
        }
    }
}
