package com.panda.linkedlist;


import com.panda.utils.NodeUtils;

public class LinkedListOperation {

    public static <T> Node<T> reverse(Node<T> head) {
        Node<T> pre = null;
        Node<T> next;
        while (head != null) {
            //先记录下一个
            next = head.next;
            //将head节点的指针只想前一个节点
            head.next = pre;
            //移动pre节点
            pre = head;
            head = next;
        }
        return pre;
    }


    public static <T> Node<T> removeNode(Node<T> head, T value) {
        //先移除头部相等的节点
        while (head != null) {
            if (!head.value.equals(value)) {
                break;
            }
            head = head.next;
        }
        Node<T> pre = head;
        Node<T> cur = head;

        //当相等时，pre.next = cur.next
        while (cur != null) {
            if (cur.value.equals(value)) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    public static void main(String[] args) {
        Node<Integer> node = NodeUtils.generateLinkedList(20, 30);
        System.out.println(NodeUtils.toString(node));
        //System.out.println(NodeUtils.toString(reverse(node)));


        System.out.println(NodeUtils.toString(removeNode(node, 1)));
    }
}
