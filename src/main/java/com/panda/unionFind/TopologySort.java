package com.panda.unionFind;

import com.panda.unionFind.graph.Graph;
import com.panda.unionFind.graph.Node;

import java.util.*;

/**
 * 给定一张图
 * 图的拓扑排序
 * 用于判断前后依赖关系
 * 确定哪个步骤需要先行完成
 */
public class TopologySort {

    public static List<Node> topologySort(Graph graph) {
        //记录节点入度
        Map<Node, Integer> inMap = new HashMap<>();
        //记录入度为0的节点
        Queue<Node> zeroQueue = new LinkedList<>();

        List<Node> res = new ArrayList<>();
        //初始化
        graph.nodes.forEach((integer, node) -> {
            inMap.put(node, node.in);
            if (node.in == 0) {
                zeroQueue.offer(node);
            }
        });

        while (!zeroQueue.isEmpty()) {
            Node zeroInNode = zeroQueue.poll();
            res.add(zeroInNode);
            zeroInNode.nexts.forEach(node -> {
                int in = inMap.get(node) - 1;
                inMap.put(node, in);
                //如果为0则加入zeroQueue
                if (in == 0) {
                    zeroQueue.offer(node);
                }

            });
        }

        return res;
    }
}
