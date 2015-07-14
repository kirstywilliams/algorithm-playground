/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph;

import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.hash.THashSet;
import uk.co.kirstywilliams.algorithms.graph.utils.node.INode;

import java.util.ArrayDeque;
import java.util.Queue;


/**
 * Finds all the nodes reachable from the a given source node, s.
 * BFS traverses a connected component of a given graph and defines
 * a spanning tree.
 *
 * @author kirsty
 */
public class BreadthFirstSearch {

    private TObjectIntMap<INode> depth;
    private int maxDepth = Integer.MIN_VALUE;
    private EachNode eachNode = new EachNode();

    /**
     * Object Procedure for each node.
     */
    private class EachNode implements TObjectProcedure<INode> {

        /* visited nodes */
        THashSet<INode> visited;
        /* the depth of the successor node */
        int successorDepth;
        public INode n;
        public Queue<INode> queue;
        public int depthLimit;

        /**
         * Execute the procedure on the node.
         *
         * @param m the node
         * @return result of execution
         */
        public boolean execute(INode m) {
            if (visited.contains(m))
                return true;

            if (!depth.containsKey(m)) {
                successorDepth = depth.get(n) + 1;

                if (successorDepth > getMaxDepth()) {
                    setMaxDepth(successorDepth);
                }

                if (successorDepth > depthLimit) {
                    return true;
                }

                depth.put(m, successorDepth);
                queue.add(m);
            }
            return true;
        }
    }

    /**
     * Find all reachable nodes from the source node.
     *
     * @param source the source node.
     * @return set of all reachable nodes.
     */
    public final THashSet<INode> findAll(final INode source) {
        return findAll(source, Integer.MAX_VALUE, 255);
    }

    /**
     * Find all reachable nodes from the source node.
     *
     * @param source starting node
     * @param totalNodes total number of nodes in the graph
     * @return set of all connected nodes
     */
    public final THashSet<INode> findAll(final INode source, final int totalNodes) {
        return findAll(source, Integer.MAX_VALUE, totalNodes);
    }

    /**
     * Find all reachable nodes from the source node.
     *
     * @param source starting node
     * @param depthLimit depth limit
     * @param totalNodes total number of nodes in the graph
     * @return set of all connected nodes
     */
    public final THashSet<INode> findAll(final INode source, final int depthLimit, final int totalNodes) {

        if (source.getDegree() == 0) {
            return emptyTree(source);
        }

        // initialise queue and visited node set
        final Queue<INode> queue = new ArrayDeque<>(totalNodes);
        final THashSet<INode> visited = new THashSet<>(totalNodes);

        depth = new TObjectIntHashMap<>(totalNodes, 0.5f, Integer.MIN_VALUE);
        setMaxDepth(Integer.MIN_VALUE); // reset

        // start with the source node.
        queue.add(source);
        depth.put(source, 0);
        INode m;

        eachNode.depthLimit = depthLimit;
        eachNode.queue = queue;
        eachNode.visited = visited;
        while (!queue.isEmpty()) {
            m = queue.poll();
            // mark node as visited
            visited.add(m);

            // Enqueue successors
            eachNode.n = m;
            m.eachNeighbour(eachNode);
        }

        // return set of all reachable nodes from the source node.
        return visited;
    }

    /**
     * Gets an empty tree with only the source node.
     *
     * @param source the source node.
     * @return new tree set.
     */
    private THashSet<INode> emptyTree(INode source) {
        final THashSet<INode> visited = new THashSet<>(1);
        visited.add(source);

        return visited;
    }

    /**
     * Gets the tree depth.
     *
     * @return the depth.
     */
    public final TObjectIntMap<INode> getDepth() {
        return depth;
    }

    /**
     * Gets the depth of a particular node in the tree.
     *
     * @return the depth.
     */
    public final int getDepth(final INode v) {
        return depth.get(v);
    }

    /**
     * Gets the maximum depth.
     *
     * @return the maximum depth.
     */
    public final int getMaxDepth() {
        return maxDepth;
    }

    /**
     * Sets the maximum depth.
     *
     * @param maxDepth the new maximum depth.
     */
    private void setMaxDepth(final int maxDepth) {
        this.maxDepth = maxDepth;
    }
}
