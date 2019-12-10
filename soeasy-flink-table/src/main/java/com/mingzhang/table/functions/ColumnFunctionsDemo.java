package com.mingzhang.table.functions;

/**
 * 列功能
 * 列功能用于选择或取消选择表列。
 * <p>
 * 句法	数据中心
 * withColumns（…）	选择指定的列
 * 没有专栏（...）	取消选择指定的列
 * 详细语法如下：
 * <p>
 * columnFunction:
 * withColumns(columnExprs)
 * withoutColumns(columnExprs)
 * <p>
 * columnExprs:
 * columnExpr [, columnExpr]*
 * <p>
 * columnExpr:
 * columnRef | columnIndex to columnIndex | columnName to columnName
 * <p>
 * columnRef:
 * columnName(The field name that exists in the table) | columnIndex(a positive integer starting from 1)
 * 下表说明了column函数的用法。（假设我们有一个包含5列的表格：）(a: Int, b: Long, c: String, d:String, e: String)：
 * withColumns（*）| *
 * select("withColumns(*)") | select("*") = select("a, b, c, d, e")
 * 所有列
 * withColumns（m至n）
 * select("withColumns(2 to 4)") = select("b, c, d")
 * 从m到n的列
 * withColumns（m，n，k）
 * select("withColumns(1, 3, e)") = select("a, c, e")
 * m，n，k列
 * withColumns（m，n至k）
 * select("withColumns(1, 3 to 5)") = select("a, c, d ,e")
 * 上面两种表示的混合
 * 无列（m至n）
 * select("withoutColumns(2 to 4)") = select("a, e")
 * 取消选择从m到n的列
 * 没有列（m，n，k）
 * select("withoutColumns(1, 3, 5)") = select("b, d")
 * 取消选择列m，n，k
 * 没有列（m，n至k）
 * select("withoutColumns(1, 3 to 5)") = select("b")
 * 上面两种表示的混合
 * 列函数可以在所有需要列字段的地方使用，select, groupBy, orderBy, UDFs etc.例如：
 * <p>
 * table
 * .groupBy("withColumns(1 to 3)")
 * .select("withColumns(a to b), myUDAgg(myUDF(withColumns(5 to 20)))")
 */
public class ColumnFunctionsDemo {
    public static void main(String[] args) {
        String columnSQL = "列功能暂时不支持sql";


        FunctionDemoMain.runFunction(columnSQL);
    }
}
