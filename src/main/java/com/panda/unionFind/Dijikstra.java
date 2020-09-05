package com.panda.unionFind;

import com.panda.unionFind.graph.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 从某个点出发，到各个点的最短路径长度
 */
public class Dijikstra {

    public static Map<Node, Integer> dijikstra1(Node from) {
        //每个节点的最短距离
        Map<Node, Integer> distanceMap = new HashMap<>();

        distanceMap.put(from, 0);

        //已经经过的节点
        Set<Node> throughNodes = new HashSet<>();

        Node minNode = getMinDistanceAndUnThroughNode(distanceMap, throughNodes);

        while (minNode != null) {
            int distance = distanceMap.get(minNode);

            //从该点到每个to节点中最小的距离
            minNode.edges.forEach(edge -> {
                Node to = edge.to;
                if (!distanceMap.containsKey(to)) {
                    //没有的点直接加入
                    distanceMap.put(to, distance + edge.weight);
                } else {
                    //存在的点进行比较
                    distanceMap.put(to, Math.min(distanceMap.get(to), distance + edge.weight));
                }
            });
            //已经经过的节点中加入
            throughNodes.add(minNode);
            //进行下一次循环
            minNode = getMinDistanceAndUnThroughNode(distanceMap, throughNodes);
        }
        return distanceMap;
    }

    /**
     * 选出没经过节点中距离最短的节点
     *
     * @param distanceMap
     * @param throughNodes
     * @return
     */
    private static Node getMinDistanceAndUnThroughNode(Map<Node, Integer> distanceMap, Set<Node> throughNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> nodeIntegerEntry : distanceMap.entrySet()) {
            Node node = nodeIntegerEntry.getKey();
            int distance = nodeIntegerEntry.getValue();
            if (!throughNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }


    private static class NodeRecord {
        Node node;
        int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * 自定义堆结构
     */
    private static class NodeHeap {
        //堆结构
        private final Node[] nodes;
        //某个node在数组中的位置 -1：代表进过，但是不再堆中，需要被忽略
        private final Map<Node, Integer> heapIndexMap;
        //起始节点出发到当前节点的最短距离
        private final Map<Node, Integer> distanceMap;
        //堆上有多少个绩点
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 节点是否进入过堆
         *
         * @return
         */
        public boolean isEnter(Node node) {
            return heapIndexMap.containsKey(node);
        }


        /**
         * 判断一个节点是否在堆中
         *
         * @param node
         * @return
         */
        public boolean isOnHeap(Node node) {
            Integer i = heapIndexMap.get(node);
            return i != null && i != -1;
        }


        public void addOrUpdateOrIgnore(Node node, int distance) {
            //update
            if (isOnHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                //上浮调整
                siftUp(heapIndexMap.get(node));
            }

            //insert
            if (!isEnter(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                //上浮调整
                siftUp(size++);
            }
            //直接ignore
        }

        public NodeRecord pop() {
            //头部的弹出
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            //头节点换成尾节点
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            //调整堆结构，下沉
            siftDown(0, size--);
            return nodeRecord;
        }

        private void siftDown(int index, int size) {
            int minChild;
            while ((minChild = getMinChild(index)) < size & minChild != -1) {
                swap(index, minChild);
                index = minChild;
            }
        }

        public void swap(int from, int dest) {
            heapIndexMap.put(nodes[from], dest);
            heapIndexMap.put(nodes[dest], from);
            Node tmp = nodes[from];
            nodes[from] = nodes[dest];
            nodes[dest] = tmp;
        }

        /**
         * 通过下标获取父亲节点的下标
         *
         * @param index
         * @return
         */
        public int getParent(int index) {
            return (index - 1) >> 2;
        }

        /**
         * 找到值小的子节点
         *
         * @param index
         * @return
         */
        public int getMinChild(int index) {
            int leftIndex = index << 1 + 1;
            if (leftIndex > size) {
                return -1;
            } else {
                if (leftIndex + 1 >= size) {
                    return leftIndex;
                } else {
                    return heapIndexMap.get(nodes[leftIndex]) < heapIndexMap.get(nodes[leftIndex + 1]) ? leftIndex : leftIndex + 1;
                }
            }
        }

        private void siftUp(int index) {
            while (heapIndexMap.get(nodes[index]) < heapIndexMap.get(nodes[getParent(index)])) {
                swap(index, getParent(index));
                index = getParent(index);
            }
        }
    }


    public static Map<Node, Integer> dijikstra2(Node node, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        //大根堆中加入节点
        nodeHeap.addOrUpdateOrIgnore(node, 0);
        Map<Node, Integer> res = new HashMap<>();

        while (!nodeHeap.isEmpty()) {
            //弹出一个节点
            NodeRecord pop = nodeHeap.pop();
            //加入或者更新或者忽略
            //1。没加入过则加入
            //2。加入过的没弹出过的更新distance为小的
            //3。加入过且已经弹出的忽略
            pop.node.edges.forEach(edge -> nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + pop.distance));
            res.put(pop.node, pop.distance);
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(1 << 1 + 1);
    }

}
