package com.mingzhang.scala.spark.firstdemo

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{DataTypes, Metadata, StructField, StructType}
import org.apache.spark.sql.{Row, SQLContext, SparkSession}

import scala.collection.mutable.ListBuffer
import scala.io.Source


/**
  * File Description:
  *
  * @author MingZhang                      --Variety is the spice of life.
  * @Description
  * @Classname SparkSqlTest
  * @date 2020-06-11 16:38
  */
object SparkSqlTest {
  var filePath = "file:///D:/logs/贴源数据文件-20200605/L_CORE_PHPF20_20200605.txt"
  var localFilePath = "D:/logs/贴源数据文件-20200605/L_CORE_PHPF20_20200605.txt"
  var oldDelimiter = "|~*@"
  var oldSpliter = "\\|~\\*@"
  var testDelimiter = ","

  def main(args: Array[String]): Unit = {
    //    getFieldsCount
    testSql()
  }

  def testSql(): Unit = {
    val count = getFieldsCount
    var arrLength: Int = 0
    //    val fileRdd = readTextFileRdd(getSparkContext)
    val fileRdd = readTextFileRdd(getSparkContext, getSystemEncoding)

    val fileRddToRow = fileRdd
      .filter(_.length != 0)
      .map(_.replace(oldDelimiter, testDelimiter))
      .map(_.split(testDelimiter))
      //    val fileRddToRow = fileRdd.map(_.split(oldSpliter))
      //      .map(arr => {
      //      if (arrLength == 0) {
      //        arrLength = arr.length
      //        println(arrLength)
      //      }
      //      arr
      //    })
      .map(Row.fromSeq(_))
    val sqlContext = getSparkSqlContext
    import sqlContext.implicits._

    val fields = new ListBuffer[StructField]()
    for (i <- 1 to count) {
      fields.append(StructField.apply("id" + i, DataTypes.StringType, false, Metadata.empty))
    }
    val dataFrame = sqlContext.createDataFrame(fileRddToRow, StructType.apply(fields)).toDF()
    dataFrame.createOrReplaceTempView("file_table")
    val l = dataFrame.count()
    print(l)
    val frame = sqlContext.sql("select * from file_table order by id12 desc limit 3")
    frame.printSchema()
    frame.show()
  }

  def getSparkContext: SparkContext = {
    SparkContext.getOrCreate(new SparkConf(true).setMaster("local[*]").setAppName("test"))
  }

  def getSparkSqlContext: SQLContext = SparkSession.builder.getOrCreate.sqlContext

  def readTextFileRdd(spark: SparkContext, encoding: String): RDD[String] = spark.parallelize(Source.fromFile(localFilePath, encoding).getLines.toList)

  def getSystemEncoding: String = System.getProperty("file.encoding")

  def readTextFileRdd(spark: SparkContext): RDD[String] = spark.textFile(filePath)


  def getFieldsCount: Int = {
    val encoding = System.getProperty("file.encoding")
    println("系统字符集为：" + encoding)
    val line = Source.fromFile(localFilePath, encoding).getLines.find(_ => true).get
    println(line)
    //    val lines = "JX60|~*@JX6002|~*@保证金存款定期三个月|~*@0|~*@3|~*@0|~*@M3|~*@3|~*@0|~*@2|~*@0|~*@Y|~*@D|~*@0|~*@0|~*@4|~*@1|~*@|~*@|~*@|~*@25101|~*@|~*@|~*@1|~*@52110002|~*@|~*@"

    //    println(new String(line.getBytes(), 0, line.length, encoding))
    //    val line1 = line.replace(oldDelimiter, testDelimiter)
    val length = line.split(oldSpliter).length
    //    println(line1)
    println(length)
    length
  }

}
