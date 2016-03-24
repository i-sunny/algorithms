package com.algorithms.number;

/**
 * Created by sunny on 16/3/24.
 */

/**
 * 编程之美 2.14
 * 求数组A[1:n]的子数组之和的最大值
 *
 * DP
 * opt[i] A[1:i]中以a[i]结尾的最大和
 * opt(i) = opt(i-1) + a[i]   if opt(i-1) > 0
 *        = a[i]              if opt[i-1] <= 0
 */

public class SubarrayMaxSum {

    //version 1
    public static int maxSum(int[] a) {
        if (a == null || a.length == 0) return 0;
        int[] opt = new int[a.length];
        opt[0] = a[0];

        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (opt[i-1] > 0) {
                opt[i] = opt[i - 1] + a[i];
            } else {
                opt[i] = a[i];
            }
            if (max < opt[i])
                max = opt[i];
        }
        return max;
    }

    /**
     * version 2
     * opt[i]只与opt[i-1]有关 可以节省空间
     */
    public static int maxSum2(int[] a) {
        if (a == null || a.length == 0) return 0;
        int opt = a[0];
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (opt > 0) {
                opt += a[i];
            } else {
                opt = a[i];
            }
            if (max < opt)
                max = opt;
        }
        return max;
    }

    //要求返回最大子序列位置[start, end]
    //maxSum2基础上记录end即可
    public static int[] maxSumPos(int[] a) {
        if (a == null || a.length == 0) return null;
        int opt = a[0];
        int max = a[0];
        int end = 0;
        for (int i = 1; i < a.length; i++) {
            if (opt > 0) {
                opt += a[i];
            } else {
                opt = a[i];
            }
            if (max < opt) {
                max = opt;
                end = i;
            }
        }

        //根据max和end倒推出start
        int start = end;
        while (max != 0){
            max -= a[start];
            start--;
        }
        start += 1;
        return new int[]{start, end};
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,-1,4,-3,5,-4};
        System.out.println(maxSum(a));
        System.out.println(maxSum2(a));
        int[] pos = maxSumPos(a);
        for (int i = 0; i < pos.length; i++) {
            System.out.print(pos[i] + " ");
        }
    }
}
