package com.mingzhang.table.impl.elastic;

import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;

public class ElasticsearchApiTest {

    static String host = "192.168.56.102";
    static int port = 9300;

    public static void main(String[] args) throws Exception {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", true) //增加嗅探机制，找到ES集群
                .put("cluster.name", "my-application")  // 设置集群名称
                .put("thread_pool.search.size", 20)// 增加线程池个数，暂时设为20
                .build();
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName(host), port);

        TransportClient transportClient = new PreBuiltTransportClient(settings).addTransportAddresses(transportAddress);
        String index = "test";
        String doc = "_doc";
        String id = "_oItGG4BB1G7j9MTsqNf";
        System.out.println("--------------------");
        GetRequestBuilder getRequestBuilder = transportClient.prepareGet(index, doc, id);
//        SearchRequestBuilder searchRequestBuilder = transportClient.prepareSearch();
        System.out.println(getRequestBuilder.get().getSource());
        System.out.println("--------------------");
    }
}
