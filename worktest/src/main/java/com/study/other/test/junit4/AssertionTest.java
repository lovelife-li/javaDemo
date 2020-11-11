package com.study.other.test.junit4;

import org.junit.Assert;
import org.junit.Test;

/**
 * void assertEquals([String message],expected value,actual value)
 * 断言两个值相等。值类型可能是int，short，long，byte，char，Object，第一个参数是一个可选字符串消息
 *
 * void assertFalse([String message],boolean condition)
 * 断言一个条件为假
 *
 * void assertNotNull([String message],java.lang.Object object)
 * 断言一个对象不为空（null）
 *
 *
 * void assertNull([String message],java.lang.Object object)
 * 断言一个对象为空（null）
 *
 *
 * void assertSame([String message],java.lang.Object expected,java.lang.Object actual)
 * 断言两个对象引用相同的对象
 *
 * void assertNotSame([String message],java.lang.Object unexpected,java.lang.Object actual)
 * 断言两个对象不是引用同一个对象
 *
 * void assertArrayEquals([String message],expectedArray,resultArray)
 * 断言预期数组和结果数组相等，数组类型可能是int，short，long，byte，char，Object
 *
 * @author ldb
 */
public class AssertionTest {

    @Test
    public void test() {
        String obj1 = "junit";
        String obj2 = "junit";
        String obj3 = "test";
        String obj4 = "test";
        String obj5 = null;
        
        int var1 = 1;
        int var2 = 2;

        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};

        Assert.assertEquals(obj1, obj2);

        Assert.assertSame(obj3, obj4);
        Assert.assertNotSame(obj2, obj4);
        
        Assert.assertNotNull(obj1);
        Assert.assertNull(obj5);

        Assert.assertTrue(var1 < var2);
        Assert.assertFalse(var1 > var2);

        Assert.assertArrayEquals(array1, array2);

    }
}
