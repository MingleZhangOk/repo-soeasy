package com.mingzhang.scala.helper

/**
  * File Description:
  *
  * @author MingZhang                      --Variety is the spice of life.
  * @Description
  * @Classname LoveHelper
  * @date 2020-06-08 10:33
  */
class LoveHelper private(who: String) {

  def love(beLoved: String) = {
    beLoved match {
      case LoveHelper.You => println(who + " Love " + beLoved)
      case _ => println("You don't love " + beLoved)
    }

  }
}

object LoveHelper {

  val I = "I"

  val You = "You"

  implicit def conver(who: String) = new LoveHelper(who)

}
