package com.mingzhang.parser.test;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-10 17:32
 */
public class OperatorAndObject {
    /**
     * 普通java语法
     * 支持 +,-,*,/,<,>,<=,>=,==,!=,<>【等同于!=】,%,mod【取模等同于%】,++,--,
     * in【类似sql】,like【sql语法】,&&,||,!,等操作符
     * 支持for，break、continue、if then else 等标准的程序控制逻辑
     *  n=10;
     *  for(sum=0,i=0;i<n;i++){
     *      sum=sum+i;
     *  }
     *  return sum;
     *
     *  逻辑三元操作
     * a=1;
     * b=2;
     * maxnum = a>b?a:b;
     */
    public static void test1(){

    }

    /**
     * 不支持try{}catch{}
     * 不支持java8的lambda表达式
     * 不支持for循环集合操作for (GRCRouteLineResultDTO item : list)
     * 弱类型语言，请不要定义类型声明,更不要用Templete（Map<String,List>之类的）
     * array的声明不一样
     * min,max,round,print,println,like,in 都是系统默认函数的关键字，请不要作为变量名
     *
     * //java语法：使用泛型来提醒开发者检查类型
     * keys = new ArrayList<String>();
     * deviceName2Value = new HashMap<String,String>(7);
     * String[] deviceNames = {"ng","si","umid","ut","mac","imsi","imei"};
     * int[] mins = {5,30};
     *
     * //ql写法：
     * keys = new ArrayList();
     * deviceName2Value = new HashMap();
     * deviceNames = ["ng","si","umid","ut","mac","imsi","imei"];
     * mins = [5,30];
     *
     * //java语法：对象类型声明
     * FocFulfillDecisionReqDTO reqDTO = param.getReqDTO();
     * //ql写法：
     * reqDTO = param.getReqDTO();
     *
     * //java语法：数组遍历
     * for(GRCRouteLineResultDTO item : list) {
     * }
     * //ql写法：
     * for(i=0;i<list.size();i++){
     * item = list.get(i);
     * }
     *
     * //java语法：map遍历
     * for(String key : map.keySet()) {
     *   System.out.println(map.get(key));
     * }
     * //ql写法：
     *   keySet = map.keySet();
     *   objArr = keySet.toArray();
     *   for (i=0;i<objArr.length;i++) {
     *   key = objArr[i];
     *    System.out.println(map.get(key));
     *   }
     */
    public static void test2(){

    }

}
