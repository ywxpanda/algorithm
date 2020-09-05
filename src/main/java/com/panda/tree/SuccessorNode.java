package com.panda.tree;


/**
 * 寻找后继节点
 */
public class SuccessorNode {

    static class TreeNode<T> {
        public T value;
        public TreeNode<T> right;
        public TreeNode<T> left;
        public TreeNode<T> parent;

        public TreeNode(T value) {
            this.value = value;
        }
    }

    /**
     * 找到某个节点的后继节点（中序遍历的下一个节点）
     *
     * @param node
     * @param <T>
     * @return
     */
    public static <T> TreeNode<T> getSuccessor(TreeNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getLeftMost(node.right);
        } else {
            //如果node.right ==null
            TreeNode<T> parent = node.parent;
            //向上找，找到自己不是父节点的右子节点的第一个节点既是后继节点
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    /**
     * 找到有变节点的最左节点
     *
     * @param node
     * @param <T>
     * @return
     */
    private static <T> TreeNode<T> getLeftMost(TreeNode<T> node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


}
