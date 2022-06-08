package com.paolotalks.graph;

import java.util.HashSet;

public class DirectedWeightedGraph extends AbstractGraph<WeightedEdge> implements WeightedGraph<WeightedEdge> {

    @Override
    public void addEdge(int from, int to, int weight) {
        Node src = getOrCreateNode(from);
        Node tgt = getOrCreateNode(to);
        addEdgeInternal(src, tgt, weight);
    }

    protected void addEdgeInternal(Node src, Node tgt, int weight){
        edges.computeIfAbsent(src, s -> new HashSet<>())
             .add(new WeightedEdge(src, tgt, weight));
    }

}
