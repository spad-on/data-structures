package com.paolotalks.heap;

import java.util.*;
import java.util.function.Function;

/**
 * This class implement a heap with updatable keys in O(log n) time.
 * Standard Java priority queues implement remove() method in a linear fashion
 * giving worst case complexity in the order of O(log n). This can create
 * problems in algorithms that need to update values in the queue.
 *
 * This class accounts some of these shortcomings by providing better guarantees
 * for remove() and updateKeys() operations.
 * Due to its structure it is also able to get() any key in O(1) constant time
 * in addition to the top element in O(1) as well.
 *
 * The K key value is used as unique identifier of the elements in the heap.
 * Sometimes we might want to retrieve elements by id, and update them by id instead of
 * having to instantiate a complex object.
 *
 * @param <K> the key used to index elements
 * @param <V> the value to be keep sorted
 */
public class HeapMap<K, V> implements Heap<V> {
    protected final TreeMap<K, V> queue;
    protected final Map<K, V> index;
    private final Function<V, K> keyExtractor;
    private final Comparator<? super V> cmp;
    private V top;

    public HeapMap(Function<V, K> keyExtractor){
        this(null, keyExtractor);
    }
    public HeapMap(){
        this(null, null);
    }
    public HeapMap(Comparator<? super V> cmp){
        this(cmp, null);
    }

    public HeapMap(Comparator<? super V> cmp, Function<V, K> keyExtractor){
        this.cmp = cmp;
        this.keyExtractor = keyExtractor;
        this.index = new HashMap<>();
        this.queue = new TreeMap<>(this::mapCompare);
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return index.containsKey(o);
    }

    @Override
    public Iterator<V> iterator() {
        return queue.values().iterator();
    }

    @Override
    public V poll(){
        if (isEmpty()){
            return null;
        }
        Map.Entry<K, V> entry = queue.pollFirstEntry();
        V el = entry.getValue();
        index.remove(entry.getKey());
        updateTop();
        return el;
    }

    @Override
    public V element() {
        if (isEmpty())
            throw new NoSuchElementException();
        return top;
    }

    @Override
    public V peek() {
        if (isEmpty()) return null;
        return top;
    }

    @Override
    public boolean remove(Object key) {
        if (!index.containsKey(key)) return false;
        V v = queue.remove(key);
        index.remove(key);
        if (top == v)
            updateTop();
        return true;
    }

    @Override
    public boolean add(V v) {
        return update(v);
    }

    @Override
    public void clear() {
        this.queue.clear();
        this.index.clear();
        updateTop();
    }

    @Override
    public boolean offer(V el){
        return update(el);
    }

    @Override
    public V remove() {
        if (isEmpty())
            throw new NoSuchElementException();
        return poll();
    }

    @Override
    public V get(Object key){
        return index.get(key);
    }

    @Override
    public boolean update(V el){
        K k = key(el);
        return update(k, el);
    }

    @Override
    public Object[] toArray() {
        return queue.values().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return queue.values().toArray(a);
    }

    public boolean update(K key, V el){
        K newKey = key;
        if (keyExtractor != null) {
            newKey = key(el);
        }
        return update(key, newKey, el);
    }

    public boolean update(K key, K newKey, V el){
        V e = index.get(key);
        if (e == null){
            return insert(key, el);
        } else {
            remove(key);
            return insert(newKey, el);
        }
    }

    public boolean offer(K key, V el){
        return update(key, el);
    }

    protected K key(V value){
        Objects.requireNonNull(keyExtractor);
        return keyExtractor.apply(value);
    }

    protected boolean insert(K k, V v){
        this.index.put(k, v);
        this.queue.put(k, v);
        updateTop();
        return true;
    }

    private int mapCompare(K k1, K k2){
        V v1 = index.get(k1);
        V v2 = index.get(k2);
        int cmp = compareValues(v1, v2);
        if (cmp == 0){
            cmp = compareKeys(k1, k2);
        }
        return cmp;
    }

    private int compareValues(V v1, V v2){
        if (cmp != null) {
            return cmp.compare(v1, v2);
        } else {
            @SuppressWarnings("unchecked")
            Comparable<? super V> vv1 = (Comparable<? super V>) v1;
            return vv1.compareTo(v2);
        }
    }


    private int compareKeys(K k1, K k2){
        if (Comparator.class.isAssignableFrom(k1.getClass())){
            Comparable<? super K> kk1 = (Comparable<? super K>)k1;
            return kk1.compareTo(k2);
        }
        return 0;
    }

    protected void updateTop(){
        top = isEmpty() ? null : queue.firstEntry().getValue();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
