package com.algorithms.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 4/2/16.
 *
 * 给定一棵树，同时给出树中的两个结点(n1和n2)，求它们的最低公共祖先
 * http://www.acmerblog.com/lca-lowest-common-ancestor-5574.html
 */


public class LowestCommonAncestor {
    /**
     * O(n)
     * 1) 找到从根到n1的路径，并存储在一个向量或数组中。
     * 2)找到从根到n2的路径，并存储在一个向量或数组中。
     * 3) 遍历这两条路径，直到遇到一个不同的节点，则前面的那个即为最低公共祖先.
     */
    private boolean findPath(Node root, List<Node> path, int val){

        if (root == null)
            return false;

        path.add(root);
        if (root.val == val){
            return true;
        }
        //如果在左子树或右子树中搜索到路径
        if (findPath(root.left, path, val) || findPath(root.right, path, val)) {
            return true;
        }
        //否则该节点下未找到则弹出该节点
        path.remove(path.size() - 1);
        return false;
    }

    public Node fingLCA(Node root, int n1, int n2){
        List<Node> path1 = new ArrayList<Node>(), path2 = new ArrayList<Node>();
        boolean find1 = findPath(root, path1, n1);
        boolean find2 = findPath(root, path2, n2);
        if (find1 && find2){
            int i;
            for (i = 0; i < path1.size() && i < path2.size(); i++) {
                if (path1.get(i) != path2.get(i))
                    break;
            }
            return path1.get(i - 1);
        }
        return null;
    }

    /**
     * O(n)并且只需遍历一次
     */
    public Node findLCA2(Node root, int n1, int n2) {

        //递归出口
        if (root == null)
            return null;
        // 只要n1 或 n2 的任一个匹配即可
        if (root.val == n1 || root.val == n2)
            return root;

        Node left = findLCA2(root.left, n1, n2);
        Node right = findLCA2(root.right, n1, n2);
        // 如果都返回非空指针, 则说明两个节点分别出现了在两个子树中，则当前节点肯定为LCA
        // 如果一个为空，在说明LCA在另一个子树
        if (left != null && right != null){
            return root;
        } else if(left != null) {
            return left;
        } else if(right != null) {
            return right;
        } else {
            return null;
        }

    }

    public static void main(String[] args) {
        char[] preOrder = new char[]{'a', 'b', 'd', 'c', 'e', 'f'};
        char[] inOrder = new char[]{'d', 'b', 'a', 'e', 'c', 'f'};
        Node root = RebuildBT.rebuildTree(preOrder.length, preOrder, 0, inOrder, 0);

        LowestCommonAncestor lca = new LowestCommonAncestor();
        Node ans = lca.fingLCA(root, 'b', 'f');
        System.out.println(ans.val);

        //version 2
        ans = lca.findLCA2(root, 'e', 'f');
        System.out.println(ans.val);
    }

}
