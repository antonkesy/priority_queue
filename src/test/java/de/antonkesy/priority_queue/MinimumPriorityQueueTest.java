package de.antonkesy.priority_queue;

import de.antonkesy.priority_queue.key.IntegerKey;
import de.antonkesy.priority_queue.minimum.binary_heap.MinimumBinaryHeap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumPriorityQueueTest {

    private final int TEST_CAPACITY = 1000;

    PriorityQueue<Integer> getEmptyPriorityQueue() {
        return new MinimumPriorityQueue<>(new MinimumBinaryHeap<>(TEST_CAPACITY));
    }

    @Test
    void push() {
        PriorityQueue<Integer> priorityQueue = getEmptyPriorityQueue();

        for (int i = 0; i < TEST_CAPACITY; ++i)
            assertTrue(priorityQueue.push(new IntegerKey(i), i));

        //push to full queue
        assertFalse(priorityQueue.push(new IntegerKey(0), 0));

        priorityQueue.pop();

        //push to full queue
        assertTrue(priorityQueue.push(new IntegerKey(0), 0));
    }

    @Test
    void empty_top() {
        PriorityQueue<Integer> priorityQueue = getEmptyPriorityQueue();

        assertNull(priorityQueue.top());

        //fill and empty queue
        priorityQueue.push(new IntegerKey(0), 0);
        assertEquals(0, priorityQueue.pop());

        assertNull(priorityQueue.top());
    }

    @Test
    void top_insert() {
        PriorityQueue<Integer> priorityQueue = getEmptyPriorityQueue();

        for (int i = 0; i < TEST_CAPACITY; ++i) {
            priorityQueue.push(new IntegerKey(TEST_CAPACITY - i), i);
            assertEquals(i, priorityQueue.top());
        }

        int currentTop = priorityQueue.top();

        //push to full queue
        assertFalse(priorityQueue.push(new IntegerKey(0), 0));

        assertEquals(currentTop, priorityQueue.top());
    }

    @Test
    void pop_empty() {
        PriorityQueue<Integer> priorityQueue = getEmptyPriorityQueue();

        assertNull(priorityQueue.pop());

        //fill and empty queue
        priorityQueue.push(new IntegerKey(0), 0);
        assertEquals(0, priorityQueue.pop());

        assertNull(priorityQueue.pop());
    }

    @Test
    void pop_ordered() {
        PriorityQueue<Integer> priorityQueue = getEmptyPriorityQueue();

        for (int i = 0; i < TEST_CAPACITY; ++i)
            priorityQueue.push(new IntegerKey(i), i);

        for (int i = 0; i < TEST_CAPACITY; ++i)
            assertEquals(priorityQueue.pop(), i);
    }

    @Test
    void pop_reverse() {
        PriorityQueue<Integer> priorityQueue = getEmptyPriorityQueue();

        for (int i = 0; i < TEST_CAPACITY; ++i) {
            int reverseIterator = TEST_CAPACITY - i - 1;
            priorityQueue.push(new IntegerKey(reverseIterator), reverseIterator);
        }

        for (int i = 0; i < TEST_CAPACITY; ++i)
            assertEquals(priorityQueue.pop(), i);

    }

    @Test
    void invalid_decreaseKey() {
        PriorityQueue<Integer> priorityQueue = getEmptyPriorityQueue();

        priorityQueue.push(new IntegerKey(0), 1);
        priorityQueue.decreaseKey(new IntegerKey(0), new IntegerKey(0));

        assertThrows(IllegalArgumentException.class, () -> {
            priorityQueue.decreaseKey(new IntegerKey(0), new IntegerKey(10));
        });
    }

    @Test
    void decreaseKey() {
        PriorityQueue<Integer> priorityQueue = getEmptyPriorityQueue();

        priorityQueue.push(new IntegerKey(TEST_CAPACITY), TEST_CAPACITY);

        for (int i = 1; i < TEST_CAPACITY; ++i)
            priorityQueue.push(new IntegerKey(i), i);

        assertEquals(1, priorityQueue.top());

        //set as new minimum
        priorityQueue.decreaseKey(new IntegerKey(TEST_CAPACITY), new IntegerKey(0));

        assertEquals(TEST_CAPACITY, priorityQueue.pop());
        //remove and check if original is next minimum
        assertEquals(1, priorityQueue.top());
    }
}