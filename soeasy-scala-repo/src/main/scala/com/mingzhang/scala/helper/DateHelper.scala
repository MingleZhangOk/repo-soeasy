package com.mingzhang.scala.helper

import java.util.Calendar
import java.util.Date

/**
  * File Description:
  * 最近看了下 Scala程序设计，觉得里面的隐式类型转换蛮有趣的，可以写出DSL的效果。这篇小文就是想分享下如何写出类似" 2 days ago"和“2 days from_now"这样的代码。为了好玩点，我也会写一个" I Love You"这样的DSL，并且然后程序可以正常编译。首先看看如何写成”2 days ago" 这样的效果。请看如下代码：
  * 我们定义了一个名为helper的package，然后package里面定义一个private类型的DateHelper类以及其伴生对象。在伴生对象中，我们注意到有一个convertInt2Date方法，并且用"implicit"做了修饰。用"implicit"修改的方法很特殊，它会在类被导入时自动执行，并且将其参数进行隐式转换，如代码中将Int类型转换为了DateHelper类型。 这样当我们调用Int类型的方法时 会自动变为DateHelper类型。看看如何使用这个DateHelper就明白了。
  * @author MingZhang                      --Variety is the spice of life.
  * @Description
  * @Classname DateHelper
  * @date 2020-06-08 10:29
  */
class DateHelper private(number: Int) {

  def days(when: String): Date = {
    var date = Calendar.getInstance()
    when match {
      case DateHelper.ago => date.add(Calendar.DAY_OF_MONTH, -number)
      case DateHelper.from_now => date.add(Calendar.DAY_OF_MONTH, number)
      case _ => date
    }
    date.getTime()
  }
}

object DateHelper {
  val ago = "ago"
  val from_now = "from_now"

  implicit def convertInt2Date(number: Int) = new DateHelper(number)
}