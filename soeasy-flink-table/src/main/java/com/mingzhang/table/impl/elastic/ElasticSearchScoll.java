package com.mingzhang.table.impl.elastic;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.List;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2019-12-17 17:57
 */
public class ElasticSearchScoll {

    //    private static String esHost = "172.22.4.223,172.22.4.224,172.22.4.231";
    private static String esHost = "172.22.4.219,172.22.4.220,172.22.4.221";
    private static int esPort = 9300;
    private static String index = ".monitoring-es-6-2019.12.17";
    private static String type = "doc";
    private static int size = 5000;
    //总量331769,size =5000
    //scroll 模式启动！耗时: 270399
    //from size 模式启动！耗时: 470996

    //总量331769,size =1000
    //from size 模式启动！耗时: 197108
    //scroll 模式启动！耗时: 183795

    //总量331769,size =3000
    //from size 模式启动！耗时: 173329
    //scroll 模式启动！耗时: 172830

    //总量331769,size =5000
    //scroll 模式启动！耗时:
    //from size 模式启动！耗时:

    public static void main(String[] args) throws Exception {

        Settings settings = Settings.builder()
                .put("cluster.name", "my-application")
                .put("client.transport.sniff", true)// 开启ES嗅探功能，确保集群连上多个节点
                .build();

        // 创建客户端
        PreBuiltTransportClient transportClient = new PreBuiltTransportClient(settings);
        // 添加es的节点信息，可以添加1个或多个

        String[] esHosts = esHost.split(",");
        for (int i = 0; i < esHosts.length; i++) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(esHosts[i], esPort);
//        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(esHost), esPort);
            TransportAddress transportAddress = new TransportAddress(inetSocketAddress);
            transportClient.addTransportAddresses(transportAddress);
        }

//        InetSocketAddress inetSocketAddress = new InetSocketAddress(esHost, esPort);
////        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(esHost), esPort);
//        TransportAddress transportAddress = new TransportAddress(inetSocketAddress);
//        transportClient.addTransportAddresses(transportAddress);

        // 连接到的节点
        List<DiscoveryNode> discoveryNodes = transportClient.connectedNodes();
        for (DiscoveryNode discoveryNode : discoveryNodes) {
            System.out.println(discoveryNode.getHostAddress());
        }

        System.out.println("from size 模式启动！");
        Date begin = new Date();

        SearchRequestBuilder builder = transportClient.prepareSearch(index);
//        builder.setQuery(QueryBuilders.matchQuery("age", 35));
        SearchResponse res = builder.get();
        long count = res.getHits().getTotalHits();

//        long count = transportClient.prepareCount(index).setTypes(type).execute().actionGet().getCount();//获取所有记录
        SearchRequestBuilder requestBuilder = transportClient.prepareSearch(index).setTypes(type).setQuery(QueryBuilders.matchAllQuery());
        for (int i = 0, sum = 0; sum < count; i++) {
            SearchResponse response = requestBuilder.setFrom(i).setSize(size).execute().actionGet();
            sum += response.getHits().getHits().length;
            System.out.println("总量" + count + " 已经查到" + sum);
        }
        Date end = new Date();
        System.out.println("耗时: " + (end.getTime() - begin.getTime()));


        System.out.println("scroll 模式启动！");
        begin = new Date();
        SearchResponse scrollResponse = transportClient.prepareSearch(index)
//                .setSearchType(type)   //在ES 5.x版本中不存在SearchType.SCAN用法，可以用addSort(SortBuilders.fieldSort("_doc"))
//                .addSort(SortBuilders.fieldSort(type))
                .setSize(size) //实际返回的数量为size*index的主分片个数（在ES 5.x版本中，返回的数据量就是参数中指定的数据量）
                .setScroll(TimeValue.timeValueMinutes(8))
                .execute().actionGet();
        count = scrollResponse.getHits().getTotalHits();//获取所有记录，第一次不返回数据(在ES 5.x版本中，第一次有数据返回)
        for (int sum = size; sum < count; ) {
            scrollResponse = transportClient.prepareSearchScroll(scrollResponse.getScrollId())
                    .setScroll(TimeValue.timeValueMinutes(8))
                    .execute().actionGet();
            sum += scrollResponse.getHits().getHits().length;
            System.out.println("总量" + count + " 已经查到" + sum + ",取数据长度为" + scrollResponse.getHits().getHits().length);
            if (scrollResponse.getHits().getHits().length == 0) {
                System.out.println("取数长度为0，取数结束");
                break;
            }
        }
        end = new Date();
        System.out.println("耗时: " + (end.getTime() - begin.getTime()));

    }
}
