package com.learn.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            System.out.println("等待连接...");
            final Socket socket = serverSocket.accept();
            System.out.println("有客户端连接了");

            handler(socket);
            // 多线程
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        handler(socket);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
        }
    }

    public static void handler(Socket socket) throws IOException {
        System.out.println("Thread id = " + Thread.currentThread().getId());

        byte[] bytes = new byte[1024];
        System.out.println("准备read....");
        int read = socket.getInputStream().read(bytes);
        System.out.println("read 读取完毕");
        if (read != -1) {
            System.out.println("接收到客户端连接：" + new String(bytes, 0, read));
            System.out.println("Thread id = " + Thread.currentThread().getId());
        }

        socket.getOutputStream().write("HelloClient".getBytes());
        socket.getOutputStream().flush();
    }
}
