/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils;

/**
 * A simple interface for edge objects.
 *
 * @author kirsty
 */
public interface IEdge {

    double getEuclideanDistance();

    INode getSourceNode();
    INode getTargetNode();

    double getWeight();
    void setWeight(final double weight);

}
