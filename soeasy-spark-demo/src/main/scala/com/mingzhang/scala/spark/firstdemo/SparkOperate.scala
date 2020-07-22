package com.mingzhang.scala.spark.firstdemo

import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.LoggerFactory

import scala.io.Source

class SparkOperate extends Serializable {

  val LOG = LoggerFactory.getLogger(classOf[SparkOperate])

  var localFileDelimiter: String = _
  var hdfsFileDelimiter: String = _

  def uploadLocalFile2HDFS(localFile: String, hdfsFile: String, replaceFlag: Boolean): Long = {
    var numLines = 0l
    try {
      val str = System.getProperty("file.encoding")
      val spark = SparkContext.getOrCreate(new SparkConf().setAppName("file2Hdfs").setMaster("local[*]"))
      if (replaceFlag) {
        val value = spark.parallelize(Source.fromFile(localFile, str).getLines.toList).map(_.replace(localFileDelimiter, hdfsFileDelimiter))
        numLines = value.count()
        value.saveAsTextFile(hdfsFile)
      } else {
        val value = spark.parallelize(Source.fromFile(localFile, str).getLines.toList)
        numLines = value.count()
        value.saveAsTextFile(hdfsFile)
      }

      LOG.info("本次文件卸数共处理数据: " + numLines + " 条")
    } catch {
      case exception: Exception => LOG.info("本次文件卸数出现异常:" + exception.getMessage + "，文件数据: " + numLines + " 条")
    }
    numLines
  }

}