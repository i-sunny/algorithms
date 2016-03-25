package com.algorithms.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 16/3/25.
 *
 * 编程之美 3.10 分层遍历二叉树
 */

public class LevelOrderTravesalBT {

    /**
     * 问题1）打印二叉树中某层次的节点(从左到右),其中根节点为第0层
     * 递归：遍历root点的第n层节点 相当于遍历左右子树的第n-1层节点
     */
    public static List<Node> travesalNodeAtLevel(Node root, int level) {

        //递归出口
        List<Node> ret = new ArrayList<Node>();
        if (level == 0){
            ret.add(root);
        }

        if (root.left != null) {
            ret.addAll(travesalNodeAtLevel(root.left, level - 1));
        }
        if (root.right != null) {
            ret.addAll(travesalNodeAtLevel(root.right, level - 1));
        }
        return ret;
    }

    /**
     * 问题2) 分层遍历整棵二叉树
     * 直接根据travesalNodeAtLevel()求解
     */

    public static void main(String[] args) {
        char[] preOrder = new char[]{'a', 'b', 'd', 'c', 'e', 'f'};
        char[] inOrder = new char[]{'d', 'b', 'a', 'e', 'c', 'f'};
        Node root = RebuildBT.rebuildTree(preOrder.length, preOrder, 0, inOrder, 0);

        for (Node node : travesalNodeAtLevel(root, 2)) {
            System.out.println(node.val);
        }
    }
}
