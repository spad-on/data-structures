package com.paolotalks.graph;

import java.util.HashSet;

public class DirectedGraph extends AbstractGraph<Edge> implements SimpleGraph<Edge> {

    @Override
    public void addEdge(int from, int to) {
        Node src = getOrCreateNode(from);
        Node tgt = getOrCreateNode(to);
        addEdgeInternal(src, tgt);
    }

    protected void addEdgeInternal(Node src, Node tgt){
        edges.computeIfAbsent(src, s -> new HashSet<>())
             .add(new Edge(src, tgt));
    }

}
