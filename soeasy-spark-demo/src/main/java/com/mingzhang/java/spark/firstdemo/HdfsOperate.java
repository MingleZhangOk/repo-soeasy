package com.mingzhang.java.spark.firstdemo;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class HdfsOperate implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(HdfsOperate.class);

    private static Configuration conf;

    public void initHdfs() {
        ClassLoader classLoader = HdfsOperate.class.getClassLoader();
//        Properties prop = new Properties();
//        prop.load(classLoader.getResourceAsStream("conf.properties"));
        conf = new Configuration();
        conf.addResource(classLoader.getResourceAsStream("hdfs-site.xml"));
        conf.addResource(classLoader.getResourceAsStream("core-site.xml"));
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
        LOG.info("集群登录认证。。。。");
        //集群认证
        System.setProperty("HADOOP_USER_NAME", "root");
        LOG.info("登录成功...");
    }

    public void deleteHdfsFile(String path) throws Exception {
        FileSystem fileSystem = null;
        Path hdfsPath = new Path(path);
        try {
            fileSystem = FileSystem.get(conf);
            if (fileSystem.exists(hdfsPath)) {
                fileSystem.delete(hdfsPath, true);
            }
        } catch (Exception e) {
            throw new Exception("hdfs文件执行删除失败。。。", e);
        } finally {
            if (fileSystem != null) {
                fileSystem.close();
            }
        }

    }

    synchronized boolean uploadLocalFile2HDFS(String localFile, String hdfsFile) throws Exception {
        if (StringUtils.isBlank(localFile) || StringUtils.isBlank(hdfsFile)) {
            return false;
        }
        FileSystem fileSystem = FileSystem.get(conf);
        Path src = new Path(localFile);
        Path dst = new Path(hdfsFile);
        fileSystem.copyFromLocalFile(false, src, dst);//true为删除源文件
        fileSystem.close();
        return true;
    }
}
