package com.study.javanewspecial.java5.day3.aopFramework;

import com.study.javanewspecial.java5.day3.Advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class ProxyFactoryBean {

	private Advice advice;
	private Object target;
	
	public Advice getAdvice() {
		return advice;
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Object getProxy() {
		// TODO Auto-generated method stub
		Object proxy3 = Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(),
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						
						/*long beginTime = System.currentTimeMillis();
						Object retVal =  method.invoke(target, args);
						long endTime = System.currentTimeMillis();
						System.out.println(method.getName() + "运行时间:" + (endTime - beginTime));
						return retVal;*/
						
						advice.beforeMethod(method);
						Object retVal =  method.invoke(target, args);
						advice.afterMethod(method);
						return retVal;
					}
				}
		);
		return proxy3;
	}

}
