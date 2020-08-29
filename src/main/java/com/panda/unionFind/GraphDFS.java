package com.panda.unionFind;

import com.panda.unionFind.graph.Node;
import sun.jvm.hotspot.utilities.ObjectReader;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 深度优先遍历
 */
public class GraphDFS {

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.push(node);
        set.add(node);

        if (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node next : pop.nexts) {
                if (!set.contains(next)) {
                    stack.push(pop);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
