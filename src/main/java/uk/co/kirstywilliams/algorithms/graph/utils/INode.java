/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils;

import gnu.trove.procedure.TObjectProcedure;

import java.util.Set;

/**
 * Simple interface for node objects.
 *
 * @author kirsty
 */
public interface INode {

    String getLabel();
    void setLabel(final String label);
    double getWeight();
    void setWeight(final double weight);
    Coordinate getLocation();
    void setLocation(final Coordinate coordinate);
    void setLocation(final double x, final double y);

    IEdge getEdge(INode n);
    Set<IEdge> getEdges();
    IEdge addEdge(IEdge e);
    IEdge addEdge(final INode n);
    IEdge addEdge(final INode n, final double weight);
    void clearEdges();
    IEdge deleteEdge(IEdge e);
    IEdge deleteEdge(INode n);

    void eachNeighbour(final TObjectProcedure<INode> eachNode);
    int getEccentricity();
    Set<INode> getNeighbours();
    boolean isConnected(INode n);
    double getEuclideanDistance(INode n);

    int getDegree();

}