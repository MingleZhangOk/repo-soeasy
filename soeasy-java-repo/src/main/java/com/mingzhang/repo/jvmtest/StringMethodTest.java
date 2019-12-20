package com.mingzhang.repo.jvmtest;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @date 2019-12-20 23:43
 */
public class StringMethodTest {

    public static final String A = "ab"; // 常量A

    public static final String B = "cd"; // 常量B

    public static final String C; // 常量A
    public static final String D;    // 常量B
    static {
        C = "ab";
        D = "cd";
    }

    public static void test1(){
        String a= "no";
        String b ="no";
//运行时常量池相对于CLass文件常量池的另外一个重要特征是具备动态性，Java语言并不要求常量一定只有编译期才能产生，也就是并非预置入CLass文件中常量池的内容才能进入方法区运行时常量池，运行期间也可能将新的常量放入池中，这种特性被开发人员利用比较多的就是String类的intern()方法。
//String的intern()方法会查找在常量池中是否存在一份equal相等的字符串,如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池。
        String intern = a.intern();
        String intern1 = b.intern();
        System.out.println(intern);
        System.out.println(intern1);
        System.out.println(a.equals(b));
        System.out.println(a.equalsIgnoreCase(b));
        System.out.println(a==b);
        //   常量池的好处
        //常量池是为了避免频繁的创建和销毁对象而影响系统性能，其实现了对象的共享。
        //例如字符串常量池，在编译阶段就把所有的字符串文字放到一个常量池中。
        //（1）节省内存空间：常量池中所有相同的字符串常量被合并，只占用一个空间。
        //（2）节省运行时间：比较字符串时，==比equals()快。对于两个引用变量，只用==判断引用是否相等，也就可以判断实际值是否相等。
    }

    public  static void test2(){
        String s1 = "Hello";
      String s2 = "Hello";
      String s3 = "Hel" + "lo";
      String s4 = "Hel" + new String("lo");
      String s5 = new String("Hello");
      String s6 = s5.intern();
      String s7 = "H";
      String s8 = "ello";
      String s9 = s7 + s8;

       System.out.println(s1 == s2);  // true
       System.out.println(s1 == s3);  // true
       System.out.println(s1 == s4);  // false
       System.out.println(s1 == s9);  // false
       System.out.println(s4 == s5);  // false
       System.out.println(s1 == s6);  // true

        //  首先说明一点，在java 中，直接使用==操作符，比较的是两个字符串的引用地址，并不是比较内容，比较内容请用String.equals()。
        //
        //     s1 == s2这个非常好理解，s1、s2在赋值时，均使用的字符串字面量，说白话点，就是直接把字符串写死，在编译期间，这种字面量会直接放入class文件的常量池中，从而实现复用，载入运行时常量池后，s1、s2指向的是同一个内存地址，所以相等。
        //
        //     s1 == s3这个地方有个坑，s3虽然是动态拼接出来的字符串，但是所有参与拼接的部分都是已知的字面量，在编译期间，这种拼接会被优化，编译器直接帮你拼好，因此String s3 = "Hel" + "lo";在class文件中被优化成String s3 = "Hello"，所以s1 == s3成立。只有使用引号包含文本的方式创建的String对象之间使用“+”连接产生的新对象才会被加入字符串池中。
        //
        //     s1 == s4当然不相等，s4虽然也是拼接出来的，但new String("lo")这部分不是已知字面量，是一个不可预料的部分，编译器不会优化，必须等到运行时才可以确定结果，结合字符串不变定理，鬼知道s4被分配到哪去了，所以地址肯定不同。对于所有包含new方式新建对象（包括null）的“+”连接表达式，它所产生的新对象都不会被加入字符串池中。
    }

    public static void test3() {
        String s = A + B;  // 将两个常量用+连接对s进行初始化
        String t = "abcd";
        if (s == t) {
            System.out.println("s等于t，它们是同一个对象");
        } else {
            System.out.println("s不等于t，它们不是同一个对象");
        }
    }

    public static void test4() {
        // 将两个常量用+连接对s进行初始化
        String s = C + D;
        String t = "abcd";
        if (s == t) {
            System.out.println("s等于t，它们是同一个对象");
        } else {
            System.out.println("s不等于t，它们不是同一个对象");
        }
        //我们可以得出三个非常重要的结论：
        //           必须要关注编译期的行为，才能更好的理解常量池。
        //           运行时常量池中的常量，基本来源于各个class文件中的常量池。
        //           程序运行时，除非手动向常量池中添加常量(比如调用intern方法)，否则jvm不会自动添加常量到常量池。
    }

    public static void main(String[] args) {
        test4();
    }

}
