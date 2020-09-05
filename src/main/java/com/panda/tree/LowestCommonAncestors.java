package com.panda.tree;

import java.util.HashSet;
import java.util.Set;

import com.panda.tree.SuccessorNode.TreeNode;

/**
 * 最低公共祖先节点
 */
public class LowestCommonAncestors {


    /**
     * 获取ab两个节点的最低公共祖先
     *
     * @param head
     * @param a
     * @param b
     * @param <T>
     * @return
     */
    public static <T> TreeNode<T> getLowestCommonAncestors(TreeNode<T> head, TreeNode<T> a, TreeNode<T> b) {
        //通过hashSet
        Set<TreeNode<T>> set = new HashSet<>();

        //a 向上找全部加入set中
        TreeNode<T> node = a;
        while (node.parent != null) {
            set.add(node);
            node = node.parent;
        }

        //b开始向上找，第一个包含
        node = b;
        while (!set.contains(node)) {
            node = node.parent;
        }
        return node;
    }


    public static <T> TreeNode<T> getLowestCommonAncestors2(TreeNode<T> head, TreeNode<T> a, TreeNode<T> b) {
        if (head == null) {
            return null;
        }
        return lowestCommonAncestors(head, a, b).node;
    }

    static class Info<T> {
        TreeNode<T> node;
        boolean findA;
        boolean findB;

        public Info(TreeNode<T> node, boolean findA, boolean findB) {
            this.node = node;
            this.findA = findA;
            this.findB = findB;
        }
    }

    private static <T> Info<T> lowestCommonAncestors(TreeNode<T> node, TreeNode<T> a, TreeNode<T> b) {
        if (node == null) {
            return new Info<>(null, false, false);
        }
        Info<T> left = lowestCommonAncestors(node.left, a, b);
        Info<T> right = lowestCommonAncestors(node.right, a, b);

        //子节点中是否存在a
        boolean findA = node == a || left.findA || right.findA;
        //子节点中是否存在b
        boolean findB = node == b || right.findB || left.findB;

        TreeNode<T> n = null;
        if (left.node != null) {
            n = left.node;
        }
        if (right.node != null) {
            n = right.node;
        }
        if (n != null) {
            //从这个节点开始两边都找到了，就是当前节点
            if (findA && findB) {
                n = node;
            }
        }
        return new Info<>(n, findA, findB);
    }


}
