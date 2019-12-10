/*
package cn.com.mingzhang.test.table.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

*/
/**
 * hbase客户端连接工具类
 *//*

public class HbaseClientUtils {

    public static final String ZKhosts = "ZKhosts";
    public static final String ZKport = "ZKport";
    public static final String ZKparent = "parent";
    public static final String tableName = "hbase_tablename";
    public static final String nameSpace = "namespace";
    private static final String ZKdefaultPort = "2181";
    private static final String ZKdefaultParent = "/hbase";


    //连接超时时间默认300
    private static int sessionTimeout = 300;

    */
/**
     * zookeeper连接性测试
     *
     * @param fieldMap 从数据库中查询到的hbase配置内容，包含：
     *                 zookeeper的节点ip ，多台可以用“,”号分隔，如："172.16.60.41，172.16.60.42"
     *                 zookeeper的节点端口 ，默认："2181"
     *                 parentNode zookeeper中的hbase存放路径 ，默认："/hbase"
     * @return 若连接成功，则返回true，否则返回false
     *//*

    public static boolean checkZookeeperConnect(Map<String, String> fieldMap) {
        String hosts = fieldMap.get(ZKhosts);
        String port = fieldMap.get(ZKport);
        String parentNode = fieldMap.get(ZKparent);

        if ("".equals(port)) {
            port = ZKdefaultPort;
            fieldMap.put(ZKport, ZKdefaultPort);
        }
        if ("".equals(parentNode)) {
            parentNode = ZKdefaultParent;
            fieldMap.put(ZKparent, ZKdefaultParent);
        }
        String[] splitHosts = hosts.split(",");
        String zookeeperHosts = "";
        for (String host : splitHosts) {
            zookeeperHosts = zookeeperHosts + "," + host + ":" + port;
        }
        if ("".equals(zookeeperHosts)) {
            return false;
        }
        try {
            new ZooKeeper(zookeeperHosts, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println(event.getType() + "---" + event.getPath());
                }
            }).getChildren(parentNode, true);
            System.out.println("连接成功：" + zookeeperHosts);
            return true;
        } catch (Exception e) {
            System.out.println("连接失败：" + zookeeperHosts + "  错误信息：" + e.getMessage());
            return false;
        }
    }

    */
/**
     * 测试hbase中的表是否存在
     *
     * @param tableName hbase中的表名，如果非default命名空间，格式为：namespace:tableName
     * @param hbaseConf hbase连接的配置参数
     * @return 若表存在，则返回true，否则返回false
     *//*

    public static boolean checkTableIsExist(String tableName, Configuration hbaseConf) throws Exception {
        return ConnectionFactory.createConnection(hbaseConf).getAdmin().isTableAvailable(TableName.valueOf(tableName));
    }

    */
/**
     * 创建hbase 表
     *
     * @param tableName    hbase表名
     * @param columnfamily hbase列族
     * @param hbaseConf    hbase配置实例
     * @throws Exception
     *//*

    public static void createTableAndColumnfamily(String tableName, String columnfamily, Configuration hbaseConf) throws Exception {
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(columnfamily);
        hTableDescriptor.addFamily(hColumnDescriptor);
        ConnectionFactory.createConnection(hbaseConf).getAdmin().createTable(hTableDescriptor);
    }

    */
/**
     * 清空hbase表
     *
     * @param tableName    hbse表名
     * @param columnfamily hbase列族
     * @param hbaseConf    hbase配置实例
     * @throws Exception
     *//*

    public static void truncTable(String tableName, String columnfamily, Configuration hbaseConf) throws Exception {
        Admin admin = ConnectionFactory.createConnection(hbaseConf).getAdmin();

//        admin.disableTable(TableName.valueOf(tableName));
//        admin.truncateTable(TableName.valueOf(tableName), true);
//        admin.enableTable(TableName.valueOf(tableName));
        if (!admin.isTableDisabled(TableName.valueOf(tableName))) {
            admin.disableTable(TableName.valueOf(tableName));
        }
        admin.deleteTable(TableName.valueOf(tableName));

        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(columnfamily);
        hTableDescriptor.addFamily(hColumnDescriptor);
        admin.createTable(hTableDescriptor);
    }

    */
