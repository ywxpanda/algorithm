package com.panda.tree;

import java.util.*;

public class TreeTraverse {

    public static <T> void preOrderRecursive(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrderRecursive(node.left);
        preOrderRecursive(node.right);
    }

    public static <T> void inOrderRecursive(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        inOrderRecursive(node.left);
        System.out.print(node.value + " ");
        inOrderRecursive(node.right);
    }

    public static <T> void postOrderRecursive(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        postOrderRecursive(node.left);
        postOrderRecursive(node.right);
        System.out.print(node.value + " ");
    }

    public static <T> void preOrder(TreeNode<T> node) {
        if (node == null) return;
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            node = stack.pop();
            System.out.print(node.value + " ");
            //先进后出，先出left, right先压入栈中
            if (node.right != null) {
                stack.push(node.right);
            }
            //left 后压入，先出
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static <T> void inOrder(TreeNode<T> node) {
        if (node == null) return;
        Stack<TreeNode<T>> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                //先将左节点全部压入栈中
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.value + " ");
                node = node.right;
            }
        }
        System.out.println();
    }

    public static <T> void inOrder2(TreeNode<T> node) {
        if (node == null) return;
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(node);
        if (node.left != null) {
            node = node.left;
        } else {
            node = node.right;
        }
        while (!stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.print(node.value + " ");
            node = node.right;
        }
        System.out.println();
    }


    public static <T> void postOrder(TreeNode<T> node) {
        if (node == null) return;
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(node);
        //用域标记上一个节点
        TreeNode<T> parent = null;
        while (!stack.isEmpty()) {
            //记录父亲节点
            parent = stack.peek();
            //重复问题,parent左右子节点不能等于上一个弹出的节点
            if (parent.left != null && node != parent.left) {
                stack.push(parent.left);
            } else if (parent.right != null && node != parent.right) { //同理
                stack.push(parent.right);
            } else {
                System.out.print(stack.pop().value + " ");
                //记录弹出的节点
                node = parent;
            }
        }
    }


    /**
     * 层次遍历
     *
     * @param node
     * @param <T>
     */
    public static <T> void levelTraversal(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode<T> poll = queue.poll();
            System.out.println(poll.value);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
    }


    /**
     * 求树的最大宽度
     *
     * @param node
     * @param <T>
     * @return
     */
    public static <T> int maxLevelWithMap(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        //TreeNode 在哪一层
        Map<TreeNode<T>, Integer> levelMap = new HashMap<>();
        Queue<TreeNode<T>> queue = new LinkedList<>();
        int curLevel = 1;
        queue.offer(node);
        levelMap.put(node, curLevel);
        //当前层存在多少个节点
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode<T> cur = queue.poll();
            //当前节点所在的层数
            int curNodeLevel = levelMap.get(cur);
            //处理下一层
            if (cur.left != null) {
                queue.offer(cur.left);
                levelMap.put(cur.left, curNodeLevel + 1);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                levelMap.put(cur.right, curNodeLevel + 1);
            }

            //处理当前层的个数
            if (curLevel == curNodeLevel) {
                curLevelNodes++;
            } else {
                //代表到了下一层
                curLevel++;
                //获取最大值
                max = Math.max(max, curLevelNodes);
                //下一层的第一个节点已经过了，所以设为1
                curLevelNodes = 1;
            }
        }
        //最后一层没有被计算过
        return Math.max(max, curLevelNodes);
    }

    public static <T> int maxLevel(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        TreeNode<T> curEnd = node;
        TreeNode<T> nextEnd = null;
        int max = 0;
        int curLevelNodes = 0;
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode<T> cur = queue.poll();
            //每弹出一个当前层的数量++
            curLevelNodes++;
            if (cur.left != null) {
                queue.offer(cur.left);
                nextEnd = cur.left;
            }

            if (cur.right != null) {
                queue.offer(cur.right);
                nextEnd = cur.right;
            }
            //当前节点是当前层最后一个节点，进入下一层
            if (curEnd == cur) {
                max = Math.max(max, curLevelNodes);
                //进入下一层之前将当前层的最后一个节点改为下一层的最后一个节点
                curEnd = nextEnd;
                curLevelNodes = 0;
            }
        }
        return max;
    }

    /**
     * 折纸打印折印是向上还是向下
     *
     * @param n
     */
    public static void paperFolds(int n) {
        printFolds(1, n, true);
    }

    private static void printFolds(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        //左子树 凹
        printFolds(i + 1, n, true);

        System.out.print(down ? "凹 " : "凸 ");
        //右子树凸
        printFolds(i + 1, n, false);
    }

    public static void main(String[] args) {
        paperFolds(3);
    }
}
