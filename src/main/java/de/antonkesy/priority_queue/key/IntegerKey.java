package de.antonkesy.priority_queue.key;

public class IntegerKey extends Key {
    private final int value;

    public IntegerKey(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof IntegerKey && ((IntegerKey) other).value == value;
    }

    @Override
    public int compareTo(Key o) {
        if (!(o instanceof IntegerKey)) throw new IllegalArgumentException("Not same type of key");
        return value - ((IntegerKey) o).value;
    }
}
