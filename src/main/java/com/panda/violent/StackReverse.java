package com.panda.violent;

import java.util.Stack;

/**
 * 不实用额外的数据结构堆栈进行反转
 */
public class StackReverse {


    public static <T> void reverse(Stack<T> stack) {

        if (stack == null || stack.isEmpty()) {
            return;
        }
//        T t = process(stack);
//        reverse(stack);
//        stack.push(t);
        process(stack);

    }

    /**
     * 获取栈的最底层的一个元素
     *
     * @param stack
     * @param <T>
     * @return
     */
    private static <T> T process(Stack<T> stack) {
        T res = stack.pop();
        if (stack.isEmpty()) {
            return res;
        }
        T las = process(stack);
        System.out.println("res:" + res);
        stack.push(res);
//        System.out.println(stack);
        return las;
    }


//    public static <T> void print(Stack<T> stack) {
//        StringBuilder stringBuilder = new StringBuilder();
//        while (!stack.isEmpty()) {
//            stringBuilder.append(stack.pop());
//            if (!stack.isEmpty()) {
//                stringBuilder.append(",");
//            }
//        }
//
//        System.out.println(stringBuilder.toString());
//    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        reverse(stack);


        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + ",");
        }
    }
}
