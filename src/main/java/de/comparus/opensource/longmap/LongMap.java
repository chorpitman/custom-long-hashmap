package de.comparus.opensource.longmap;

import java.util.Collection;

public interface LongMap<V> {
    V put(long key, V value);
    V get(long key);
    V remove(long key);

    boolean isEmpty();
    boolean containsKey(long key);
    boolean containsValue(V value);

    long[] keys();
    Collection<V> values();

    long size();
    void clear();
}
