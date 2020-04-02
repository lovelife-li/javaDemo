package com.study.javanewspecial.java5.day3;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

public class ProxyTest {

	/**
	 * @param args
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
		System.out.println(clazzProxy1.getName());
		
		System.out.println("---begin constructors----");
		Constructor[] constructors = clazzProxy1.getConstructors();
		for(Constructor c :constructors){
			String name = c.getName();
			StringBuilder builder = new StringBuilder(name);
			builder.append("(");
			Class[] classParams = c.getParameterTypes();
			for(Class clazzParam:classParams){
				builder.append(clazzParam.getName()).append(",");
			}
			if(classParams!=null&&classParams.length!=0){
				builder.deleteCharAt(builder.length()-1);
			}
			builder.append(")");
			System.out.println(builder.toString());
		}
		
		System.out.println("---begin methods list----");
		Method[] methods = clazzProxy1.getDeclaredMethods();
		for(Method c :methods){
			String name = c.getName();
			StringBuilder builder = new StringBuilder(name);
			builder.append("(");
			Class[] classParams = c.getParameterTypes();
			for(Class clazzParam:classParams){
				builder.append(clazzParam.getName()).append(",");
			}
			if(classParams!=null&&classParams.length!=0){
				builder.deleteCharAt(builder.length()-1);
			}
			builder.append(")");
			System.out.println(builder.toString());
		}
		System.out.println("---begin create  instance----");
		
		Constructor constructor = clazzProxy1.getDeclaredConstructor(InvocationHandler.class);
		class MyInvocationHandler1 implements InvocationHandler{

			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		
		
		Collection proxy1 = (Collection) constructor.newInstance(new MyInvocationHandler1());
		System.out.println(proxy1);
		final ArrayList target = new ArrayList();
		Collection proxy3 = (Collection) getProxy(target,new MyAdvicer());
		
		
		proxy3.add("123");
		proxy3.add("22");
		proxy3.add("dd");
		System.out.println(proxy3.size());
	}

	private static Object getProxy(final Object target,final Advice advice) {
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
						System.out.println(method.getName() + "����ʱ��:" + (endTime - beginTime));
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
