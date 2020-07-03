package com.study.javanewspecial.jvm.demo01;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * todo
 *
 * @author ldb
 * @date 2020/06/30
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(JavaclassExecuter.execute(loadClassData("Target")));

    }
    //类存放的路径
    private static String path = "E:\\BaiduNetdiskDownload\\java基础\\worktest\\target\\classes\\com\\study" +
            "\\javanewspecial\\jvm\\demo01\\";

    public static byte[] loadClassData(String name) {
        try {
            name = name.replace(".", "//");
            FileInputStream is = new FileInputStream(new File(path + name + ".class"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b = 0;
            while ((b = is.read()) != -1) {
                baos.write(b);
            }
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
