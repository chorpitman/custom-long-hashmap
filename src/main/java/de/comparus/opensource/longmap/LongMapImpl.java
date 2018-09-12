package de.comparus.opensource.longmap;

import java.util.List;
import java.util.Objects;

public class LongMapImpl<V> implements LongMap<V> {
    private static final int DEFAULT_HASH_MAP_SIZE = 16;
    private Node<V>[] hashTable;
    private int size;

    public LongMapImpl() {
        hashTable = new Node[DEFAULT_HASH_MAP_SIZE];
    }

    public V put(long key, V value) {
        //create node
        Node<V> newNode = new Node<>(key, value);
        //get index for hashtable
        int arrIndex = hashFunction(key, hashTable.length);

        //empty cell in hashtable
        if (hashTable[arrIndex] == null) {
            hashTable[arrIndex] = new Node<>(0, null);
            hashTable[arrIndex].getNodesList().add(newNode);
            size++;

            return value;
        }

        //cell has something
        List<Node<V>> foundNode = hashTable[arrIndex].getNodesList();

        for (Node<V> vNode : foundNode) {
            //key exist in hashtable value does not same
            if (vNode.getKey() == key && !Objects.equals(vNode.getValue(), value)) {
                //replace value
                vNode.setValue(value);

                return value;
            }
            //collision. hashcode same but key and value are different
            if (vNode.getKey() != key && !Objects.equals(vNode.getValue(), value) && Objects.equals(newNode, vNode)) {
                foundNode.add(newNode);
                size++;

                return value;
            }
        }

        return null;
    }

    public V get(long key) {
        int arrIndex = hashFunction(key, hashTable.length);

        return null;
    }

    public V remove(long key) {
        //remove using key
        int arrIndex = hashFunction(key, hashTable.length);

        if (hashTable[arrIndex] == null) {
            return null;
        }

        if (hashTable[arrIndex].getNodesList().size() == 1) {
            V removeCandidate = hashTable[arrIndex].getNodesList().get(0).getValue();
            hashTable[arrIndex] = null;
            size--;

            return removeCandidate;
        }

        List<Node<V>> nodes = hashTable[arrIndex].getNodesList();
        for (Node<V> node : nodes) {
            if (key == node.getKey()) {
                V removeCandidate = node.getValue();
                nodes.remove(node);

                return removeCandidate;
            }
        }

        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(long key) {
        return false;
    }

    public boolean containsValue(V value) {
        return false;
    }

    public long[] keys() {
        return null;
    }

    public V[] values() {
        return null;
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
        return (int) ((31 * 17 + key) % hashTableLength);
    }
}
