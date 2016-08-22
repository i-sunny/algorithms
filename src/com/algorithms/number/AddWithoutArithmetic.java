package com.algorithms.number;

/**
 * дһ��������������������֮�ͣ�Ҫ���ں������ڲ���ʹ�ã�������������
 * @author xiaoqi.sxq
 * @version $Id: AddWithoutArithmetic.java, v 0.1 2016-08-22 17:57 xiaoqi.sxq Exp $
 */
public class AddWithoutArithmetic {

    /**
     * ʹ�����^�õ�������λ�Ľ����ʹ��&�õ���λ���ظ�����ֱ��û�н�λ
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
