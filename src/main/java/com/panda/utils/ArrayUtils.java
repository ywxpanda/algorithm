package com.panda.utils;

import java.util.Random;

public class ArrayUtils {

    /**
     * 交换数组中的i,j两个位置的数
     */
    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * @param maxSize  数组最大size
     * @param maxValue 数组最大值
     * @return 随机生成的数组数组
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        Random r = new Random();
        int[] arr = new int[r.nextInt(maxSize)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = r.nextInt(maxSize);
        }
        return arr;
    }


    /**
     * 判断两个数组值是否完全相等
     */
    public static boolean isArrayEquals(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] minAndMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            max = Math.max(i, max);
            min = Math.min(min, i);
        }
        return new int[]{min, max};
    }

    /**
     * 打印
     *
     * @param arr
     * @return
     */
    public static String toString(int[] arr, char split, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(arr[i]);
            if (i != size - 1)
                stringBuilder.append(split);
        }
        return stringBuilder.toString();
    }


    public static String toString(int[] arr) {
        return toString(arr, ',', arr.length);
    }

    public static String toString(int[] arr, int size) {
        return toString(arr, ',', size);
    }

    /**
     * copy一个新的数组
     *
     * @param arr 原数组
     * @return 新数组
     */
    public static int[] copyArray(int[] arr) {
        int[] tmp = new int[arr.length];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        return tmp;
    }


}
