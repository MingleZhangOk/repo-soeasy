package com.mingzhang.scala.spark.firstdemo

import java.io.File

import com.mingzhang.java.spark.firstdemo.HdfsOperate
import org.apache.hadoop.conf.Configuration
import org.apache.spark.sql.types._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{Row, SparkSession}
import org.slf4j.LoggerFactory

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.io.Source

/**
  * File Description:
  *
  * @author MingZhang                      --Variety is the spice of life.
  * @Description
  * @Classname SparkTest
  * @date 2020-06-10 13:40
  */
class SparkTest {

  def initHdfs() {
    val loader = SparkTest.getClass.getClassLoader
    //        Properties prop = new Properties();
    //        prop.load(classLoader.getResourceAsStream("conf.properties"));
    SparkTest.conf = new Configuration();
    SparkTest.conf.addResource(loader.getResourceAsStream("hdfs-site.xml"));
    SparkTest.conf.addResource(loader.getResourceAsStream("core-site.xml"));
    SparkTest.conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
    SparkTest.LOG.info("集群登录认证。。。。");
    //集群认证
    System.setProperty("HADOOP_USER_NAME", "root");
    SparkTest.LOG.info("登录成功...");
  }

  def uploadLocalFile2HDFS(localFile: String, hdfsFile: String): Boolean = {
    //    val sc = SparkContext.getOrCreate(new SparkConf().setAppName("file2Hdfs").setMaster("local[*]"))
    val sqlContext = SparkSession.builder.getOrCreate.sqlContext
    sqlContext.read.load()

    return true
  }


}

object SparkTest {

  var filePath = "file:///D:/logs/贴源数据文件-20200605/L_CORE_PHPF20_20200606.txt"
  var localFilePath = "D:/logs/贴源数据文件-20200605/L_CORE_PHPF20_20200606.txt"
  var oldDelimiter = "|~*@"
  var newDelimiter = "|**|"
  var testDelimiter = ","
  var numLines = 0l

  def main(args: Array[String]): Unit = {
    test8()
  }

  def test8(): Unit = {
    val encoding = System.getProperty("file.encoding")
    val spark = SparkContext.getOrCreate(new SparkConf(true).setMaster("local[*]").setAppName("test"))
    //    val fileRdd = spark.textFile(filePath).map(_.replace(oldDelimiter, testDelimiter)).map(_.split(testDelimiter)).map(Row.fromSeq(_))
    val fileRdd = spark.parallelize(Source.fromFile(localFilePath, encoding).getLines.toList).map(_.replace(oldDelimiter, testDelimiter)).map(_.split(testDelimiter)).map(Row.fromSeq(_))
    val sparkSql = SparkSession.builder.getOrCreate.sqlContext

    var fields = new ListBuffer[StructField]()
    for (i <- 1 to test7()) {
      fields.append(StructField.apply("id" + i, DataTypes.StringType, true, Metadata.empty))
    }
    val value = StructType.apply(fields)
    val dataFrame = sparkSql.createDataFrame(fileRdd, value)
    //    sparkSql.sql("select * from " + dataFrame)
    dataFrame.printSchema()
    dataFrame.show()
    //    val count = dataFrame.count()
    //    println("数据一共： " + count)
  }


  def test7(): Int = {
    val str = System.getProperty("file.encoding")
    println("系统字符集为：" + str)
    var line = "JXE0|~*@JXE024|~*@按月等本减息24年|~*@1|~*@5|~*@0|~*@Y24|~*@24|~*@0|~*@1|~*@0|~*@M|~*@D|~*@0|~*@1|~*@1|~*@1|~*@|~*@|~*@|~*@|~*@|~*@|~*@1|~*@|~*@|~*@"
    println(new String(line.getBytes(), 0, line.length, str))
    val line1 = line.replace(oldDelimiter, testDelimiter)
    val length = line1.split(testDelimiter).length
    println(line1)
    println(length)
    return length
  }

  def test6(): Unit = {
    println(File.separator)
    val spark = SparkContext.getOrCreate(new SparkConf().setAppName("file2Hdfs").setMaster("local[*]"))
    val value = spark.textFile("/test").filter(_ != null).map(_.replace(newDelimiter, oldDelimiter))
    numLines = value.count()
    value.repartition(1).saveAsTextFile("file:///D:/logs/save_path/" + System.currentTimeMillis())
    println(numLines)
  }

  def test5(): Unit = {
    val str = System.getProperty("file.encoding")
    val list = Source.fromFile(localFilePath, str).getLines()
    for (line <- list) {
      println(line)
    }
  }

  def test4(): Unit = {
    deleteFile()
    val str = System.getProperty("file.encoding")
    val spark = SparkContext.getOrCreate(new SparkConf().setAppName("file2Hdfs").setMaster("local[*]"))
    val value = spark.parallelize(Source.fromFile(localFilePath, str).getLines.toList).map(_.replace(oldDelimiter, newDelimiter))
    numLines = value.count()
    value.saveAsTextFile("/test")
    println(numLines)
  }

  def test3(): Unit = {
    val str = System.getProperty("file.encoding")
    val spark = SparkContext.getOrCreate(new SparkConf().setAppName("file2Hdfs").setMaster("local[*]"))

    val value = spark.textFile(filePath).map(line => new String(line.getBytes(), 0, line.length, str)).map(_.replace(oldDelimiter, newDelimiter))
    numLines = value.count()
    value.saveAsTextFile("/test")

    println(numLines)

  }

  def test2(): Unit = {
    val spark = SparkContext.getOrCreate(new SparkConf().setAppName("file2Hdfs").setMaster("local[*]"))
    val sqlContext = SparkSession.builder.master("local").getOrCreate.sqlContext
    val lines = spark.textFile(filePath).map(_.split(oldDelimiter)).map(splits => Row(ArrayBuffer[String]() ++= splits)).toJavaRDD()
  }

  def test1(): Unit = {
    val spark = SparkContext.getOrCreate(new SparkConf().setAppName("file2Hdfs").setMaster("local[*]"))
    val sqlContext = SparkSession.builder.master("local").getOrCreate.sqlContext

    import sqlContext.implicits._

    val frame = sqlContext.read.format("com.databricks.spark.csv")
      .option("header", "true").option("delimiter", oldDelimiter).textFile(filePath)
    //    val dfframe = frame.toDF("id", "name", "age")
    frame.printSchema()
    //    dfframe.printSchema()
    frame.show(20)
  }

  def deleteFile(): Unit = {
    val hdfsOperate = new HdfsOperate
    hdfsOperate.initHdfs()
    hdfsOperate.deleteHdfsFile("hdfs://engine:9000/test")
  }

  val LOG = LoggerFactory.getLogger(classOf[SparkTest])

  var conf: Configuration = null

}
