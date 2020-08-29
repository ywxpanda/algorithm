package com.panda.linkedlist;

public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T data) {
        this.value = data;
    }
}
