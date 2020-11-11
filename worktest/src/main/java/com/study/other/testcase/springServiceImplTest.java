package com.study.other.testcase;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class springServiceImplTest {

    private springServiceImpl springServiceImplUnderTest;

    @Before
    public void setUp() {
        springServiceImplUnderTest = new springServiceImpl();
    }

    @Test
    public void testJudge() {
        // Setup
        final springServiceImpl.Person person = new springServiceImpl.Person();
        person.setName("name");
        person.setAge(0);
        final List<springServiceImpl.Person> persons = Arrays.asList(person);

        // Run the test
        final boolean result = springServiceImplUnderTest.judge(persons);

        // Verify the results
        assertTrue(result);
    }
}
