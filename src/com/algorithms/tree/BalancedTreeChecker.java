package com.algorithms.tree;


/**
 * 判断二叉树是否为平衡二叉树:即任意一个节点,两棵子树的高度差不超过1
 * 程序员面试经典-4.1
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
     * 方法1:
     * 递归这棵树,计算每个节点的两棵子树的高度
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
     * 方法2:
     * T(n) = 2T(n/2) + O(1) = O(n)
     * 直接检查一颗树是否平衡:
     * (1)该树的左子树是平衡树且该树的右子树是平衡树
     * (2)该树的左子树和右子树高度差1以内
     *
     * 返回平衡树的高度,如果不是平衡树则返回-1
     */
    public static int checkBalancedHeight(Node root) {
        if (root == null)
            return 0;

        // 该树的左子树是平衡树?
        int leftHeight = checkBalancedHeight(root.left);
        if (leftHeight < 0)
            return -1;

        // 该树的右子树是平衡树?
        int rightHeight = checkBalancedHeight(root.right);
        if (rightHeight < 0)
            return -1;

        // 该树的左子树和右子树高度差1以内?
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
