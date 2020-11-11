package com.study.other.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData2 {
    @ExcelProperty(index = 0)
    private String key;

    @ExcelProperty(index = 1)
    private String desc;


}