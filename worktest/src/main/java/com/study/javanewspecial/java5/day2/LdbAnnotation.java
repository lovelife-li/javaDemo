package com.study.javanewspecial.java5.day2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface LdbAnnotation {
	
	public String color() default "blue";
	public String value();
	int[] arr();
	MetaAnnotation meta() default @MetaAnnotation("5");
	
}
