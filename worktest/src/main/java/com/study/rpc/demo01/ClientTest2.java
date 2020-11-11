package com.study.rpc.demo01;

import org.junit.Test;

public class ClientTest2 {
	
	@Test
	public void test() {
		// 本地没有接口实现，通过代理获得接口实现实例
		RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9998);
		StudentService service = proxy.getProxy(StudentService.class);
		
		System.out.println(service.getInfo());
		
		Student student = new Student();
		student.setAge(24);
		student.setName("hashmap");
		student.setSex("男");
		System.out.println(service.printInfo(student));
	}


	
}
