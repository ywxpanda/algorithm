package com.panda.array;

import org.panda.utils.ArrayUtils;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class Sort {

    /**
     * 选择排序
     * 找到最小的元素放到第i个位置
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            ArrayUtils.swap(arr, i, minIndex);
        }
    }

    /**
     * 冒泡排序
     * 找到最大的数，放到最后一个位置
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                //大的数不停的向上冒
                if (arr[i] > arr[i + 1]) {
                    ArrayUtils.swap(arr, i, i + 1);
                }
            }
        }
    }

    /**
     * 插入排序
     * 排序 0~i的数，如果前面的数比自己大，则进行交换
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    ArrayUtils.swap(arr, j, j - 1);
                }
            }
        }
    }


    /**
     * 要求数组有序
     *
     * @return 该数字所在的下标
     */
    public static int binarySearch(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = sortedArr.length - 1;
        int mid = -1;

        while (left < right) {
            mid = left + (right - left) >> 1;
            if (sortedArr[mid] == num) {
                return mid;
            } else if (sortedArr[mid] > num) {
                //找左边
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //left == right 跳出循环
        if (sortedArr[left] == num) {
            return left;
        }
        return mid;
    }

    /**
     * 归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        merge(arr, 0, arr.length - 1);
    }


    private static void merge(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        //防止越界
        int mid = left + ((right - left) >> 1);
        //处理左边的数组，使其有序
        merge(arr, left, mid);
        //处理右边的数组使其有序
        merge(arr, mid + 1, right);
        //将左右两边有序的数组合并成一个数组
        handleMerge(arr, left, mid, right);
    }

    private static void handleMerge(int[] arr, int left, int mid, int right) {
        //tmp数组，将两个有序数组拷贝到tmp数组当中
        int[] tmp = new int[right - left + 1];
        //tmp数组下标
        int i = 0;
        int leftIndex = left;
        int rightIndex = mid + 1;
        //copy
        while (leftIndex <= mid && rightIndex <= right) {
            tmp[i++] = arr[leftIndex] < arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        //将剩余的部分copy到tmp数组中
        while (leftIndex <= mid) {
            tmp[i++] = arr[leftIndex++];
        }
        while (rightIndex <= right) {
            tmp[i++] = arr[rightIndex++];
        }
        //将tmp数组copy回原数组
        System.arraycopy(tmp, 0, arr, left, tmp.length);
    }


    //荷兰国旗问题,通过下标为right的数值进行划分,返回arr[right]左右两边的下标
    //大于num在num的右边，小于num的在num的左边
    public static int[] netherlandsFlag(int[] arr, int left, int subScript) {
        if (left > subScript) {
            return new int[]{-1, -1};
        }
        if (left == subScript) {
            return new int[]{left, subScript};
        }
        int num = arr[subScript];
        System.out.println("num:" + num);
        //左边界
        int leftBoundary = left - 1;
        //右边界
        int rightBoundary = subScript;
        //索引
        int index = left;
        while (index < rightBoundary) {
            if (arr[index] == num) {
                index++;
            } else if (arr[index] < num) {
                //小的向前换，左边界后移
                ArrayUtils.swap(arr, index++, ++leftBoundary);
            } else {
                //大的向后换，右边界前移
                ArrayUtils.swap(arr, index, --rightBoundary);
            }
//            System.out.println(ArrayUtils.toString(arr));
        }
        ArrayUtils.swap(arr, rightBoundary, subScript);
        return new int[]{leftBoundary + 1, rightBoundary};
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickProcess(arr, 0, arr.length - 1);
    }

    private static void quickProcess(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        //random一个下标进行
        int[] ints = netherlandsFlag(arr, left, right);
        //System.out.println(ArrayUtils.toString(arr));
        quickProcess(arr, left, ints[0] - 1);
        quickProcess(arr, ints[1] + 1, right);
    }


    public static void heapSort(int[] arr) {
        //构建大根堆
        for (int i = getParent(arr.length - 1); i >= 0; i--) {
            siftDown(arr, i, arr.length);
        }
        int heapSize = arr.length;
        // O(N*logN)
        while (heapSize > 0) { // O(N)
            siftDown(arr, 0, heapSize); // O(logN)
            //将最大的数放到最后
            ArrayUtils.swap(arr, 0, --heapSize); // O(1)
        }
    }

    private static int getParent(int index) {
        return (index - 1) / 2;
    }

    /**
     * 下沉操作
     */
    private static void siftDown(int[] arr, int index, int heapSize) {
        int maxChild;
        while ((maxChild = getMaxChildIndex(arr, index, heapSize)) < heapSize && maxChild != -1) {
            if (arr[maxChild] > arr[index])
                ArrayUtils.swap(arr, maxChild, index);
            index = maxChild;
        }
    }

    /**
     * 找到左右孩子中大孩子的下标
     *
     * @return
     */
    private static int getMaxChildIndex(int[] arr, int index, int heapSize) {
        int realLeftIndex = 2 * index + 1;
        if (realLeftIndex > heapSize) {
            return -1;
        } else {
            if (realLeftIndex + 1 >= heapSize) {
                return realLeftIndex;
            } else {
                return arr[realLeftIndex] > arr[realLeftIndex + 1] ? realLeftIndex : realLeftIndex + 1;
            }
        }
    }


    /**
     * 计数排序
     * 适合较小范围的数组
     *
     * @param range 最大最小的差值
     * @param arr   数组
     */
    public static void countSort(int[] arr, int range) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int[] minAndMax = ArrayUtils.minAndMax(arr);
        int min = minAndMax[0];
        int max = minAndMax[1];
        if (max - min > range) {
            throw new IllegalArgumentException("数组范围越界");
        }
        int[] bucket = new int[max - min + 1];
        for (int i : arr) {
            bucket[i - min]++;
        }
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                arr[index++] = i + min;
            }
        }
    }


    /**
     * 基数排序
     */
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        Map<Integer, Queue<Integer>> map = new TreeMap<>();
        int maxBits = getMaxBits(arr);
        for (int i = 0; i < maxBits; i++) {
            for (int num : arr) {
                int digit = getDigit(num, i);
                if (map.containsKey(digit)) {
                    map.get(digit).add(num);
                } else {
                    Queue<Integer> queue = new LinkedList<>();
                    queue.add(num);
                    map.put(digit, queue);
                }
            }
            //System.out.println(map);
            int index = 0;
            for (Map.Entry<Integer, Queue<Integer>> entry : map.entrySet()) {
                Queue<Integer> queue = entry.getValue();
                while (!queue.isEmpty()) {
                    arr[index++] = queue.poll();
                }
            }
        }
    }


    public static void radixSort2(int[] arr) {
        int[] helpArr = new int[arr.length];
        int radix = 10;
        int maxBits = getMaxBits(arr);
        int j;
        for (int i = 0; i < maxBits; i++) {
            //用域记录某个位数值之前存在多少个数字
            int[] counts = new int[radix];
            for (int num : arr) {
                j = getDigit(num, i);
                counts[j]++;
            }
            for (int k = 1; k < radix; k++) {
                counts[k] += counts[k - 1];
            }

            //System.out.println("counts: " + ArrayUtils.toString(counts));

            //System.out.println("arr:" + ArrayUtils.toString(arr));
            //System.out.println("before: " + ArrayUtils.toString(helpArr));
            for (int k = arr.length - 1; k >= 0; k--) {
                j = getDigit(arr[k], i);
                //从count记录的该位数的值开始向前放
                helpArr[counts[j] - 1] = arr[k];
                //System.out.println("helpArr: " + ArrayUtils.toString(helpArr));
                counts[j]--;
            }

            //System.out.println("after:" + ArrayUtils.toString(helpArr));
            int k = 0;
            while (k < arr.length) {
                arr[k] = helpArr[k];
                k++;
            }
        }


    }

    /**
     * 获取最大的位数
     */
    private static int getMaxBits(int[] arr) {
        int max = ArrayUtils.minAndMax(arr)[1];
        int count = 0;
        while (max != 0) {
            count++;
            max /= 10;
        }
        return count;

    }

    /**
     * 取数字num上第n位的数字
     *
     * @param num
     * @return
     */
    private static int getDigit(int num, int n) {
        return (int) (num / Math.pow(10, n) % 10);
    }


    public static void main(String[] args) {


//        System.out.println(getDigit(12043, 10));

//        int[] ints = ArrayUtils.generateRandomArray(50, 50);
        int[] ints = {17, 0, 1, 5, 11, 37, 46, 20, 47, 38, 21, 38, 44, 10, 38, 0, 30, 41, 6, 7, 35, 9, 33, 23, 3, 49, 3, 10, 44, 45, 8, 46, 9, 8, 48, 10, 46, 10, 19, 32, 6};

//        System.out.println(ints.length);
//
//        System.out.println(ArrayUtils.toString(ints));

        radixSort2(ints);
        System.out.println(ArrayUtils.toString(ints));
    }
}
