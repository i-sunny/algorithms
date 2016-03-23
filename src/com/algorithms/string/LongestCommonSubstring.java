package com.algorithms.string;

/**
 * Created by sunny on 16/3/23.
 */

/**
 * 最长公共子串（公共子串是连续substring，不同于公共子序列）
 * 指求给定的一组字符串长度最大的共有的子串的问题。
 * 例如:
 * "abab","baba",公共子串(Common Substring)有"","a","b","ab","ba","aba","bab"。
 * 其中最长公共子串(LCS) 即为"aba"或"bab"
 */
public class LongestCommonSubstring {

    /**
     * DP
     * time: O(mn) space:O(mn)
     * http://blog.csdn.net/liufeng_king/article/details/8528858
     * 设opt(i,j)表示前缀子串对x[1: i] ,y[1:j]的最长公共后缀
     * opt(i,j) = opt(i-1,j-1) + x(i)   if x(i) = y(j)
     *          = ""                    ifx(i) != y(j)
     */
    public static String lcsDp(String x, String y) {
        int m = x.length(), n = y.length();
        int i, j;
        String ret = "";

        //初始化opt
        String[][] opt = new String[m + 1][n + 1];
        for (i = 0; i <= m; i++) {
            opt[i][0] = "";
        }
        for (j = 0; j <= n; j++) {
            opt[0][j] = "";
        }

        //dp
        int lcsLen = 0;
        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    opt[i][j] = opt[i - 1][j - 1] + x.charAt(i - 1);
                } else {
                    opt[i][j] = "";
                }

                if (opt[i][j].length() > lcsLen) {
                    lcsLen = opt[i][j].length();
                    ret = opt[i][j];
                } else if (opt[i][j].length() == lcsLen) {
                    ret += "\n" + opt[i][j];
                }
            }
        }
        return ret;
    }

    /**
     * 利用后缀数组实现
     */
    public static void main(String[] args) {
        System.out.println(lcsDp("abab", "baba"));
    }

}
