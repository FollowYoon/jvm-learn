package com.jvm.learn;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池
 * @author qindong
 * @date 2021/9/24 14:10
 */
public class HttpServer03 {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
        final ServerSocket serverSocket = new ServerSocket(8083);
        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                executorService.execute(()->service(socket));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello nio3";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
