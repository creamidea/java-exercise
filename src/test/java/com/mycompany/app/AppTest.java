package com.mycompany.app;

import junit.framework.*;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );

    }

    public void testSplit() throws Exception {
        ArrayList<String> arrayList = new App().split("aaa1bbb1ccc", '1');
        Assert.assertTrue(arrayList.size() == 3);
        Assert.assertEquals("aaa", arrayList.get(0));
        Assert.assertEquals("bbb", arrayList.get(1));
        Assert.assertEquals("ccc", arrayList.get(2));
    }
}
