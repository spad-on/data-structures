package com.paolotalks.graph;

public interface SimpleGraph<E extends Edge> extends Graph<E> {

    void addEdge(int from, int to);
}
