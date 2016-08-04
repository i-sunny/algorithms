package com.algorithms.etc;

/**
 * 100¥��������������
 * �ο�:
 *  http://www.tuicool.com/articles/NzUNJr
 *  http://blog.csdn.net/guoxuequan/article/details/8020457
 *
 *  DP����
 *  opt(i,j)��ʾi���¥j��������Ҫ���ԵĴ���,��ô�������������ڵ�k��
 *  ����һ�ż���,�������:
 *      (1)��������,����������opt[k-1,j-1]
 *      (2)����û��,���������opt[i-k,j]
 *  opt(i,j) = 1 + min{max{opt(k-1,j-1),opt(i-k,j)}}, k = [1,2...,i]
 *  �߽�:opt(i,1) = i, i=0,1,...     opt(0,*) = 0
 *
 * @author xiaoqi.sxq
 * @version $Id: TwoEggProblem.java, v 0.1 2016-08-04 17:07 xiaoqi.sxq Exp $
 */
public class TwoEggProblem {

    public static int minTest(int f, int e) {
        int[][] opt = new int[f+1][e+1];

        int i, j, tmp;
        //�߽�����
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
