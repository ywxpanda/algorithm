package com.panda.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class CopyWithRandom {

    //
    public static <T> RandomNode<T> copy1(RandomNode<T> head) {
        if (head == null) {
            return null;
        }
        Map<RandomNode<T>, RandomNode<T>> map = new HashMap<>();
        RandomNode<T> cur = head;
        //将原链表放入map中
        while (cur != null) {
            map.put(cur, new RandomNode<>(cur.value));
            cur = cur.next;
        }

        //构建next和rand
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }

        return map.get(head);
    }

    public static <T> RandomNode<T> copy2(RandomNode<T> head) {
        if (head == null) {
            return null;
        }
        RandomNode<T> cur = head;
        //构建一个链表，先只构建next指针
        RandomNode<T> next;
        while (cur != null) {
            next = cur.next;
            cur.next = new RandomNode<>(cur.value);
            cur.next.next = next;
            cur = next;
        }

        //构建rand指针
        cur = head;
        RandomNode<T> copyNode;
        while (cur != null) {
            copyNode = cur.next;
            next = cur.next.next;
            //若当前节点的rand不为空，则它的rand节点的next节点即为拷贝节点的rand节点
            copyNode.rand = cur.rand == null ? null : cur.rand.next;
            cur = next;
        }

        //将构建的拷贝节分离出来
        RandomNode<T> copyHead = head.next;
        cur = head;

        //cur.next.next
        //copyNode.next.next
        //copyNode = cur.next;
        while (cur != null) {
            next = cur.next.next;
            copyNode = cur.next;
            cur.next = next;
            copyNode.next = next == null ? null : next.next;
            cur = next;
        }
        return copyHead;
    }
}
