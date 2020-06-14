package com.andrwe.study.algorithm;

import java.util.Arrays;

/**
 * @Author bo.fang
 * @Description
 * @Date 9:45 下午 2020/6/7
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] ints = {12, 35, 99, 18, 76};
        bubbleSort(ints);
        Arrays.stream(ints).forEach(s -> System.out.print(s + "  "));
    }

    /**
     * 冒泡排序
     *
     * @param ints
     */
    public static void bubbleSort(int[] ints) {
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = 0; j < ints.length - i - 1; j++) {
                if (ints[j] < ints[j + 1]) {
                    int temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                }
            }
        }
    }
}
