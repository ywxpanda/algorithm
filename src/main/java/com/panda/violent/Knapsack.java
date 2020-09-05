package com.panda.violent;

/**
 * 背包问题
 * 给定一个正数，代表背包的所能装的物品的最大重量
 * <p>
 * 给定两个数组 weights 和 values 代表物品的重量和价值
 * <p>
 * 求背包能够装物品的最大价值
 */
public class Knapsack {

    /**
     * @param weights 重量数组
     * @param values  价值数组
     * @param bag     背包的容量
     * @return
     */
    public static int getNaxValue(int[] weights, int[] values, int bag) {
        return process(weights, values, 0, 0, bag);
    }

    /**
     * @param weights
     * @param values
     * @param alreadyWeight 当前的放入的中重量和
     * @param bag
     * @return
     */
    private static int process(int[] weights, int[] values, int index, int alreadyWeight, int bag) {
        if (alreadyWeight >= bag) {
            //已经不能在放入物品了
            return 0;
        }
        //不选当前的物品
        int notChoose = process(weights, values, index + 1, alreadyWeight, bag);

        //选当前的物品
        int choose = process(weights, values, index + 1, alreadyWeight + weights[index], bag) + values[index];


        return Math.max(choose, notChoose);

    }
}
