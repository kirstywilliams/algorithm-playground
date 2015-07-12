/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils.base;

import java.io.Serializable;

/**
 * Represent a basic Edge with a source and
 * target node.
 *
 * @author kirsty
 */
public abstract class AbstractEdge implements Serializable {

    private static final long serialVersionUID = -2390442344472728903L;

    /* edge source node */
    protected final AbstractNode fromNode;
    /* edge target node */
    protected final AbstractNode toNode;

    /**
     * Constructor.
     *
     * @param fromNode source node
     * @param toNode target node
     */
    public AbstractEdge(final AbstractNode fromNode, final AbstractNode toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

    /**
     * Gets the source node for the edge.
     *
     * @return source node.
     */
    public final AbstractNode getFromNode() {
        return fromNode;
    }

    /**
     * Gets the target node for the edge.
     *
     * @return target node.
     */
    public final AbstractNode getToNode() {
        return toNode;
    }

    @Override
    public String toString() {
        return "Edge{" + fromNode + ", " + toNode + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEdge)) return false;

        AbstractEdge that = (AbstractEdge) o;

        return !(getFromNode() != null ? !getFromNode().equals(that.getFromNode()) : that.getFromNode() != null)
                && !(getToNode() != null ? !getToNode().equals(that.getToNode()) : that.getToNode() != null);
    }

    @Override
    public int hashCode() {
        int result = getFromNode() != null ? getFromNode().hashCode() : 0;
        result = 31 * result + (getToNode() != null ? getToNode().hashCode() : 0);
        return result;
    }
}
