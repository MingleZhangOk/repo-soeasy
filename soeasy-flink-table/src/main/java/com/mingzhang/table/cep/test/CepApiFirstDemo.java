package com.mingzhang.table.cep.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternSelectFunction;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.PatternTimeoutFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.OutputTag;

import java.util.List;
import java.util.Map;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @email zhangming0392@163.com
 * @date 2020-08-13 18:55
 */
public class CepApiFirstDemo {


    /**
     * 简介
     *        flink cep 是flink的复杂处理库。主要用来筛选，流式数据中，符合条件的某个系列动作。譬如筛选出连续的告警，或者是用户生成订单后，规定时间内没有完成支付等。
     *
     *        一般cep都用于KeyedStream。
     *
     * 一般开发流程
     * 生成一个datastream或者KeyedStream
     * 定义一组规则
     * 将这组规则应用于流，生成PatternStream
     * 将生成的PatternStream，通过select方法，将符合规则的数据通过自定义的输出形式，生成结果流
     * 结果流就是我们最终得到的数据
     * Demo
     *         这个例子是判断订单是否正常，譬如说，订单的有效时间是30分钟，所以要在30分钟内完成支付，才算一次正常的支付。如果超过了30分钟，用户依然发起了支付动作，这个时候就是有问题的，要发出一条指令告诉用户该订单已经超时。
     *
     *      模拟数据，mock用户的订单行为：
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        /**
         *  接收source并将数据转换成一个tuple
         */
        DataStream<Tuple3<String, String, String>> myDataStream = env.addSource(new MySource()).map(new MapFunction<String, Tuple3<String, String, String>>() {
            @Override
            public Tuple3<String, String, String> map(String value) throws Exception {

                JSONObject json = JSON.parseObject(value);
                return new Tuple3<>(json.getString("userid"), json.getString("orderid"), json.getString("behave"));
            }
        });


        /**
         * 定义一个规则
         * 接受到behave是order以后，下一个动作必须是pay才算符合这个需求
         */
        Pattern<Tuple3<String, String, String>, Tuple3<String, String, String>> myPattern = Pattern.<Tuple3<String, String, String>>begin("start").where(new IterativeCondition<Tuple3<String, String, String>>() {
            @Override
            public boolean filter(Tuple3<String, String, String> value, Context<Tuple3<String, String, String>> ctx) throws Exception {
                System.out.println("进入首个规则过滤条件ORDER-----------------------------------------");
                System.out.println("value:" + value);
                boolean order = value.f2.equals("order");
                System.out.println("order:" + order);
                return order;
            }
        }).next("next").where(new IterativeCondition<Tuple3<String, String, String>>() {
            @Override
            public boolean filter(Tuple3<String, String, String> value, Context<Tuple3<String, String, String>> ctx) throws Exception {
                System.out.println("进入第二个规则过滤条件PAY-----------------------------------------");
                System.out.println("value:" + value);
                boolean pay = value.f2.equals("pay");
                System.out.println("pay:" + pay);
                return pay;
            }
        }).within(Time.seconds(3));


        PatternStream<Tuple3<String, String, String>> pattern = CEP.pattern(myDataStream.keyBy(0), myPattern);

        //记录超时的订单
        OutputTag<String> outputTag = new OutputTag<String>("myOutput") {
        };

        SingleOutputStreamOperator<String> resultStream = pattern.select(outputTag,
                /**
                 * 超时的
                 */
                new PatternTimeoutFunction<Tuple3<String, String, String>, String>() {
                    @Override
                    public String timeout(Map<String, List<Tuple3<String, String, String>>> pattern, long timeoutTimestamp) throws Exception {
                        System.out.println("进入第一个模式匹配-----------------------------------------");
                        System.out.println("pattern:" + pattern);
                        List<Tuple3<String, String, String>> startList = pattern.get("start");
                        System.out.println("startList:" + startList);
                        Tuple3<String, String, String> tuple3 = startList.get(0);
                        System.out.println("tuple3:" + tuple3);
                        return tuple3.toString() + "迟到的";
                    }
                }, new PatternSelectFunction<Tuple3<String, String, String>, String>() {
                    @Override
                    public String select(Map<String, List<Tuple3<String, String, String>>> pattern) throws Exception {
                        System.out.println("进入第二个模式匹配-----------------------------------------");
                        //匹配上第一个条件的
                        List<Tuple3<String, String, String>> startList = pattern.get("start");
                        System.out.println("startList:" + startList);
                        //匹配上第二个条件的
                        List<Tuple3<String, String, String>> endList = pattern.get("next");
                        System.out.println("endList:" + endList);

                        Tuple3<String, String, String> tuple3 = endList.get(0);
                        System.out.println("tuple3:" + tuple3);

                        return tuple3.toString();
                    }
                }
        );

        //输出匹配上规则的数据
        resultStream.print();

        //输出超时数据的流
        DataStream<String> sideOutput = resultStream.getSideOutput(outputTag);
        sideOutput.print();


        /**
         * 发现点：
         * 1.对于超时数据来说(匹配上了一个条件，但是在规定时间内，下一条数据没有匹配上第二个条件)，他只有等到下一条数据来了，才会判断上一条数据是否超时了。而不是等到时间窗口到了，就立即判断这条数据是否超时。
         *
         * 2.上面例子中的 next("next") 可以替换成followedByAny("next")或者是followedBy("next")。
         *
         * 这里涉及到两个概念 ，next是严格邻近。其他的是非严格邻近。
         *
         * 严格邻近：表示当前数据流中，所匹配的数据必须是严格一前一后的，中间没有其他数据。譬如上述的，来了3条数据，A数据：userid = 1,orderid=2,behave=create，B数据：userid = 1,orderid=2,behave=create，C数据：userid = 1,orderid=2,behave=pay
         *
         * 都是在3秒内来的。这个时候，匹配上规则的是B和C。
         *
         * 非严格邻近：表示在当前数据流中，所匹配的数据可以不用一前一后，中间允许有其他数据。那么这个时候，匹配上规则的数据就有两组，分别是A和C  以及  B和C。
         *
         * followedByAny 和 followedBy的区别：
         *
         * 模式为begin("first").where(_.name='a').followedBy("second").where(.name='b')
         *
         * 当数据为 a,c,b,b的时候，followedBy输出的模式是a,b   而followedByAny输出的模式是{a,b},{a,b}两组。
         */
        env.execute("Test CEP");


    }

}
