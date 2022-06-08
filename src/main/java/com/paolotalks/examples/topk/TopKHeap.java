package com.paolotalks.examples.topk;

import com.paolotalks.examples.Item;
import com.paolotalks.heap.HeapMap;
import com.paolotalks.heap.Heap;
import com.paolotalks.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class TopKHeap {

    private Map<Integer, Integer> freqs;
    private Heap<Pair<Item, Integer>> heap;
    private final int topk;

    public TopKHeap(int topk){
        this.topk = topk;
        this.freqs = new HashMap<>();
        this.heap = new HeapMap<>(Comparator.<Pair<Item, Integer>>comparingInt(Pair::getSecond)
                .thenComparingInt( s -> s.getFirst().getId()), s ->s.getFirst().getId());
    }

    public void receive(Item item){
        int freq = freqs.merge(item.getId(), 1, Integer::sum); // O(1)
        if (heap.size() < topk) {
            heap.offer(new Pair<>(item, freq)); // O(log k)
        } else if (!heap.isEmpty() && heap.peek().getSecond() < freq){
            heap.poll(); // O(log k)
            heap.update(new Pair<>(item, freq)); // O(log k)
        }
    }

    public List<Item> getTopK(){
        // O(k)
        return heap.stream().map(Pair::getFirst).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        TopKHeap topk = new TopKHeap(3);
        for (int e : Arrays.asList(1, 1, 2, 5, 3, 7, 6, 3, 9, 1, 3, 3, 7)){
            topk.receive(new Item(e));
        }
        List<Item> items = topk.getTopK();
        System.out.println(items);// [7, 1, 3]
    }
}
