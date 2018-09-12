package de.comparus.opensource.longmap;

import java.util.ArrayList;
import java.util.List;

public class Node<V> {
    private List<Node<V>> nodesList;
    private long key;
    private V value;
    private int hash;

    public Node(long key, V value) {
        this.key = key;
        this.value = value;
        nodesList = new ArrayList<>();
    }

    public List<Node<V>> getNodesList() {
        return nodesList;
    }

    public void setNodesList(List<Node<V>> nodesList) {
        this.nodesList = nodesList;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int hashFunction(long key, int hashTableLength) {
        hash = 31;
        return (int) ((hash * 17 + key) % hashTableLength);
    }

    //todo think about equals and hashcode
}
