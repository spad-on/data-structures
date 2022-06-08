package com.paolotalks.heap;

import java.util.*;

public interface Heap<E> extends Queue<E> {

    boolean update(E el);

    E get(Object key);

    @Override
    default boolean containsAll(Collection<?> c) {
        for (Object e : c){
            if (!contains(e)) return false;
        }
        return true;
    }

    @Override
    default boolean addAll(Collection<? extends E> c) {
        for (E v : c){
            if (!add(v)) return false;
        }
        return true;
    }

    @Override
    default boolean removeAll(Collection<?> c) {
        for (Object v : c){
            if (!remove(v)) return false;
        }
        return true;
    }

    @Override
    default boolean retainAll(Collection<?> c) {
        removeIf(e -> !c.contains(e));
        return true;
    }
}
