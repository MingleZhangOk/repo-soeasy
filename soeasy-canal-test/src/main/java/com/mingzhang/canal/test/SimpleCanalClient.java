package com.mingzhang.canal.test;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-05-08 9:56
 */
public class SimpleCanalClient {

    private CanalConnector connector=null;

    public SimpleCanalClient(String ip,String port,String instance) {

        // 创建链接
        connector = CanalConnectors.newSingleConnector(new InetSocketAddress(ip, Integer.parseInt(port)),instance, "", "");
    }
    public List<Entry> execute(int batchSize,Class<?> clazz ) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException {

        //int batchSize = 1;
        int emptyCount = 0;
        Object obj = clazz.newInstance();
        Method method = clazz.getMethod("send",Message.class);
        try {
            connector.connect();
            // connector.subscribe(".*\\..*");
            connector.subscribe("test.test1");

            connector.rollback();
            int totalEmptyCount = 120;
            while (emptyCount < totalEmptyCount) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    emptyCount++;
                    System.out.println("empty count : " + emptyCount);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                } else {
                    emptyCount = 0;
                    method.invoke(obj, message);
                }
                connector.ack(batchId); // 提交确认

                // connector.rollback(batchId); // 处理失败, 回滚数据
            }

            System.out.println("empty too many times, exit");
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }
        return null;
    }
}
