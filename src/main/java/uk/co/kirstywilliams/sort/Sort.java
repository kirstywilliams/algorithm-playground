package uk.co.kirstywilliams.sort;

/**
 * A collection of static sort
 * algorithms.
 *
 * Algorithms covered:
 * - Insertion sort
 */
public class Sort {

    /**
     * Generic insertion sort.
     *
     * for i = 1 to n - 1 do
     *   for j = i + 1 downto 2 do
     *     if(A[j] < A[j - 1]) then swap(A[j], a[j - 1])
     *
     * @param a the array to sort
     * @param <T> the object type
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