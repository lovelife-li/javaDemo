package com.study.utils.hutool.core;

import cn.hutool.core.convert.Convert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ldb
 * @date 2020/04/28
 * @description 类型转换
 * Convert类中大部分方法为toXXX，参数为Object可以实现将任意可能的类型转换为指定类型。
 * 同时支持第二个参数defaultValue用于在转换失败时返回一个默认值。
 */
public class ConvertTest {

    /**
     * 转换为字符串
     */
    @Test
    public void test1(){
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);
        System.out.println(aStr);
        long[] b = {1,2,3,4,5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        System.out.println(bStr);

    }

    /**
     * 转换为指定类型数组
     */
    @Test
    public void test2(){
        String[] b = { "1", "2", "3", "4" };
        //结果为Integer数组
        Integer[] intArray = Convert.toIntArray(b);

        long[] c = {1,2,3,4,5};
        //结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);

        System.out.println(Arrays.toString(intArray));
        System.out.println(Arrays.toString(intArray2));
    }
}
