package com.algorithms.tree;

/**
 * Created by sunny on 16/3/25.
 *
 * 根据前序和中序重建二叉树
 * 前序: abdcef  中序: dbaecf  根据前序可以确定a是根节点，根据中序确定(db)和(ecf)是左右子树
 * 前序: a(X)(Y) 中序: (X)a(Y) 因此问题可以转换成X,Y两个子问题
 * T(n) = 2T(n/2) + O(n) = nlg(n)  在中序中寻找前序第一个点a 需要时间O(n)
 *
 * 注意：该题目存在前提：每个节点中的val值是唯一的
 */

class Node {
    Node left;
    Node right;
    char val;       //根据语境确定

    //不是必需的
    public Node() {
        left = null;
        right = null;
        val = 0;
    }

}

public class RebuildBT {
    /**
     * 由于Java不支持指针 只能使用pbeg标记preOrder中的位置  ibeg标记在inOrder中的位置
     */
    public static Node rebuildTree(int nTreeLen, char[] preOrder, int pbeg, char[] inOrder, int ibeg) {

        //边界条件
        if (nTreeLen == 0 || pbeg >= preOrder.length || ibeg >= inOrder.length) {
            return null;
        }
        //获取前序遍历第一个节点
        Node root = new Node();
        root.val = preOrder[pbeg];

        //已经是叶节点
        if (nTreeLen == 1) {
            return root;
        }

        //在中序中找到该根节点位置
        int i;
        for (i = ibeg; i < ibeg + nTreeLen; i++) {
            if (root.val == inOrder[i]) {
                break;
            }
        }

        int nLeftLen = i - ibeg, nRightLen = nTreeLen - nLeftLen - 1;
        //递归重构左子树
        Node left = rebuildTree(nLeftLen, preOrder, pbeg + 1, inOrder, 0);
        root.left = left;
        //递归重构右子树
        Node right = rebuildTree(nRightLen, preOrder, pbeg + nLeftLen + 1, inOrder, i+1);
        root.right = right;

        return root;
    }

    public static void main(String[] args) {
        char[] preOrder = new char[]{'a', 'b', 'd', 'c', 'e', 'f'};
        char[] inOrder = new char[]{'d', 'b', 'a', 'e', 'c', 'f'};
        Node root = rebuildTree(preOrder.length, preOrder, 0, inOrder, 0);
    }
}
