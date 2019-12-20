package ru.spbstu.telematics.java;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyTreeMap<K, V> implements Map<K, V>{

    private int size = 0;
    private Node root = null;

    protected class Node {
        public K key;
        public V value;
        public Node left = null;
        public Node right = null;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyTreeMap<K, V>.Node makeNode(K key, V value) {
        return new Node(key, value);
    }

    public void setTree(Node node, int size ) {
        this.root = node;
        this.size = size;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(Object target) {
        return findNode(target) != null;
    }

    public boolean containsValue(Object target) {
        return containsValueHelper(root, target);
    }

    private boolean containsValueHelper(Node node, Object target) {
        if (node == null) {
            return false;
        }
        if (equals(target, node.value)) {
            return true;
        }
        if (containsValueHelper(node.left, target)) {
            return true;
        }
        if (containsValueHelper(node.right, target)) {
            return true;
        }
        return false;
    }

    private boolean equals(Object target, Object obj) {
        if (target == null) {
            return obj == null;
        }
        return target.equals(obj);
    }

    public V get(Object key) {
        Node node = findNode(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node findNode(Object target) {
        if (target == null) {
            throw new IllegalArgumentException();
        }

        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) target;

        Node node = root;
        while (node != null) {
            int cmp = k.compareTo(node.key);
            if (cmp < 0)
                node = node.left;
            else if (cmp > 0)
                node = node.right;
            else
                return node;
        }
        return null;
    }

    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new Node(key, value);
            size++;
            return null;
        }
        return putHelper(root, key, value);
    }

    private V putHelper(Node node, K key, V value) {
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        int cmp = k.compareTo(node.key);

        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node(key, value);
                size++;
                return null;
            } else {
                return putHelper(node.left, key, value);
            }
        }
        if (cmp > 0) {
            if (node.right == null) {
                node.right = new Node(key, value);
                size++;
                return null;
            } else {
                return putHelper(node.right, key, value);
            }
        }
        V oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    public V remove(Object key) {
        if (root == null) throw new NullPointerException();
        return removeHelper(root, key).value;
    }

    private Node removeHelper(Node node, Object key) {
        @SuppressWarnings("unchecked")
        Comparable<? super K> k = (Comparable<? super K>) key;
        int cmp = k.compareTo(node.key);

        if (cmp < 0) {
            node.left = removeHelper(node.left, key);
        }
        else if (cmp > 0) {
            node.right = removeHelper(node.right, key);
        }
        else if (node.left != null && node.right != null){
            node.key = minimum(node.right).key;
            node.right = removeHelper(node.right, node.key);
        }
        else{
            if (node.left != null)
                node = node.left;
            else if (node.right != null)
                node = node.right;
            else
                node = null;
            size--;
        }
        return node;
    }

    private Node minimum(Node node){
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        return;
    }

    public void clear() {
        size = 0;
        root = null;
    }

    public Set<K> keySet() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
