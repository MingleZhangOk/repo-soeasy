/*
package cn.com.mingzhang.test.table.util

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.hbase._
import org.apache.hadoop.hbase.client._

object HbaseOperatorUtil {

  def getHbaseAdmin(hbaseConf: Configuration): Admin = {
    ConnectionFactory.createConnection(hbaseConf).getAdmin
  }

  def getHbaseConfiguration(zookeeper_hosts: String, port: String = "2181"): Configuration = {
    val hbaseConf = HBaseConfiguration.create()
    hbaseConf.set(HConstants.ZOOKEEPER_QUORUM, zookeeper_hosts)
    hbaseConf.set("hbase.zookeeper.property.clientPort", port);
    hbaseConf
  }

  def checkExistsOrCreateTable(admin: HBaseAdmin, tablename: String): Unit = {
    if (!admin.isTableAvailable(tablename)) {
      val tableDesc = new HTableDescriptor(TableName.valueOf(tablename))
      admin.createTable(tableDesc)
    }
  }

  //  def getHbaseInputJobConfiguration(hbaseConf: Configuration, sc: SparkContext): RDD = {
  //    val hBaseRDD = sc.newAPIHadoopRDD (hbaseConf, classOf[TableInputFormat],
  //    classOf[org.apache.hadoop.hbase.io.ImmutableBytesWritable],
  //    classOf[org.apache.hadoop.hbase.client.Result] )
  //    hBaseRDD
  //  }

}
*/
