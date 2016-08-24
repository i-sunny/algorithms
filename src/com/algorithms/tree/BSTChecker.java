package com.algorithms.tree;

/**
 * ���һ�ö������Ƿ�Ϊ����������
 * ����Ա���Խ��-4.3
 *
 * @author xiaoqi.sxq
 * @version $Id: BSTChecker.java, v 0.1 2016-08-24 20:48 xiaoqi.sxq Exp $
 */
public class BSTChecker {

    /**
     * �������������˼��,ֻ�Ƕ��¼һ�����������һ��Ԫ��(����Ԫ��ֵ)
     * 1)����������Ƿ����������(�������ֵleftMax)
     * 2)��鵱ǰ�ڵ��Ƿ񱣳ֶ�������������(��������leftMax�Ƚ�)
     * 3)����������Ƿ����������
     */
    private static int leftMax = Integer.MIN_VALUE;

    public static boolean checkBST(Node root) {
        if (root == null)
            return true;

        //1)���������
        if (!checkBST(root.left))
            return false;

        //2)��鵱ǰ�ڵ�
        if (root.val <= leftMax)
            return false;
        leftMax = root.val;

        //3)���������
        if (!checkBST(root.right))
            return false;

        return true;
    }

    public static void main(String[] args) {
        char[] preOrder = new char[]{'c', 'b', 'a', 'e', 'd', 'f'};
        char[] inOrder = new char[]{'a', 'b', 'c', 'd', 'e', 'f' };
        Node root = RebuildBT.rebuildTree(preOrder.length, preOrder, 0, inOrder, 0);

        System.out.println(checkBST(root));
    }
}
