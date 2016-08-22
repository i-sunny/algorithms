package com.algorithms.number;

/**
 * 写一个函数，求两个整数的之和，要求在函数体内不得使用＋、－、×、÷
 * @author xiaoqi.sxq
 * @version $Id: AddWithoutArithmetic.java, v 0.1 2016-08-22 17:57 xiaoqi.sxq Exp $
 */
public class AddWithoutArithmetic {

    /**
     * 使用异或^得到不带进位的结果，使用&得到进位，重复操作直至没有进位
     */
    public static int addWithLoop(int num1,int num2) {
        int sum = 0;
        int carry = 0;
        do {
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;

            num1 = sum;
            num2 = carry;
        } while (num2 != 0);
        return num1;
    }

    public static int addWithRecursion(int num1, int num2) {

        if (num2 == 0)
            return num1;

        int sum = num1 ^ num2;
        int carry = (num1 & num2) << 1;
        return addWithRecursion(sum, carry);
    }

    public static void main(String[] args) {
        int num1 = 50, num2 = 71;
        System.out.println(addWithLoop(num1, num2));
        System.out.println(addWithRecursion(num1, num2));
    }


}
