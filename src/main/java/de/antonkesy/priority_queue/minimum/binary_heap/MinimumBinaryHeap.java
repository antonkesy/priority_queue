package de.antonkesy.priority_queue.minimum.binary_heap;

import de.antonkesy.priority_queue.key.Key;
import de.antonkesy.priority_queue.minimum.MinimumCollection;

import java.util.ArrayList;
import java.util.List;

public class MinimumBinaryHeap<T> implements MinimumCollection<T> {

    private class Node {
        public Key key;
        public T value;

        public void swap(Node other) {
            Key tmpKey = key;
            T tmpValue = value;

            value = other.value;
            key = other.key;

            other.value = tmpValue;
            other.key = tmpKey;
        }

        public boolean isSmaller(Node other) {
            return key.compareTo(other.key) < 0;
        }
    }

    private final List<Node> elements;
    private final int capacity;
    private int size = 0;

    public MinimumBinaryHeap(int capacity) {
        this.capacity = capacity;
        this.elements = new ArrayList<>(capacity);
        //fill list with elements to increase size
        for (int i = 0; i < capacity; ++i) {
            elements.add(i, new Node());
        }
    }

    @Override
    public boolean push(Key key, T value) {
        if (capacity == size) return false;

        elements.get(size).value = value;
        elements.get(size).key = key;

        decreaseKey(size++, key);

        return true;
    }

    @Override
    public T top() {
        if (size == 0) return null;
        return elements.get(0).value;
    }

    @Override
    public T pop() {
        if (size == 0) return null;

        //decrease size before
        --size;

        //1 element in queue
        if (size == 0) return elements.get(0).value;

        //multiple elements in queue
        T minValue = elements.get(0).value;
        elements.set(0, elements.get(size));
        heapify(0);

        return minValue;
    }


    @Override
    public void decreaseKey(int indexToDecrease, Key newValue) {
        Key keyToDecrease = elements.get(indexToDecrease).key;
        if (keyToDecrease.compareTo(newValue) < 0) throw new IllegalArgumentException("new key not smaller");


        elements.get(indexToDecrease).key = newValue;

        while (indexToDecrease != 0 &&
                elements.get(indexToDecrease).isSmaller(elements.get(parentIndex(indexToDecrease)))) {

            elements.get(indexToDecrease).swap(elements.get(parentIndex(indexToDecrease)));
            indexToDecrease = parentIndex(indexToDecrease);
        }
    }

    @Override
    public int indexOf(Key key) {
        for (int i = 0; i < size; ++i)
            if (elements.get(i).key.equals(key)) return i;

        return -1;
    }

    private void heapify(int index) {
        int left = leftIndex(index);
        int right = rightIndex(index);
        int smallest = index;

        if (left < size && elements.get(left).isSmaller(elements.get(index))) smallest = left;

        if (right < size && elements.get(right).isSmaller(elements.get(smallest))) smallest = right;

        if (smallest != index) {
            elements.get(index).swap(elements.get(smallest));
            heapify(smallest);
        }
    }

    private static int leftIndex(int fromIndex) {
        return 2 * fromIndex + 1;
    }

    private static int rightIndex(int fromIndex) {
        return 2 * fromIndex + 2;
    }

    private static int parentIndex(int fromIndex) {
        return (fromIndex - 1) / 2;
    }
}
