package com.study.javanewspecial.jvm;

import com.study.javanewspecial.jvm.demo01.ByteUtils;

/**
 * 测试类
 *
 * @author ldb
 * @date 2020/06/29
 */
public class Test {

    public void print(String str){
        System.out.println(str);
    }

    public void print2(){
        System.out.println("helloo");
    }

    public static int byte4int(byte[] bytes){
        int value = 0;
        for (int i = 0; i < bytes.length; i++) {

        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(2<<0);

        byte[] b = new byte[]{1,2,3,4};
//        System.out.println(ByteUtils.bytes2Int(b,0,2));
//        System.out.println(b[2]);

        b = new byte[]{1,2};

        System.out.println(ByteUtils.bytes2Int(b,0,2));
        System.out.println(ByteUtils.bytes2Int2(b,0,2));
        Math.pow(1,2);
    }
}
