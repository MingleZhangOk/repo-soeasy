package com.vova.parser.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author WangYang - vova
 * @version Create in 3:52 下午 2020/8/3
 */


public class DemoParser {
    private static  final Logger logger = LoggerFactory.getLogger(DemoParser.class);

    public static void main(String[] args) {

//        unitTest();

        try {
            demoMain();
        } catch (Exception e) {
            logger.error("DemoParser-main,异常",e);
        }


    }

    private static void unitTest() {
        String v1 = "33.00";
        String v2 = "3.24";

        try {
            System.out.println(compare(v1, v2, "="));
            System.out.println(compare(v1, v2, "!="));
            System.out.println(compare(v1, v2, ">"));
            System.out.println(compare(v1, v2, ">="));
            System.out.println(compare(v1, v2, "<"));
            System.out.println(compare(v1, v2, "<="));

            System.out.println(handleMath(v1, v2, "+"));
            System.out.println(handleMath(v1, v2, "-"));
            System.out.println(handleMath(v1, v2, "*"));
            System.out.println(handleMath(v1, v2, "/"));

            System.out.println("*********");

            System.out.println(handleLogical("true", "true", "&&"));
            System.out.println(handleLogical("false", "false", "&&"));
            System.out.println(handleLogical("true", "true", "||"));
            System.out.println(handleLogical("false", "false", "||"));
            System.out.println(handleLogical("true", "false", "&&"));
            System.out.println(handleLogical("true", "false", "||"));
        } catch (Exception e) {
            logger.error("DemoParser-unitTest,异常",e);
        }
    }

    public static void demoMain() throws Exception{


//        String parserStr = "
//        ( $age > 23 )
//        &&
//        ( $age < 88 )
//        &&
//        444 isNull";
//        String parserStr = "( $age > 22 ) && ( $name = 'xiaoling' )";
        /**符号之间需要用空格隔开，便于分割*/
//        String parserStr = "( ( $age > 0 ) || ( $age 大于 30 ) ) && ( ( 100 < 50 ) || ( 0 < -100 ) ) || ( ( 1 + 1 )  > 1 ) ";

        /**不带括号的业务逻辑*/
//        String parserStr = " $age + $age * $age ";
//        String parserStr = " ( ( 100 < 50 ) || ( 0 < -100 ) ) || ( ( 1 + 1 ) > 1 ) ";

        /**数字计算的相关逻辑*/
        String parserStr = "( $age ) *  ( 1 * 2 - ( 1 * 1 ) + ( 100 ) ) ";

        String[] subParserStr = parserStr.split(" ");
        int length = subParserStr.length;

        List<String> strList = new LinkedList<String>();

        for (int i = 0; i < length; i++) {
            String subStr = subParserStr[i];
            if (subStr.length() > 0) {
                strList.add(subStr);
            }
        }

        /**DEBUG 打印*/
        printDebug(strList);

        /** 1 、替换参数，这里需要重新处理*/
        //TODO 替换标签和字段值
        for (int i=0;i<strList.size();i++) {

            if (strList.get(i).startsWith("$")) {
                strList.set(i,"32");
            }

        }

        /**打印*/
        printDebug(strList);

        /** 语义处理,括号处理*/

        /**堆栈数据结构遇到括号，优先出栈处理*/
        Stack<String> stringStackBracket = new Stack<String>();

        /**子逻辑*/
        List<String> subHandleString = new ArrayList<String>();

        if (strList.contains(")")) {
            for (String s : strList) {
                if (!s.equals(")")) {
                    stringStackBracket.push(s);
                    System.out.println("stack ---- "+stringStackBracket.toString());
                }else{
                    String popStr=null;
                    while (!(popStr = stringStackBracket.pop()).equals("(")){
                        subHandleString.add(0,popStr);
                    }
                    System.out.println("subHandleString ---- "+subHandleString.toString());
                    String subRes = handleSubLogical(subHandleString);
                    stringStackBracket.push(subRes);
                    subHandleString.clear();

                    System.out.println("stack ---- "+stringStackBracket.toString());
                }
            }
        }else{
            for (String s : strList) {
                stringStackBracket.push(s);
            }
        }
        System.out.println("stack ---- "+stringStackBracket.toString());

        System.out.println(handleSubLogical(stringStackBracket));
    }

