package com.panda.unionFind.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 图，由节点和边构成
 */
public class Graph {
    //从哪个节点过来的 value -> node
    public Map<Integer,Node> nodes;
    public Set<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }


    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (Integer[] integers : matrix) {
            // matrix[0][0], matrix[0][1]  matrix[0][2]
            Integer weight = integers[0];
            Integer from = integers[1];
            Integer to = integers[2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}
