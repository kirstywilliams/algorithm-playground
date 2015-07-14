/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests that the euclidean distance is computed
 * correctly.
 *
 * @author kirsty
 */
public class CoordinateTest {

    Coordinate a;
    Coordinate b;

    /**
     * Set up the coordinates.
     */
    @Before
    public void setup() {
        a = new Coordinate(2, -1);
        b = new Coordinate(-2, 2);
    }

    /**
     * Tests that an accurate distance is computed.
     */
    @Test
    public void shouldComputeTheCorrectDistanceBetweenTwoPoints() {

        assertEquals(a.getDistance(b), 5, 0.0001);
    }
}
