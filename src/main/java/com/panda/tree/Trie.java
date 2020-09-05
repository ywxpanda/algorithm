package com.panda.tree;

import com.panda.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Trie {

    private static class Node {
        //记录经过该节点的字符串数量
        int pass;
        //以该节点结束的字符串的数量
        int end;
        //记录以该节点为前一个字符的后字符集
        Map<Character, Node> nextMap;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nextMap = new HashMap<>();
        }
    }

    private final Node root;


    public Trie() {
        this.root = new Node();
    }

    /**
     * 统计单词的数量
     *
     * @return
     */
    public int totalWord() {
        return totalMap(root);
    }

    private int totalMap(Node node) {
        int total = 0;
        if (node.nextMap != null) {
            for (Node value : node.nextMap.values()) {
                total += value.end;
                total += totalMap(value);
            }
        }
        return total;
    }

    public void insert(String word) {
        if (StringUtils.isEmpty(word)) {
            return;
        }
        char[] chars = word.toCharArray();
        Node node = root;
        //经过该节点
        node.pass++;
        for (char ch : chars) {
            if (!node.nextMap.containsKey(ch)) {
                node.nextMap.put(ch, new Node());
            }
            node = node.nextMap.get(ch);
            node.pass++;
        }
        node.end++;
    }

    /**
     * 查找加入过几次
     *
     * @param word
     * @return 次数
     */
    public int search(String word) {
        if (StringUtils.isEmpty(word)) {
            return 0;
        }
        char[] chars = word.toCharArray();
        Node node = root;
        for (char ch : chars) {
            if (!node.nextMap.containsKey(ch)) {
                return 0;
            }
            node = node.nextMap.get(ch);
        }
        return node.end;
    }

    public void delete(String word) {
        if (search(word) <= 0) {
            return;
        }
        char[] chars = word.toCharArray();
        Node node = root;
        for (char ch : chars) {
            //删除的时候pass-1
//            node.nextMap.get(ch).pass--;
            //当pass==0代表之后的节点都无效直接remove
            if (--node.nextMap.get(ch).pass == 0) {
                node.nextMap.remove(ch);
            }

            node = node.nextMap.get(ch);
        }
        node.end--;
    }


    /**
     * 前缀匹配
     *
     * @param prefix
     * @return
     */
    public int prefixCount(String prefix) {
        if (StringUtils.isEmpty(prefix)) {
            return 0;
        }
        char[] chars = prefix.toCharArray();
        Node node = root;
        for (char ch : chars) {
            if (!node.nextMap.containsKey(ch)) {
                return 0;
            }
            node = node.nextMap.get(ch);
        }
        return node.pass;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] arr = {"panda", "hadoop", "redis", "tomcat", "spark", "flink", "panda", "pandaywx"};

        for (String s : arr) {
            trie.insert(s);
        }
        System.out.println(trie.totalWord());

        //System.out.println(trie.prefixCount("panda"));
        trie.delete("panda");
        //System.out.println(trie.totalWord());
        //System.out.println(trie.prefixCount("panda"));

    }
}
