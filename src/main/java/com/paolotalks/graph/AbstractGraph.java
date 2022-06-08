package com.paolotalks.graph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class AbstractGraph<E extends Edge> implements Graph<E> {

    protected Map<Integer, Node> nodes;
    protected Map<Node, Set<E>> edges;

    public AbstractGraph(){
        this.nodes = new HashMap<>();
        this.edges = new HashMap<>();
    }

    @Override
    public Node getNode(int id) {
        return nodes.get(id);
    }

    @Override
    public boolean isEmpty(){
        return nodes.isEmpty();
    }

    @Override
    public int size(){
        return nodes.size();
    }

    @Override
    public Set<E> getEdges(int nodeId) {
        return getEdges(getNode(nodeId));
    }

    @Override
    public Set<E> getEdges(Node node) {
        return node == null ? Set.of() : edges.getOrDefault(node, Set.of());
    }

    @Override
    public Collection<Node> nodeSet() {
        return nodes.values();
    }

    @Override
    public Stream<Node> nodes() {
        return nodes.values().stream();
    }

    @Override
    public Collection<E> edgeSet() {
        return edges.values().stream().flatMap(Collection::stream).collect(Collectors.toSet());
    }

    @Override
    public Stream<E> edges() {
        return edges.values().stream().flatMap(Collection::stream);
    }

    @Override
    public void addNode(int id) {
        getOrCreateNode(id);
    }

    protected Node getOrCreateNode(int id){
        Node node = nodes.get(id);
        if (node == null){
            node = new Node(id);
            nodes.put(id, node);
        }
        return node;
    }
}
