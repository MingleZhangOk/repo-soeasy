package com.mingzhang.scala.helper

import DateHelper._

/**
  * File Description:
  * 返回结果：
  *
  * two days ago is Fri May 31 22:16:58 CST 2013
  * two days after is Tue Jun 04 22:16:58 CST 2013
  * 事实上，"2 days ago" 相当于调用 2.days(ago), 因为Scala的方法中的点号可以省略，括号也可以省略，所以看起来就像DSL了。
  *
  * 也许有朋友会问，那这个"ago"怎么来的？好问题！我们看DateHelper的伴生对象，里面不是有定义了一个ago变量么，变量的值就是"ago", 然后导入DateHelper._时，将其也导入进来了，所以可以直接访问。其实就是 2.days("ago"), 换汤不换药而已。
  *
  * 调用DateHelper中的days方法时，我们用到了模式匹配，只有当传入的参数值为DateHelper.ago和DateHelper.from_now时，我们才进行处理，其他情况一律返回当前日期。
  *
  * 很简单吧，但是真的很强大！现在，我们在看一个" I Love You"的效果。当代码中写入"I Love You" 时，程序打印出"I love You"
  *
  * @author MingZhang                      --Variety is the spice of life.
  * @Description
  * @Classname Sample
  * @date 2020-06-08 10:29
  */
object Sample {

  def main(args: Array[String]): Unit = {

    val twoDayAgo = 2 days ago
    val twoDayAfter = 2 days from_now
    println("two days ago is " + twoDayAgo)
    println("two days after is " + twoDayAfter)

  }

}
