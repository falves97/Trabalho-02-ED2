package Estructures;

import java.util.Objects;

public class Item<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Item<K, V>> {
    private final K key;
    private final V value;

    public Item(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item<?, ?> item = (Item<?, ?>) o;
        return getKey().equals(item.getKey()) && getValue().equals(item.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }

    @Override
    public String toString() {
        return "Item{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Item item) {
        return this.getKey().compareTo((K) item.getKey()) + this.getValue().compareTo((V) item.getValue());
    }
}
