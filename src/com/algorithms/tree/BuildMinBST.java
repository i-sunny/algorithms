package com.algorithms.tree;

/**
 * ����һ����������,Ԫ�ظ�����ͬ�Ұ���������,����һ�ø߶���С�Ķ���������
 * ����Ա���Խ��-4.3
 * @author xiaoqi.sxq
 * @version $Id: BuildMinBST.java, v 0.1 2016-08-22 21:43 xiaoqi.sxq Exp $
 */
public class BuildMinBST {

    /**
     * Ҫ�ø߶���С,�����������ڵ����������ӽ�,����������Ҫ��:
     * �����м��Ϊ���ڵ�,�������һ��Ϊ������,�ұ�һ��Ϊ������,���ݹ�
     * T(n) = 2T(n/2) + O(1) = O(n)
     */
    public static TreeNode build(int[] input, int begin, int end) {
        if (begin > end)
            return null;

        int mid = (begin + end) >> 1;
        TreeNode root = new TreeNode(input[mid]);

        root.left = build(input, begin, mid - 1);
        root.right = build(input, mid + 1, end);

        return root;
    }

    private static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 3, 5, 6};
        TreeNode root = build(input, 0, input.length - 1);
        System.out.println(root);
    }
}
