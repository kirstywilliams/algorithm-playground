/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils;

import uk.co.kirstywilliams.algorithms.graph.utils.matrix.AbstractMatrix;

/**
 * Represents a path matrix, with the distance
 * between hubs (nodes). Assumes integer costs.
 *
 * @author kirsty
 */
public class PathMatrix extends AbstractMatrix<Integer> {

    /* number of hubs in the path */
    private int numHubs;
    /* maximum distance of the path */
    private int maxDistance;

    /**
     * Instantiate a path matrix with the specified
     * number of hubs (nodes).
     *
     * @param numHubs the number of hubs in the matrix.
     */
    public PathMatrix(int numHubs) {
        super(numHubs, numHubs); // n x n matrix

        this.numHubs = numHubs;

        initialiseMatrix();
    }

    /**
     * Instantiate a path matrix with the specified
     * number of hubs (nodes) and the specified
     * maximum distance.
     *
     * @param numHubs the number of hubs in the matrix.
     * @param maxDistance the maximum distance.
     */
    public PathMatrix(int numHubs, int maxDistance) {
        super(numHubs, numHubs); // n x n matrix

        this.numHubs = numHubs;
        this.maxDistance = maxDistance;

        initialiseMatrix();
    }

    /**
     * Set cost between a hub and itself to 0;
     * otherwise initialise with infinitely high
     * value.
     */
    private void initialiseMatrix() {

        final int INF = -Integer.MAX_VALUE;

        for (int i = 0; i < numHubs; i++) {
            for (int j = 0; j < numHubs; j++) {
                if (i == j) {
                    setCost(i, j, 0);
                } else {
                    setCost(i, j, INF);
                    setCost(j, i, INF);
                }
            }
        }
    }

    /**
     * Get the number of hubs in the matrix.
     *
     * @return the number of hubs.
     */
    public int getNumHubs() {
        return numHubs;
    }

    /**
     * Get the maximum distance.
     *
     * @return the maximum distance.
     */
    public int getMaxDistance() {
        return maxDistance;
    }

    /**
     * Set the maximum distance.
     *
     * @param maxDistance the maximum distance.
     */
    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    /**
     * Get the path matrix.
     *
     * @return the matrix.
     */
    public Integer[][] getMatrix() {
        return matrix;
    }

    /**
     * Get the cost between the source and target
     * hubs.
     *
     * @param source the source hub.
     * @param target the target hub.
     * @return the cost associated with these hubs.
     */
    public int getCost(int source, int target) {
        return get(source, target);
    }

    /**
     * Set the cost between the source and target
     * hubs.
     *
     * @param source the source hub.
     * @param target the target hub.
     * @param cost the cost associated with these hubs.
     */
    public void setCost(int source, int target, int cost) {
        set(source, target, cost);
    }

}
