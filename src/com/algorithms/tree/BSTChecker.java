package com.algorithms.tree;

/**
 * 检查一棵二叉树是否为二叉排序树
 * 程序员面试金典-4.3
 *
 * @author xiaoqi.sxq
 * @version $Id: BSTChecker.java, v 0.1 2016-08-24 20:48 xiaoqi.sxq Exp $
 */
public class BSTChecker {

    /**
     * 利用中序遍历的思想,只是多记录一课子树的最后一个元素(最大的元素值)
     * 1)检查左子树是否二叉排序树(返回最大值leftMax)
     * 2)检查当前节点是否保持二叉排序树特性(与左子树leftMax比较)
     * 3)检查右子树是否二叉排序树
     */
    private static int leftMax = Integer.MIN_VALUE;

    public static boolean checkBST(Node root) {
        if (root == null)
            return true;

        //1)检查左子树
        if (!checkBST(root.left))
            return false;

        //2)检查当前节点
        if (root.val <= leftMax)
            return false;
        leftMax = root.val;

        //3)检查右子树
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
