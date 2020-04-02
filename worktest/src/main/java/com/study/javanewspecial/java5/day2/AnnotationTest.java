package com.study.javanewspecial.java5.day2;

@LdbAnnotation(color = "red",value = "11",arr={1,2})
public class AnnotationTest {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.runFinalizersOnExit(true);
		if(AnnotationTest.class.isAnnotationPresent(LdbAnnotation.class)){
			LdbAnnotation annotation = AnnotationTest.class.getAnnotation(LdbAnnotation.class);
			System.out.println(annotation.color());
		}
	}
	@Deprecated
	public void sayHello(){
		System.out.println("hello,world");
	}

}
