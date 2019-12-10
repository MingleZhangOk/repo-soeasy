package com.mingzhang.table.functions;

/**
 * CAST(value AS type)
 * 返回一个新值，存在投键入类型。请在此处查看受支持的类型。
 * <p>
 * 例如，CAST('42' AS INT)返回42；CAST(NULL AS VARCHAR)返回VARCHAR类型的NULL。
 */
public class TypeConversionFunctionsDemo {

    public static void main(String[] args) {
        String typeConversionSQL = "";


        FunctionDemoMain.runFunction(typeConversionSQL);
    }
}
