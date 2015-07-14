/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils.impl;

import uk.co.kirstywilliams.algorithms.graph.utils.IEdge;
import uk.co.kirstywilliams.algorithms.graph.utils.INode;

/**
 * A default edge with weight.
 *
 * @author kirsty
 */
public class Edge implements IEdge {

    /* The source node of this edge */
    protected final INode sourceNode;
    /* The target node of this edge */
    protected final INode targetNode;
    /* The weight of this edge */
    private double weight;

    /**
     * Instantiates an edge with a source and target node.
     *
     * @param sourceNode the source node.
     * @param targetNode the target node.
     */
    public Edge(final INode sourceNode, final INode targetNode) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
    }

    /**
     * Get the euclidean distance of this edge.
     *
     * @return the euclidean distance of this edge.
     */
    public final double getEuclideanDistance() {
        return getSourceNode().getEuclideanDistance(getTargetNode());
    }

    /**
     * Get the source node of this edge.
     *
     * @return the source node.
     */
    public final INode getSourceNode() {
        return sourceNode;
    }

    /**
     * Get the target node of this edge.
     *
     * @return the target node.
     */
    public final INode getTargetNode() {
        return targetNode;
    }

    /**
     * Get the weight of this edge.
     *
     * @return this edge's associated weight.
     */
    public final double getWeight() {
        return weight;
    }

    /**
     * Set the weight of this edge.
     *
     * @param weight the new weight.
     */
    public final void setWeight(final double weight) {
        this.weight = weight;
    }

    /**
     * toString.
     *
     * @return source and target node representation of this edge.
     */
    public final String toString() {
        return "E[" + sourceNode.toString() + ", " + targetNode.toString() + "]";
    }

}
