package com.paolotalks.tree;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class SegmentTree<E> {

    private final Object[] tree;
    private final BinaryOperator<E> combiner;
    private final int size;
    private final int root;

    public SegmentTree(E[] elements, BinaryOperator<E> combiner){
        Objects.requireNonNull(elements);
        Objects.requireNonNull(combiner);
        this.size = elements.length;
        this.combiner = combiner;
        this.tree = new Object[elements.length*4];
        this.root = 0;
        build(elements, root, 0, size-1);
    }

    private void build(E[] elements, int node, int tl, int tr) {
        if (tl == tr){
            setValue(node, elements[tl]);
            return;
        }
        int tm = (tl + tr) >>> 1;
        build(elements, left(node), tl, tm);
        build(elements, right(node), tm+1, tr);
        combineFromChildren(node);
    }

    public E query(int l, int r, Supplier<E> identity) {
        return query(root, 0, size-1, l, r, identity);
    }

    private E query(int node, int tl, int tr, int l, int r, Supplier<E> identity) {
        if (l > r) {
            return identity.get();
        }
        if (l == tl && r == tr){
            return getValue(node);
        }
        int tm = (tr + tl) >>> 1;
        E left = query(left(node), tl, tm, l, Math.min(tm, r), identity);
        E right = query(right(node), tm+1, tr, Math.max(l, tm+1),r, identity);
        return combiner.apply(left, right);
    }

    public void update(int pos, E newValue){
        update(root, 0, size-1, pos, newValue);
    }

    private void update(int node, int tl, int tr, int pos, E newValue){
        if (tl == tr){
            setValue(node, newValue);
        } else {
            int tm = (tl + tr) >>> 1;
            if (pos <= tm){
                update(left(node), tl, tm, pos, newValue);
            } else {
                update(right(node), tm + 1, tr, pos, newValue);
            }
            combineFromChildren(node);
        }
    }

    private void combineFromChildren(int v){
        E value = combiner.apply(getValue(left(v)), getValue(right(v)));
        setValue(v, value);
    }

    protected int left(int v){
        return 2 * v + 1;
    }

    protected int right(int v){
        return 2 * v + 2;
    }

    @SuppressWarnings("unchecked")
    protected E getValue(int v){
        return (E)tree[v];
    }

    protected void setValue(int v, E val){
        tree[v] = val;
    }
}
