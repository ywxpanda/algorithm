package com.panda.unionFind.graph;

/**
 * 边
 */
public class Edge {
    //权重
    public int weight;
    //from节点
    public Node from;
    //to节点
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

}
