package com.panda.violent;

/**
 * 汉诺塔问题
 */
public class Hanoi {

    public static void hanoi(int N) {
        process(N, "left", "tight", "mid");
    }

    /**
     * 从左边移动到右边
     *
     * @param n value
     * @param from 从哪个塔上来的
     * @param to 要去向哪个塔
     * @param other 不动
     */
    private static void process(int n, String from, String to, String other) {
        if (n == 1) {
            //最后一层直接输出;
            print(1, from, to);
            return;
        }
        //将n-1个位置移动到other,n位置此时已经在to上了
        process(n - 1, from, other, to);
        //输出
        print(n, from, to);
        //将other位置的移动到to上
        //1。将other中的最大的移动到to上
        process(n-1,other,to,from);


    }


    private static void print(int value, String from, String to) {
        System.out.println("Move " + value + " from " + from + " to " + to);
    }


}
