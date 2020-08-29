package com.panda.number;

public class NumberOperation {

    /**
     * arr中，只有一种数，出现奇数次
     *
     * @return 该奇数
     */
    public static int getOddTimesNum(int[] arr) {
        int ret = 0;
        //通过异或运算
        for (int i : arr) {
            ret ^= i;
        }
        return ret;
    }

    /**
     * arr中，有两种数，出现奇数次
     *
     * @param arr
     */
    public static int[] getOddTimesNum2(int[] arr) {
        int ret = 0;
        for (int i : arr) {
            ret ^= i;
        }

        //提取出最右的1，两个奇数在该位置必定不同，偶数个数的不影响

        int rightOne = getRightOne(ret);
        int ret1 = 0;
        //通过该位将数组划分为两个部分,相同的为一个部分
        for (int i : arr) {
            if ((i & rightOne) != 0) {
                //在rightOne的第一1的位置不等
                ret1 ^= i;
            }
        }
        int[] res = new int[2];
        res[0] = ret1;
        res[1] = ret1 ^ ret;
        return res;
    }


    /**
     * 计算一个int数的二进制当中出现1的次数
     *
     * @return
     */
    public static int countBitOne(int num) {
        int count = 0;
        while (num != 0) {
            //找到最右的一个1
            int rightOne = getRightOne(num);
            count++;
            //将最有的一个1 变为0
            num ^= rightOne;
        }
        return count;
    }

    /**
     * 获取二进制数的最右的一个1
     *
     * @param num
     * @return
     */
    public static int getRightOne(int num) {
        return num & (~num + 1);
    }

    public static void main(String[] args) {
        System.out.println(countBitOne(10));
    }
}
