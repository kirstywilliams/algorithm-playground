/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph;

import gnu.trove.map.TObjectIntMap;
import gnu.trove.map.hash.TObjectIntHashMap;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.hash.THashSet;
import uk.co.kirstywilliams.algorithms.graph.utils.base.AbstractNode;

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

    private TObjectIntMap<AbstractNode> depth;
    private int maxDepth = Integer.MIN_VALUE;
    private EachNode eachNode = new EachNode();

    /**
     * Object Procedure for each node.
     */
    private class EachNode implements TObjectProcedure<AbstractNode> {

        /* visited nodes */
        THashSet<AbstractNode> visited;
        /* the depth of the successor node */
        int successorDepth;
        public AbstractNode n;
        public Queue<AbstractNode> queue;
        public int depthLimit;

        /**
         * Execute the procedure on the node.
         *
         * @param m the node
         * @return result of execution
         */
        public boolean execute(AbstractNode m) {
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
    public final THashSet<AbstractNode> findAll(final AbstractNode source) {
        return findAll(source, Integer.MAX_VALUE, 255);
    }

    /**
     * Find all reachable nodes from the source node.
     *
     * @param source starting node
     * @param totalNodes total number of nodes in the graph
     * @return set of all connected nodes
     */
    public final THashSet<AbstractNode> findAll(final AbstractNode source, final int totalNodes) {
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
    public final THashSet<AbstractNode> findAll(final AbstractNode source, final int depthLimit, final int totalNodes) {

        if (source.getOutDegree() == 0) {
            return emptyTree(source);
        }

        // initialise queue and visited node set
        final Queue<AbstractNode> queue = new ArrayDeque<>(totalNodes);
        final THashSet<AbstractNode> visited = new THashSet<>(totalNodes);

        depth = new TObjectIntHashMap<>(totalNodes, 0.5f, Integer.MIN_VALUE);
        setMaxDepth(Integer.MIN_VALUE); // reset

        // start with the source node.
        queue.add(source);
        depth.put(source, 0);
        AbstractNode m;

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
    private final THashSet<AbstractNode> emptyTree(AbstractNode source) {
        final THashSet<AbstractNode> visited = new THashSet<>(1);
        visited.add(source);

        return visited;
    }

    /**
     * Gets the tree depth.
     *
     * @return the depth.
     */
    public final TObjectIntMap<AbstractNode> getDepth() {
        return depth;
    }

    /**
     * Gets the depth of a particular node in the tree.
     *
     * @return the depth.
     */
    public final int getDepth(final AbstractNode v) {
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
    private final void setMaxDepth(final int maxDepth) {
        this.maxDepth = maxDepth;
    }
}
