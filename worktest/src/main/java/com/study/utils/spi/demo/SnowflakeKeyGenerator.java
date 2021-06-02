package com.study.utils.spi.demo;

public class SnowflakeKeyGenerator implements KeyGenerator {

    @Override 
    public String getKey() { 

       return "SnowflakeKey"; 
    } 
}