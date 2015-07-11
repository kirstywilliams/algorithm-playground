/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */
package uk.co.kirstywilliams.algorithms;

/**
 * A collection of static sort
 * algorithms.
 *
 * Algorithms covered:
 * - Insertion sort
 */
public class Sort {


    /**
     * Insertion sort.
     * Starts with one element and incrementally
     * inserts the remaining elements so that the
     * list stays sorted.
     *
     * @See <a href="https://en.wikipedia.org/wiki/Insertion_sort">https://en.wikipedia.org/wiki/Insertion_sort</a>
     *
     * @param <T>  the type parameter
     * @param a the array to sort
     */
    public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            T element = a[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (a[j].compareTo(element) <= 0) break;
                a[j + 1] = a[j];
            }
            a[j + 1] = element;
        }
    }
}