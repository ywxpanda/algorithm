package com.panda.violent;

/**
 * 范围尝试模型
 * <p>
 * <p>
 * 给定一个整数数组arr,代表不同的数字排成的线
 * <p>
 * 玩家A和玩家B一次拿只能从边缘拿走一个数字
 * <p>
 * A，B玩家绝顶聪明
 * <p>
 * 求获胜玩家的分数
 */
public class CardsInline {

    public static int getWin(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(firstHandle(arr, 0, arr.length - 1), backHandle(arr, 0, arr.length - 1));
    }

    /**
     * 先手拿
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int firstHandle(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        //先手怎么拿？
        //1.拿左边的，之后的最优解
        //2。拿右边的，和之后的最优解

        //取最大值

        return Math.max(arr[left] + backHandle(arr, left + 1, right), arr[right] + backHandle(arr, left, right - 1));
    }

    private static int backHandle(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        //因为先手已经拿过了，只能从中差的中取好的，是从左边的拿，还是从右边的拿
        return Math.min(firstHandle(arr, left + 1, right), firstHandle(arr, left, right - 1));
    }


}
