package com.mingzhang.scala.helper

import LoveHelper._

/**
  * File Description:
  * 运行结果为：
  * I Love You
  * You don't love marry
  * 如果明白了DateHelper的例子，这个就非常简单了。这个代码是地String类型做了隐式转换，转换为LoveHelper对象，然后调用里面的love方法。
  *
  * Scala真的非常强大，可以写成简单易读的DSL代码出来，为我们的coding life增加了不少色彩，大家也可以玩一玩哦！
  *
  * @author MingZhang                      --Variety is the spice of life.
  * @Description
  * @Classname LoveSample
  * @date 2020-06-08 10:36
  */
object LoveSample {

  def main(args: Array[String]): Unit = {

    I love You

    I love "marry"

  }

}
