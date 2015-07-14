/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils;

import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.hash.THashSet;
import uk.co.kirstywilliams.algorithms.graph.BreadthFirstSearch;
import uk.co.kirstywilliams.algorithms.graph.utils.impl.Node;

import java.awt.geom.Rectangle2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

/**
 * A simple graph of connected node objects.
 * Nodes and edges may have an associated weight.
 */
public class Graph {

    /* The nodes in this graph */
    private final THashSet<INode> nodes;
    /* The edges in this graph */
    private final THashSet<IEdge> edges;
    /* The node class being used. */
    private Class<? extends INode> nodeClass = Node.class;

    /**
     * Instantiates an empty graph.
     */
    public Graph() {
        nodes = new THashSet<>();
        edges = new THashSet<>();
    }

    /**
     * Instantiates a graph with the specified number of nodes.
     *
     * @param numNodes the number of nodes.
     */
    public Graph(int numNodes) {
        nodes = new THashSet<>(numNodes);
        edges = new THashSet<>();
    }

    /**
     * Instantiates the graph with the specified number of nodes
     * and edge.
     *
     * @param numNodes the number of nodes.
     * @param numEdges the number of edges.
     */
    public Graph(int numNodes, int numEdges) {
        nodes = new THashSet<>(numNodes);
        edges = new THashSet<>(numEdges);
    }

    /**
     * Add the specified node to the graph.
     *
     * @param n the node to add.
     * @return the added node.
     */
    public final INode addNode(INode n) {
        nodes.add(n);

        return n;
    }

    /**
     * Remove all edges for the specified node from the graph.
     *
     * @param n the node to disconnect.
     */
    public final void clearNodeEdges(INode n) {
        edges.removeAll(n.getEdges());
        n.clearEdges();
    }

    /**
     * Create a new directed edge between the two specified
     * nodes.
     *
     * @param source the source node.
     * @param target the target node.
     * @return the new edge.
     */
    public final IEdge createDirectedEdge(final INode source, final INode target) {
        if ((null == source) || (null == target) || (source == target)) {
            return null;
        }

        final IEdge e = source.addEdge(target);
        edges.add(e);

        return e;
    }

    /**
     * Create a new weighted directed edge between the two
     * specified nodes.
     *
     * @param source the source node.
     * @param target the target node.
     * @param weight the weight.
     * @return the new edge.
     */
    public final IEdge createWeightedDirectedEdge(final INode source, final INode target, final double weight) {
        final IEdge e = createDirectedEdge(source, target);
        e.setWeight(weight);

        return e;
    }

    /**
     * Create a new bidirectional edge between the two
     * specified nodes.
     *
     * @param source the source node.
     * @param target the target node.
     * @return whether the new edges have been created.
     */
    public final boolean createBidirectionalEdge(final INode source, final INode target) {
        if ((null == source) || (null == target) || (source == target)) {
            return false;
        }

        edges.add(source.addEdge(target));
        edges.add(target.addEdge(source));

        return true;
    }

    /**
     * Create a new weight bidirectional edge between the
     * two specified nodes.
     *
     * @param source the source node.
     * @param target the target node.
     * @param weight the weight.
     * @return whether the new edges have been created.
     */
    public final boolean createWeightedBidirectionalEdge(final INode source, final INode target, final double weight) {
        if ((null == source) || (null == target) || (source == target)) {
            return false;
        }

        createWeightedDirectedEdge(source, target, weight);
        createWeightedDirectedEdge(target, source, weight);

        return true;
    }

    /**
     * Create a new node with the specified coordinates
     * with the specified initial cache size.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @param cacheSize the initial cache size.
     * @return the new node.
     */
    public final INode createNode(double x, double y, int cacheSize) {
        INode n = null;
        try {
            Constructor<? extends INode> c = nodeClass.getDeclaredConstructor(
                    double.class, double.class, int.class
            );
            n = c.newInstance(cacheSize);
        } catch (NoSuchMethodException |
                SecurityException |
                InstantiationException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException exception) {
            exception.printStackTrace();
        }

        return addNode(n);
    }

    /**
     * Create a new node with the specified initial
     * cache size.
     *
     * @param cacheSize the initial cache size.
     * @return the new node.
     */
    public final INode createNode(int cacheSize) {
        INode n = null;
        try {
            Constructor<? extends INode> c = nodeClass
                    .getDeclaredConstructor(int.class);
            n = c.newInstance(cacheSize);
        } catch (InstantiationException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException |
                NoSuchMethodException |
                SecurityException exception) {
            exception.printStackTrace();
        }

        return addNode(n);
    }

    /**
     * Create a new node at the specified coordinates,
     * with the specified initial cache size.
     *
     * @param position the coordinate position of the node.
     * @param cacheSize the initial cache size.
     * @return the new node.
     */
    public final INode createNode(Coordinate position, int cacheSize) {
        INode n = null;
        try {
            Constructor<? extends INode> c = nodeClass
                    .getDeclaredConstructor(Coordinate.class, int.class);
            n = c.newInstance(position, cacheSize);
        } catch (NoSuchMethodException |
                SecurityException |
                InstantiationException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException exception) {
            exception.printStackTrace();
        }

        return addNode(n);
    }

