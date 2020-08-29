package com.panda.unionFind.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点
 */
public class Node {
    public int value;
    //入度
    public int in;
    //出度
    public int out;

    public List<Node> nexts;
    public List<Edge> edges;

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
