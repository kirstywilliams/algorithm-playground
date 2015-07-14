/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph;

import com.google.common.collect.BiMap;
import uk.co.kirstywilliams.algorithms.graph.utils.PathMatrix;
import uk.co.kirstywilliams.algorithms.graph.utils.INode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Starting from some point p0, we walk
 * first to its nearest neighbour p1. From
 * p1, we walk to its nearest unvisited
 * neighbour, thus excluding only p0 as a
 * candidate. We now repeat this process
 * until we run out of unvisited points,
 * at which time we return to p0 to close
 * off the tour.
 *
 * @author kirsty
 */
public class NearestNeighbourTSP {

    /* Bidirectional map for easy lookup */
    private BiMap<Integer, INode> hubMap;
    /* distance matrix */
    private PathMatrix distances;
    /* source hub i.e. where to start the algorithm from. */
    private Integer sourceHub;
    /* the route taken */
    private List<Integer> route;
    /* the total computation time in millis */
    long computationTime;

    /**
     * Instantiates the nearest neighbour algorithm
     * with the specified path matrix,
     * hubmap and source hub.
     *
     * @param matrix the adjacency matrix.
     * @param hubMap the hub map (mapping integer to the node).
     * @param sourceHub the source hub.
     */
    public NearestNeighbourTSP(PathMatrix matrix,
                               BiMap<Integer, INode> hubMap,
                               Integer sourceHub) {
        distances = matrix;
        this.sourceHub = sourceHub;
        this.hubMap = hubMap;
    }

    /**
     * Execute the algorithm.
     *
     * @return the found route.
     */
    public List<Integer> execute() {

        route = new ArrayList<>();
        route.add(sourceHub);

        long startTime = System.currentTimeMillis();
        search(sourceHub);
        long endTime = System.currentTimeMillis();
        computationTime = (endTime - startTime);

        return route;
    }

    /**
     * Search the graph starting at the specified
     * hub node.
     *
     * @param from the hub to start from.
     */
    private void search(Integer from) {

        /* track which nodes we still have to visit */
        Set<Integer> unvisited = new HashSet<>(distances.getNumHubs());
        for (int i = 0; i < distances.getNumHubs(); i++) {
            unvisited.add(i);
        }

        Integer currentHub = from;
        unvisited.remove(currentHub);

        while (!unvisited.isEmpty()) {

            int minDistance = Integer.MAX_VALUE;
            Integer selectedHub = -1;

            for (Integer hub : unvisited) {
                if (!(currentHub == hub)) {
                    int distance = distances.getCost(currentHub, hub);

                    if (distance < minDistance) {
                        minDistance = distance;
                        selectedHub = hub;
                    }
                }
            }

            if (selectedHub == -1) {
                throw new IllegalStateException("attempting to construct illegal route");
            }

            unvisited.remove(selectedHub);
            route.add(selectedHub);
            currentHub = selectedHub;
        }
    }

    /**
     * Get the computation time for the last execution.
     *
     * @return the total execution time in millis.
     */
    public long getComputationTime() {
        return computationTime;
    }
}
