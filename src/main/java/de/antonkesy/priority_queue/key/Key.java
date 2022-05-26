package de.antonkesy.priority_queue.key;

public abstract class Key implements Comparable<Key> {
    //force equals implementation
    public abstract boolean equals(Object other);
}
