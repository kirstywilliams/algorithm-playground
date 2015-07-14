/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Before;
import org.junit.Test;
import uk.co.kirstywilliams.algorithms.graph.utils.impl.Edge;
import uk.co.kirstywilliams.algorithms.graph.utils.impl.Node;
import uk.co.kirstywilliams.algorithms.graph.utils.PathMatrix;
import uk.co.kirstywilliams.algorithms.graph.utils.IEdge;
import uk.co.kirstywilliams.algorithms.graph.utils.INode;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests the nearest neighbour algorithm.
 *
 * @author kirsty
 */
public class NearestNeighbourTSPTest {

    BiMap<Integer, INode> hubMap;
    PathMatrix distances;

    @Before
    public void setupTreeMap() {

        hubMap = HashBiMap.create();

        // create nodes
        INode nodeA = new Node(3);
        nodeA.setLabel("A");
        INode nodeB = new Node(3);
        nodeB.setLabel("B");
        INode nodeC = new Node(3);
        nodeC.setLabel("C");
        INode nodeD = new Node(3);
        nodeD.setLabel("D");

        // create edges
        IEdge edge1 = new Edge(nodeA, nodeB);
        IEdge edge1b = new Edge(nodeB, nodeA);
        IEdge edge2 = new Edge(nodeA, nodeC);
        IEdge edge2b = new Edge(nodeC, nodeA);
        IEdge edge3 = new Edge(nodeA, nodeD);
        IEdge edge3b = new Edge(nodeD, nodeA);
        IEdge edge4 = new Edge(nodeB, nodeC);
        IEdge edge4b = new Edge(nodeC, nodeB);
        IEdge edge5 = new Edge(nodeB, nodeD);
        IEdge edge5b = new Edge(nodeD, nodeB);
        IEdge edge6 = new Edge(nodeC, nodeD);
        IEdge edge6b = new Edge(nodeD, nodeC);

        // add the edges to the nodes
        nodeA.addEdge(edge1);
        nodeB.addEdge(edge1b);
        nodeA.addEdge(edge2);
        nodeC.addEdge(edge2b);
        nodeA.addEdge(edge3);
        nodeD.addEdge(edge3b);
        nodeB.addEdge(edge4);
        nodeC.addEdge(edge4b);
        nodeB.addEdge(edge5);
        nodeD.addEdge(edge5b);
        nodeC.addEdge(edge6);
        nodeD.addEdge(edge6b);

        // add the nodes to the tree
        hubMap.put(0, nodeA);
        hubMap.put(1, nodeB);
        hubMap.put(2, nodeC);
        hubMap.put(3, nodeD);
    }

    /**
     * Set up the path costs.
     */
    @Before
    public void setupDistances() {

        distances = new PathMatrix(4);
        distances.setCost(0, 1, 20); // (A,B)
        distances.setCost(1, 0, 20); // (B,A)
        distances.setCost(0, 2, 42); // (A,C)
        distances.setCost(2, 0, 42); // (C,A)
        distances.setCost(0, 3, 35); // (A,D)
        distances.setCost(3, 0, 35); // (D,A)
        distances.setCost(1, 2, 30); // (B,C)
        distances.setCost(2, 1, 30); // (C,B)
        distances.setCost(1, 3, 34); // (B,D)
        distances.setCost(3, 1, 34); // (D,B)
        distances.setCost(2, 3, 12); // (C,D)
        distances.setCost(3, 2, 12); // (D,C)

    }

    /**
     * Test that the tree has been set up correctly.
     */
    @Test
    public void nodesInConstructedTreeShouldHaveCorrectDegree() {

        INode A = hubMap.get(0);
        assertEquals("Node A should have degree of 3", A.getDegree(), 3);

        INode B = hubMap.get(1);
        assertEquals("Node 1 should have degree of 3", B.getDegree(), 3);

        INode C = hubMap.get(2);
        assertEquals("Node 2 should have degree of 3", C.getDegree(), 3);

        INode D = hubMap.get(3);
        assertEquals("Node 3 should have degree of 3", D.getDegree(), 3);


    }

    /**
     * Tests the result of computing the nearest neighbour.
     */
    @Test
    public void nearestNeighbourShouldComputeCorrectMinimumCostStartingAtZero() {

        NearestNeighbourTSP nn = new NearestNeighbourTSP(distances, hubMap, 0);

        List<Integer> route = nn.execute();

        Iterator iterator = route.iterator();

        String result = "";
        while (iterator.hasNext()) {
            Integer hub = (Integer) iterator.next();
            result += hub;
        }

        assertEquals("Result should equal 0 -> 1 -> 2 -> 3 -> 0, i.e. 0123.", result, "0123");
    }


}
