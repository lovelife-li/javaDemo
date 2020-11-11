package com.study.other.test.mockito;

import com.alibaba.fastjson.JSON;
import com.study.utils.web.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Mockito 用法
 *
 * @author ldb
 * @date 2020/11/4
 */
@RunWith(MockitoJUnitRunner.class)
public class MockTest {
    @Test
    public void testMockBase(){
        //创建ArrayList的Mock对象
        List mockList = mock(ArrayList.class);
        //pass
        Assert.assertTrue(mockList instanceof ArrayList);

        //当我们mockList调用方法去add("张三")的时候会返回true
        when(mockList.add("张三")).thenReturn(true);
        //当我们mockList调用方法size()的时候返回10
        when(mockList.size()).thenReturn(10);
        //pass
        Assert.assertTrue(mockList.add("张三"));
        //pass
        Assert.assertFalse(mockList.add("李四"));
        //pass
        Assert.assertEquals(mockList.size(),10);
        //null
        System.out.println(mockList.get(0));
    }
    @Test
    public void test2(){
        //使用mock
        List mockedList = mock(ArrayList.class);
        mockedList.add("once");
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        //这里默认是判断该方法调用times(1),同下
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");
        //从没调用，times(0)
        verify(mockedList, never()).add("never happened");
        //最少一次，最少几次，最多几次
        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");


        //方法执行在100ms以内的时候可以通过
//        verify(mock, timeout(100)).someMethod();
//        //同上
//        verify(mock, timeout(100).times(1)).someMethod();
//
//        //方法2次调用均没超过100ms
//        verify(mock, timeout(100).times(2)).someMethod();
//        verify(mock, timeout(100).atLeast(2)).someMethod();

//        linkedList.add("element");
//        // anyInt() 任何整数我们都返回 element
//        when(linkedList.get(anyInt())).thenReturn("element");
//
//        System.out.print(linkedList.get(10));//返回element


    }

    @Test(expected = RuntimeException.class)
    public void test3(){
        List list = mock(List.class);
        doThrow(new RuntimeException()).when(list).add(1);
        list.add(1);
    }


    @Mock
    private User user;

//    @Before
//    public void setUp() {
//        initMocks(this);
//    }

    @Test
    public void test4(){
        List mockedList = mock(ArrayList.class);
        System.out.println(user.getNickname());
        System.out.println(JSON.toJSONString(user));
        when(mockedList.get(anyInt())).thenReturn("element");
        System.out.println(mockedList.get(3));
    }

    @Test
    public void test5(){
        Service spy = Mockito.spy(new Service());
        System.out.println(spy.service());

        doReturn("spy!").when(spy).service();
        when(spy.service()).thenReturn("spy!");
        // 对 spy 变量打桩时，如果使用 when 去设置模拟值时，他里面的代码逻辑依然会被执行，
        // 只是mock了返回结果；使用 doReturn 设置模拟值的话，则不会出现这个问题！
    }

    public class Service {
        public String service() {
            System.out.print("invoked!");
            return "real service!";
        }
    }
}
