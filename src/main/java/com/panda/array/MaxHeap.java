package com.panda.array;


import com.panda.utils.ArrayUtils;

/**
 * 大根堆
 */
public class MaxHeap {

    private final int[] arr;
    private final int limit;
    private int heapSize;

    public MaxHeap(int limit) {
        arr = new int[limit];
        this.limit = limit;
        this.heapSize = 0;
    }


    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void push(int value) {
        if (heapSize == limit) {
            throw new RuntimeException("heap is full");
        }
        arr[heapSize] = value;
        siftUp(arr, heapSize++);
    }


    public int pop() {
        if (heapSize <= 0) {
            throw new RuntimeException("heap is empty");
        }
        int res = arr[0];
        ArrayUtils.swap(arr, 0, --heapSize);
        siftDown(arr, 0, heapSize);
        return res;
    }


    /**
     * 下沉操作
     */
    private void siftDown(int[] arr, int index, int heapSize) {
        int maxChild;
        while ((maxChild = getMaxChildIndex(index)) < heapSize && maxChild != -1) {
            if (arr[maxChild] > arr[index])
                ArrayUtils.swap(arr, maxChild, index);
            index = maxChild;
        }
    }

    /**
     * 上浮
     */
    private void siftUp(int[] arr, int index) {
        while (arr[index] > arr[getParent(index)]) {
            //父子节点交换位置
            ArrayUtils.swap(arr, index, getParent(index));
            index = getParent(index);
        }
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }


    /**
     * 找到左右孩子中大孩子的下标
     *
     * @return
     */
    private int getMaxChildIndex(int index) {
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


    public static void main(String[] args) {
        int count = 50000;
        while (count-- >= 0) {
            int[] arr = ArrayUtils.generateRandomArray(50, 20);
            MaxHeap maxHeap = new MaxHeap(50002);
            for (int i : arr) {
                maxHeap.push(i);
            }

            int pre = Integer.MAX_VALUE;
            while (!maxHeap.isEmpty()) {
                int pop = maxHeap.pop();
                if (pop > pre) {
                    System.out.println(false);
                }
                pre = pop;
            }
        }

    }
}
