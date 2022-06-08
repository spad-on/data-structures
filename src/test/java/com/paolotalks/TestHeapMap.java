package com.paolotalks;

import java.util.NoSuchElementException;
import java.util.Queue;

import com.paolotalks.heap.HeapMap;
import com.paolotalks.tuple.Pair;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestHeapMap {

    @Test
    public void testSingleElementOfferPollPeek() {
        Queue<Integer> queue =
                new HeapMap<>(String::valueOf);
        assertTrue(queue.isEmpty());
        queue.offer(1);
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertNotNull(queue.peek());
        assertEquals(1, queue.peek().intValue());
        // poll the only element
        Integer one = queue.poll();
        assertNotNull(one);
        assertEquals(1, one.intValue());
        assertTrue(queue.isEmpty());
        assertNull(queue.peek());
    }

    @Test
    public void testSingleElementAddRemoveElement() {
        Queue<Integer> queue =
                new HeapMap<>(String::valueOf);
        assertTrue(queue.isEmpty());
        queue.add(1);
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertNotNull(queue.element());
        assertEquals(1, queue.element().intValue());
        // poll the only element
        Integer one = queue.remove();
        assertNotNull(one);
        assertEquals(1, one.intValue());
        assertTrue(queue.isEmpty());
        assertThrows(NoSuchElementException.class, queue::element);
        assertThrows(NoSuchElementException.class, queue::remove);
    }


    @Test
    public void testOrder() {
        Queue<Integer> queue =
                new HeapMap<>(String::valueOf);
        assertTrue(queue.isEmpty());
        queue.offer(1);
        queue.offer(5);
        queue.offer(4);

        // poll first element
        Integer one = queue.poll();
        assertNotNull(one);
        assertEquals(1, one.intValue());

        // poll second element
        Integer four = queue.poll();
        assertNotNull(four);
        assertEquals(4, four.intValue());

        // add another one
        queue.offer(2);

        // poll third element
        Integer two = queue.poll();
        assertNotNull(two);
        assertEquals(2, two.intValue());


        // poll fourth element
        Integer ten = queue.poll();
        assertNotNull(ten);
        assertEquals(5, ten.intValue());

        assertTrue(queue.isEmpty());

    }

    @Test
    public void testSameValues() {
        Queue<Integer> queue =
                new HeapMap<>(String::valueOf);
        assertTrue(queue.isEmpty());
        queue.offer(6);
        queue.offer(6);
        queue.offer(6);

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        while (!queue.isEmpty()){
            Integer n = queue.poll();
            assertNotNull(n);
            assertEquals(6, n.intValue());
        }

    }


    @Test
    public void testSameFrequenciesWithId() {
        // Uniqueness of element is dictated by the entire pair (s -> s)
        Queue<Pair<Integer, Integer>> queue =
                new HeapMap<>(Pair.naturalComparator(), s -> s);

        queue.offer(new Pair<>(10, 1));
        queue.offer(new Pair<>(10, 1));
        queue.offer(new Pair<>(10, 3));
        queue.offer(new Pair<>(8, 4));
        queue.offer(new Pair<>(10, 4));

        assertFalse(queue.isEmpty());
        assertEquals(4, queue.size());
        Pair<Integer, Integer> min = queue.poll();
        assertNotNull(min);
        assertEquals(8, min.getFirst().intValue());
        while (!queue.isEmpty()){
            Pair<Integer, Integer> n = queue.poll();
            assertNotNull(n);
            assertEquals(10, n.getFirst().intValue());
        }
    }

    @Test
    public void testKeyUniqueness() {
        // Uniqueness of element is dictated by the second element of the pair
        Queue<Pair<Integer, Integer>> queue =
                new HeapMap<>(Pair.naturalComparator(), Pair::getSecond);
        assertTrue(queue.isEmpty());
        queue.offer(new Pair<>(10, 1));
        queue.offer(new Pair<>(10, 1));
        queue.offer(new Pair<>(10, 3));
        queue.offer(new Pair<>(8, 4));
        queue.offer(new Pair<>(10, 4)); // should replace Pair(8, 4)

        assertFalse(queue.isEmpty());
        assertEquals(3, queue.size());
        while (!queue.isEmpty()){
            Pair<Integer, Integer> n = queue.poll();
            assertNotNull(n);
            assertEquals(10, n.getFirst().intValue());
        }
    }

    @Test
    public void testUpdate() {
        HeapMap<String, Integer> queue =
                new HeapMap<>(String::valueOf);
        assertTrue(queue.isEmpty());
        queue.offer(6);
        queue.offer(6);
        queue.offer(6);

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertNotNull(queue.peek());
        assertEquals(6, queue.peek().intValue());

        queue.update("6", 10);
        assertEquals(1, queue.size());
        assertNotNull(queue.peek());
        assertEquals(10, queue.peek().intValue());

        queue.update("2", 2);
        assertEquals(2, queue.size());
        assertNotNull(queue.peek());
        assertEquals(2, queue.peek().intValue());

    }
}
