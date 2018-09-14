package de.comparus.opensource.longmap;

import java.util.ArrayList;
import java.util.List;

public class Node<V> {
    private List<Node<V>> nodesList;
    private long key;
    private V value;

    public Node(long key, V value) {
        this.key = key;
        this.value = value;
        nodesList = new ArrayList<>();
    }

    public Node() {
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

}
