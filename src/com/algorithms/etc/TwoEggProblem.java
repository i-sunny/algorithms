package com.algorithms.etc;

/**
 * 100楼扔两个鸡蛋问题
 * 参考:
 *  http://www.tuicool.com/articles/NzUNJr
 *  http://blog.csdn.net/guoxuequan/article/details/8020457
 *
 *  DP问题
 *  opt(i,j)表示i层大楼j个鸡蛋需要尝试的次数,那么假设我们首先在第k层
 *  扔了一颗鸡蛋,两种情况:
 *      (1)鸡蛋碎了,则变成子问题opt[k-1,j-1]
 *      (2)鸡蛋没碎,变成子问题opt[i-k,j]
 *  opt(i,j) = 1 + min{max{opt(k-1,j-1),opt(i-k,j)}}, k = [1,2...,i]
 *  边界:opt(i,1) = i, i=0,1,...     opt(0,*) = 0
 *
 * @author xiaoqi.sxq
 * @version $Id: TwoEggProblem.java, v 0.1 2016-08-04 17:07 xiaoqi.sxq Exp $
 */
public class TwoEggProblem {

    public static int minTest(int f, int e) {
        int[][] opt = new int[f+1][e+1];

        int i, j, tmp;
        //边界条件
        for (i = 0; i <= f; i++) {
            opt[i][1] = i;
        }
        for (j = 0; j <= e; j++) {
            opt[0][j] = 0;
        }

        for (i = 1; i <= f; i++) {
            for (j = 2; j <= e; j++) {
                int tmpMin = Integer.MAX_VALUE;
                for (int k = 1; k <= i; k++) {
                    tmp = 1 + Math.max(opt[k-1][j-1], opt[i-k][j]);
                    if (tmp < tmpMin) {
                        tmpMin = tmp;
                    }
                }
                opt[i][j] = tmpMin;
            }
        }
        return opt[f][e];
    }

    public static void main(String[] args) {
        System.out.println(minTest(100, 2));
    }

}
