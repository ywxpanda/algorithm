package com.panda.unionFind;

import com.panda.unionFind.graph.Edge;
import com.panda.unionFind.graph.Graph;
import com.panda.unionFind.graph.Node;

import java.util.*;

/**
 * K算法，最小生成树
 */
public class Kruskal {

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind.UnionSet<Node> unionSet = new UnionFind.UnionSet<>(graph.nodes.values());
        //构建优先队列,小的在前
        Queue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        queue.addAll(graph.edges);
        Set<Edge> res = new HashSet<>();
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            //不是同一个集合
            if (!unionSet.isSameSet(edge.from, edge.to)) {
                res.add(edge);
                //使其成为一个集合
                unionSet.union(edge.from, edge.to);
            }
        }
        return res;
    }
}
