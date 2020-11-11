package com.study.other.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    @ExcelProperty(index = 3)
    private String key;

    @ExcelProperty(index = 1)
    private String name;

    @ExcelProperty(index = 2)
    private String desc;

    @ExcelProperty(index = 0)
    private String group;

    @ExcelProperty(index = 4)
    private String roleName;

}