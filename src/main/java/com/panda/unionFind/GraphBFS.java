package com.panda.unionFind;

import com.panda.unionFind.graph.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 图的宽度优先遍历
 */
public class GraphBFS {
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        //用于判断已经加入过的节点就不再加入了
        Set<Node> set = new HashSet<>();
        set.add(node);
        queue.add(node);
        System.out.println(node.value);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value);
            //将相邻的节点加入到queue中
            node.nexts.forEach(node1 -> {
                //已经加入的不要在加入了
                if (!set.contains(node1)) {
                    set.add(node1);
                    queue.add(node1);
                }
            });
        }
    }
}
