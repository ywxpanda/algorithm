package com.panda.tree;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一颗树是否是完全二叉树
 */
public class IsCompleteBinaryTree {

    //通过层次遍历
    public static <T> boolean isCompleteBinaryTree(TreeNode<T> node) {
        if (node == null) {
            return true;
        }
        Queue<TreeNode<T>> queue = new LinkedList<>();
        //是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        TreeNode<T> left;
        TreeNode<T> right;
        queue.offer(node);
        while (!queue.isEmpty()) {
            TreeNode<T> poll = queue.poll();
            left = poll.left;
            right = poll.right;
            //应该是最后一层了，但是还存在下一层，则不是完全二叉树
            if ((leaf && (left != null || right != null))
                    //有右无左，肯定不是完全二叉树
                    || (left == null && right != null)) {
                return false;
            }
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
            //孩子节点不全有，下一层必须是最后一层
            if (left == null || right == null) {
                leaf = true;
            }
        }
        return true;
//        return cbt(node).isCompleteBinaryTree;
    }


    //递归套路
    static class CompleteBinaryTreeInfo {
        //是否是满二叉树
        boolean isFull;
        //是否是完全二叉树
        boolean isCompleteBinaryTree;
        //高度，左最多比右高1，右不能比左高
        int height;

        public CompleteBinaryTreeInfo(boolean isFull, boolean isCompleteBinaryTree, int height) {
            this.isFull = isFull;
            this.isCompleteBinaryTree = isCompleteBinaryTree;
            this.height = height;
        }
    }


    private static <T> CompleteBinaryTreeInfo cbt(TreeNode<T> node) {
        if (node == null) {
            return new CompleteBinaryTreeInfo(true, true, 0);
        }

        CompleteBinaryTreeInfo left = cbt(node.left);
        CompleteBinaryTreeInfo right = cbt(node.right);

        //加入当前节点后是否是满二叉树
        boolean isFull = left.isFull && right.isFull && (left.height == right.height);
        int height = Math.max(left.height, right.height) + 1;

        //是否是完全二叉树
        boolean isCompleteBinaryTree = false;
        if (isFull) {
            //满二叉树必定是完全二叉树
            isCompleteBinaryTree = true;
        } else {
            //左右子树都是完全二叉树才有可能构成一颗新的完全二叉树
            if (left.isCompleteBinaryTree && right.isCompleteBinaryTree) {
                //右子树是满二叉树且左子树的高度比右子树高1,左子树是满二叉树还是完全二叉树都可以
                if (right.isFull && left.height == right.height + 1) {
                    isCompleteBinaryTree = true;
                }
                //左子树是一颗满二叉树，右子树是完全二叉树且高度相等
                if (left.isFull && left.height == right.height) {
                    isCompleteBinaryTree = true;
                }
            }
        }
        return new CompleteBinaryTreeInfo(isFull, isCompleteBinaryTree, height);
    }
}
