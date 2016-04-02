package com.algorithms.sort;

/**
 * Created by sunny on 4/2/16.
 */
public class MergeSort {

    /**
     * a[low, mid]和a[mid+1, high]为两个有序数组
     * merge成一个完整有序有序数组
     */
    private static void merge(int[] a, int low, int mid, int high) {

        int[] tmp = new int[high - low + 1];
        int li = low;
        int hi = mid + 1;
        int k = 0;   //index for tmp[]

        while (li <= mid && hi <= high) {
            if (a[li] <= a[hi]) {
                tmp[k++] = a[li];
                li++;
            } else {
                tmp[k++] = a[hi];
                hi++;
            }
        }

        // 把左边剩余的数移入数组
        while (li <= mid){
            tmp[k++] = a[li++];
        }

        // 把右边剩余的数移入数组
        while (hi <= high){
            tmp[k++] = a[hi++];
        }

        // 把新数组中的数覆盖a数组
        for (int i = 0; i < tmp.length; i++) {
            a[low + i] = tmp[i];
        }
    }

    public static void mergeSort(int[] a, int low, int high){
        if (low >= high)
            return;
        int mid = (low + high) >> 1;
        mergeSort(a, low, mid);
        mergeSort(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 1, 1, 1,1,1};
        MergeSort.mergeSort(a, 0, a.length - 1);
    }
}
