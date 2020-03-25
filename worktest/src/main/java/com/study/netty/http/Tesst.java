package com.study.netty.http;

import java.io.File;

/**
 * @author ldb
 * @date 2019-10-21 11:50
 */
public class Tesst {
    public static void main(String[] args) {
        String sourcePath = "F:\\工作内容\\7.0关注点改造\\10.12补录\\女生录音";
        String targetPath = "D:\\mnt\\aegis_file\\wav\\system\\wenwen";
        File file = new File(sourcePath);
        for (String name : file.list()) {
            System.out.println(name);
        }
    }

}
