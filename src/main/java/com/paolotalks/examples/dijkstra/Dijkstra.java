package com.paolotalks.examples.dijkstra;

import com.paolotalks.tuple.Pair;
import com.paolotalks.graph.Node;
import com.paolotalks.graph.UndirectedWeightedGraph;
import com.paolotalks.graph.WeightedEdge;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {

    public Map<Node, Integer> run(Node source, UndirectedWeightedGraph graph){
        return visit(source, graph);
    }

    private Map<Node, Integer> visit(Node source, UndirectedWeightedGraph graph) {
        Map<Node, Integer> distance = new HashMap<>();
        Map<Node, Integer> partials = new HashMap<>();
        Queue<Pair<Node, Integer>> queue = new PriorityQueue<>(Pair.compareBySecond());
        queue.offer(new Pair<>(source, 0));
        while (!queue.isEmpty()){ // O(n)
            Pair<Node, Integer> pair = queue.poll(); // O(log n)
            Node node = pair.getFirst();
            int distSoFar = pair.getSecond();
            distance.put(node, distSoFar);
            for (WeightedEdge edge : graph.getEdges(node)){ // sum to O(m)
                Node next = edge.getTarget();
                int w = edge.getWeight();
                if (distance.containsKey(next)) continue;
                Integer temp = partials.get(next);
                if (temp == null){
                    queue.offer(new Pair<>(next, distSoFar + w)); // O(log n)
                    partials.put(next, distSoFar + w);
                } else if (distSoFar + w < temp){
                    queue.removeIf(s -> s.getFirst().equals(next)); // O(n)
                    queue.offer(new Pair<>(next, distSoFar + w)); // O(log n)
                    partials.put(next, distSoFar + w);
                }
            }
        } // O(n log n + m log n + m * n) = O(m*n) = O(n^3) for dense graphs

        return distance;
    }

    public static void main(String[] args) {
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph();
        graph.addEdge(1, 2, 7);
        graph.addEdge(1, 3, 9);
        graph.addEdge(1, 6, 14);
        graph.addEdge(2, 3, 10);
        graph.addEdge(2, 4, 15);
        graph.addEdge(3, 4, 11);
        graph.addEdge(3, 6, 2);
        graph.addEdge(4, 5, 6);
        graph.addEdge(5, 6, 9);

        Map<Node, Integer> distance = new Dijkstra().run(graph.getNode(1), graph);
        System.out.println(distance); // {1=0, 2=7, 3=9, 4=20, 5=20, 6=11}

    }

}
