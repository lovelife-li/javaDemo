package com.study.other.test.junit5;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

/**
 * @author lidebo
 */
@Slf4j
@DisplayName("Testing using JUnit 5")
public class JUnit5AppTest {

    public  JUnit5AppTest(){
        log.info("构造JUnit5AppTest");
    }

    @BeforeAll
    public static void init() {
        // Do something before ANY test is run in this class
        log.info("init");
    }

    @AfterAll
    public static void done() {
        // Do something after ALL tests in this class are run
        log.info("done");
    }

    @BeforeEach
    public void setUp() throws Exception {
        log.info("setUp");
    }

    @AfterEach
    public void tearDown() throws Exception {
        log.info("tearDown");
    }

    @Test
    @DisplayName("Dummy test")
    void aTest() {
        log.info("As written, this test will always pass!");
        assertEquals(4, (2 + 2));
    }

    @Test
    @Disabled
    @DisplayName("A disabled test")
    void testNotRun() {
        log.info("This test will not run (it is disabled, silly).");
    }


    /**
     * 所有 JUnit Jupiter 断言方法都接受一个可选的 message 参数（作为最后一个参数），以显示断言是否失败，而不是显示标准的缺省消息。
     * 失败
     * org.opentest4j.AssertionFailedError: INCONCEIVABLE! ==>
     * Expected :4
     * Actual   :6
     *
     */
    @Test
    @DisplayName("Dummy test")
    void dummyTest() {
        int expected = 4;
        int actual = 2 + 2;
        assertEquals(expected, actual, "INCONCEIVABLE!");
        //
        Object nullValue = null;
        assertFalse(nullValue != null);
        assertNull(nullValue);
        assertNotNull("A String", "INCONCEIVABLE!");
        assertTrue(nullValue == null);
    }

    /**
     * assertAll() 的有趣之处在于，它包含的 所有断言都会执行，即使一个或多个断言失败也是如此。
     * 与此相反，在dummyTest 中的代码中，如果任何断言失败，测试就会在该位置失败，意味着不会执行任何其他断言。
     */
    @Test
    @DisplayName("Dummy test2")
    void dummyTest2() {
        int expected = 4;
        int actual = 2 + 4;
        Object nullValue = null;
        assertAll(
                "Assert All of these",
                () -> assertEquals(expected, actual, "INCONCEIVABLE!"),
                () -> assertFalse(nullValue != null),
                () -> assertNull(nullValue),
                () -> assertNotNull("A String", "INCONCEIVABLE!"),
                () -> assertTrue(nullValue == null));
    }

    /**
     * 在某些条件下，接受测试的类应抛出异常。JUnit 4 通过 expected = 方法参数或一个 @Rule 提供此能力。
     * 与此相反，JUnit Jupiter 通过 Assertions 类提供此能力，使它与其他断言更加一致。
     *
     * 异常情况会执行：@afterAll 标注的静态方法
     */
    @Test()
    @DisplayName("Empty argument")
    public void testAdd_ZeroOperands_EmptyArgument() {
        long[] numbersToSum = {};
        assertThrows(IllegalArgumentException.class, () -> "".endsWith("a") );
    }


    /**
     * 前置条件 (Assumption) 与断言类似，但前置条件必须为 true，否则测试将 中止 。
     * 与此相反，当断言失败时，则将测试视为已失败 。测试方法只应在某些条件 — 前置条件 下执行时，前置条件很有用。
     * 注意：@afterAll标注的静态方法会执行
     */
    @Test
    @DisplayName("This test is only run on Fridays")
    public void testAdd_OnlyOnFriday() {
        LocalDateTime ldt = LocalDateTime.now();
        assumeTrue(ldt.getDayOfWeek().getValue() == 5);
        // Remainder of test (only executed if assumption holds)...
        // 其余测试（仅在假设成立的情况下执行）...
        log.info("假设成立才会执行到这！"); // 不会执行
    }

    /**
     * 如果在前置条件成立时仅应执行测试方法的一 部分 ，可以使用 assumingThat() 方法编写上述条件，该方法使用 lambda 语法.
     * 注意，无论 assumingThat() 中的前置条件成立与否，都会执行 lambda 表达式后的所有代码。
     */
    @Test
    @DisplayName("This test is only run on Fridays (with lambda)")
    public void testAdd_OnlyOnFriday_WithLambda() {
        LocalDateTime ldt = LocalDateTime.now();
        assumingThat(ldt.getDayOfWeek().getValue() == 5,
                () -> {
                    // Execute this if assumption holds...
                    System.out.println("execute");
                });
        // Execute this regardless
        System.out.println("假设不成立，不执行lamdbda,但会执行到这里");
    }

    /**
     * 嵌套单元测试，实现清晰的结构
     * JUnit Jupiter API 允许您创建嵌套的类，以保持测试代码更清晰，这有助于让测试结果更易读。
     * 通过在主类中创建嵌套的测试类，可以创建更多的名称空间，这提供了两个主要优势：
     * 1,每个单元测试可以拥有自己的测试前和测试后生命周期。这让您能使用特殊条件创建要测试的类，从而测试极端情况。
     * 2,单元测试方法的名称变得更简单。在 JUnit 4 中，所有测试方法都以对等形式存在，
     * 不允许重复的方法名（所以您最终会得到类似testMethodButOnlyUnderThisOrThatCondition_2() 的方法名）。
     * 从 JUnit Jupiter 开始，只有嵌套类中的方法必须具有唯一的名称。清单 JUnit5AppZeroOperandsTest 展示了这一优势。
     */

    /**
     * 传递一个空或 null 数组引用
     */
    @Nested
    @DisplayName("When zero operands")
    class JUnit5AppZeroOperandsTest {

        // @Test methods go here...
        @Test
        public void test01() {
            System.out.println("hello");
        }


    }

}