    /**
     * 核心业务逻辑
     * 处理子逻辑的逻辑，递归调用，注意堆栈情况
     * @param subHandleString
     * @return
     */
    private static String handleSubLogical(List<String> subHandleString) throws Exception{


        if (subHandleString.contains("*")){
            int index = subHandleString.indexOf("*");

            String v1 = subHandleString.get(index-1);
            String v2 = subHandleString.get(index+1);

            subHandleString.remove(index + 1);
            subHandleString.remove(index );
            subHandleString.remove(index - 1);

            subHandleString.add(index - 1, handleMath(v1, v2, SymbolConstant.MULTIPLY));

        } else if (subHandleString.contains("/")) {
            int index = subHandleString.indexOf("/");

            String v1 = subHandleString.get(index-1);
            String v2 = subHandleString.get(index+1);

            subHandleString.remove(index + 1);
            subHandleString.remove(index );
            subHandleString.remove(index - 1);

            subHandleString.add(index - 1, handleMath(v1, v2, SymbolConstant.DIVIDE));


        }

        if (subHandleString.size() == 1) {
            return subHandleString.get(0);

        }else if(subHandleString.size() == 2){
            String v1 = subHandleString.get(0);
            String handleType = subHandleString.get(1);
            if (SymbolMap.NoneSym.containsKey(handleType)) {
                return handleNoneValue(v1, handleType).toString();
            }

        } else if (subHandleString.size() == 3) {
            String v1 = subHandleString.get(0);
            String v2 = subHandleString.get(2);
            String handleType = subHandleString.get(1);

            if (SymbolMap.mathSym.containsKey(handleType)) {
                return handleMath(v1, v2, handleType);

            } else if (SymbolMap.compareSym.containsKey(handleType)) {
                return compare(v1, v2, handleType);

            } else if (SymbolMap.logicalSym.containsKey(handleType)) {
                return handleLogical(v1, v2, handleType).toString();
            }
        }else{

            List<String> firstString = subHandleString.subList(0, 3);
            List<String> othersString = subHandleString.subList(4,subHandleString.size());
            String handleType = subHandleString.get(3);
            String v1 = handleSubLogical(firstString);
            String v2 = handleSubLogical(othersString);

            if (SymbolMap.mathSym.containsKey(handleType)) {
                return handleMath(v1, v2, handleType);

            } else if (SymbolMap.compareSym.containsKey(handleType)) {
                return compare(v1, v2, handleType);

            } else if (SymbolMap.logicalSym.containsKey(handleType)) {
                return handleLogical(v1, v2, handleType).toString();

            }
        }

        return "";
    }


    /**
     * 打印
     * @param subParserStr
     */
    private static void printDebug(List<String> subParserStr) throws Exception{
        for (String s : subParserStr) {
            if (s.length() == 0) {
                continue;
            }
            System.out.println("" + s);
        }

        System.out.println("==================");
    }

    public static String compare(String v1, String v2, String symbolStr) throws Exception{

        String symbol = SymbolMap.compareSym.get(symbolStr);
        if (symbol == null || symbol.length() == 0) {
            throw new RuntimeException("比较类符号不存在");
        }


        BigDecimal bv1 = new BigDecimal(v1);
        BigDecimal bv2 = new BigDecimal(v2);

        int resCompare = bv1.compareTo(bv2);

        if (resCompare == 1) {
            if (">".equals(symbol) || ">=".equals(symbol) || "!=".equals(symbol)) {
                return "true";
            }

        } else if (resCompare == -1) {
            if ("<".equals(symbol) || "<=".equals(symbol) || "!=".equals(symbol)) {
                return "true";
            }

        } else if (resCompare == 0) {
            if ("=".equals(symbol) || "<=".equals(symbol) || ">=".equals(symbol)) {
                return "true";
            }
        }
        return "false";
    }

    /**
     * 处理数学运算
     * @author WangYang - vova
     * @version Create in 5:09 下午 2020/8/3
     */
    public static String handleMath(String v1,String v2,String symbolStr) throws Exception{

        String symbol = SymbolMap.mathSym.get(symbolStr);
        if (symbol == null || symbol.length() == 0) {
            throw new RuntimeException("数字处理类符号不存在");
        }

        BigDecimal bv1 = new BigDecimal(v1);
        BigDecimal bv2 = new BigDecimal(v2);

        if (SymbolConstant.ADD.equals(symbol)) {
            return bv1.add(bv2).toString();

        } else if (SymbolConstant.SUBTRACT.equals(symbol)) {
            return bv1.subtract(bv2).toString();

        } else if (SymbolConstant.MULTIPLY.equals(symbol)) {
            return bv1.multiply(bv2).toString();

        } else if (SymbolConstant.DIVIDE.equals(symbol)) {
            return bv1.divide(bv2).toString();

        }else{
            throw new RuntimeException("异常");
        }

    }

    /**
     * 处理逻辑运算
     * @author WangYang - vova
     * @version Create in 5:09 下午 2020/8/3
     */
    public static Boolean handleLogical(String str1,String str2,String symbolStr) throws Exception{

        String symbol = SymbolMap.logicalSym.get(symbolStr);

        if (symbol == null || symbol.length() == 0) {
            throw new RuntimeException("逻辑类符号不存在");
        }



        Boolean b1 = Boolean.valueOf(str1);
        Boolean b2 = Boolean.valueOf(str2);
        if (SymbolConstant.OR.equals(symbol)) {
            return b1 || b2;

        } else if (SymbolConstant.AND.equals(symbol)) {
            return b1 && b2;
        }

        return false;
    }

    /**
     * 空值校验
     * @author WangYang - vova
     * @version Create in 5:09 下午 2020/8/3
     */
    public static Boolean handleNoneValue(String str1,String symbolStr) throws Exception{

        String symbol = SymbolMap.NoneSym.get(symbolStr);

        if (symbol == null || symbol.length() == 0) {
            throw new RuntimeException("值校验符号不存在");
        }

        if (SymbolConstant.ISNULL.equals(symbol)) {
            return str1 == null || str1.equals("null");

        } else if (SymbolConstant.ISNOTNULL.equals(symbol)) {
            return str1 != null || !str1.equals("null");
        }

        return false;
    }

}
