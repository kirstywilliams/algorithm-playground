/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils;

import uk.co.kirstywilliams.algorithms.graph.utils.base.AbstractEdge;
import uk.co.kirstywilliams.algorithms.graph.utils.base.AbstractNode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * A default node.
 *
 * @author kirsty
 */
public class DefaultNode extends AbstractNode {

    /**
     * The edge type that this node uses.
     */
    private Class<? extends AbstractEdge> edgeClass = DefaultEdge.class;

    /**
     * Instantiates a node with cache size equal
     * to the specified node's edge count.
     *
     * @param n the node to use when initialising this node's cache.
     */
    public DefaultNode(AbstractNode n, String label) {
        super(n, label);
    }

    /**
     * Initialises the node with initial cache
     * of size edgeCache.
     *
     * @param edgeCache the initial size for the node's cache.
     */
    public DefaultNode(int edgeCache, String label) {
        super(edgeCache, label);
    }

    /**
     * Creates an edge.
     *
     * @param n the node toNode create an edge toNode.
     * @return new edge.
     */
    public AbstractEdge addEdge(final AbstractNode n) {
        // find edge, if exists
        final AbstractEdge e = getEdge(n);

        if (null != e) {
            return e;
        }

        return addEdge(createEdge(this, n));
    }

    /**
     * Creates an edge connecting the two specified nodes.
     *
     * @param n the source node.
     * @param m the target node.
     * @return a edge connecting the two nodes.
     */
    private final AbstractEdge createEdge(final AbstractNode n, final AbstractNode m) {
        AbstractEdge e = null;

        try {
            Constructor<? extends AbstractEdge> c = edgeClass.getDeclaredConstructor(
                    AbstractNode.class, AbstractNode.class);
            e = c.newInstance(n, m);
        } catch (InstantiationException
                | IllegalAccessException
                | IllegalArgumentException
                | InvocationTargetException
                | NoSuchMethodException
                | SecurityException exception) {
            exception.printStackTrace();
        }

        return e;
    }

    /**
     * Gets the type of edge that this node type uses.
     *
     * @return the edge type
     */
    public Class<? extends AbstractEdge> getEdgeClass() {
        return edgeClass;
    }

    /**
     * Sets the type of edge that this node type uses.
     *
     * @param edgeClass the class of the edge to be used.
     */
    public void setEdgeClass(Class<? extends AbstractEdge> edgeClass) {
        this.edgeClass = edgeClass;
    }
}
