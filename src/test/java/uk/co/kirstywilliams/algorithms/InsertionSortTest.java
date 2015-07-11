/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the insertion sort algorithm.
 *
 * @author kirsty
 */
public class InsertionSortTest {

    /**
     * This test checks the insertion sort algorithm on
     * an integer array.
     */
    @Test
    public void insertionSortShouldReturnSortedIntegerArray() {

        // initialise array
        int size = 10;
        Random rand = new Random();
        Integer[] a = new Integer[size];
        boolean isSuccess = true;

        // fill array
        for(int i = 0; i < size; i++) {
            a[i] = rand.nextInt(size);
        }

        // do algorithms
        Sort.insertionSort(a);

        // check array elements are sorted
        for (int i = 1; i < size; i++) {
            if (a[i] < a[i - 1]) {
                isSuccess = false;
            }
        }

        assertEquals("success must be true", isSuccess, true);
    }

    /**
     * This test checks the insertion sort algorithm on
     * a String array.
     */
    @Test
    public void insertionSortShouldReturnSortedStringArray() {

        // initialise array
        String[] a = new String[] {"Bob", "Dog", "Alice", "Cat" };
        boolean isSuccess = true;

        // do algorithms
        Sort.insertionSort(a);

        // check array elements are sorted
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) <= 0) {
                isSuccess = false;
            }
        }

        assertEquals("success must be true", isSuccess, true);
    }
}
