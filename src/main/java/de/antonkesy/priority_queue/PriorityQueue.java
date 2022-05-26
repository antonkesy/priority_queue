package de.antonkesy.priority_queue;

import de.antonkesy.priority_queue.key.Key;

public interface PriorityQueue<T> {
    boolean push(Key key, T value);

    T top();

    T pop();

    boolean decreaseKey(Key toDecrease, Key newValue);
}
