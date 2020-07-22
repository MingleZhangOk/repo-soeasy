package com.mingzhang.java.spark.firstdemo;

import com.mingzhang.scala.spark.firstdemo.SparkOperate;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class TaskOperate extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(TaskOperate.class);

    private Socket socket;

    public TaskOperate(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter out = null;
        String type = null;
        String context = null;
        String line;

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            out = new PrintWriter(socket.getOutputStream(), true);

            while ((line = br.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    continue;
                }
                LOG.info("接收到报文：" + line);
                try {
                    // 解析报文
                    Element rootEle = DocumentHelper.parseText(line).getRootElement();
                    type = rootEle.elementTextTrim("type");
                    context = rootEle.elementTextTrim("context");
                } catch (Exception e) {
                    out.println("fail");// 参数校验失败
                    LOG.error("报文解析失败...", e);
                }
                //  out.println("success");// 参数校验成功

                try {

                    if ("testConnect".equals(type)) {
                        LOG.info("测试连接成功");
                        out.println("success");
                        continue;
                    }

                    if (StringUtils.isBlank(context)) {
                        LOG.info("context=" + context);
                        out.println("File path is empty ,Please check this file path!!!");
                        continue;
                    }

                    if ("checkFileExists".equals(type)) {
                        LOG.info("校验文件是否存在，文件路径为：" + context);
                        if (isDateOfRolling(context)) {
                            LOG.info("文件为日期滚动目录：" + context + "  ，跳过校验！返回true");
                            out.println("true");
                            continue;
                        }
                        File file = new File(context);
                        if (file.exists()) {
                            LOG.info(context + "文件存在，返回true");
                            out.println("true");
                            continue;
                        } else {
                            LOG.info(context + "文件不存在，返回false");
                            out.println("false");
                            continue;
                        }
                    }
                    if ("fileLineSize".equals(type)) {
                        LOG.info("读取字段长度，文件路径为：" + context);

                        String[] strings = context.split(",");

                        if (strings.length == 2) {
                            context = strings[0];
                            FileClientStarter.delimiter = strings[1];
                        }

                        if (isDateOfRolling(context)) {
                            LOG.info("文件为日期滚动目录：" + context + "  ，跳过字段提取！返回1");
                            out.println("1");
                            continue;
                        }
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(context))));
                        String lineContext = bufferedReader.readLine();
                        LOG.info("读取文件第一行，内容为：" + lineContext);
                        int length = lineContext.split(FileClientStarter.delimiter).length;
                        LOG.info("文件切分符号为\"" + FileClientStarter.delimiter + "\"-，切分字段成功，数量=" + length + "返回该数值.");
                        out.println("" + length);
                        bufferedReader.close();
                    }

                } catch (Exception e) {
                    LOG.error("解析任务实例或数据源出错：" + context, e);
                }
                try {
                    if ("runjob".equals(type) && context != null) {
                        String[] fields = context.split(",");
                        String localFile = fields[0];
                        String hdfsFile = fields[1];

                        SparkOperate sparkOperate = new SparkOperate();
                        sparkOperate.hdfsFileDelimiter_$eq(FileClientStarter.delimiter);
                        sparkOperate.localFileDelimiter_$eq(FileClientStarter.delimiter);

                        boolean replaceFlag = false;

                        if (fields.length == 3) {
                            sparkOperate.localFileDelimiter_$eq(fields[2]);
                            replaceFlag = true;
                        }

//                        HdfsOperate hdfsOperate = new HdfsOperate();
//                        hdfsOperate.initHdfs();
//                        LOG.info("删除hdfs文件,hdfsFile=" + hdfsFile);
//                        hdfsOperate.deleteHdfsFile(hdfsFile);
                        LOG.info("开始上传文件:localFile=" + localFile + ",hdfsFile=" + hdfsFile);
//                        boolean bool = hdfsOperate.uploadLocalFile2HDFS(localFile, hdfsFile);
                        long uploadFileSize = sparkOperate.uploadLocalFile2HDFS(localFile, hdfsFile, replaceFlag);
                        if (uploadFileSize != 0) {
                            LOG.info("上传完成,数据量为: " + uploadFileSize + " 条");
                        } else {
                            LOG.info("上传失败.");
                        }
                        out.println("" + uploadFileSize);
                    }
                } catch (Exception e) {
                    LOG.error("任务接收处理失败：", e);
                    out.println(e.getMessage());
                }
            }

            Thread.sleep(1000);
            LOG.info("当前没有可执行任务.");

        } catch (Exception e) {
            if (out != null) {
                out.print("fail");// 参数校验失败
            }
            LOG.error("socket接收线程加载异常：", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOG.error("socket BufferedReader closing failed！", e);
                }
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    LOG.error("socket closing failed !", e1);
                }
            }
        }
    }

    private boolean isDateOfRolling(String context) {
        return (context.contains("${") && context.contains("}") && context.lastIndexOf("${") + 2 < context.lastIndexOf("}"));
    }

}
