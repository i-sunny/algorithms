package com.algorithms.tree;


/**
 * �ж϶������Ƿ�Ϊƽ�������:������һ���ڵ�,���������ĸ߶Ȳ����1
 * ����Ա���Ծ���-4.1
 *
 * @author xiaoqi.sxq
 * @version $Id: BalancedTreeChecker.java, v 0.1 2016-08-22 16:38 xiaoqi.sxq Exp $
 */
public class BalancedTreeChecker {

    // T(n) = 2T(n/2) + O(1) = O(n)
    private static int getHeight(Node root) {
        if (root == null)
            return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    /**
     * ����1:
     * �ݹ������,����ÿ���ڵ�����������ĸ߶�
     * T(n) = 2T(n/2) + O(n) = O(nlg(n))
     */
    public static boolean isBalanced(Node root) {
        if (root == null)
            return true;
        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * ����2:
     * T(n) = 2T(n/2) + O(1) = O(n)
     * ֱ�Ӽ��һ�����Ƿ�ƽ��:
     * (1)��������������ƽ�����Ҹ�������������ƽ����
     * (2)���������������������߶Ȳ�1����
     *
     * ����ƽ�����ĸ߶�,�������ƽ�����򷵻�-1
     */
    public static int checkBalancedHeight(Node root) {
        if (root == null)
            return 0;

        // ��������������ƽ����?
        int leftHeight = checkBalancedHeight(root.left);
        if (leftHeight < 0)
            return -1;

        // ��������������ƽ����?
        int rightHeight = checkBalancedHeight(root.right);
        if (rightHeight < 0)
            return -1;

        // ���������������������߶Ȳ�1����?
        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        else
            return Math.max(leftHeight, rightHeight) + 1;
    }

    public static boolean isBalanced2(Node root) {
        return checkBalancedHeight(root) >= 0;
    }

    public static void main(String[] args) {
        char[] preOrder = new char[]{'a', 'b', 'd', 'c', 'e', 'f'};
        char[] inOrder = new char[]{'d', 'b', 'a', 'e', 'c', 'f' };
        Node root = RebuildBT.rebuildTree(preOrder.length, preOrder, 0, inOrder, 0);

        System.out.println(isBalanced2(root));
    }

}
