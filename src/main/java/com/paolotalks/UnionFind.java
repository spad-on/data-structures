package com.paolotalks;

import java.util.Objects;

public interface UnionFind<E> {

    E find(E i);
    void makeSet(E i);
    void union(E i, E j);

    int getComponents();

    default boolean isSameSet(E i, E j) {
        return Objects.equals(find(i), find(j));
    }


}
