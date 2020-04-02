package com.study.utils.reflect.demo03;

public class TestClass {

    private String MSG = "Original";

    private final String FINAL_VALUE = "hello";


    private void privateMethod(String head , int tail){
        System.out.print(head + tail);
    }

    public String getMsg(){
        return MSG;
    }

    public String getFinalValue(){
        //剧透，会被优化为: return "FINAL" ,拭目以待吧
        return FINAL_VALUE;
    }
}
