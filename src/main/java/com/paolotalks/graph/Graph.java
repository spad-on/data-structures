package com.paolotalks.graph;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

public interface Graph<E extends Edge> {

    Node getNode(int id);
    Set<E> getEdges(int nodeId);
    Set<E> getEdges(Node node);
    Collection<Node> nodeSet();
    Stream<Node> nodes();
    Stream<E> edges();
    Collection<E> edgeSet();
    boolean isEmpty();
    int size();
    void addNode(int id);
}
