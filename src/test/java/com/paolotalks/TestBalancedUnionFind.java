package com.paolotalks;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class TestBalancedUnionFind {

    @Test
    public void testConstructor() {
        BalancedUnionFind<Integer> uf = new BalancedUnionFind<>();
        assertEquals(0, uf.getComponents());
    }

    @Test
    public void testMakeSet() {
        BalancedUnionFind<Integer> uf = new BalancedUnionFind<>();
        uf.makeSet(10);

        assertTrue(uf.exists(10));
        assertTrue(uf.isSameSet(10, 10));
        assertEquals(1, uf.getComponents());
        uf.makeSet(10); // double call should have no effect
        assertEquals(1, uf.getComponents());
    }

    @Test
    public void testFind() {
        BalancedUnionFind<Integer> uf = new BalancedUnionFind<>();
        uf.makeSet(10);

        Integer ten = uf.find(10);
        assertNotNull(ten);
        assertEquals(10, ten.intValue());

        uf.makeSet(5);
        Integer five = uf.find(5);
        assertNotNull(five);
        assertEquals(5, five.intValue());

        assertNotEquals(five, ten);
        assertFalse(uf.isSameSet(10, 5));
    }

    @Test
    public void testUnion() {
        BalancedUnionFind<Integer> uf = new BalancedUnionFind<>();
        uf.makeSet(2);
        uf.makeSet(3);
        uf.makeSet(4);

        // test initial
        assertFalse(uf.isSameSet(2, 3));
        assertFalse(uf.isSameSet(2, 4));
        assertFalse(uf.isSameSet(3, 4));
        assertEquals(3, uf.getComponents());
        // test first union
        uf.union(2, 4);
        assertTrue(uf.isSameSet(2, 4));
        assertTrue(uf.isSameSet(4, 2));
        assertFalse(uf.isSameSet(2, 3));
        assertFalse(uf.isSameSet(3, 4));
        assertEquals(2, uf.getComponents());
        Integer two = uf.find(2);
        Integer four = uf.find(4);
        assertEquals(two, four);

        // test union by rank
        uf.union(3, 4);
        Integer three = uf.find(3);
        assertEquals(three, two);
        assertEquals(1, uf.getComponents());

        uf.union(3, 4); // same set, no effect
        uf.union(3, 3); // same set, no effect
        assertEquals(1, uf.getComponents());
    }

    @Test
    public void testPostMakeSet() {
        List<Integer> result = new ArrayList<>(3);
        BalancedUnionFind<Integer> uf = new BalancedUnionFind<>() {
            @Override
            protected void postMakeSet(Integer i) {
                result.add(i);
            }
        };
        uf.makeSet(2);
        uf.makeSet(3);
        uf.makeSet(4);
        uf.makeSet(4); // double call should be ignored
        // make sure postMakeSet has been called three times
        assertEquals(3, result.size());
        // Make sure right arguments are passed
        assertEquals(List.of(2, 3, 4), result);
    }

    @Test
    public void testPostUnion() {
        Map<Integer, Integer> map = new HashMap<>();
        final AtomicInteger count = new AtomicInteger();
        BalancedUnionFind<Integer> uf = new BalancedUnionFind<>() {
            @Override
            protected void postUnion(Integer from, Integer dest) {
                map.put(from, dest);
                count.incrementAndGet();
            }
        };
        uf.makeSet(2);
        uf.makeSet(3);
        uf.makeSet(4);
        uf.union(2, 3); // 1st
        uf.union(4, 2); // 2nd
        uf.union(2, 2);
        // make sure postUnion was called properly
        assertEquals(2, count.get());
        // 3 go as a child of 2 (1st union)
        // 4 goes as child of 2 (2nd union)
        assertEquals(Map.of(3, 2,
                            4, 2), map);
    }

}
