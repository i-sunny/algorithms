package com.algorithms.string;

/**
 * Created by sunny on 16/3/23.
 */

/**
 * DP 算法导论 最长公共子序列 p208
 * 设两个序列X={x1,x2,x3，...xi}, Y={y1,y2,y3，....，yi}
 * 设一个opt[i,j]: 保存Xi与Yj的LCS长度
 * opt(i,j) = max{opt(i,j-1),opt(i-1,j)} if x[i] != y[j]
 *          = opt(i-1, j-1) + 1          if x[i] = y[j]
 * 注意opt[i][j]的i与j与X,Y的i,j不同
 */
public class LongestCommonSubsequence {

    private int[][] opt;

    public int LCS(String x, String y) {
        if (x == null || y == null) return 0;
        int m = x.length(), n = y.length();
        int i, j;
        //初始化opt
        opt = new int[m + 1][n + 1];
        for (i = 0; i <= m; i++) {
            opt[i][0] = 0;
        }
        for (j = 0; j <= n; j++) {
            opt[0][j] = 0;
        }

        //dp
        for (i = 1; i <= m; i++) {
            for (j = 1; j <= n; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    opt[i][j] = opt[i - 1][j - 1] + 1;
                } else {
                    opt[i][j] = Math.max(opt[i - 1][j], opt[i][j - 1]);
                }
            }
        }
        return opt[m][n];
    }

    /**
     * 可以根据opt[][]矩阵确定LCS路线
     * 即可以通过opt[i-1][j-1], opt[i-1][j]和opt[i][j-1]
     * O(1)时间推测出opt[i][j]从哪一个值计算过来
     */
    public String printLCS(String x, String y) {
        StringBuilder sb = new StringBuilder();
        //计算opt
        LCS(x, y);
        int i = opt.length - 1, j = opt[0].length - 1;
        //倒推LCS路径
        while (i > 0 && j > 0) {
            if (x.charAt(i - 1) == y.charAt(j - 1) && opt[i][j] == opt[i - 1][j - 1] + 1) {
                sb.append(x.charAt(i - 1));      //x[i] == y[j]
                i--;
                j--;
            } else if (opt[i][j] == opt[i][j - 1]) {
                j--;
            } else if (opt[i][j] == opt[i - 1][j]) {
                i--;
            }
        }
        sb.reverse();
        return sb.toString();
    }

    /**
     * 另外的优化:opt[i][j]只与当前行和前一行有关
     * 单纯计算LCS长度时只需保存两行opt数据即可 O(2*min(m,n))
     */

    //test
    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        System.out.println(lcs.printLCS("cnblogs", "belong"));
    }
}
