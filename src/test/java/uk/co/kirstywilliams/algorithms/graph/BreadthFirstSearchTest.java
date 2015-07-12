/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph;

import org.junit.Before;
import org.junit.Test;
import uk.co.kirstywilliams.algorithms.graph.utils.DefaultEdge;
import uk.co.kirstywilliams.algorithms.graph.utils.DefaultNode;
import uk.co.kirstywilliams.algorithms.graph.utils.base.AbstractEdge;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the breadth first search algorithm.
 *
 * @author kirsty
 */
public class BreadthFirstSearchTest {

    List<DefaultNode> tree;

    @Before
    public void setupTree() {

        tree = new ArrayList<>();

        // create nodes
        DefaultNode node0 = new DefaultNode(1, "0");
        DefaultNode node1 = new DefaultNode(3, "1");
        DefaultNode node2 = new DefaultNode(3, "2");
        DefaultNode node3 = new DefaultNode(2, "3");
        DefaultNode node4 = new DefaultNode(2, "4");
        DefaultNode node5 = new DefaultNode(3, "5");
        DefaultNode node6 = new DefaultNode(2, "6");

        // create edges
        AbstractEdge edge1 = new DefaultEdge(node0, node1);
        AbstractEdge edge1b = new DefaultEdge(node1, node0);
        AbstractEdge edge2 = new DefaultEdge(node1, node4);
        AbstractEdge edge2b = new DefaultEdge(node4, node1);
        AbstractEdge edge3 = new DefaultEdge(node1, node5);
        AbstractEdge edge3b = new DefaultEdge(node5, node1);
        AbstractEdge edge4 = new DefaultEdge(node2, node3);
        AbstractEdge edge4b = new DefaultEdge(node3, node2);
        AbstractEdge edge5 = new DefaultEdge(node2, node4);
        AbstractEdge edge5b = new DefaultEdge(node4, node2);
        AbstractEdge edge6 = new DefaultEdge(node2, node5);
        AbstractEdge edge6b = new DefaultEdge(node5, node2);
        AbstractEdge edge7 = new DefaultEdge(node3, node6);
        AbstractEdge edge7b = new DefaultEdge(node6, node3);
        AbstractEdge edge8 = new DefaultEdge(node5, node6);
        AbstractEdge edge8b = new DefaultEdge(node6, node5);

        // add the edges to the nodes
        node0.addEdge(edge1);
        node1.addEdge(edge1b);
        node1.addEdge(edge2);
        node1.addEdge(edge3);
        node2.addEdge(edge4);
        node2.addEdge(edge5);
        node2.addEdge(edge6);
        node3.addEdge(edge4b);
        node3.addEdge(edge7);
        node4.addEdge(edge2b);
        node4.addEdge(edge5b);
        node5.addEdge(edge3b);
        node5.addEdge(edge6b);
        node5.addEdge(edge8);
        node6.addEdge(edge7b);
        node6.addEdge(edge8b);

        // add the nodes to the tree
        tree.add(node0);
        tree.add(node1);
        tree.add(node2);
        tree.add(node3);
        tree.add(node4);
        tree.add(node5);
        tree.add(node6);
    }

    /**
     * Test that the tree has been set up correctly.
     */
    @Test
    public void nodesInConstructedTreeShouldHaveCorrectDegree() {

        DefaultNode n0 = tree.get(0);
        assertEquals("Node 0 should have degree of 1", n0.getOutDegree(), 1);

        DefaultNode n1 = tree.get(1);
        assertEquals("Node 1 should have degree of 3", n1.getOutDegree(), 3);

        DefaultNode n2 = tree.get(2);
        assertEquals("Node 2 should have degree of 3", n2.getOutDegree(), 3);

        DefaultNode n3 = tree.get(3);
        assertEquals("Node 3 should have degree of 2", n3.getOutDegree(), 2);

        DefaultNode n4 = tree.get(4);
        assertEquals("Node 4 should have degree of 2", n4.getOutDegree(), 2);

        DefaultNode n5 = tree.get(5);
        assertEquals("Node 5 should have degree of 3", n5.getOutDegree(), 3);

        DefaultNode n6 = tree.get(6);
        assertEquals("Node 6 should have degree of 2", n6.getOutDegree(), 2);

    }

    /**
     * Test that the BFS performed on each node in the tree
     * computes the correct maximum depth.
     */
    @Test
    public void breadthFirstSearchShouldReturnACorrectMaxDepthForEachNodeInTheTree() {

        DefaultNode n0 = tree.get(0);
        BreadthFirstSearch bfs0 = new BreadthFirstSearch();
        bfs0.findAll(n0);
        assertEquals("Node 0 should have max depth of 4", bfs0.getMaxDepth(), 4);


        DefaultNode n1 = tree.get(1);
        BreadthFirstSearch bfs1 = new BreadthFirstSearch();
        bfs1.findAll(n1);
        assertEquals("Node 1 should have max depth of 3", bfs1.getMaxDepth(), 3);


        DefaultNode n2 = tree.get(2);
        BreadthFirstSearch bfs2 = new BreadthFirstSearch();
        bfs2.findAll(n2);
        assertEquals("Node 2 should have max depth of 3", bfs2.getMaxDepth(), 3);


        DefaultNode n3 = tree.get(3);
        BreadthFirstSearch bfs3 = new BreadthFirstSearch();
        bfs3.findAll(n3);
        assertEquals("Node 3 should have max depth of 4", bfs3.getMaxDepth(), 4);


        DefaultNode n4 = tree.get(4);
        BreadthFirstSearch bfs4 = new BreadthFirstSearch();
        bfs4.findAll(n4);
        assertEquals("Node 4 should have max depth of 3", bfs4.getMaxDepth(), 3);


        DefaultNode n5 = tree.get(5);
        BreadthFirstSearch bfs5 = new BreadthFirstSearch();
        bfs5.findAll(n5);
        assertEquals("Node 5 should have max depth of 2", bfs5.getMaxDepth(), 2);


        DefaultNode n6 = tree.get(6);
        BreadthFirstSearch bfs6 = new BreadthFirstSearch();
        bfs6.findAll(n6);
        assertEquals("Node 6 should have max depth of 3", bfs6.getMaxDepth(), 3);

    }
}
