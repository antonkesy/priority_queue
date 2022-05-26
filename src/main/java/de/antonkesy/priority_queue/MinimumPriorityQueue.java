package de.antonkesy.priority_queue;

import de.antonkesy.priority_queue.key.Key;
import de.antonkesy.priority_queue.minimum.MinimumCollection;

public class MinimumPriorityQueue<T> implements PriorityQueue<T> {

    private final MinimumCollection<T> minimumCollection;

    public MinimumPriorityQueue(MinimumCollection<T> minimumCollection) {
        this.minimumCollection = minimumCollection;
    }

    @Override
    public boolean push(Key key, T value) {
        return minimumCollection.push(key, value);
    }

    @Override
    public T top() {
        return minimumCollection.top();
    }

    @Override
    public T pop() {
        return minimumCollection.pop();
    }

    @Override
    public boolean decreaseKey(Key toDecrease, Key newValue) {
        int toDecreaseIndex = minimumCollection.indexOf(toDecrease);
        if (toDecreaseIndex < 0) return false;

        minimumCollection.decreaseKey(toDecreaseIndex, newValue);
        return true;
    }
}