/**
     * 校验map中的字段是否为null或“”，如果错误，不进入rpc连接，节省时间和资源
     *
     * @param fieldMap hbase配置信息map
     * @return 若校验成功，则范围true，否则返回false
     *//*

    public static boolean checkMapField(Map<String, String> fieldMap) {
        if (null != fieldMap.get(ZKhosts) &&
                null != fieldMap.get(ZKport) &&
                null != fieldMap.get(ZKparent) &&
                null != fieldMap.get(tableName) &&
                StringUtils.isNotBlank(fieldMap.get(tableName)) &&
                StringUtils.isNotBlank(fieldMap.get(ZKhosts))) {
            return true;
        } else {
            if (StringUtils.isBlank(fieldMap.get(tableName))) {
                System.out.println(tableName + "为空");
            }
            if (StringUtils.isBlank(fieldMap.get(ZKhosts))) {
                System.out.println(ZKhosts + "为空");
            }
            return false;
        }
    }

    */
/**
     * 获取hbase配置实例
     *
     * @param checkedMap 经过校验的字段map
     * @return 返回hbase配置实例
     *//*

    public static Configuration getHbaseConfiguration(Map<String, String> checkedMap) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        conf.set("zookeeper.znode.parent", "/" + checkedMap.get(ZKparent));
        conf.set("hbase.zookeeper.quorum", checkedMap.get(ZKhosts));
        //conf.set("hbase.zookeeper.quorum", "172.16.60.41");
        conf.set("hbase.zookeeper.property.clientPort", checkedMap.get(ZKport));
        conf.set("hbase.client.pause", "5000");
        conf.set("hbase.client.retries.number", "1");
        conf.set("hbase.rpc.timeout", "60000");
        conf.set("hbase.client.operation.timeout", "60000");
        conf.set("hbase.client.scanner.timeout.period", "60000");
        conf.set("zookeeper.recovery.retry", "2");
        conf.set("ipc.socket.timeout", "60000");
        conf.set("zookeeper.recovery.retry.intervalmill", "5000");

//        String hbaseTable_Name = checkedMap.get(tableName);
//        if (StringUtils.isNotBlank(checkedMap.get(nameSpace))) {
//            hbaseTable_Name = checkedMap.get(nameSpace) + ":" + hbaseTable_Name;
//        }
//        conf.set(TableInputFormat.INPUT_TABLE, hbaseTable_Name);
        //checkTableIsExist(hbaseTable_Name, conf);
        return conf;
    }

    public static List<String> getHbaseColumnFamilieAsList(String tableName, Configuration hbaseConf) throws Exception {
        Connection hbaseConn = ConnectionFactory.createConnection(hbaseConf);
        Table table = hbaseConn.getTable(TableName.valueOf(tableName));
        HTableDescriptor tableDescriptor = table.getTableDescriptor();
        ArrayList<String> columnFamilieList = new ArrayList<>();

        for (HColumnDescriptor fdescriptor : tableDescriptor.getColumnFamilies()) {
            columnFamilieList.add(fdescriptor.getNameAsString());
        }

        columnFamilieList.forEach(System.out::println);
        System.out.println(tableDescriptor);
        return columnFamilieList;
    }

    public static List<String> getHbaseColumnAsList(String tableName, String columnfamily, Configuration hbaseConf) throws Exception {
        Connection hbaseConn = ConnectionFactory.createConnection(hbaseConf);
        Table hbaseTable = hbaseConn.getTable(TableName.valueOf(tableName));
        ResultScanner scanner = hbaseTable.getScanner(Bytes.toBytes(columnfamily));
        //ResultScanner HbaseTableScanner = hbaseTable.getScanner(new Scan());
        for (Result nextResult : scanner) {
            System.out.println(nextResult);
        }


        //Result nextResult = HbaseTableScanner.next();
        // System.out.println(nextResult);
        // System.out.println(nextResult);

        return null;
    }
}
*/
