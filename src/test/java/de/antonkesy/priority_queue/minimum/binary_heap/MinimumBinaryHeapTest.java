package de.antonkesy.priority_queue.minimum.binary_heap;

import de.antonkesy.priority_queue.key.IntegerKey;
import de.antonkesy.priority_queue.minimum.MinimumCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumBinaryHeapTest {

    private final int TEST_CAPACITY = 10;

    @Test
    void push() {
        MinimumBinaryHeap<Integer> testHeap = new MinimumBinaryHeap<>(TEST_CAPACITY);

        for (int i = 0; i < TEST_CAPACITY; ++i)
            assertTrue(testHeap.push(new IntegerKey(i), i));

        //push to full heap
        assertFalse(testHeap.push(new IntegerKey(0), 0));

        testHeap.pop();

        //push to full heap
        assertTrue(testHeap.push(new IntegerKey(0), 0));
    }

    @Test
    void empty_top() {
        MinimumCollection<Integer> testHeap = new MinimumBinaryHeap<>(TEST_CAPACITY);

        assertNull(testHeap.top());
    }

    @Test
    void top_insert() {
        MinimumCollection<Integer> testHeap = new MinimumBinaryHeap<>(TEST_CAPACITY);

        for (int i = 0; i < TEST_CAPACITY; ++i) {
            testHeap.push(new IntegerKey(TEST_CAPACITY - i), i);
            assertEquals(i, testHeap.top());
        }

        int currentTop = testHeap.top();

        //push to full heap
        assertFalse(testHeap.push(new IntegerKey(0), 0));

        assertEquals(currentTop, testHeap.top());
    }

    @Test
    void pop_empty() {
        MinimumCollection<Integer> testHeap = new MinimumBinaryHeap<>(TEST_CAPACITY);

        assertNull(testHeap.pop());

        //fill and empty heap
        testHeap.push(new IntegerKey(0), 0);
        assertEquals(0, testHeap.pop());

        assertNull(testHeap.pop());
    }

    @Test
    void pop_ordered() {
        MinimumCollection<Integer> testHeap = new MinimumBinaryHeap<>(TEST_CAPACITY);

        for (int i = 0; i < TEST_CAPACITY; ++i)
            testHeap.push(new IntegerKey(i), i);

        for (int i = 0; i < TEST_CAPACITY; ++i)
            assertEquals(testHeap.pop(), i);
    }

    @Test
    void pop_reverse() {
        MinimumCollection<Integer> testHeap = new MinimumBinaryHeap<>(TEST_CAPACITY);

        for (int i = 0; i < TEST_CAPACITY; ++i) {
            int reverseIterator = TEST_CAPACITY - i - 1;
            testHeap.push(new IntegerKey(reverseIterator), reverseIterator);
        }

        for (int i = 0; i < TEST_CAPACITY; ++i)
            assertEquals(testHeap.pop(), i);

    }

    @Test
    void invalid_decreaseKey() {
        MinimumCollection<Integer> testHeap = new MinimumBinaryHeap<>(TEST_CAPACITY);

        testHeap.push(new IntegerKey(0), 1);
        testHeap.decreaseKey(0, new IntegerKey(0));

        assertThrows(IllegalArgumentException.class, () -> {
            testHeap.decreaseKey(0, new IntegerKey(10));
        });
    }

    @Test
    void indexOf() {
        MinimumCollection<Integer> testHeap = new MinimumBinaryHeap<>(TEST_CAPACITY);

        testHeap.push(new IntegerKey(0), 0);
        assertEquals(0, testHeap.indexOf(new IntegerKey(0)));
    }
}