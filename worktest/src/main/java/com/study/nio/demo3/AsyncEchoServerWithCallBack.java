package com.study.nio.demo3;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeoutException;

public class AsyncEchoServerWithCallBack {
    private AsynchronousServerSocketChannel server;
    private AsynchronousSocketChannel worker;
    private AsynchronousChannelGroup group;

    public AsyncEchoServerWithCallBack() throws IOException, ExecutionException, InterruptedException {
        System.out.println("Open Server Channel");
        group = AsynchronousChannelGroup.withFixedThreadPool(10, Executors.defaultThreadFactory());
        server = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress("127.0.0.1", 9090));
        //当有新连接建立时会调用 CompletionHandler接口实现对象中的 completed()方法
        /**
         * accept方法的第一个参数可以是一个任意类型的对象，称为调用时的" 附加对象"。
         * 附件对象在 accept()方法调用时传入，可以在 CompletionHandler 接口的实现对象中从 completed 和 failed
         * 方法的参数(attachment)中获取，这样就可以进行数据的传递。使用 CompletionHandler接口的方法都支持使用附件
         * 对象来传递数据。
         *。
         */
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                if (server.isOpen()) {
                    server.accept(null, this);
                }
                worker = result;
                if ((worker != null) && (worker.isOpen())) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(100);
                    worker.read(byteBuffer);
                    try {
                        System.out.println("received the client: " + new String(byteBuffer.array(),"UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                //TODO
            }
        });
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException, TimeoutException {
        AsyncEchoServerWithCallBack server = new AsyncEchoServerWithCallBack();


    }
}

