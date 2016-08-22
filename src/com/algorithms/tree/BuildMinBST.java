package com.algorithms.tree;

/**
 * 给定一个有序数组,元素各不相同且按升序排序,创建一棵高度最小的二叉排序树
 * 程序员面试金典-4.3
 * @author xiaoqi.sxq
 * @version $Id: BuildMinBST.java, v 0.1 2016-08-22 21:43 xiaoqi.sxq Exp $
 */
public class BuildMinBST {

    /**
     * 要让高度最小,则左右子树节点数量尽量接近,所以我们需要让:
     * 数组中间成为根节点,数组左边一半为左子树,右边一半为右子树,并递归
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
