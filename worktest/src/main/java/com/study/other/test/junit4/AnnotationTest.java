package com.study.other.test.junit4;

import org.junit.*;

/**
 * JUnit4为了保证每个测试方法都是单元测试，是独立的互不影响。所以每个测试方法执行前都会重新实例化测试类。
 * 1,@BeforeClass和@AfterClass在类被实例化前（构造方法执行前）就被调用了，而且只执行一次
 * 2,@Before和@After和在每个@Test执行前后都会被执行一次。
 * 3,@Test的两个属性
 * excepted  会检查是否抛出异常，是则测试成功。否则测试失败。
 * timeout  用来测试性能的，就是测试一个方法能不能在规定时间内完成。timeout的值为2000，单位和毫秒，也就是说超出2秒将视为测试不通过
 * 4,@Runwith就是放在测试类名之前，用来确定这个类怎么运行的。也可以不标注，会使用默认运行器。
 * @author ldb
 */
public class AnnotationTest {

    public AnnotationTest() {
        System.out.println("构造方法");
    }


    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("AfterClass");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @After
    public void tearDown() {
        System.out.println("After");
    }

    @Test(expected = Exception.class)
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Ignore
    public void test3() {
        System.out.println("test3");
    }

}