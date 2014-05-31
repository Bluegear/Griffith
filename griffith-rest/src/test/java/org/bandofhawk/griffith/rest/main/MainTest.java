package org.bandofhawk.griffith.rest.main;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Bluegear on 5/29/14.
 */
public class MainTest extends TestCase {

    Main main;

    @Before
    public void setUp() throws Exception {
        main = new Main();
    }

    @Test
    public void testSquare() throws Exception {
        assertEquals(4, main.square(2));
    }
}
