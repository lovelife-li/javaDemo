package com.study.javanewspecial.java5.day2;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import java.util.Map.Entry;

public class GenericTest {

	/**
	 * @param args
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		// TODO Auto-generated method stub
		Collection<Integer> coll = new ArrayList<Integer>();
		
		HashMap<String,Integer> maps = new HashMap<String, Integer>();
		
		maps.put("zxx", 12);
		maps.put("ldb",32);
		
		Set<Entry<String, Integer>> set = maps.entrySet();
		for(Entry<String, Integer> entry : set){
			System.out.println(entry.getKey()+"  "+entry.getValue());
		}
		
		swap(new String[]{"11","2","aaa"},1,2);
		//swap(new int[]{1,2,3},1,2);有错
		Method m = GenericTest.class.getDeclaredMethod("applyVector", Vector.class);
		Type[] types = m.getGenericParameterTypes();
		ParameterizedType ptype = (ParameterizedType) types[0];
		System.out.println(ptype.getRawType());
		System.out.println(ptype.getOwnerType());
		System.out.println(ptype.getActualTypeArguments()[0]);
		
	}

	//? 通配符
	public void printCollection(Collection<?> c){
		for(Object obj : c){
			System.out.println(obj);
		}
	}
	//通过方法获得泛型类型
	private static  void applyVector(Vector<Date> v){
		
	}
	
	private static  <T> T add(T x,T y){
		return null;
	}
	
	private static <T> void fillArray(T[] arr,T o){
		for(int i=0;i<arr.length;i++){
			arr[i] = o;
		}
	}
	//复制集合到数组
	private static <T> void copyCollectionToArray(Collection<T> org,T[] dest){
		
	}
	//复制集合到数组
	private static <T> void copyArrayToCollection(Collection<T> dest,T[] org){
		
	}

	//打印任意类型的集合
	private static <T> void printColl2(Collection<T> c){
		for(Object o: c){
			System.out.println(c);
			
		}
	}

	//自动类型转换
	private static <T> T autoConver(Object o){
		return (T)o;
	}

	//交换数组元素位置
	private static <T> void swap(T[] a,int i ,int j){
		T tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
