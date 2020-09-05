package com.panda.tree;

import com.panda.utils.CompareUtils;

public class TreeFunction {

    static class BalanceInfo {
        private final boolean isBalance;
        private final int height;

        public BalanceInfo(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }

    }

    public static <T> boolean isBalance(TreeNode<T> node) {
        return balance(node).isBalance;
    }

    public static <T> BalanceInfo balance(TreeNode<T> node) {
        if (node == null) {
            return new BalanceInfo(true, 0);
        }
        BalanceInfo leftInfo = balance(node.left);
        BalanceInfo rightInfo = balance(node.right);
        //判断左右子树组合之后的二叉树是否平衡
        if (leftInfo.isBalance && rightInfo.isBalance && Math.abs(leftInfo.height - rightInfo.height) < 2) {
            return new BalanceInfo(true, Math.max(leftInfo.height, rightInfo.height) + 1);
        }
        return new BalanceInfo(false, -1);
    }


    static class MaxInfo {
        private final int height;
        private final int maxDistance;

        public MaxInfo(int height, int maxDistance) {
            this.height = height;
            this.maxDistance = maxDistance;
        }
    }

    public static <T> int maxDistance(TreeNode<T> node) {
        return distance(node).maxDistance;
    }

    private static <T> MaxInfo distance(TreeNode<T> node) {
        if (node == null) {
            return new MaxInfo(0, 0);
        }
        MaxInfo leftDistance = distance(node.left);
        MaxInfo rightDistance = distance(node.right);
        //最大高度为左右子树最大高度加上1
        int height = Math.max(leftDistance.height, rightDistance.height) + 1;
        //最远距离为Max(左子树最远距离,右子树最远距离,(左右子树最大高度和+1))
        int maxDistance = Math.max(Math.max(leftDistance.maxDistance, rightDistance.maxDistance), leftDistance.height + rightDistance.height + 1);
        return new MaxInfo(height, maxDistance);

    }


    static class BSTInfo<T extends Comparable<T>> {
        //子树二叉搜索树的最大值
        private final T max;
        //最小值
        private final T min;
        //是否是二叉搜索数
        private final boolean isBST;
        //二叉搜索子树的最大大小
        private final int maxSubBSTSize;

        public BSTInfo(T max, T min, boolean isBST, int maxSubBSTSize) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
            this.maxSubBSTSize = maxSubBSTSize;
        }
    }

    public static <T extends Comparable<T>> int MaxBSTSize(TreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return bstSize(node).maxSubBSTSize;
    }

    private static <T extends Comparable<T>> BSTInfo<T> bstSize(TreeNode<T> node) {
        if (node == null) {
            return null;
        }
        BSTInfo<T> leftBSTInfo = bstSize(node.left);
        BSTInfo<T> rightBSTInfo = bstSize(node.right);

        T max = node.value;
        T min = node.value;
        int maxSubSize = 0;

        //左子树中最大的size
        if (leftBSTInfo != null) {
            max = CompareUtils.max(max, leftBSTInfo.max);
            min = CompareUtils.min(min, leftBSTInfo.max);
            maxSubSize = Math.max(maxSubSize, leftBSTInfo.maxSubBSTSize);
        }

        //右子树中最大的二叉搜索树的size
        if (rightBSTInfo != null) {
            max = CompareUtils.max(max, rightBSTInfo.max);
            min = CompareUtils.min(min, rightBSTInfo.max);
            maxSubSize = Math.max(maxSubSize, rightBSTInfo.maxSubBSTSize);
        }

        //判断当前节点是否是搜索二叉树
        boolean isBST = false;
        //左子树是否是平衡二叉树，右子树是否是平衡二叉树
        if ((leftBSTInfo == null || (leftBSTInfo.isBST && CompareUtils.larger(node.value, leftBSTInfo.max))) &&
                (rightBSTInfo == null || (rightBSTInfo.isBST && CompareUtils.smaller(node.value, rightBSTInfo.min)))) {
            //整颗数是二叉搜索树
            isBST = true;
            //左子树的长度+右子树的长度
            maxSubSize = (leftBSTInfo == null ? 0 : leftBSTInfo.maxSubBSTSize) + (rightBSTInfo == null ? 0 : rightBSTInfo.maxSubBSTSize) + 1;
        }
        return new BSTInfo<>(max, min, isBST, maxSubSize);
    }


    /**
     * 找到最大搜索二叉树的头节点
     *
     * @param node
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> TreeNode<T> maxSearchTreeNode(TreeNode<T> node) {
        if (node == null) {
            return null;
        }
        return maxBstTreeNode(node).maxSubBSTHead;
    }

    static class MaxSearchTreeInfo<T extends Comparable<T>> {
        //最大的头节点，通过判断该节点是否是头节点判断这颗树是否是二叉搜索树
        public TreeNode<T> maxSubBSTHead;
        //最大的size
        public int maxSubBSTSize;
        public T min;
        public T max;

        public MaxSearchTreeInfo(TreeNode<T> h, int size, T mi, T ma) {
            maxSubBSTHead = h;
            maxSubBSTSize = size;
            min = mi;
            max = ma;
        }
    }

    public static <T extends Comparable<T>> MaxSearchTreeInfo<T> maxBstTreeNode(TreeNode<T> node) {
        if (node == null) {
            return null;
        }
        MaxSearchTreeInfo<T> left = maxBstTreeNode(node.left);
        MaxSearchTreeInfo<T> right = maxBstTreeNode(node.right);

        T max = node.value;
        T min = node.value;
        int maxSubBSTSize = 0;
        TreeNode<T> maxSubBSTHead = null;
        //找到左边最大的
        if (left != null) {
            min = CompareUtils.min(min, left.min);
            max = CompareUtils.max(max, left.max);
            maxSubBSTHead = left.maxSubBSTHead;
            maxSubBSTSize = left.maxSubBSTSize;
        }
        //左右两边最大的
        if (right != null) {
            min = CompareUtils.min(min, right.min);
            max = CompareUtils.max(max, right.max);
            if (right.maxSubBSTSize > maxSubBSTSize) {
                maxSubBSTSize = right.maxSubBSTSize;
                maxSubBSTHead = right.maxSubBSTHead;
            }
        }

        //处理特殊情况
        if ((left == null || (left.maxSubBSTHead == maxSubBSTHead && CompareUtils.smaller(left.max, node.value))) //处理左子树 1.左子树为空 2.左子树的最大值小于当前节点，当前节点和左子树构成的树是二叉搜索树
                || (right == null || (right.maxSubBSTHead == node.value && CompareUtils.larger(left.min, node.value))) //处理右子树 1.右子树为空  2.右子树的最小节点大于等于当前节点
        ) {
            maxSubBSTHead = node;
            maxSubBSTSize = (left == null ? 0 : left.maxSubBSTSize) + (right == null ? 0 : right.maxSubBSTSize) + 1;
        }
        return new MaxSearchTreeInfo<>(maxSubBSTHead, maxSubBSTSize, min, max);
    }
}
