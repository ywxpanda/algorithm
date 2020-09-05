package com.panda.utils;

import com.panda.tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeUtils {

    //先序序列化
    public static <T> Queue<T> preSerial(TreeNode<T> head) {
        Queue<T> queue = new LinkedList<>();
        preS(head, queue);
        return queue;
    }

    private static <T> void preS(TreeNode<T> head, Queue<T> queue) {
        if (head == null) {
            queue.offer(null);
        } else {
            queue.add(head.value);
            preS(head.left, queue);
            preS(head.right, queue);
        }
    }


    /**
     * 中序序列化
     *
     * @param head
     * @param <T>
     * @return
     */
    public static <T> Queue<T> inSerial(TreeNode<T> head) {
        Queue<T> queue = new LinkedList<>();
        inS(head, queue);
        return queue;

    }

    private static <T> void inS(TreeNode<T> head, Queue<T> queue) {
        if (head == null) {
            queue.offer(null);
        } else {
            inS(head.left, queue);
            queue.offer(head.value);
            inS(head.right, queue);
        }
    }

    /**
     * 后序序列化
     *
     * @param head
     * @param <T>
     * @return
     */
    public static <T> Queue<T> postSerial(TreeNode<T> head) {
        Queue<T> queue = new LinkedList<>();
        postS(head, queue);
        return queue;
    }

    private static <T> void postS(TreeNode<T> head, Queue<T> queue) {
        if (head == null) {
            queue.offer(null);
        } else {
            inS(head.left, queue);
            inS(head.right, queue);
            queue.offer(head.value);
        }
    }

    public static <T> TreeNode<T> preDeserial(Queue<T> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        return preD(queue);
    }

    private static <T> TreeNode<T> preD(Queue<T> queue) {
        T value = queue.poll();
        if (value == null) {
            return null;
        }
        TreeNode<T> head = new TreeNode<>(value);
        head.left = preD(queue);
        head.right = preD(queue);
        return head;
    }


    /**
     * 中序无法进行反序列化
     *
     * @param queue
     * @param <T>
     * @return
     */
    @Deprecated
    public static <T> TreeNode<T> inDeserial(Queue<T> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        return inD(queue);
    }

    private static <T> TreeNode<T> inD(Queue<T> queue) {
//        T value = queue.poll();
//        if (value == null) {
//            return null;
//        }
//        TreeNode<T> head = new TreeNode<>(value);
//        head.left = preD(queue);
//        head.right = preD(queue);
//        return head;
        return null;
    }


    /**
     * 后序遍历反序列化
     *
     * @param queue
     * @param <T>
     * @return
     */
    public static <T> TreeNode<T> postDeserial(Queue<T> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        //使用stack从后向前生成
        Stack<T> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return postD(stack);
    }

    private static <T> TreeNode<T> postD(Stack<T> stack) {
        T value = stack.pop();
        if (value == null) {
            return null;
        }
        TreeNode<T> head = new TreeNode<>(value);
        head.left = postD(stack);
        head.right = postD(stack);
        return head;
    }

    /**
     * 按层进行序列化
     *
     * @param head
     * @param <T>
     * @return
     */
    public static <T> Queue<T> levelSerial(TreeNode<T> head) {
        Queue<T> queue = new LinkedList<>();
        if (head == null) {
            queue.offer(null);
        } else {
            queue.offer(head.value);
            Queue<TreeNode<T>> treeNodeQueue = new LinkedList<>();
            treeNodeQueue.offer(head);
            while (!treeNodeQueue.isEmpty()) {
                head = treeNodeQueue.poll();
                if (head.left != null) {
                    queue.offer(head.left.value);
                    treeNodeQueue.offer(head.left);
                } else {
                    queue.offer(null);
                }
                if (head.right != null) {
                    queue.offer(head.right.value);
                    treeNodeQueue.offer(head.right);
                } else {
                    queue.offer(null);
                }
            }
        }

        return queue;
    }

    /**
     * 按层进行反序列化
     *
     * @param queue
     * @param <T>
     * @return
     */
    public static <T> TreeNode<T> levelDeserial(Queue<T> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        TreeNode<T> head = generateTreeNode(queue.poll());

        Queue<TreeNode<T>> treeNodeQueue = new LinkedList<>();
        if (head != null) {
            treeNodeQueue.add(head);
        }

        TreeNode<T> node;
        while (!queue.isEmpty()) {
            node = treeNodeQueue.poll();
            node.left = generateTreeNode(queue.poll());
            node.right = generateTreeNode(queue.poll());

            if (node.left != null) {
                treeNodeQueue.offer(node.left);
            }
            if (node.right != null) {
                treeNodeQueue.offer(node.right);
            }
        }
        return head;
    }


    public static <T> void printTreeNode(TreeNode<T> head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    private static <T> void printInOrder(TreeNode<T> head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        //return "" + space.repeat(Math.max(0, num));
        return "";
    }



    private static <T> TreeNode<T> generateTreeNode(T value) {
        if (value == null) {
            return null;
        }
        return new TreeNode<>(value);
    }
}
