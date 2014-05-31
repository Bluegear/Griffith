package org.bandofhawk.griffith.rest.main;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Griffith
 * Created by Bluegear on 5/29/14.
 */
public class MainTest {

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
