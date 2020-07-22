package com.mingzhang.java.spark.firstdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @Description
 * @Classname FileClientListener
 * @date 2020-06-10 10:11
 */
public class FileClientListener extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(FileClientListener.class);
    private static final int CLIENT_PORT = 9600;
    private ServerSocket ss;

    @Override
    public void run() {
        try {
            ss = new ServerSocket(CLIENT_PORT);

            while (true) {
                new TaskOperate(ss.accept()).start();
            }
        } catch (Exception e) {
            LOG.error("socket队列处理失败！", e);
        }
    }

}
