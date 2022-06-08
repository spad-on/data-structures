package com.paolotalks.tuple;

import com.paolotalks.BalancedUnionFind;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class TestPair {

    @Test
    public void testGetters() {
        Pair<Integer, Double> pair = new Pair<>(42, 2.0);
        // getFirst
        assertNotNull(pair.getFirst());
        assertEquals(42, pair.getFirst().intValue());
        // getSecond
        assertNotNull(pair.getSecond());
        assertEquals(2.0, pair.getSecond(), 1e-5);
    }

    @Test
    public void testHasCodeAndEquals() {
        Pair<Integer, Integer> p1 = new Pair<>(1, 2);
        Pair<Integer, Integer> p2 = new Pair<>(2, 3);
        Pair<Integer, Integer> p3 = new Pair<>(1, 2);

        assertNotEquals(p1.hashCode(), p2.hashCode());
        assertNotEquals(p1, p2);
        assertEquals(p1, p3);
        assertEquals(p1.hashCode(), p3.hashCode());
    }

    @Test
    public void testInvert() {
        Pair<Integer, Long> p1 = new Pair<>(1, 2L);
        Pair<Long, Integer> p2 = Pair.invert(p1);

        assertEquals(p1.getFirst(), p2.getSecond());
        assertEquals(p1.getSecond(), p2.getFirst());
    }

    @Test
    public void testCompare() {
        Pair<Integer, Long> p1 = new Pair<>(1, 2L);
        Pair<Integer, Long> p2 = new Pair<>(1, 3L);
        Pair<Integer, Long> p3 = new Pair<>(2, 4L);
        Pair<Integer, Long> p4 = new Pair<>(3, 1L);

        // first only
        compare(Pair.compareByFirst(), List.of(p1, p4, p3), List.of(p1, p3, p4));
        // second only
        compare(Pair.compareBySecond(), List.of(p1, p2, p3, p4), List.of(p4, p1, p2, p3));
        // first then second
        compare(Pair.naturalComparator(), List.of(p4, p1, p2, p3), List.of(p1, p2, p3, p4));
        // second then first
        compare(Pair.reversedComparator(), List.of(p1, p2, p3, p4), List.of(p4, p1, p2, p3));
    }

    private void compare(Comparator<Pair<Integer, Long>> cmp,
                         List<Pair<Integer, Long>> input,
                         List<Pair<Integer, Long>> expected){
        Set<Pair<Integer, Long>> set = new TreeSet<>(cmp);
        set.addAll(input);
        int i = 0;
        for (Pair<Integer, Long> pair : set){
            assertEquals(expected.get(i++), pair);
        }
    }
}
