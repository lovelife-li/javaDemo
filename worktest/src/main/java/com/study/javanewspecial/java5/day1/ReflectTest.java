package com.study.javanewspecial.java5.day1;

import java.lang.reflect.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;



public class ReflectTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		String str1 = "abc";
		Class cal1 = str1.getClass();
		Class cal2 = String.class;
		Class cal3 = Class.forName("java.lang.String");
		System.out.println(cal1==cal2);
		System.out.println(cal2==cal3);
		System.out.println("=======================");
		System.out.println("///"+cal1.isPrimitive());
		System.out.println(int.class.isPrimitive());
		System.out.println(int.class == Integer.class);
		System.out.println(int.class == Integer.TYPE);
		System.out.println(void.class);
		System.out.println(int[].class.isArray());
		
		Constructor constructor = String.class.getConstructor(StringBuffer.class);
		String s = (String) constructor.newInstance(new StringBuffer("abc"));
		System.out.println(s.charAt(2));
		
		
		ReflectPoint point = new ReflectPoint(3, 5);
		Field fieldy = point.getClass().getField("y");
		Field fieldx = point.getClass().getDeclaredField("x");
		fieldx.setAccessible(true);//设置成可访问的
        //fieldy 只是变量，要取某个对象上的值
		System.out.println(fieldy.get(point));
		System.out.println(fieldx.get(point));
		
		changeStringValue(point);
		System.out.println(point.str1);


        //要调用str1中的CharAt
		Method methodCharAt = String.class.getMethod("charAt", int.class);
		System.out.println(methodCharAt.invoke("str1", 2));


        //通过一个反射调用其他类中的方法
		//TestArguments.main(new String[]{"1","2","3"});
		String className = args[0];
		Method mainMethod = Class.forName(className).getMethod("main", String[].class);
		mainMethod.invoke(null, new Object[]{new String[]{"1","2","3"}});

        //比较数组字节码
		int[] a1 = new int[]{1,2,3};
		int[] a2 = new int[4];
		int[][] a3 = new int[4][4];
		String[] a4 =new String[]{"a","b","c"};
		System.out.println(a1.getClass() == a2.getClass());
		//System.out.println(a1.getClass() == a4.getClass());
		//System.out.println(a1.getClass() == a3.getClass());
		System.out.println(a1.getClass().getName());
		System.out.println(a1.getClass().getSuperclass().getName());
		System.out.println(a4.getClass().getSuperclass().getName());
		System.out.println(int.class.getName());
		Object obj1 = a1;
		Object[] obj2 = a4;
		//Object[] obj3 = a1;//报错 因为基本类型不是对象
		Object[] obj4 = a4;
		int aa = 3;
		Object o = aa;
		
		System.out.println(a1);
		System.out.println(a4);
		System.out.println(Arrays.asList(new Object[]{a1}));
		System.out.println(Arrays.asList(a4));
		
		//打印Object
		Object xx = a1;
		printObject(xx);
	}

	private static void printObject(Object xx) {
		// TODO Auto-generated method stub
		Class clazz = xx.getClass();
		if(clazz.isArray()){
			int len = Array.getLength(xx);
			for(int i=0;i<len;i++){
				System.out.println(Array.get(xx, i));
			}
		}else{
			System.out.println(xx);
		}
	}

	private static void changeStringValue(ReflectPoint p) throws Exception{
		// TODO Auto-generated method stub
		Field[] fs = p.getClass().getFields();
		for(Field f : fs){
			if(f.getType()==(String.class)){
				String ss = (String) f.get(p);
				String newValue = ss.replace('b','a');
				f.set(p,newValue);
			}
		}
		
	}

}
class TestArguments{
	public static void main(String[] args) {
		for(String arg : args){
			System.out.print(arg+" ;");
		}
	}
}