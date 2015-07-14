/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils;

import java.util.Arrays;

/**
 * A simple, generic matrix for storing
 * objects.
 *
 * @param <T> the object type to store in the matrix.
 *
 * @author kirsty
 */
public abstract class AbstractMatrix<T> {

    /* The Object Matrix */
    protected T[][] matrix;

    /**
     * Create a matrix with the specified number
     * of rows and columns.
     *
     * @param numRows the number of rows.
     * @param numCols the number of columns.
     */
    @SuppressWarnings("unchecked")
    protected AbstractMatrix(int numRows, int numCols) {
        this.matrix = (T[][]) (new Object[numRows][numCols]);
    }

    /**
     * Create a matrix that's a clone
     * of another matrix.
     *
     * @param matrix the matrix to clone.
     */
    protected AbstractMatrix(T[][] matrix) {
        this.matrix = (T[][]) (new Object[matrix.length][]);

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i].length != matrix[0].length) {
                throw new IllegalArgumentException("All rows must have the same length");
            }

            this.matrix[i] = matrix[i].clone();
        }
    }

    /**
     * Set the matrix entry at the specified
     * location.
     *
     * @param row the row location.
     * @param col the column location.
     * @param entry the object to store.
     */
    protected void set(int row, int col, T entry) {
        matrix[row][col] = entry;
    }

    /**
     * Return the entry at the specified
     * location in the matrix.
     *
     * @param row the row location.
     * @param col the column location.
     * @return the entry in the matrix.
     */
    protected T get(int row, int col) {
        return matrix[row][col];
    }

    /**
     * Get the number of rows in the matrix.
     *
     * @return the number of rows.
     */
    protected int getRows() {
        return matrix.length;
    }

    /**
     * Get the number of columns in the matrix.
     *
     * @return the number of columns.
     */
    protected int getCols() {
        return matrix[0].length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractMatrix)) return false;

        AbstractMatrix<?> matrix1 = (AbstractMatrix<?>) o;

        return Arrays.deepEquals(matrix, matrix1.matrix);

    }

    @Override
    public int hashCode() {
        return matrix != null ? Arrays.deepHashCode(matrix) : 0;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }

    public void print() {
        for (T[] row : matrix) {
            //for each number in the row
            for (T j : row) {
                System.out.print(j.toString() + " ");
            }
            System.out.println();
        }
    }
}
