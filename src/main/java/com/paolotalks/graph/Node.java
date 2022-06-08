package com.paolotalks.graph;

import java.util.Objects;

public class Node implements Comparable<Node> {

    private final int id;

    public Node(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return id == node.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "" + id;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.id, o.id);
    }
}
