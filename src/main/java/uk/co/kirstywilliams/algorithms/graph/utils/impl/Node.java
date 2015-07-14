/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils.impl;

import gnu.trove.map.hash.THashMap;
import gnu.trove.procedure.TObjectProcedure;
import uk.co.kirstywilliams.algorithms.graph.BreadthFirstSearch;
import uk.co.kirstywilliams.algorithms.graph.utils.Coordinate;
import uk.co.kirstywilliams.algorithms.graph.utils.IEdge;
import uk.co.kirstywilliams.algorithms.graph.utils.INode;
import uk.co.kirstywilliams.algorithms.graph.utils.edge.Edge;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * A default graph node with weight
 * and a coordinate location on a 2D
 * plane.
 *
 * @author kirsty
 */
public class Node implements INode {

    /* reference to the edge class used. */
    private Class<? extends IEdge> edgeClass = Edge.class;

    /* the coordinate location of the node in the plane */
    private Coordinate location = new Coordinate();

    /* cache, lookup by node */
    protected final THashMap<INode, IEdge> cache;

    /* nodes weight */
    private double weight;

    /* nodes label */
    private String label;

    /**
     * Instantiates a node with (x, y) coordinates and
     * an intial cache size.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param cacheSize the initial cache size.
     */
    public Node(final double x, final double y, final int cacheSize) {
        setLocation(x, y);
        cache = new THashMap<>(cacheSize);
    }

    /**
     * Instantiates a node with an initial cache size.
     *
     * @param cacheSize the initial cache size.
     */
    public Node(final int cacheSize) {
        cache = new THashMap<>(cacheSize);
    }

    /**
     * Instantiates a node with the same properties as
     * the specified node.
     *
     * @param n the node to base this node on.
     */
    public Node(final INode n) {
        setLocation(n.getLocation());
        cache = new THashMap<>(n.getDegree());
    }

    /**
     * Add to the cache.
     *
     * @param e the edge to add.
     */
    private void addCache(final IEdge e) {
        cache.put(e.getTargetNode(), e);
    }

    /**
     * Add edge to this node.
     *
     * @param e the edge to add.
     * @return the added edge.
     */
    public IEdge addEdge(final IEdge e) {
        addCache(e);
        return e;
    }

    /**
     * Add edge to this node.
     *
     * @param n the node connected to the edge to add.
     * @return the added edge.
     */
    public final IEdge addEdge(final INode n) {
        final IEdge e = getEdge(n);

        if (null != e) {
            return e;
        }

        return addEdge(createEdge(this, n));
    }

    /**
     * Add edge to this node.
     *
     * @param n the node connected to the edge to add.
     * @param weight the weight for the edge.
     * @return the added edge.
     */
    public final IEdge addEdge(final INode n, final double weight) {
        final IEdge e = addEdge(n);
        e.setWeight(weight);

        return e;
    }

    /**
     * Clear the cache.
     */
    public void clearEdges() {
        cache.clear();
    }

    /**
     * Create an edge between the specified nodes.
     *
     * @param n the source node.
     * @param m the target node.
     * @return the created edge.
     */
    private IEdge createEdge(final INode n, final INode m) {
        IEdge e = null;

        try {
            Constructor<? extends IEdge> c = edgeClass.getDeclaredConstructor(
                    INode.class, INode.class
            );
            e = c.newInstance(n, m);
        } catch (IllegalArgumentException |
                NoSuchMethodException |
                InstantiationException |
                IllegalAccessException |
                InvocationTargetException |
                SecurityException exception) {
            exception.printStackTrace();
        }

        return e;
    }

    /**
     * Delete an edge from the cache.
     *
     * @param e the edge to delete.
     * @return the deleted edge.
     */
    public final IEdge deleteEdge(final IEdge e) {
        removeCache(e);
        return e;
    }

    /**
     * Delete an edge from the cache.
     *
     * @param n the node connected to the edge to delete.
     * @return the deleted edge.
     */
    public final IEdge deleteEdge(final INode n) {
        return removeCache(n);
    }

