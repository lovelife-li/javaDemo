package com.study.other.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ldb
 * @date 2020/10/29
 */
public class TestExcel {
    public static void main(String[] args) {

    }

    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        String fileName = "d:/test/a.xlsx";

        Map<String, DemoData> map = new LinkedHashMap<>(256);
        Map<String, DemoData2> map2 = new LinkedHashMap<>(256);

        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener(map)).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }

        fileName = "d:/test/b.xlsx";
        ExcelReader excelReader2 = null;
        try {
            excelReader2 = EasyExcel.read(fileName, DemoData2.class, new DemoDataListener2(map2)).build();
            ReadSheet readSheet2 = EasyExcel.readSheet(0).build();
            excelReader2.read(readSheet2);
        } finally {
            if (excelReader2 != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader2.finish();
            }
        }


        System.out.println("aa");

        ArrayList<DemoData3> demoData3s = new ArrayList<>(512);
        map2.forEach((key, value) -> {
            if (map.get(key) == null) {
                System.out.println(key);
                demoData3s.add(new DemoData3(key, "", value.getDesc(), "", ""));

            } else {
                DemoData d = map.get(key);
                demoData3s.add(new DemoData3(key, d.getName(), d.getDesc(), d.getGroup(), d.getRoleName()));
            }
        });
        // 写法1
        String fileName3 = "d:/test/d.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName3, DemoData3.class).sheet("模板").doWrite(demoData3s);
    }

//    public void print(Map<String, String> permissionPathMap) throws Exception {
//        FileOutputStream file = new FileOutputStream("d:/diff.txt");
//        OutputStreamWriter writer = new OutputStreamWriter(file, "utf-8");
//        permissionPathMap.forEach((key, value) -> {
//            try {
//                writer.write(key + "\t" + value + "\r\n");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        writer.close();
//        file.close();
//
//    }
}
