package com.algorithms.string;

/**
 * Created by sunny on 16/3/23.
 */

import java.util.ArrayList;
import java.util.List;

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
     *          = ""                    if x(i) != y(j)
     */
    public static List<String> lcsDp(String x, String y) {
        int m = x.length(), n = y.length();
        int i, j;
        List<String> ret = new ArrayList<String>();

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
                    ret.clear();
                    ret.add(opt[i][j]);
                } else if (opt[i][j].length() == lcsLen) {
                    ret.add(opt[i][j]);
                }
            }
        }
        return ret;
    }

    /**
     * 利用后缀数组实现
     * 将两个字符串s1和s2进行拼接s=s1#s2
     * 根据后缀的最长公共前缀height[]进行求解
     * 最长公共子串(poj2774)  只要求求长度
     */
    public static int lcsSa(String s1, String s2) {
        String s = s1 + "#" + s2;
        int m = s1.length(), n = s2.length();
        //计算后缀数组
        int[] sa = SuffixArray.buildSuffixArray(s);
        int[] rank = SuffixArray.buildRank(s, sa);
        int[] height = SuffixArray.buildHeight(s, sa, rank);

        //寻找最长公共子串
        List<String> ret = new ArrayList<String>();
        int lcsLen = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] > lcsLen) {
                //保证两个后缀子串分别位于s1和s2中
                if (sa[i - 1] < m && sa[i] > m || sa[i - 1] > m && sa[i] < m) {
                    lcsLen = height[i];
                }
            } else if (height[i] == lcsLen) {
                ;
            }
        }
        return lcsLen;
    }

    public static void main(String[] args) {
        String s1 = "abbad", s2 = "baba";
        System.out.println(lcsDp(s1, s2));
        System.out.println(lcsSa(s1, s2));
    }

}
