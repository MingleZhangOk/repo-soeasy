/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: Student
 * Author:   h
 * Date:     2018/11/8 17:30
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.mingzhang.table.tests.other_test;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author h
 * @create 2018/11/8
 * @since 1.0.0
 */
public class Student {
    public int stuid;
    public String stuname;
    public String stuaddr;
    public String stusex;
    public Integer count;

    public Student() {
    }

    public Student(int stuid, String stuname, String stuaddr, String stusex) {
        this.stuid = stuid;
        this.stuname = stuname;
        this.stuaddr = stuaddr;
        this.stusex = stusex;
        this.count = count;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStuaddr() {
        return stuaddr;
    }

    public void setStuaddr(String stuaddr) {
        this.stuaddr = stuaddr;
    }

    public String getStusex() {
        return stusex;
    }

    public void setStusex(String stusex) {
        this.stusex = stusex;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                ", stuaddr='" + stuaddr + '\'' +
                ", stusex='" + stusex + '\'' +
                ", count=" + count +
                '}';
    }
}