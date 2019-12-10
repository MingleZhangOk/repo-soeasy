package com.mingzhang.table.functions;

/**
 *MD5(string)
 * 以32个十六进制数字的字符串形式返回string的MD5哈希值；如果string为NULL，则返回NULL。
 *
 * SHA1(string)
 * 以40个十六进制数字的字符串形式返回string的SHA-1哈希值；如果string为NULL，则返回NULL。
 *
 * SHA224(string)
 * 以56个十六进制数字的字符串形式返回字符串的SHA-224哈希值；如果string为NULL，则返回NULL。
 *
 * SHA256(string)
 * 以64位十六进制数字的字符串形式返回string的SHA-256哈希值；如果string为NULL，则返回NULL。
 *
 * SHA384(string)
 * 以96个十六进制数字的字符串形式返回string的SHA-384哈希值；如果string为NULL，则返回NULL。
 *
 * SHA512(string)
 * 以128个十六进制数字的字符串形式返回string的SHA-512哈希值；如果string为NULL，则返回NULL。
 *
 * SHA2(string, hashLength)
 * 使用SHA-2系列哈希函数（SHA-224，SHA-256，SHA-384或SHA-512）返回哈希。第一个参数字符串是要进行哈希处理的字符串，第二个参数hashLength是结果的位长（224、256、384或512）。如果string或hashLength为NULL，则返回NULL。
 */
public class HashFunctionsDemo {
    public static void main(String[] args) {
        String hashSQL="";


        FunctionDemoMain.runFunction(hashSQL);
    }
}
