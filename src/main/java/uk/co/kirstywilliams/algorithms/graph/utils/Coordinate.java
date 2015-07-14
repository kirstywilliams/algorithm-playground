/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils;

/**
 * Object representing a point on a 2D plane.
 *
 * @author kirsty
 */
public class Coordinate {

    /* The X coordinate */
    private double xCoord;
    /* The Y coordinate */
    private double yCoord;

    public Coordinate() { /* intentionally blank */ }

    /**
     * Instantiate a new Coordinate.
     *
     * @param xCoord the x coordinate
     * @param yCoord the y coordinate
     */
    public Coordinate(double xCoord, double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * Gets the X coordinate.
     *
     * @return the x coordinate.
     */
    public double getxCoord() {
        return xCoord;
    }

    /**
     * Sets the x coordinate.
     *
     * @param xCoord the new x coordinate.
     */
    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Gets the y coordinate.
     *
     * @return the y coordinate.
     */
    public double getyCoord() {
        return yCoord;
    }

    /**
     * Sets the y coordinate.
     *
     * @param yCoord the new y coordinate.
     */
    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * Updates the x and y coordinates based
     * on the specified Coordinate object.
     *
     * @param coord the new Coordinate.
     */
    public void setLocation(Coordinate coord) {
        this.xCoord = coord.getxCoord();
        this.yCoord = coord.getyCoord();
    }

    /**
     * Sets the x and y coordinates based on
     * the specified coordinates.
     *
     * @param xCoord the new x coordinate.
     * @param yCoord the new y coordinate.
     */
    public void setLocation(double xCoord, double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * Computes the euclidean distance between
     * this coordinate and the specified
     * Coordinate.
     *
     * @param coord the coordinate to compute difference.
     * @return the euclidean distance of this and the specified Coordinate.
     */
    public double getDistance(Coordinate coord) {
        return Math.sqrt(
                // (a.x - b.x)^2
                ((this.xCoord - coord.xCoord) * (this.xCoord - coord.xCoord)) +
                        // (a.y - b.y)^2
                        ((this.yCoord - coord.yCoord) * (this.yCoord - coord.yCoord)));
    }
}
