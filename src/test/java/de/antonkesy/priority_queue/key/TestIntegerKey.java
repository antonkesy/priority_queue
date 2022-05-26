package de.antonkesy.priority_queue.key;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestIntegerKey {
    final IntegerKey a = new IntegerKey(1);
    final IntegerKey b = new IntegerKey(2);

    @Test
    void testCompare() {
        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertTrue(a.compareTo(a) == 0);
    }

    @Test
    void testEqual() {
        assertEquals(a, a);
        assertNotEquals(a, b);
    }
}
