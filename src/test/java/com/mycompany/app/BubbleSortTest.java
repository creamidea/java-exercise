package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;

public class BubbleSortTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BubbleSortTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(BubbleSortTest.class);
    }

    public void testSort() throws Exception {
        BubbleSort bs = new BubbleSort();
        LinkedList<Integer> source = new LinkedList(){{
            add(3);
            add(2);
            add(5);
            add(6);
            add(3);
            add(10);
        }};
        Integer[] expectSource = {2, 3, 3, 5, 6, 10};
        bs.sort(source);
        assertArrayEquals(source.toArray(), expectSource);

        bs.setOrder("DESC");
        LinkedList<String> students = new LinkedList(){{
            add("xiaoming");
            add("cisong");
            add("weiziman");
        }};
        String[] expectStudents = {"xiaoming", "weiziman", "cisong"};
        bs.sort(students);
        assertArrayEquals(students.toArray(), expectStudents);
    }
}
