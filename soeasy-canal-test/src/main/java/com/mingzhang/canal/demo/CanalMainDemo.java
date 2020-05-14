package com.mingzhang.canal.demo;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.protocol.Message;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2020-05-08 9:40
 */
public class CanalMainDemo {
    /**
     * 客户端入口方法
     *
     * @param args
     */
    public static void main(String[] args) {
        // 加载配置文件
        String host = GlobalConfigUtil.canalHost;
        int port = Integer.parseInt(GlobalConfigUtil.canalPort);
        String instance = GlobalConfigUtil.canalInstance;
        String username = GlobalConfigUtil.mysqlUsername;
        String password = GlobalConfigUtil.mysqlPassword;

        // 获取Canal连接
        CanalConnector conn = CanalClient.getConn(host, port, instance, username, password);

        // 从binlog中读取数据
        int batchSize = 100;
        int emptyCount = 1;

        try {
            conn.connect();
            conn.subscribe(".*\\..*");
            conn.rollback();

            int totalCount = 120; //循环次数

            while (totalCount > emptyCount) {
                // 获取数据
                Message message = conn.getWithoutAck(batchSize);

                long id = message.getId();
                int size = message.getEntries().size();
                if (id == -1 || size == 0) {
                    //没有读取到任何数据
                } else {
                    //有数据，那么解析binlog日志
                    CanalClient.analysis(message.getEntries(), emptyCount);
                    emptyCount++;
                }
                // 确认消息
                conn.ack(message.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }
}
