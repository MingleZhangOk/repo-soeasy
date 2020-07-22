package com.mingzhang.java.spark.firstdemo;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @Description
 * @Classname XmlDemo
 * @date 2020-06-10 15:31
 */
public class XmlDemo {
    public static void main(String[] args) throws Exception {
        String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msgHeader><type>runjob</type><context>D:/logs/贴源数据文件-20200605/L_CORE_PHPF20_20200606.txt,hdfs://engine:9000/des/20200609/file_src/DBUNLOADER/L_CORE_PHPF20/ALL,|~*@</context></msgHeader>";

        Element rootEle = DocumentHelper.parseText(line).getRootElement();
        String type = rootEle.elementTextTrim("type");
        String context = rootEle.elementTextTrim("context");
        System.out.println(type + "======" + context);
    }
}
