package com.paolotalks.graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestEdge {

    @Test
    public void testEdgeGetters() {
        Node a = new Node(2);
        Node b = new Node(3);
        Edge edge = new Edge(a, b);
        assertEquals(a, edge.getSource());
        assertEquals(b, edge.getTarget());
    }

}
