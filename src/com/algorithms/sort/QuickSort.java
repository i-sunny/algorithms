package com.algorithms.sort;

/**
 * Created by sunny on 4/2/16.
 */

public class QuickSort {

    public static void quickSort(int[] a, int low, int high){
        if (low >= high)
            return;
        int p = partition(a, low, high);
        quickSort(a, low, p - 1);
        quickSort(a, p + 1, high);
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[low];
        int li = low + 1, hi = high;
        while (li <= hi){
            while (li <= hi && a[li] <= pivot){
                li++;
            }
            while (li <= hi && a[hi] > pivot) {
                hi--;
            }
            if (li < hi){
                swap(a, li, hi);
                li++;
                hi--;
            }
        }
        //主元素与hi交换位置
        swap(a, low, hi);
        return hi;
    }

    private static void swap(int[] a, int i, int j){
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args){
        int[] a = new int[]{4, 1, 5, 8, 1, 6};
        QuickSort.quickSort(a, 0, a.length - 1);
    }
}
