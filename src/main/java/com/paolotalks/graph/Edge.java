package com.paolotalks.graph;

import java.util.Comparator;
import java.util.Objects;

public class Edge implements Comparable<Edge> {

    private final Node source;
    private final Node target;

    public Edge(Node source, Node target){
        this.source = source;
        this.target = target;
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return Objects.equals(source, edge.source) &&
                Objects.equals(target, edge.target);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }

    @Override
    public String toString(){
        return "(" + source + ", " + target + ")";
    }

    @Override
    public int compareTo(Edge o) {
        return Comparator
                .comparing(Edge::getSource)
                .thenComparing(Edge::getTarget)
                .compare(this, o);
    }
}
