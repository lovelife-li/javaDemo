package com.study.rpc.demo01;

import org.junit.Test;

public class ServerTest {
	
	@Test
	public void startServer() {
		RpcServer server = new RpcServer();
		server.start(9998, "io.netty.example.chapter0.rpc01");
	}
	
	
}
