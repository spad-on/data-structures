package com.paolotalks.tree;

public interface Tree<E> {

    void addNode(int data);
    boolean contains(int data);
    E getRoot();
}
