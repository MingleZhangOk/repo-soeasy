package com.mingzhang.parser.test;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-10 17:32
 */
public class JavaObjectOperator {

    /**
     * import com.ql.util.express.test.OrderQuery;
     * 系统自动会import java.lang.*,import java.util.*;
     *
     *
     * query = new OrderQuery();//创建class实例,会根据classLoader信息，自动补全类路径
     * query.setCreateDate(new Date());//设置属性
     * query.buyer = "张三";//调用属性,默认会转化为setBuyer("张三")
     * result = bizOrderDAO.query(query);//调用bean对象的方法
     * System.out.println(result.getId());//静态方法
     */
    public static void test1(){

    }
}
