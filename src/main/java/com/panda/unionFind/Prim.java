package com.panda.unionFind;

import com.panda.unionFind.graph.Edge;
import com.panda.unionFind.graph.Graph;
import com.panda.unionFind.graph.Node;

import java.util.*;

/**
 * P算法
 * 通过一个点解锁相邻的边
 * 在通过相邻边的toNode解锁相邻的点
 */
public class Prim {

    public static Set<Edge> primMST(Graph graph) {
        // 解锁的边进入小根堆
        Queue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        //经过的就不不再经过第二遍
        Set<Edge> through = new HashSet<>();

        // 哪些点被解锁出来了
        Set<Node> nodeSet = new HashSet<>();
        Set<Edge> result = new HashSet<>(); // 依次挑选的的边在result里
        for (Node node : graph.nodes.values()) { // 随便挑了一个点 ，for循环防森林
            // node 是开始点
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                // 由一个点，解锁所有相连的边
                node.edges.forEach(edge -> {
                    if (!through.contains(edge)) {
                        through.add(edge);
                        priorityQueue.offer(edge);
                    }
                });
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
                    Node toNode = edge.to; // 可能的一个新的点
                    //通过相邻的边的toNode解锁下一个点
                    if (!nodeSet.contains(toNode)) { // 不含有的时候，就是新的点
                        nodeSet.add(toNode);
                        result.add(edge);
                        toNode.edges.forEach(nextEdge -> {
                            if (!through.contains(nextEdge)) {
                                through.add(nextEdge);
                                priorityQueue.offer(nextEdge);
                            }
                        });
                    }
                }
            }
            // break;
        }
        return result;
    }
}
