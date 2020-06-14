package com.andrwe.study.algorithm;

import java.util.Arrays;

/**
 * @Author bo.fang
 * @Description
 * @Date 10:57 下午 2020/6/8
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] ints = {10, 6, 20, 18, 6, 26, 30, 11};
        quickSort(0, ints.length - 1, ints);
        Arrays.stream(ints).forEach(s -> System.out.print(s + " "));
    }

    /**
     * 快速排序
     *
     * @param left
     * @param right
     * @param ints
     */
    public static void quickSort(int left, int right, int[] ints) {
        int i, j;
        i = left;
        j = right;
        if (left > right) {
            return;
        }
        int baseNum = ints[left];

        while (i != j) {
            while (ints[j] >= baseNum && i < j) {
                j--;
            }
            while (ints[i] <= baseNum && i < j) {
                i++;
            }
            if (i < j) {
                int temp = ints[i];
                ints[i] = ints[j];
                ints[j] = temp;
            }
        }
        ints[left] = ints[i];
        ints[i] = baseNum;
        quickSort(left, i - 1, ints);
        quickSort(i + 1, right, ints);
    }

}