    /**
     * Create a new node based on the specified node.
     *
     * @param n the node to use in the creation of a new node.
     * @return the new node.
     */
    private INode createNode(final INode n) {
        INode m = null;
        try {
            Constructor<? extends INode> c = nodeClass
                    .getDeclaredConstructor(INode.class);
            m = c.newInstance(n);
        } catch (NoSuchMethodException |
                SecurityException |
                InstantiationException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException exception) {
            exception.printStackTrace();
        }

        return createNode(m);
    }

    /**
     * Delete an edge from the graph.
     *
     * @param e the edge to delete.
     * @return the result of deleting this edge.
     */
    public final boolean deleteEdge(IEdge e) {

        return deleteEdge(e.getSourceNode(), e.getTargetNode());
    }

    /**
     * Delete the edge connecting the two specified
     * nodes from the graph.
     *
     * @param source the source node.
     * @param target the target node.
     * @return the result of deleting the edge.
     */
    public final boolean deleteEdge(INode source, INode target) {
        if ((null == source) || (null == target)) {
            return false;
        }

        edges.remove(source.deleteEdge(target));
        edges.remove(target.deleteEdge(source));
        return true;
    }

    /**
     * Delete all the edges in this graph.
     */
    public final void deleteEdges() {
        edges.clear();
        for (INode n : nodes) {
            n.clearEdges();
        }
    }

    /**
     * Delete all the edges connected to the specified node.
     *
     * @param n the node to delete all edges from.
     */
    public final void deleteEdges(final INode n) {
        final THashSet<IEdge> deleteSet = new THashSet<>();
        for (final IEdge e : edges) {
            if ((e.getSourceNode() != n) && (e.getTargetNode() != n)) {
                continue;
            }

            deleteSet.add(e);
        }

        for (final IEdge e : deleteSet)
            deleteEdge(e);
    }

    /**
     * Delete the specified node from the graph.
     *
     * @param n the node to delete.
     */
    public final void deleteNode(INode n) {
        deleteEdges(n);
        nodes.remove(n);
    }

    /**
     * Empty the graph, deleting all nodes and edges.
     */
    public final void empty() {
        nodes.clear();
        edges.clear();
    }

    /**
     * Apply the procedure for each edge in the graph.
     *
     * @param procedure the procedure to apply.
     */
    public void forEachEdge(final TObjectProcedure<IEdge> procedure) {
        edges.forEach(procedure);
    }

    /**
     * Apply the procedure for each node in the graph.
     *
     * @param procedure the procedure to apply.
     */
    public void forEachVertex(final TObjectProcedure<INode> procedure) {
        nodes.forEach(procedure);
    }

    /**
     * Get the set of all edges in the graph.
     *
     * @return the set of edges.
     */
    public final THashSet<IEdge> getEdges() {
        return edges;
    }

    /**
     * Get the number of edges in the graph.
     *
     * @return the total number of edges.
     */
    public final int getNumEdges() {
        return edges.size();
    }

    /**
     * Get the number of nodes in the graph.
     *
     * @return the total number of nodes.
     */
    public final int getNumNodes() {
        return nodes.size();
    }

    /**
     * Get the node at the specified coordinates.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @return the node if found.
     */
    public final INode getNode(double x, double y) {
        for (INode n : nodes) {
            if ((n.getLocation().getxCoord() == x) && (n.getLocation().getyCoord() == y)) {
                return n;
            }
        }

        return null;
    }

    /**
     * Get the node at the specified coordinates.
     *
     * @param coordinate the coordinates to lookup.
     * @param tolerance the tolerance factor allowed for lookup.
     * @return the node if found.
     */
    public final INode getNode(Coordinate coordinate, double tolerance) {
        for (INode n : nodes) {
            if (coordinate.getDistance(n.getLocation()) < tolerance) {
                return n;
            }
        }
        return null;
    }

    /**
     * Get all nodes in the graph.
     *
     * @return the set of all nodes.
     */
    public final THashSet<INode> getNodes() {
        return nodes;
    }

    /**
     * Get all nodes in the graph within the specified area.
     *
     * @param area the area to lookup.
     * @return the set of nodes found.
     */
    public final Set<INode> getNodes(Rectangle2D area) {
        Set<INode> nodes = new THashSet<INode>();
        for (INode n : getNodes()) {
            if (area.contains(n.getLocation().getxCoord(), n.getLocation().getyCoord()))
                nodes.add(n);
        }

        return nodes;
    }

    /**
     * Check whether the graph is a complete graph.
     *
     * @return whether graph is complete.
     */
    public final boolean isComplete() {
        for (INode n : nodes) {
            if (n.getDegree() != (getNumNodes() - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check whether the graph is a connected graph.
     * (uses breadth-first search)
     *
     * @return whether graph is connected.
     */
    public final boolean isConnected() {
        if (nodes.size() == 0) {
            return true;
        }

        BreadthFirstSearch bfs = new BreadthFirstSearch();
        INode sourceNode = nodes.iterator().next();
        Set<INode> result = bfs.findAll(sourceNode, nodes.size());
        if (result.size() == nodes.size()) {
            return true;
        }

        return false;
    }
}
