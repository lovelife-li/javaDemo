package com.study.utils.reflect.demo04;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest2 {

	class GrandFather{
		void thinking(){
			System.out.println("I 'm grandFather!");
		}
	}
	class Father extends GrandFather{
		void thinking(){
			System.out.println("I 'm father!");
		}
	}
	class Son extends Father{
		void thinking() {
			//实现祖父类的thinking(),打印 I 'm grandFather
			MethodType mt= MethodType.methodType(void.class);
			try {
				MethodHandle md= MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt,this.getClass());
				md.invoke(this);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		MethodHandleTest2.Son son=new MethodHandleTest2().new Son();
		son.thinking();
	}
}