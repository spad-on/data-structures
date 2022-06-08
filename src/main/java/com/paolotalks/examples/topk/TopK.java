package com.paolotalks.examples.topk;

import com.paolotalks.examples.Item;
import com.paolotalks.tuple.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class TopK {

    private Map<Integer, Pair<Item, Integer>> index;
    private TreeSet<Pair<Item, Integer>> queue;
    private final int topk;

    public TopK(int topk){
        this.topk = topk;
        this.index = new HashMap<>();
        this.queue = new TreeSet<>(
                Comparator.<Pair<Item, Integer>>comparingInt(Pair::getSecond)
                          .thenComparingInt(s -> s.getFirst().getId()));
    }

    public void receive(Item item){
        Pair<Item, Integer> pair = index.get(item.getId());
        if (pair == null) {
            pair = new Pair<>(item, 1);
        } else {
            queue.remove(pair); // O(log k)
            pair = new Pair<>(item, pair.getSecond() + 1);
        }
        index.put(item.getId(), pair); // O(1)
        if (queue.size() < topk){
            queue.add(pair); // O(log k)
        } else if (!queue.isEmpty() && queue.first().getSecond() < pair.getSecond()){ // O(log k)
            queue.pollFirst(); // O(log k)
            queue.add(pair); // O(log k)
        }
    } // O(log k) * n

    public List<Item> getTopK(){
        return queue.stream().map(Pair::getFirst).collect(Collectors.toList());
    } // O(k)

    public static void main(String[] args) {
        TopK topk = new TopK(3);
        for (int e : Arrays.asList(1, 1, 2, 5, 3, 7, 6, 3, 9, 1, 3, 3, 7)){
            topk.receive(new Item(e));
        }
        List<Item> items = topk.getTopK();
        System.out.println(items);// [7, 1, 3]
    }
}
