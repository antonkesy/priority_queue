package de.antonkesy.priority_queue.minimum;

import de.antonkesy.priority_queue.key.Key;

public interface MinimumCollection<T> {
    boolean push(Key key, T value);

    T top();

    T pop();

    void decreaseKey(int indexToDecrease, Key newValue);

    int indexOf(Key key);
}
