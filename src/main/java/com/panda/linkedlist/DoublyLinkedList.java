package com.panda.linkedlist;

public class DoublyLinkedList<T> {


    static class Node<T> {
        T value;
        Node<T> next;
        Node<T> pre;

        public Node(T value) {
            this.value = value;
        }
    }

    private int size;
    private int count;

    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList(int size) {
        this.size = size;
        this.count = 0;
    }

    public DoublyLinkedList() {
        this(Integer.MAX_VALUE);
    }


    public void addHead(T value) {
        if (count >= size) {
            throw new RuntimeException("overflow");
        }
        Node<T> cur = new Node<>(value);
        if (head == null) {
            tail = cur;
        } else {
            cur.next = head;
            head.pre = cur;
        }
        head = cur;
        count++;
    }

    public void addTail(T value) {
        if (count >= size) {
            throw new RuntimeException("overflow");
        }
        Node<T> cur = new Node<>(value);
        if (head == null) {
            head = cur;
        } else {
            cur.pre = tail;
            tail.next = cur;
        }
        tail = cur;
        count++;
    }

    public T popFromHead() {
        if (head == null) {
            return null;
        }
        Node<T> cur = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            cur.next = null;
            head.pre = null;
        }
        count--;
        return cur.value;

    }

    public T popFromTail() {

        if (tail == null) {
            return null;
        }
        Node<T> cur = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.pre;
            cur.pre = null;
            tail.next = null;
        }
        count--;
        return cur.value;
    }

    public static class Stack<T> {
        private final DoublyLinkedList<T> doublyLinkedList;

        public Stack(int size) {
            doublyLinkedList = new DoublyLinkedList<>(size);
        }

        public Stack() {
            doublyLinkedList = new DoublyLinkedList<>();
        }

        public void poll(T vale) {
            doublyLinkedList.addHead(vale);
        }


        public T pop() {
            return doublyLinkedList.popFromHead();
        }
    }

    public static class Queue<T> {
        private final DoublyLinkedList<T> doublyLinkedList;

        public Queue(int size) {
            doublyLinkedList = new DoublyLinkedList<>(size);
        }

        public Queue() {
            doublyLinkedList = new DoublyLinkedList<>();
        }

        public void add(T value) {
            this.doublyLinkedList.addHead(value);
        }

        public T pop() {
            return doublyLinkedList.popFromTail();
        }
    }
}

