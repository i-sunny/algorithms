package com.algorithms.number;

/**
 * Created by sunny on 4/21/16.
 */

/**
 * 2016百度暑期实习笔试题
 * 乘法游戏表的第i行第j列位置元素为i*j，并且乘法表下标从1开始，比如2*3的乘法表为：
 * 1 2 3
 * 2 4 6
 * 对于n*m的乘法表，给出一个数k，求在乘法表中按照不降排序后的第k个元素是多少。
 *
 * 思路：乘法表在横向（沿着j方向）和纵向（i方向）都是递增的，因此可以设置两个遍历量
 *      x(i1,j1)从上到下遍历，y(i2,j2)从左到右遍历，每一步都是取min(x,y)。
 */
public class FindKthInMultiplicationTable {
    public static long find(int n, int m, int k) {
        //(i1,j1)竖向从上到下遍历
        //(i2,j2)横向从左到右遍历
        int i1 = 1, j1 = 1, i2 = 1, j2 = 1;
        long x = i1 * j1, y = i2 * j2;
        long ret = 1;
        while (k != -1) {
            if (x <= y) {
                ret = x;
                i1++;
                if (i1 > n) {
                    i1 = i2 + 1;
                    j1++;
                }
                x = i1 * j1;  //下一次的值
            } else {
                ret = y;
                j2++;
                if (j2 > m) {
                    j2 = j1 + 1;
                    i2++;
                }
                y = i2 * j2;  //下一次的值
            }
            k--;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(find(4, 5, 9));
    }
}
