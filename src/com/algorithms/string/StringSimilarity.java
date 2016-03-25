package com.algorithms.string;

/**
 * Created by sunny on 16/3/25.
 */

/**
 * 编程之美 3.3
 * 计算两个字符串的相似度（或者两个字符串的距离)）
 * A[0:m-1] = "abcdefg"和 B[0:n-1] = "abcdef"的距离为1
 * DP
 * opt(i,j)表示A中前i个构成的子串和B中前j个构成的子串的距离
 * opt(i,j)   =  min{opt(i-1,j-1), opt(i,j-1), opt(i-1, j)} + 1   if a[i] != b[j]
 *            = opt(i-1,j-1)      if a[i] = b[j]
 */

public class StringSimilarity {

    public static int distance(String a, String b){
        int m = a.length(), n = b.length();
        int i, j;
        //opt初始化
        int[][] opt = new int[m+1][n+1];
        for (i = 1; i < opt.length; i++) {
            opt[i][0] = 1;
        }
        for (j = 1; j < opt[0].length; j++) {
            opt[0][j] = 1;
        }
        opt[0][0] = 0;

        //dp
        for (i = 1; i < opt.length; i++) {
            for (j = 1; j < opt[0].length; j++) {
                if (a.charAt(i-1) == b.charAt(j-1)){
                    opt[i][j] = opt[i-1][j-1];
                } else {
                    opt[i][j] = Math.min(Math.min(opt[i-1][j], opt[i][j-1]), opt[i-1][j-1]) + 1;
                }
            }
        }
        return opt[m][n];
    }

    public static void main(String[] args) {
        String a = "abcdf", b = "ccdg";
        System.out.println(distance(a, b));
    }
}
