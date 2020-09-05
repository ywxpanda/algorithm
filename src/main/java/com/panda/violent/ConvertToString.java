package com.panda.violent;


import com.panda.utils.StringUtils;

/**
 * 规定1和A对应，2和B对应。。。。
 * 那么一个数字111就可以转化为AAA,KA,Ak
 * <p>
 * <p>
 * 给定一个字符串，会有多少中转化的结果
 */
public class ConvertToString {

    public static int countRes(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        //从左到右惊醒遍历
        return process(str.toCharArray(), 0);
    }

    private static int process(char[] chars, int index) {
        //1.递归终止条件
        if (index == chars.length) {
            return 1;
        }
        if (chars[index] == '0') {
            return 0;
        }

        if (chars[index] == '1') {
            //两种情况
            //1 只转化为一个字符
            int res = process(chars, index + 1);
            //2. 两个字符转化为一个
            if (index + 1 < chars.length) {
                res += process(chars, index + 2);
            }
            return res;
        }

        if (chars[index] == '2') {
            //1 只转化为一个字符
            int res = process(chars, index + 1);
            //两个字符转化为1个字符
            if (index + 1 < chars.length && chars[index + 1] >= '0' && chars[index + 1] <= '6') {
                res += process(chars, index + 2);
            }
            return res;
        }

        //其他的
        return process(chars, index + 1);
    }
}
