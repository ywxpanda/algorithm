package com.panda.violent;

import com.panda.utils.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符串全排列
 */
public class FullPermutation {

    public static Set<String> fullPermutation(String str) {
        if (StringUtils.isEmpty(str)) {
            return new HashSet<>();
        }
        Set<String> set = new HashSet<>();
        char[] chars = str.toCharArray();
        process(chars, 0, set);

        return set;
    }

    private static void process(char[] chars, int index, Set<String> set) {
        if (index == chars.length) {
            set.add(new String(chars));
            return;
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            process(chars, index + 1, set);
            //恢复现场
            swap(chars, index, i);
        }
    }


    /**
     * 分区限界，提前杀死某个分之
     *
     * @param chars
     * @param index
     * @param set
     */
    private static void process2(char[] chars, int index, Set<String> set) {
        if (index == chars.length) {
            set.add(new String(chars));
            return;
        }
        boolean[] flag = new boolean[26];
        for (int i = index; i < chars.length; i++) {
            //没走过
            if (!flag[chars[i] - 'a']) {
                flag[chars[i] - 'a'] = true;
                swap(chars, index, i);
                process2(chars, index + 1, set);
                //恢复现场
                swap(chars, index, i);
            }
        }
    }

    private static void swap(char[] chars, int a, int b) {
        char tmp = chars[a];
        chars[a] = chars[b];
        chars[b] = tmp;
    }
}
