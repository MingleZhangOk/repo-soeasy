package com.mingzhang.table.source.udfsocket;

import java.io.*;
import java.net.Socket;

/**
 * File Description:
 *
 * @author MingZhang                      --Variety is the spice of life.
 * @Description
 * @Classname SocketThread
 * @date 2020-06-01 16:06
 */
public class MySocketThread implements Runnable {
    public BufferedReader reader;
    private Socket socket;
    private String messages;

    //通过构造方法传递Socket
    public MySocketThread(Socket socket) {
        try {
            // 得到socket连接
            this.socket = socket;
            // 得到客户端发来的消息
            InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(isReader);
            // 清空socket缓冲区数据
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            String ms = "********************";
            pw.write(ms);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        getMessages();
    }

    private String getMessages() {
        String messages = "";
        try {
            while ((messages = reader.readLine()) != null) {
                this.messages = messages;
                System.out.println("客户端请求的消息: " + messages);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public void close() {
        if (socket != null) {
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("socket关闭异常！");
            }
        }
        if (reader != null) {
            try {
                reader.close();
            } catch (Exception e) {
                System.out.println("reader关闭异常！");
            }
        }
    }

}