package com.mingzhang.table.tests.readData_Tools; /**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: JsonRead_Tools
 * Author:   h
 * Date:     2018/11/30 10:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*

package cn.com.frms.com.mingzhang.table.tests.readData_Tools;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

public class JsonRead_Tools {
    */
/**
     * @param args
     *//*

    public static void main(String[] args) {
        // 读取nameID.txt文件中的NAMEID字段（key）对应值（value）并存储
        ArrayList<String> list = new ArrayList<String>();
        BufferedReader brname;
        try {
            brname = new BufferedReader(new FileReader("src/json/nameID.txt"));// 读取NAMEID对应值
            String sname = null;
            while ((sname = brname.readLine()) != null) {
                // System.out.println(sname);
                list.add(sname);// 将对应value添加到链表存储
            }
            brname.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // 读取原始json文件并进行操作和输出
        try {
            BufferedReader br = new BufferedReader(new FileReader(
                    "D:\\testData\\input.json"));// 读取原始json文件
            BufferedWriter bw = new BufferedWriter(new FileWriter(
                    "D:\\testData\\output.json"));// 输出新的json文件
            String s = null, ws = null;
            while ((s = br.readLine()) != null) {
                // System.out.println(s);
                try {
                    JSONObject dataJson = JSONObject.fromObject(s);
                    Object string = dataJson.get("string");
                    JSONArray features = dataJson.getJSONArray("features");// 找到features的json数组
                    for (int i = 0; i < features.length(); i++) {
                        JSONObject info = features.getJSONObject(i);// 获取features数组的第i个json对象
                        JSONObject properties = info.getJSONObject("properties");// 找到properties的json对象
                        String name = properties.getString("name");// 读取properties对象里的name字段值
                        System.out.println(name);
                        properties.put("NAMEID", list.get(i));// 添加NAMEID字段
                        // properties.append("name", list.get(i));
                        System.out.println(properties.getString("NAMEID"));
                        properties.remove("ISO");// 删除ISO字段
                    }
                    ws = dataJson.toString();
                    System.out.println(ws);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            bw.write(ws);
            // bw.newLine();

            bw.flush();
            br.close();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}*/
