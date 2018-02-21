package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public MergeSortTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(MergeSortTest.class);
    }

    public void testSort() throws Exception {
        MergeSort ms = new MergeSort();
        LinkedList<Integer> a = new LinkedList(){{
            add(3);
            add(2);
            add(5);
            add(6);
            add(3);
            add(10);
        }};
        Integer[] b = {10, 6, 5, 3, 3, 2};
        ms.sort(a);
        assertArrayEquals(a.toArray(), b);
    }
}