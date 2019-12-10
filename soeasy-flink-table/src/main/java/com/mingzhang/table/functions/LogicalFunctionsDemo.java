package com.mingzhang.table.functions;

/**
 * boolean1 OR boolean2
 * 如果boolean1为TRUE或boolean2为TRUE，则返回TRUE。支持三值逻辑。
 *
 * 例如，TRUE OR UNKNOWN返回TRUE。
 *
 * boolean1 AND boolean2
 * 如果boolean1和boolean2均为TRUE，则返回TRUE。支持三值逻辑。
 *
 * 例如，TRUE AND UNKNOWN返回未知。
 *
 * NOT boolean
 * 如果布尔值为FALSE，则返回TRUE ；否则，返回TRUE 。如果布尔值为TRUE，则返回FALSE ；否则为false 。如果布尔值为UNKNOWN，则返回UNKNOWN。
 *
 * boolean IS FALSE
 * 如果布尔值为FALSE，则返回TRUE ；否则，返回TRUE 。如果布尔值为TRUE或UNKNOWN，则返回FALSE 。
 *
 * boolean IS NOT FALSE
 * 如果布尔值为TRUE或UNKNOWN，则返回TRUE ；如果布尔值为FALSE，则返回FALSE。
 *
 * boolean IS TRUE
 * 如果布尔值为TRUE，则返回TRUE；否则，返回TRUE。如果布尔值为FALSE或UNKNOWN，则返回FALSE 。
 *
 * boolean IS NOT TRUE
 * 如果布尔值为FALSE或UNKNOWN，则返回TRUE；否则为false。如果布尔值为TRUE，则返回FALSE 。
 *
 * boolean IS UNKNOWN
 * 如果布尔值为UNKNOWN，则返回TRUE ；否则，返回TRUE 。如果布尔值为TRUE或FALSE，则返回FALSE。
 *
 * boolean IS NOT UNKNOWN
 * 如果布尔值为TRUE或FALSE，则返回TRUE ；如果布尔值为UNKNOWN，则返回FALSE 。
 */
public class LogicalFunctionsDemo {
    public static void main(String[] args) {
        String logicalSQL="";




        FunctionDemoMain.runFunction(logicalSQL);
    }
}