    /**
     * Iterate over each neighbour applying the procedure.
     *
     * @param eachNode the procedure to apply.
     */
    public final void eachNeighbour(final TObjectProcedure<INode> eachNode) {
        cache.forEachKey(eachNode);
    }

    /**
     * Get the degree for this node.
     * (cache size)
     *
     * @return this nodes degree.
     */
    public final int getDegree() {
        return cache.size();
    }

    /**
     * Get the eccentricity, i.e. the max distance
     * between this and any other node.
     * (uses breadth-first search)
     *
     * @return this nodes eccentricity
     */
    public final int getEccentricity() {
        final BreadthFirstSearch bfs = new BreadthFirstSearch();
        bfs.findAll(this);

        return bfs.getMaxDepth();
    }

    /**
     * Get the edge connecting this node to the specified
     * node.
     *
     * @param n the node connected to the desired edge.
     * @return the edge
     */
    public final IEdge getEdge(INode n) {
        return cache.get(n);
    }

    /**
     * Get the edge class being used.
     *
     * @return the edge class.
     */
    public Class<? extends IEdge> getEdgeClass() {
        return edgeClass;
    }

    /**
     * Get all edges connected to this node.
     *
     * @return all edges in the cache.
     */
    public final Set<IEdge> getEdges() {
        return (Set<IEdge>) cache.values();
    }

    /**
     * Get the euclidean distance between this node
     * and the specified node.
     *
     * @param n the node to compute the distance between.
     * @return the euclidean distance.
     */
    public final double getEuclideanDistance(INode n) {
        return location.getDistance(n.getLocation());
    }

    /**
     * Get the label for this node.
     * (will be null unless explicitly set.)
     * @return this node's label.
     */
    public final String getLabel() {
        return label;
    }

    /**
     * Set the label for this node.
     *
     * @param label the new label.
     */
    public final void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Get the location for this node in the 2D plane.
     *
     * @return this node's coordinates.
     */
    public final Coordinate getLocation() {
        return location;
    }

    /**
     * Set the location for this node in the 2D plane.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public final void setLocation(final double x, final double y) {
        location.setLocation(x, y);
    }

    /**
     * Set the location for this node in the 2D plane.
     *
     * @param coord the new coordinates.
     */
    public final void setLocation(final Coordinate coord) {
        location.setLocation(coord);
    }

    /**
     * Get this node's neighbouring nodes.
     *
     * @return this node's neighbours.
     */
    public final Set<INode> getNeighbours() {
        return cache.keySet();
    }

    /**
     * Get the weight of this node.
     * (will be null unless set.)
     *
     * @return this node's weight.
     */
    public final double getWeight() {
        return weight;
    }

    /**
     * Set the weight of this node.
     *
     * @param weight the new weight.
     */
    public final void setWeight(final double weight) {
        this.weight = weight;
    }

    /**
     * Check whether this node is connected to
     * the specified node.
     *
     * @param n the node to lookup.
     * @return whether the nodes are connected.
     */
    public final boolean isConnected(final INode n) {
        return cache.containsKey(n);
    }

    /**
     * Remove the specified edge from the cache.
     *
     * @param e the edge to remove.
     */
    protected void removeCache(final IEdge e) {
        if (null == e) {
            return;
        }

        cache.remove(e.getTargetNode());
    }

    /**
     * Remove the edge connecting this node to the
     * specified node from the cache.
     *
     * @param n the node connected by the edge to be removed.
     * @return the removed edge.
     */
    protected IEdge removeCache(final INode n) {
        final IEdge e = cache.remove(n);

        if (null == e) {
            return null;
        }

        return e;
    }

    /**
     * Set the edge class being used.
     *
     * @param edgeClass reference to the edge class being used.
     */
    public void setEdgeClass(Class<? extends IEdge> edgeClass) {
        this.edgeClass = edgeClass;
    }

    /**
     * toString.
     *
     * @return the location as a representation of this node.
     */
    public final String toString() {
        return location.toString();
    }

}
