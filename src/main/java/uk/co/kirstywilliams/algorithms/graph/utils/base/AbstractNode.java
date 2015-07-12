/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils.base;

import gnu.trove.map.hash.THashMap;
import gnu.trove.procedure.TObjectProcedure;
import uk.co.kirstywilliams.algorithms.graph.BreadthFirstSearch;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents a basic Node with local cache.
 *
 * @author kirsty
 */
public abstract class AbstractNode implements Serializable {

    private static final long serialVersionUID = 2614512928270796203L;

    /* Cache, lookup by Node (Node -> Edge) */
    protected final THashMap<AbstractNode, AbstractEdge> cache;

    private String label;

    /**
     * Create a node with an initial edge cache size.
     *
     * @param edgeCache the initial edge cache size
     */
    public AbstractNode(final int edgeCache, String label) {
        cache = new THashMap<AbstractNode, AbstractEdge>(edgeCache);
        this.label = label;
    }

    /**
     * Creates a node with a cache size equal to node degree.
     *
     * @param n node
     */
    public AbstractNode(final AbstractNode n, String label) {
        cache = new THashMap<AbstractNode, AbstractEdge>(n.getOutDegree());
        this.label = label;
    }

    /**
     * Adds an edge to the cache.
     *
     * @param e the edge to add
     */
    private final void addCache(final AbstractEdge e) {
        cache.put(e.getToNode(), e);
    }

    /**
     * Adds an edge to the node's cache.
     *
     * @param e the edge to add
     * @return the added edge
     */
    public AbstractEdge addEdge(AbstractEdge e) {
        addCache(e);
        return e;
    }

    /**
     * Clears the node's cache.
     */
    public void clearEdges() {
        cache.clear();
    }

    /**
     * Deletes an edge from the node's cache.
     *
     * @param e the edge to remove.
     * @return the removed edge.
     */
    public AbstractEdge deleteEdge(AbstractEdge e) {
        removeCache(e);
        return e;
    }

    /**
     * Deletes edge connecting this node to the
     * specified node.
     *
     * @param n node connected to the edge to be removed.
     * @return the removed edge. null if no edge.
     */
    public AbstractEdge deleteEdge(AbstractNode n) {
        return removeCache(n);
    }

    /**
     * Procedure for each neighbour of this node.
     *
     * @param eachNode the object (node) procedure
     */
    public void eachNeighbour(final TObjectProcedure<AbstractNode> eachNode) {
        cache.forEachKey(eachNode);
    }

    /**
     * Gets the out degree of the node.
     *
     * @return the node's out degree
     */
    public int getOutDegree() {
        return cache.size();
    }

    /**
     * The greatest distance between this node and
     * every other node.  It can be thought of as
     * how far this node is from the node most distant
     * from it in the graph. Uses BreadthFirstSearch.
     *
     * @return the greatest distance between this node and every other node.
     */
    public int getEccentricity() {
        final BreadthFirstSearch bfs = new BreadthFirstSearch();
        bfs.findAll(this);

        return bfs.getMaxDepth();
    }

    /**
     * Gets the edge connecting this node to the
     * specified node.
     *
     * @param n the node connected to this node via the desired edge.
     * @return the edge connecting this and the specified node.
     */
    public AbstractEdge getEdge(AbstractNode n) {
        return cache.get(n);
    }

    /**
     * Gets all edges connected to this node.
     *
     * @return the node's edges
     */
    public Set<AbstractEdge> getEdges() {
        return (Set<AbstractEdge>) cache.values();
    }

    /**
     * Gets this node's neighbours.
     *
     * @return a set of neighbouring nodes.
     */
    public Set<AbstractNode> getNeighbours() {
        return cache.keySet();
    }

    /**
     * Checks whether this node is connected to the
     * specified node.
     *
     * @param n the node to check if connection exists
     * @return whether node is connected to this node.
     */
    public boolean isConnectedTo(AbstractNode n) {
        return cache.containsKey(n);
    }

    /**
     * Reomves the edge from the cache.
     *
     * @param e the edge to remove.
     */
    protected void removeCache(final AbstractEdge e) {
        if (null == e) {
            return;
        }

        cache.remove(e.getToNode());
    }

    /**
     * Removes the edges connecting this node and
     * the specified node from the cache.
     *
     * @param n the node connected to this node via the edge to be removed.
     * @return the removed edge. null if no edge.
     */
    protected AbstractEdge removeCache(final AbstractNode n) {
        final AbstractEdge e = cache.remove(n);

        if (null == e) {
            return null;
        }

        return e;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "AbstractNode{" +
                "label='" + label + '\'' +
                ", degree=" + getOutDegree() +
                '}';
    }
}
