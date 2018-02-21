package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class QuickSortTest extends TestCase {
    public QuickSortTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(MergeSortTest.class);
    }

    public void testSort() {
        QuickSort qs = new QuickSort();
        ArrayList<Integer> a = new ArrayList(Arrays.asList(3, 2, 5, 6, 3, 10, 67, 89, 32, 21, 24, 1, 89));
        Integer[] expectSource = {1, 2, 3, 3, 5, 6, 10, 21, 24, 32, 67, 89, 89};
        qs.sort(a);
        assertArrayEquals(a.toArray(), expectSource);
    }
}
