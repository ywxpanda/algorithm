package com.panda.unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UnionFind {

    private static class Node<V> {
        V value;

        public Node(V v) {
            this.value = v;
        }
    }

    public static class UnionSet<V> {
        // 值到节点的映射
        public Map<V, Node<V>> nodes;
        // 父节点 （union节点）
        public Map<Node<V>, Node<V>> parents;
        //节点union子节点的个数
        public Map<Node<V>, Integer> sizeMap;

        public UnionSet(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V cur : values) {
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                //最开始都是自己
                parents.put(node, node);
                //都为1
                sizeMap.put(node, 1);
            }
        }

        // 从点cur开始，一直往上找，找到不能再往上的代表点，返回
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            //向上找
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // cur头节点，略去中间的节点
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        //是否是一个集合
        public boolean isSameSet(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return false;
            }
            //判断是否从一个父节点union下的
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        public void union(V a, V b) {
            if (!nodes.containsKey(a) || !nodes.containsKey(b)) {
                return;
            }
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                //不相等进行union操作，将小的union到大的上
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                //将size小的节点union到大节点上
                parents.put(small, big);
                //更新大节点的size
                sizeMap.put(big, aSetSize + bSetSize);
                //union之后，移除小的
                sizeMap.remove(small);
            }
        }
    }

}
