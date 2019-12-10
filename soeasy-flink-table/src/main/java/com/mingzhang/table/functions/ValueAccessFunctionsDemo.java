package com.mingzhang.table.functions;

/**
 * tableName.compositeType.field
 * 按名称返回Flink复合类型（例如Tuple，POJO）中的字段的值。
 *
 * tableName.compositeType.*
 * 返回Flink复合类型（例如Tuple，POJO）的平面表示形式，该复合类型将其每个直接子类型转换为单独的字段。在大多数情况下，平面表示形式的字段与原始字段的命名方式相似，但带有美元分隔符（例如mypojo$mytuple$f0）。
 */
public class ValueAccessFunctionsDemo {
    public static void main(String[] args) {
        String valueAccessSQL="";


        FunctionDemoMain.runFunction(valueAccessSQL);
    }
}
