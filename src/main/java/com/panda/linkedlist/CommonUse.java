package com.panda.linkedlist;



import com.panda.utils.NodeUtils;

import java.util.Comparator;
import java.util.Stack;

public class CommonUse {

    public static <T> Node<T> getMiddle(Node<T> head) {
        Node<T> slow = head;
        Node<T> fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static <T> boolean isPalindrome(Node<T> head) {
        Stack<Node<T>> stack = new Stack<>();
        Node<T> cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            if (!stack.pop().value.equals(cur.value)) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }


    public static <T> boolean isPalindrome2(Node<T> head) {
        //先找到中点
        if (head == null || head.next == null) {
            return true;
        }
        Node<T> mid = getMiddle(head);
        //后半部分的开始节点
        Node<T> behindHalf = mid.next;

        Node<T> cur = mid;
        //前半部分断开
        mid.next = null;
        Node<T> tmp;

        //掉头回指
        while (behindHalf != null) {
            tmp = behindHalf.next;
            behindHalf.next = cur;
            cur = behindHalf;
            behindHalf = tmp;
        }
        //判断是否是回文
        Node<T> leftNode = head;
        Node<T> rightNode = cur;
        //开始判断
        while (leftNode != null && rightNode != null) {
            if (!leftNode.value.equals(rightNode.value)) {
                return false;
            }
            leftNode = leftNode.next;
            rightNode = rightNode.next;
        }
        //todo 复原链表
        return true;
    }


    public static <T extends Comparable<T>> Node<T> netherlandsFlag(Node<T> head, T value) {
        Node<T> smallHead = null;
        Node<T> smallTail = null;
        Node<T> equalHead = null;
        Node<T> equalTail = null;
        Node<T> largeHead = null;
        Node<T> largeTail = null;

        while (head != null) {
            if (head.value.compareTo(value) == 0) {
                if (equalHead == null) {
                    equalHead = head;
                } else {
                    equalTail.next = head;
                }
                equalTail = head;
            } else if (head.value.compareTo(value) < 0) {
                if (smallHead == null) {
                    smallHead = head;
                } else {
                    smallTail.next = head;
                }
                smallTail = head;
            } else {
                if (largeHead == null) {
                    largeHead = head;
                } else {
                    largeTail.next = head;
                }
                largeTail = head;
            }
            head = head.next;
        }

        //开始拼接
        Node<T> VirtualNode = new Node<>(null);
        Node<T> node = VirtualNode;
        if (smallHead != null) {
            node.next = smallHead;
            node = smallTail;
        }
        if (equalHead != null) {
            node.next = equalHead;
            node = equalTail;
        }
        if (largeHead != null) {
            node.next = largeHead;
            node = largeTail;
        }
        node.next = null;
        return VirtualNode.next;
    }


    public static <T> Node<T> getLoopNode(Node<T> head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node<T> slow = head.next;
        Node<T> fast = head.next.next;
        while (fast != slow) {
            if (fast.next.next == null || slow.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        //fast回到head处，开始直走一部，slow依然只走一步，再次相遇的节点则为入口节点
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static <T> Node<T> getIntersectNode(Node<T> head1, Node<T> head2) {

        if (head1 == null || head2 == null) {
            return null;
        }
        Node<T> loopNode1 = getLoopNode(head1);
        Node<T> loopNode2 = getLoopNode(head2);

        if (loopNode1 == null && loopNode2 == null) {
            return noLoop(head1, head2);
        }
        if (loopNode1 != null && loopNode2 != null) {
            return bothLoop(loopNode1, loopNode2);
        }
        return null;
    }


    //两个无环节点,若相交则尾部必定一样
    public static <T> Node<T> noLoop(Node<T> head1, Node<T> head2) {
        //两个节点中长的节点先走n步
        int n = 0;
        Node<T> node1 = head1;
        while (node1 != null) {
            n++;
            node1 = node1.next;
        }
        Node<T> node2 = head2;
        while (node2 != null) {
            n--;
            node2 = node2.next;
        }
        //哪个节点先走
        Node<T> goFirstNode = n > 0 ? head1 : head1;
        Node<T> goBackNode = n >= 0 ? head2 : head1;
        n = Math.abs(n);
        while (n != 0) {
            n--;
            goFirstNode = goFirstNode.next;
        }

        //相同的步数开始走，并判断是否相等，相等的第一个节点就是, 最后next都指向null,肯定相等
        while (goFirstNode != goBackNode) {
            goBackNode = goBackNode.next;
            goFirstNode = goFirstNode.next;
        }
        return goFirstNode;
    }

    //两个都是环的情况
    public static <T> Node<T> bothLoop(Node<T> loop1, Node<T> loop2) {
        if (loop1 == loop2) {
            //直接返回loop1
            return loop1;
        } else {
            //不相等,在loop1环中找loop2若存在,则直接返回loop1
            Node<T> node = loop1.next;
            while (node != loop1) {
                if (node == loop2) {
                    return loop1;
                }
                node = node.next;
            }
            return null;
        }
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 42, 354, 43, 12, 67, 42, 1, 12, 34, 4353, 98};
        Node<Integer> node = NodeUtils.generateLinkedList(arr);
        Node<Integer> node1 = netherlandsFlag(node, 0);

        System.out.println(NodeUtils.toString(node1));


    }
}
