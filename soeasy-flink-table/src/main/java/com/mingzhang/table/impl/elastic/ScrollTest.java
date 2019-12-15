package com.mingzhang.table.impl.elastic;

import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.transport.TransportRequestOptions;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2019-12-14 14:02
 */
public class ScrollTest {

    private static String servers = "192.168.56.102";
    private static String port = "9200";
    private static String index = "my-application";
    private static String type = "_doc";
    private static RestHighLevelClient highClient;

    public static void main(String[] args) throws Exception {
        int iport = Integer.valueOf(port);

        final List<HttpHost> httpHosts = Arrays.asList(servers.split(","))
                .stream()
                .map(host -> new HttpHost(host, iport, "http"))
                .collect(Collectors.toList());

        highClient = getHighClient(httpHosts);
        scroll();
    }

    private static RestHighLevelClient getHighClient(List<HttpHost> list) throws Exception {
        RestClientBuilder builder = RestClient.builder(list.toArray(new HttpHost[list.size()]));
        RestHighLevelClient rhlClient = new RestHighLevelClient(builder);
        if (!rhlClient.ping()) {
            throw new Exception("确保ES地址是否正确");
        }
        return rhlClient;
    }


    public static void scroll() {
        RestHighLevelClient client = highClient;
        //初始化scroll
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L)); //设定滚动时间间隔
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(matchAllQuery());
        searchSourceBuilder.size(5); //设定每次返回多少条数据
        searchRequest.source(searchSourceBuilder);
        BasicHeader basicHeader = new BasicHeader("", "");
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, basicHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String scrollId = searchResponse.getScrollId();
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        System.out.println("-----首页-----");
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit.getSourceAsString());
        }
        //遍历搜索命中的数据，直到没有数据
        while (searchHits != null && searchHits.length > 0) {
            SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
            scrollRequest.scroll(scroll);
            try {
                searchResponse = client.searchScroll(scrollRequest, basicHeader);
//                searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
            scrollId = searchResponse.getScrollId();
            searchHits = searchResponse.getHits().getHits();
            if (searchHits != null && searchHits.length > 0) {
                System.out.println("-----下一页-----");
                for (SearchHit searchHit : searchHits) {
                    System.out.println(searchHit.getSourceAsString());
                }
            }

        }
        //清除滚屏
        ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
        clearScrollRequest.addScrollId(scrollId);//也可以选择setScrollIds()将多个scrollId一起使用
        ClearScrollResponse clearScrollResponse = null;
        try {
            clearScrollResponse = client.clearScroll(clearScrollRequest, basicHeader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean succeeded = clearScrollResponse.isSucceeded();
        System.out.println("succeeded:" + succeeded);
    }
}
