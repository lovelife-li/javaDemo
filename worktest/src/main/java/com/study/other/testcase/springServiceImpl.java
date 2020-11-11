package com.study.other.testcase;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

public class springServiceImpl {

    @Data
    static public class Person {
        private String name;
        private Integer age;
    }

    public boolean judge(List<Person> persons) {
        for (Person person : persons
        ) {
            if (StringUtils.isBlank(person.getName()) || person.getAge() == null) {
                return false;
            }
        }
        return true;
    }


}