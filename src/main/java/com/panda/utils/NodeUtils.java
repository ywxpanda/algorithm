package com.panda.utils;



import com.panda.linkedlist.Node;

import java.util.Random;

public class NodeUtils {

    public static Node<Integer> generateLinkedList(int size, int maxValue) {
        Random random = new Random();
        int realSize = random.nextInt(size);
        Node<Integer> head = new Node<>(random.nextInt(maxValue));
        Node<Integer> node = head;
        for (int i = 0; i < realSize; i++) {
            head.next = new Node<>(random.nextInt(maxValue));
            head = head.next;
        }
        head.next = null;
        return node;
    }

    public static Node<Integer> generateLinkedList(int[] arr) {
        Node<Integer> head = new Node<>(arr[0]);
        Node<Integer> node = head;

        for (int i = 1; i < arr.length; i++) {
            node.next = new Node<>(arr[i]);
            node = node.next;
        }
        node.next = null;
        return head;
    }

    public static <T> String toString(Node<T> node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(node.value).append(" -> ");
            node = node.next;
        }
        String res = sb.toString();
        return res.substring(0, res.length() - 4);
    }

    public static <T> Node<T> copyNode(Node<T> head) {
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 1};
        Node<Integer> node = generateLinkedList(arr);
        System.out.println(NodeUtils.toString(node));
    }
}
