package com.mingzhang.scala.spark.firstdemo

import org.apache.spark.{SparkConf, SparkContext}

/**
  * File Description:
  *
  * @author MingZhang                      --Variety is the spice of life.
  * @Description
  * @Classname Demo1
  * @date 2020-06-11 15:32
  */
object Demo1 {

  def main(args: Array[String]): Unit = {
    var line = "JXF0|~*@JXF022|~*@按季等本减息22年|~*@1|~*@5|~*@0|~*@Y22|~*@22|~*@0|~*@1|~*@0|~*@M|~*@D|~*@0|~*@2|~*@2|~*@1|~*@|~*@|~*@|~*@|~*@|~*@|~*@1|~*@|~*@|~*@"
    println(line.split("\\|~\\*@").length)
    println(line)

  }

}
