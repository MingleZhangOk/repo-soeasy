package com.mingzhang.java.spark.firstdemo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SocketDemo {

    private static String host="172.16.60.74";

    private static String[] types = {"testConnect", "checkFileExists", "fileLineSize", "runjob"};
    private static String[] contexts = {"f345dc93288c4c6d8370ac018d069d55", "E:\\Protected_Repository\\HA_ZZ_201810_YuXin_\\GitHere\\des_code\\桌面\\spark.txt", "", "runjob"};

    private static String type = types[0];
    private static String context = contexts[1];

    public static void main(String[] args) throws Exception {
        //testConn
        //sendMessage(types[1], "");
        //runjob
        //   sendMessage(types[3], "D:\\logs\\spark.txt,hdfs://yuxin/des/20190721/file/DBUNLOADER/TEST_FILE/ALL");
        //checkFileExists
        //sendMessage(types[2], "D:\\logs\\spark.txt");

        fileLineSize();
    }

    private static void fileLineSize() {
        String filePath = "D:\\logs\\part-00000";
        String execXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msgHeader>" +
                "<type>fileLineSize</type>" +
                "<context>" + filePath + "</context></msgHeader>";
        String feedback = exeCmdToServer(host, 9600, execXml);
        System.out.println("接收到反馈信息，内容为：" + feedback);
    }

    private static void sendMessage(String type, String context) {
        String line = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><msgHeader><type>" + type + "</type><context>" + context + "</context></msgHeader>";
        System.out.println("发送报文，内容为：" + line);
        String s = exeCmdToServer(host, 9600, line);
        System.out.println("接收到反馈信息，内容为：" + s);
    }


    public static SocketChannel getConnect(String hostName, int port) {
        SocketChannel sc = null;
        try {
            sc = SocketChannel.open();
            sc.configureBlocking(false);
            if (!sc.connect(new InetSocketAddress(hostName, port))) {
                while (!sc.finishConnect()) {
                    System.out.println("等待连接！！");
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sc;
    }

    /**
     * 发送命令给服务端
     *
     * @param sc
     * @param exeCmd
     */
    public static void sendCmdToServer(SocketChannel sc, String exeCmd) {
        System.out.println("执行命令：" + exeCmd);
        try {
            sc.write(ByteBuffer.wrap(exeCmd.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("执行命令异常");
        }
    }

    /**
     * 获取服务端响应消息
     *
     * @param sc
     * @return
     */
    public static String getResponse(SocketChannel sc) {
        StringBuffer sb = new StringBuffer("");
        try {
            Thread.sleep(3000);
            ByteBuffer readBuf = ByteBuffer.allocate(1024);
            while ((sc.read(readBuf)) > 0) {
                readBuf.flip();
                sb.append(new String(readBuf.array(), 0, readBuf.limit()));
                readBuf.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String exeCmdToServer(String hostName, int port, String xmlContext) {
        Socket socket = null;
        OutputStream outputStream = null;
        InputStream is = null;
        PrintWriter pw = null;
        BufferedReader br = null;
        String data = null;
        try {
            socket = new Socket(hostName, port);
            socket.setSoTimeout(5 * 60 * 1000);
            outputStream = socket.getOutputStream();
            pw = new PrintWriter(outputStream);
            pw.println(xmlContext);
            pw.flush();
            is = socket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((data = br.readLine()) != null) {
                break;
            }
        } catch (Exception e) {
            data = e.getMessage();
        } finally {
            if (socket != null) {
                try {
                    socket.shutdownInput();
                    socket.shutdownOutput();
                    br.close();
                    pw.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
