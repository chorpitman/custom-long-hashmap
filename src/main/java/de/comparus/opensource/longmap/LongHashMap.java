package de.comparus.opensource.longmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LongHashMap<V> implements LongMap<V> {
    private static final int DEFAULT_HASH_MAP_SIZE = 16;
    private Node<V>[] hashTable;
    private int size;

    public LongHashMap() {
        hashTable = new Node[DEFAULT_HASH_MAP_SIZE];
    }

    public LongHashMap(int size) {
        hashTable = new Node[size];
    }

    public V put(long key, V value) {
        Node<V> newNode = new Node<>(key, value);
        int index = hashFunction(key, hashTable.length);

        if (hashTable[index] == null) {
            hashTable[index] = new Node<>();
            hashTable[index].getNodesList().add(newNode);
            size++;

            return value;
        }

        List<Node<V>> foundNodes = hashTable[index].getNodesList();
        for (Node<V> node : foundNodes) {
            if (node.getKey() == key && !Objects.equals(node.getValue(), value)) {
                node.setValue(value);

                return value;
            }

            if (node.getKey() != key && !Objects.equals(node.getValue(), value) && Objects.equals(node, newNode)) {
                foundNodes.add(newNode);
                size++;

                return value;
            }
        }

        return null;
    }

    public V get(long key) {
        int index = hashFunction(key, hashTable.length);
        if (index > hashTable.length - 1 || index < 0 || hashTable[index] == null) {
            throw new IllegalArgumentException("element with key: " + key + " does not exist");
        }

        List<Node<V>> foundNodes = hashTable[index].getNodesList();
        for (Node<V> node : foundNodes) {
            if (node.getKey() == key) {
                return node.getValue();
            }
        }

        return null;
    }

    public V remove(long key) {
        int index = hashFunction(key, hashTable.length);

        if (hashTable[index] == null) {
            return null;
        }

        List<Node<V>> foundNodes = hashTable[index].getNodesList();
        for (Node<V> node : foundNodes) {
            if (node.getKey() == key) {
                foundNodes.remove(node);
                size--;

                return node.getValue();
            }
        }

        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(long key) {
        int index = hashFunction(key, hashTable.length);

        if (index > hashTable.length - 1 || index < 0 || hashTable[index] == null) {
            return false;
        }

        List<Node<V>> foundNode = hashTable[index].getNodesList();
        for (Node<V> node : foundNode) {
            if (node.getKey() == key) {
                return true;
            }
        }

        return false;
    }

    public boolean containsValue(V value) {
        if (size == 0) {
            return false;
        }

        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] == null || hashTable[i].getNodesList().isEmpty()) {
                continue;
            }

            if (isValuesEqual(value, hashTable[i])) {
                return true;
            }
        }

        return false;
    }

    public long[] keys() {
        List<Long> keysList = new ArrayList<>();

        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                List<Node<V>> nodesList = hashTable[i].getNodesList();
                for (Node<V> node : nodesList) {
                    keysList.add(node.getKey());
                }
            }
        }

        if (keysList.isEmpty()) {
            return new long[0];
        }

        return keysList.stream().mapToLong(l -> l).toArray();
    }

    public Collection<V> values() {
        if (size == 0) {
            return Collections.emptyList();
        }

        List<V> values = new ArrayList<>();
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] == null || hashTable[i].getNodesList().isEmpty()) {
                continue;
            }

            List<Node<V>> foundNodes = hashTable[i].getNodesList();
            for (Node<V> node : foundNodes) {
                values.add(node.getValue());
            }
        }

        return values.isEmpty() ? Collections.emptyList() : values;
    }

    public long size() {
        return size;
    }

    public void clear() {
        if (size != 0) {
            for (int i = 0; i < hashTable.length; i++) {
                if (hashTable[i] != null) {
                    hashTable[i] = null;
                    size--;
                }
            }
        }
    }

    public int hashFunction(long key, int hashTableLength) {
        return (int) (key ^ (key >>> 32)) % hashTableLength;
    }

    private boolean isValuesEqual(final V value, final Node<V> vNode) {
        List<Node<V>> nodesList = vNode.getNodesList();
        for (Node<V> node : nodesList) {
            if (Objects.equals(node.getValue(), value)) {
                return true;
            }
        }
        return false;
    }
}
