package com.study.java9;

import com.atguigu.bean.Person;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Logger;

/**
 * 测试java 9 的模块化特性
 *
 * Created by songhongkang on 2017/12/29 0029.
 */
public class ModuleTest {

    private static final Logger LOGGER = Logger.getLogger("atguigu");

    public static void main(String[] args) {

        Person p = new Person("Tom",12);

        System.out.println(p);
        LockSupport.park();

     //   User user = new User();

        LOGGER.info("aaaaaa");

    }

    @Test
    public void test1(){
        try {
            URL url = new URL("http://www.atguigu.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
