package com.paolotalks.tree;

import com.paolotalks.BalancedUnionFind;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class TestSegmentTree {

    private static final Integer[] elements = new Integer[] {1, 3, -2, 8, -7};
    private static final Supplier<Integer> zero = () -> 0;

    private SegmentTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new SegmentTree<>(elements, Integer::sum);
    }

    @Test
    public void testQuery() {
        for (int i = 0; i < elements.length; i++){
            for (int j = i; j < elements.length; j++) {
                int sum = Arrays.stream(elements)
                                .mapToInt(s -> s)
                                .skip(i)
                                .limit(j - i + 1)
                                .sum();
                assertEquals(sum, tree.query(i, j, zero).intValue());
            }
        }
    }

    @Test
    public void testUpdate() {
        // elements[2] = 3
        tree.update(2, 3);
        // sum(3)
        assertEquals(3, tree.query(2, 2, zero).intValue());
        // sum(1, 3, 3, 8)
        assertEquals(15, tree.query(0, 3, zero).intValue());
    }

}
