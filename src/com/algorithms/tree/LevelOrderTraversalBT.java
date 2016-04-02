package com.algorithms.tree;

import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sunny on 16/3/25.
 *
 * 编程之美 3.10 分层遍历二叉树
 */

public class LevelOrderTraversalBT {

    /**
     * 问题1）打印二叉树中某层次的节点(从左到右),其中根节点为第0层
     * 递归：遍历root点的第n层节点 相当于遍历左右子树的第n-1层节点
     */
    public static int traversalNodeAtLevel(Node root, int level) {

        if (root == null || level < 0){
            return 0;
        }

        //递归出口
        if (level == 0){
            System.out.print(root.val + " ");
            return 1;
        }

        return traversalNodeAtLevel(root.left, level - 1) + traversalNodeAtLevel(root.right, level - 1);
    }

    /**
     * 问题2) 分层遍历整棵二叉树
     * 直接根据travesalNodeAtLevel()求解
     */
    public static void traversalNodeByLevel(Node root) {

        for (int level = 0; ; level++) {
            if (traversalNodeAtLevel(root, level) == 0)
                break;
            System.out.println();
        }
    }

    /**
     * 问题2) 分层遍历整棵二叉树
     * 利用队列分层进行入队 同时利用curCnt指示在队列中该层节点剩余个数
     */
    public static void traversalNodeByLevel2(Node root) {

        if (root == null) return;

        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        int curCnt = 1, nxtCnt = 0;
        Node node;

        while (!queue.isEmpty()){
            if (curCnt > 0) {
                node = queue.poll();
                System.out.print(node.val + " ");

                //将其子节点放入队列
                if (node.left != null) {
                    queue.offer(node.left);
                    nxtCnt++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    nxtCnt++;
                }
                curCnt--;
            }
            if (curCnt == 0){
                //该层节点取完
                System.out.println();
                curCnt = nxtCnt;
                nxtCnt = 0;
            }
        }
    }


    public static void main(String[] args) {
        char[] preOrder = new char[]{'a', 'b', 'd', 'c', 'e', 'f'};
        char[] inOrder = new char[]{'d', 'b', 'a', 'e', 'c', 'f'};
        Node root = RebuildBT.rebuildTree(preOrder.length, preOrder, 0, inOrder, 0);

//        traversalNodeAtLevel(root, 2);

        traversalNodeByLevel(root);

        traversalNodeByLevel2(root);
    }
}
