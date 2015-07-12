/*
 * Copyright (c) 2015. Kirsty Williams <kirsty@kirstywilliams.co.uk>
 */

package uk.co.kirstywilliams.algorithms.graph.utils;

import uk.co.kirstywilliams.algorithms.graph.utils.base.AbstractEdge;
import uk.co.kirstywilliams.algorithms.graph.utils.base.AbstractNode;

/**
 * A default edge.
 *
 * @author kirsty
 */
public class DefaultEdge extends AbstractEdge {

    /**
     * Initialises the edge with the source and target nodes.
     *
     * @param from the source node.
     * @param to the target node.
     */
    public DefaultEdge(AbstractNode from, AbstractNode to) {
        super(from, to);
    }
}
