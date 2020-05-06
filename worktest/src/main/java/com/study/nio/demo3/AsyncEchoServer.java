package com.study.nio.demo3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 异步通道API提供了两种对已启动异步操作的监测与控制机制:
 *
 * 第一种通过返回一个 java.util.concurrent.Future对象来表示异步操作的结果
 * 第二种是通过传递给操作一个新类的对象java.nio.channels.CompletionHandler来完成，
 * 它会定义在操作完毕后所执行的处理程序方法。
 *
 */
public class AsyncEchoServer {
    private AsynchronousServerSocketChannel server;
    private Future<AsynchronousSocketChannel> future;
    private AsynchronousSocketChannel worker;

    public AsyncEchoServer() throws IOException, ExecutionException, InterruptedException {
        System.out.println("Open Server Channel");
        server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress("127.0.0.1", 9090));
        future = server.accept();
    }

    public void runServer() throws ExecutionException, InterruptedException, IOException, TimeoutException {
        //获取操作结果
        worker = future.get();
        if (worker != null && worker.isOpen()) {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            //将通道中的数据写入缓冲区
            worker.read(buffer).get(10, TimeUnit.SECONDS);
            System.out.println("received from client: " + new String(buffer.array()));
        }
        server.close();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, TimeoutException {
        AsyncEchoServer server = new AsyncEchoServer();
        server.runServer();
    }
}

