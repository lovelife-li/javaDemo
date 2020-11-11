package com.study.other.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DemoData3 {
    @ExcelProperty("url")
    private String key;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("描述")
    private String desc;

    @ExcelProperty("分组")
    private String group;

    @ExcelProperty("角色")
    private String roleName;


}