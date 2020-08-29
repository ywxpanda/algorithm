package com.panda.utils;

public class CompareUtils {

    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }

    public static <T extends Comparable<T>> T min(T a, T b) {
        return a.compareTo(b) > 0 ? b : a;
    }

    public static <T extends Comparable<T>> boolean larger(T a, T b) {
        return a.compareTo(b) > 0;
    }

    public static <T extends Comparable<T>> boolean smaller(T a, T b) {
        return !larger(a, b);
    }

}
