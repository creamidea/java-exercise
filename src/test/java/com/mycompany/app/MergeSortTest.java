package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortTest  extends TestCase {
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
        int[] a = {3, 2, 5, 6, 3, 10};
        int[] b = {2, 3, 3, 5, 6, 10};
        ms.sort(a, 0, a.length - 1);
        assertArrayEquals(a, b);
    }
}