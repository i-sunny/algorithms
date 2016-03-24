package com.algorithms.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by sunny on 16/3/23.
 */

/**
 * 后缀数组
 * http://www.acmerblog.com/suffix-array-6150.html
 * http://blog.csdn.net/self_chou/article/details/7761180
 * https://www.byvoid.com/blog/lcs-suffix-array/
 */

public class SuffixArray {

    /**
     * 最直接的方法构建后缀数组
     * Time: O(n^2lgn) 排序复杂度O(nlgn),compare复杂度O(n)
     * SA[i]表示排名第i的后缀在str中的起始位
     */
    public static int[] buildSuffixArray(final String str) {
        Integer[] sa = new Integer[str.length()];
        for (int i = 0; i < str.length(); i++) {
            sa[i] = i;
        }
        Arrays.sort(sa, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return str.substring(i1).compareTo(str.substring(i2));
            }
        });

        int[] ret = new int[sa.length];
        for (int i : sa) {
            ret[i] = sa[i];
        }
        return ret;
    }

    public static int[] buildRank(String str, int[] sa) {
        int[] rank = new int[sa.length];
        for (int i = 0; i < sa.length; i++) {
            rank[sa[i]] = i;
        }
        return rank;
    }

    // Height[i]表示后缀SA[i]和SA[i-1]的最长公共前缀(Longest Common Prefix,LCP)
    // 简记为Height[i]=LCP(SA[i],SA[i-1])
    public static int[] buildHeight(String str, int[] sa) {
        int[] height = new int[sa.length];
        height[0] = 0;

        int h = 0;
        for (int i = 1; i < sa.length; i++) {
            while (sa[i] + h < str.length() && sa[i - 1] + h < str.length() &&
                    str.charAt(sa[i] + h) == str.charAt(sa[i - 1] + h)) {
                h++;
            }
            height[i] = h;
            h = 0;
        }
        return height;
    }

    //通过rank[] 更高效
    public static int[] buildHeight(String str, int[] sa, int[] rank) {
        int len = str.length();
        int i, k, h = 0;
        int[] height = new int[len];
        for (i = 0; i < str.length(); i++) {
            if (rank[i] == 0)
                h = 0;
            else {
                k = sa[rank[i] - 1];
                if (--h < 0) h = 0;
                while (i + h < len && k + h < len && str.charAt(i + h) == str.charAt(k + h)) {
                    h++;
                }
            }
            height[rank[i]] = h;
        }
        return height;
    }

    /**
     * 倍增算法
     * DC3算法
     * http://blog.csdn.net/self_chou/article/details/7761180
     */

    public static void main(String[] args) {
        String str = "ababfffaf";
        int[] sa = buildSuffixArray(str);
        System.out.print("sa: ");
        for (int i : sa) {
            System.out.print(i + " ");
        }

        System.out.print("\nrank: ");
        int[] rank = buildRank(str, sa);
        for (int i : rank) {
            System.out.print(i + " ");
        }

        System.out.print("\nheight1: ");
        int[] h = buildHeight(str, sa);
        for (int i : h) {
            System.out.print(i + " ");
        }

        System.out.print("\nheight2: ");
        int[] h2 = buildHeight(str, sa, rank);
        for (int i : h2) {
            System.out.print(i + " ");
        }
    }
}



