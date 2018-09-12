package de.comparus.opensource.longmap;

import java.util.List;
import java.util.Objects;

public class LongMapImpl<V> implements LongMap<V> {
    private static final int DEFAULT_HASH_MAP_SIZE = 16;
    private Node<V>[] hashTable;
    private int hashTableSize;

    public LongMapImpl() {
        hashTable = new Node[DEFAULT_HASH_MAP_SIZE];
    }

    public V put(long key, V value) {
        //create node
        Node<V> newNode = new Node<>(key, value);
        //get index for hashtable
        int arrIndex = newNode.hashFunction(key, hashTable.length);

        //empty cell in hashtable
        if (hashTable[arrIndex] == null) {
            hashTable[arrIndex] = new Node<>(0, null);
            hashTable[arrIndex].getNodesList().add(newNode);
            hashTableSize++;

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
                hashTableSize++;

                return value;
            }
        }

        return null;
    }

    public V get(long key) {
        return null;
    }

    public V remove(long key) {
        //remove using key
        return null;
    }

    public boolean isEmpty() {
        return false;
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
        return hashTableSize;
    }

    public void clear() {

    }
}
