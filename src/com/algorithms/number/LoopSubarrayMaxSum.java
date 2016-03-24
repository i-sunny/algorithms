package com.algorithms.number;

/**
 * Created by sunny on 16/3/24.
 */

/**
 * 数组A[0:n-1]收尾相邻 允许找到一段数字A[i],.., A[n-1], A[0],..,A[j]使其和最大
 *
 * DP
 * 1)如果解没有跨越A[n-1]和A[0] 解M1与SubarrayMaxSum一致
 * 2)解跨越A[n-1]和A[0] 寻找从A[0]开始的最大一段以及寻找以A[n-1]结尾的最大一段
 * M2 = A[0]+ ... + A[i] + A[j] + ...+A[n-1]
 * if i > j M2 = sum(A[0:n-1])
 */

public class LoopSubarrayMaxSum {

    public static int maxSum(int[] a) {
        //case 1
        int max1 = SubarrayMaxSum.maxSum(a);

        //case 2
        int i = 0, j = a.length - 1;
        int sum0 = a[0], sum1 = a[a.length - 1];
        int tmp = sum0;
        for (int k = 1; k < a.length; k++) {
            tmp += a[k];
            if (sum0 < tmp) {
                sum0 = tmp;
                i = k;
            }
        }
        int sum = tmp;
        tmp = sum1;
        for (int k = a.length - 2; k >= 0; k--) {
            tmp += a[k];
            if (sum1 < tmp){
                sum1 = tmp;
                j = k;
            }
        }

        int max2;
        if (i > j) {
            max2 = sum;
        } else {
            max2 = sum0 + sum1;
        }
        return max1 > max2? max1 : max2;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2,-5,-1,2,4};
        System.out.println(maxSum(a));
    }
}
