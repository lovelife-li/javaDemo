package com.study.algo.sort;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * 电话号码排序
 *
 * @author ldb
 * @date 2020/08/06
 */
public class TelNumberSort {

    public static void radixSort(long[] arr) {
        /**
         * 1,找最大值
         * 2，借助稳定排序，依次对每一位排序
         */
        if (arr == null || arr.length <= 0) {
            return;
        }
        long max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        System.out.println("max:" + max);
        for (int i = 1; max / i > 0; i *= 10) {
            countSort(arr, i);
        }
    }

    public static void countSort(long[] arr, int i) {
        if (arr.length <= 1) {
            return;
        }
        int[] c = new int[10];
        for (int j = 0; j < arr.length; j++) {
            int n = (int) arr[j] / i % 10;
            c[n]++;
        }

        // 计算位置
        for (int j = 1; j < c.length; j++) {
            c[j] += c[j - 1];
        }

        // 存储排序结果到临时数组r
        long[] r = new long[arr.length];
        for (int j = arr.length - 1; j >= 0; j--) {
            int index = (int) arr[j] / i % 10;
            r[c[index] - 1] = arr[j];
            c[index]--;
        }

        for (int j = 0; j < arr.length; j++) {
            arr[j] = r[j];
        }
    }

    public static void main(String[] args) throws Exception {
        long arr[] = new long[10];
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            StringBuilder val = new StringBuilder("1");
            for (int j = 0; j < 9; j++) {
                val.append(r.nextInt(10));
            }
            arr[i] = Integer.valueOf(val.toString());
        }

        System.out.println(Arrays.toString(arr));
        arr[0] = 2;
        arr[1] = 0;
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        try {
            FileInputStream fis = new FileInputStream("d:/fdfs.tar.gz");
            FileOutputStream fos = new FileOutputStream("d:/xxx.log");
            byte[] buffer = new byte[1024];
            while (fis.read(buffer)>0){
                fos.write(buffer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
