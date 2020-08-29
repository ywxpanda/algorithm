package com.panda.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最小字符串数组最小字典序
 */
public class MinimumDictionaryOrder {

    public static String minimumDictionaryOrder(String[] strs) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        //如何进行排序，两个字符如何比较组合出来的字典序大小 res = (a+b) < (b+a)?b:a
        Arrays.sort(strs, (a, b) -> (a + b).compareTo(b + a));

        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }
}
