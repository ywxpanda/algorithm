package com.panda.violent;

import java.util.HashSet;
import java.util.Set;

/**
 * 返回一个字符串的所有子序列
 */
public class StringChildSequence {


    public static Set<String> getAllSeq(String str) {
        char[] chars = str.toCharArray();
        Set<String> res = new HashSet<>();
        process(chars, 0, res, "");
        return res;
    }

    private static void process(char[] chars, int i, Set<String> res, String prefix) {
        if (i == chars.length) {
            res.add(prefix);
            return;
        }
        //第一种情况，不要当前的
        process(chars, i + 1, res, prefix);

        //第二种情况，要当前的
        process(chars, i + 1, res, prefix + chars[i]);
    }

    public static void main(String[] args) {
        System.out.println(getAllSeq("abcd"));
    }
}
