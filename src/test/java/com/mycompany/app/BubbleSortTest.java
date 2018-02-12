package com.mycompany.app;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
        Integer source[] = {3, 2, 5, 6, 3, 10};
        Integer _source[] = {2, 3, 3, 5, 6, 10};
        Double source2[] = {2.1, 3.4, 39.0, 89.1, -1.2};
        Double _source2[] = {-1.2, 2.1, 3.4, 39.0, 89.1};

        bs.sort(source);
        assertArrayEquals(source, _source);

        bs.sort(source2);
        assertArrayEquals(source2, _source2);

        bs.setOrder("DESC");
        Double __source2[] = {89.1, 39.0, 3.4, 2.1, -1.2};
        bs.sort(source2);
        assertArrayEquals(source2, __source2);

        bs.setOrder("ASC");
        String[] students = {"xiaoming", "cisong", "weiziman"};
        String[] _students = {"cisong", "weiziman", "xiaoming"};
        bs.sort(students);
        assertArrayEquals(students, _students);
    }
}
