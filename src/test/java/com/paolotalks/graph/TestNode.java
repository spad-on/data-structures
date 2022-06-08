package com.paolotalks.graph;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestNode {

    @Test
    public void testId() {
        Node node = new Node(12);
        assertEquals(12, node.getId());
    }

    @Test
    public void testHashCodeAndEquals() {
        Node a = new Node(12);
        Node b = new Node(4);
        Node c = new Node(12);
        // equals
        assertNotEquals(a, b);
        assertNotEquals(c, b);
        assertEquals(a, c);
        // hashCode
        assertNotEquals(a.hashCode(), b.hashCode());
        assertNotEquals(c.hashCode(), b.hashCode());
        assertEquals(a.hashCode(), c.hashCode());
    }


    @Test
    public void testToString() {
        Node node = new Node(12);
        assertEquals("12", node.toString());
    }

    @Test
    public void testCompareTo() {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node aa = new Node(1);
        assertTrue(a.compareTo(b) < 0);
        assertTrue(c.compareTo(b) > 0);
        assertTrue(aa.compareTo(a) == 0);
    }


}
