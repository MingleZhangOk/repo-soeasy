//package com.mingzhang.table.source.elastic;
//
//import com.mingzhang.table.pojo.TableFieldPojo;
//import org.apache.flink.configuration.Configuration;
//import org.apache.flink.streaming.api.functions.source.RichSourceFunction;
//import org.apache.flink.types.Row;
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//
//import java.util.List;
//import java.util.Map;
//
//public class ElasticSource extends RichSourceFunction<Row> {
//
//    private List<HttpHost> list;
//    private String index;
//    private String type = "_doc";
//    private RestHighLevelClient rhlClient;
//    private SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//    private List<TableFieldPojo> tableFieldPojo;
//
//    public ElasticSource(List<HttpHost> list, String index, List<TableFieldPojo> tableFieldPojo) {
//        this.tableFieldPojo = tableFieldPojo;
//        this.list = list;
//        this.index = index;
//    }
//
//    private RestHighLevelClient getHighClient(List<HttpHost> list) throws Exception {
//        RestClientBuilder builder = RestClient.builder(list.toArray(new HttpHost[list.size()]));
//        RestHighLevelClient rhlClient = new RestHighLevelClient(builder);
//        if (!rhlClient.ping()) {
//            throw new Exception("确保ES地址是否正确");
//        }
//        return rhlClient;
//    }
//
//    @Override
//    public void open(Configuration parameters) throws Exception {
//        super.open(parameters);
//        rhlClient = getHighClient(list);
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(5000);
//    }
//
//    @Override
//    public void close() throws Exception {
//        super.close();
//        if (null != rhlClient) {
//            rhlClient.close();
//        }
//    }
//
//    @Override
//    public void run(SourceContext<Row> sourceContext) throws Exception {
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse response = rhlClient.search(searchRequest);
//        SearchHits hits = response.getHits();
//        SearchHit[] hits1 = hits.getHits();
//
//        for (int i = 0; i < hits1.length; i++) {
//            Row row = new Row(tableFieldPojo.size());
//            Map<String, Object> sourceAsMap = hits1[i].getSourceAsMap();
//            for (int j = 0; j < tableFieldPojo.size(); j++) {
//                row.setField(j, sourceAsMap.get(tableFieldPojo.get(j).getFieldCode()));
//            }
//            sourceContext.collect(row);
//        }
//    }
//
//    @Override
//    public void cancel() {
//
//    }
//
//}
