package com.algorithms.number;

/**
 * Created by sunny on 16/3/24.
 */

/**
 * 求数组A[0:n-1]中最长递增子序列
 * DP
 * opt(i)以a[i]为最后元素的最长递增子序列长度
 * opt(i)   = max{opt[k] + 1, 1}  k in [0, i-1] if a[i] > a[k]
 */
public class LongestIncreasingSubsequence {

    // O(n^2)
    public static int increasingSubsequence(int[] a) {

        int[] opt = new int[a.length];
        int max = 1;
        opt[0] = 1;
        for (int i = 1; i < a.length; i++) {
            opt[i] = 1;
            for (int k = 0; k < i; k++) {
                if (a[i] > a[k] && opt[k] + 1 > opt[i])
                    opt[i] = opt[k] + 1;
            }
            if (opt[i] > max)
                max = opt[i];
        }
        return max;
    }

    /**
     * maxV[i]数组表示子序列长度为i的最大元素的最小值
     * 减少比较次数
     * O(n^2)
     * 优化: 1)MaxV[i]对于i单调递增,因此for(j=opt[i-1]; j >=0; j--)即可
     *      2)上述查找利用二分查找可以降为O(nlgn)
     */
    public static int increasingSubsequence2(int[] a) {
        int[] maxV = new int[a.length + 1];
        maxV[1] = a[0];
        maxV[0] = min(a) - 1;     // 数组中最小值，边界值
        int nMaxLIS = 1;          //数组最长递增子序列
        int[] opt = new int[a.length];
        opt[0] = 1;

        for (int i = 1; i < a.length; i++) {
            opt[i] = 1;
            int j;
            for (j = nMaxLIS; j >= 0; j--) {
                if (a[i] > maxV[j]) {
                    opt[i] = j + 1;
                    break;
                }
            }

            //如果当前最长子序列大于之前最长递增子序列，更新最长信息
            if (j == nMaxLIS) {
                nMaxLIS = opt[i];
                maxV[nMaxLIS] = a[i];
            } else if (a[i] > maxV[j] && a[i] < maxV[j + 1]) {
                maxV[j + 1] = a[i];
            }
        }
        return nMaxLIS;
    }

    private static int min(int[] a) {
        int min = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < min)
                min = a[i];
        }
        return min;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, -3, 5, -7, 9, 11, 13,1};
        System.out.println(increasingSubsequence(a));
        System.out.println(increasingSubsequence2(a));
    }
}
