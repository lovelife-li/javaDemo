package com.study.javanewspecial.java5.day3.aopFramework;

import com.study.javanewspecial.java5.day3.Advice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class BeanFactory {
	
	Properties properties = new Properties();
	
	public BeanFactory(InputStream is){
		
		try {
			properties.load(is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object getBean(String name){
		String className = properties.getProperty(name);
		Class clazz = null;
		Object bean = null;
		try {
			clazz = Class.forName(className);
			//javaBeanһ��Ҫ��һ�����������Ĺ��췽��
			bean = clazz.newInstance();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(bean instanceof ProxyFactoryBean){
			
			ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean)bean;
			Object proxy = null;
			try {
				Advice advice = (Advice) Class.forName(properties.get(name+".advice").toString()).newInstance();
				Object target = Class.forName(properties.get(name+".target").toString()).newInstance();
				proxyFactoryBean.setAdvice(advice);
				proxyFactoryBean.setTarget(target);
				proxy = proxyFactoryBean.getProxy();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return proxy;
		}
		return bean;
	}
}
