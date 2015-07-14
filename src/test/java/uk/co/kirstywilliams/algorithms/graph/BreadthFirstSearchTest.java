/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph;

import org.junit.Before;
import org.junit.Test;
import uk.co.kirstywilliams.algorithms.graph.utils.Edge;
import uk.co.kirstywilliams.algorithms.graph.utils.Node;
import uk.co.kirstywilliams.algorithms.graph.utils.edge.IEdge;
import uk.co.kirstywilliams.algorithms.graph.utils.node.INode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the breadth first search algorithm.
 *
 * @author kirsty
 */
public class BreadthFirstSearchTest {

    List<INode> tree;

    @Before
    public void setupTree() {

        tree = new ArrayList<>();

        // create nodes
        INode node0 = new Node(1);
        node0.setLabel("0");
        INode node1 = new Node(3);
        node1.setLabel("1");
        INode node2 = new Node(3);
        node2.setLabel("2");
        INode node3 = new Node(2);
        node3.setLabel("3");
        INode node4 = new Node(2);
        node4.setLabel("4");
        INode node5 = new Node(3);
        node5.setLabel("5");
        INode node6 = new Node(2);
        node6.setLabel("6");

        // create edges
        IEdge edge1 = new Edge(node0, node1);
        IEdge edge1b = new Edge(node1, node0);
        IEdge edge2 = new Edge(node1, node4);
        IEdge edge2b = new Edge(node4, node1);
        IEdge edge3 = new Edge(node1, node5);
        IEdge edge3b = new Edge(node5, node1);
        IEdge edge4 = new Edge(node2, node3);
        IEdge edge4b = new Edge(node3, node2);
        IEdge edge5 = new Edge(node2, node4);
        IEdge edge5b = new Edge(node4, node2);
        IEdge edge6 = new Edge(node2, node5);
        IEdge edge6b = new Edge(node5, node2);
        IEdge edge7 = new Edge(node3, node6);
        IEdge edge7b = new Edge(node6, node3);
        IEdge edge8 = new Edge(node5, node6);
        IEdge edge8b = new Edge(node6, node5);

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

        INode n0 = tree.get(0);
        assertEquals("Node 0 should have degree of 1", n0.getDegree(), 1);

        INode n1 = tree.get(1);
        assertEquals("Node 1 should have degree of 3", n1.getDegree(), 3);

        INode n2 = tree.get(2);
        assertEquals("Node 2 should have degree of 3", n2.getDegree(), 3);

        INode n3 = tree.get(3);
        assertEquals("Node 3 should have degree of 2", n3.getDegree(), 2);

        INode n4 = tree.get(4);
        assertEquals("Node 4 should have degree of 2", n4.getDegree(), 2);

        INode n5 = tree.get(5);
        assertEquals("Node 5 should have degree of 3", n5.getDegree(), 3);

        INode n6 = tree.get(6);
        assertEquals("Node 6 should have degree of 2", n6.getDegree(), 2);

    }

    /**
     * Test that the BFS performed on each node in the tree
     * computes the correct maximum depth.
     */
    @Test
    public void breadthFirstSearchShouldReturnACorrectMaxDepthForEachNodeInTheTree() {

        INode n0 = tree.get(0);
        BreadthFirstSearch bfs0 = new BreadthFirstSearch();
        bfs0.findAll(n0);
        assertEquals("Node 0 should have max depth of 4", bfs0.getMaxDepth(), 4);


        INode n1 = tree.get(1);
        BreadthFirstSearch bfs1 = new BreadthFirstSearch();
        bfs1.findAll(n1);
        assertEquals("Node 1 should have max depth of 3", bfs1.getMaxDepth(), 3);


        INode n2 = tree.get(2);
        BreadthFirstSearch bfs2 = new BreadthFirstSearch();
        bfs2.findAll(n2);
        assertEquals("Node 2 should have max depth of 3", bfs2.getMaxDepth(), 3);


        INode n3 = tree.get(3);
        BreadthFirstSearch bfs3 = new BreadthFirstSearch();
        bfs3.findAll(n3);
        assertEquals("Node 3 should have max depth of 4", bfs3.getMaxDepth(), 4);


        INode n4 = tree.get(4);
        BreadthFirstSearch bfs4 = new BreadthFirstSearch();
        bfs4.findAll(n4);
        assertEquals("Node 4 should have max depth of 3", bfs4.getMaxDepth(), 3);


        INode n5 = tree.get(5);
        BreadthFirstSearch bfs5 = new BreadthFirstSearch();
        bfs5.findAll(n5);
        assertEquals("Node 5 should have max depth of 2", bfs5.getMaxDepth(), 2);


        INode n6 = tree.get(6);
        BreadthFirstSearch bfs6 = new BreadthFirstSearch();
        bfs6.findAll(n6);
        assertEquals("Node 6 should have max depth of 3", bfs6.getMaxDepth(), 3);

    }
}
