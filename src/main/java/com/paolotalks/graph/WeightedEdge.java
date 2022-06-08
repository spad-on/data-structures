package com.paolotalks.graph;

public class WeightedEdge extends Edge {

    private final int weight;

    public WeightedEdge(Node source, Node target, int weight){
        super(source, target);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString(){
        return "(" + getSource() + ", " + getTarget() + ", " + weight + ")";
    }
}
