//package com.mingzhang.table.impl.elastic;
//
//import org.apache.http.HttpHost;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.RestClient;
//import org.elasticsearch.client.RestClientBuilder;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.index.query.*;
//import org.elasticsearch.search.SearchHit;
//import org.elasticsearch.search.SearchHits;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class ElasticsearchRESThighClient {
//
//
//    private static String servers = "192.168.56.102";
//    private static String port = "9200";
//    private static String index = "datalog_sectps";
//    private static String type = "_doc";
//    private static RestHighLevelClient highClient;
//
//    public static void main(String[] args) throws Exception {
//        int iport = Integer.valueOf(port);
//
//        final List<HttpHost> httpHosts = Arrays.asList(servers.split(","))
//                .stream()
//                .map(host -> new HttpHost(host, iport, "http"))
//                .collect(Collectors.toList());
//
//        highClient = getHighClient(httpHosts);
//        queryTest5();
//        close();
//
//    }
//
//    private static void close() throws Exception {
//        if (null != highClient) {
//            highClient.close();
//        }
//
//    }
//
//    private static RestHighLevelClient getHighClient(List<HttpHost> list) throws Exception {
//        RestClientBuilder builder = RestClient.builder(list.toArray(new HttpHost[list.size()]));
//        RestHighLevelClient rhlClient = new RestHighLevelClient(builder);
//        if (!rhlClient.ping()) {
//            throw new Exception("确保ES地址是否正确");
//        }
//        return rhlClient;
//    }
//
//    private static void queryTest1() {
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.from(0);
//        sourceBuilder.size(10);
//        sourceBuilder.fetchSource(new String[]{"title"}, new String[]{});
////        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "费德勒");
////        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("tag", "体育");
//        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("SysTime");
////        rangeQueryBuilder.gte("2018-01-26T08:00:00Z");
//        rangeQueryBuilder.gte("201911150000");
////        rangeQueryBuilder.lte("2018-01-26T20:00:00Z");
//        rangeQueryBuilder.lt("201911160000");
//        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
////        boolBuilder.must(matchQueryBuilder);
////        boolBuilder.must(termQueryBuilder);
//        boolBuilder.must(rangeQueryBuilder);
//        sourceBuilder.query(boolBuilder);
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        searchRequest.source(sourceBuilder);
//        try {
//            SearchResponse response = highClient.search(searchRequest);
//            System.out.println(response);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    private static void queryTest2() {
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.from(0);
//        sourceBuilder.size(10);
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        searchRequest.source(sourceBuilder);
//        try {
//            SearchResponse response = highClient.search(searchRequest);
//            System.out.println(response);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    private static void queryTest3() {
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        searchRequest.source(new SearchSourceBuilder());
//        try {
//            SearchResponse response = highClient.search(searchRequest);
//            SearchHits hits = response.getHits();
//            System.out.println(response);
//            System.out.println(hits);
//            SearchHit[] hits1 = hits.getHits();
//            for (int i = 0; i < hits1.length; i++) {
//                System.out.println(hits1[i]);
//            }
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    private static void queryTest4() {
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.from(0);
//        searchSourceBuilder.size(5000);
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        searchRequest.source(searchSourceBuilder);
//        try {
//            SearchResponse response = highClient.search(searchRequest);
//            SearchHits hits = response.getHits();
//            SearchHit[] hits1 = hits.getHits();
//            for (int i = 0; i < hits1.length; i++) {
//                System.out.println(hits1[i]);
//                Map<String, Object> sourceAsMap = hits1[i].getSourceAsMap();
//                Object OrgId = sourceAsMap.get("OrgId");
//                Object TrxTps = sourceAsMap.get("TrxTps");
//                System.out.println(OrgId);
//                System.out.println(TrxTps);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private static void queryTest5() {
//        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
//        sourceBuilder.from(0);
//        sourceBuilder.fetchSource(new String[]{"title"}, new String[]{});
//        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("SysTime");
//        rangeQueryBuilder.gte("201911150000");
//        rangeQueryBuilder.lt("201911160000");
//        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
////        boolBuilder.must(matchQueryBuilder);
////        boolBuilder.must(termQueryBuilder);
//        boolBuilder.must(rangeQueryBuilder);
//        sourceBuilder.query(boolBuilder);
//        SearchRequest searchRequest = new SearchRequest(index);
//        searchRequest.types(type);
//        searchRequest.source(sourceBuilder);
//        try {
//            SearchResponse response = highClient.search(searchRequest);
//            System.out.println(response);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//
//}
