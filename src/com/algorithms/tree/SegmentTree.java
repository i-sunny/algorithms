package com.algorithms.tree;

/**
 * Created by sunny on 16/3/25.
 */

/**
 * 示例：区间求和
 * Time complexity to create segment tree is O(nlogn)
 * Space complexity to create segment tree is O(nlogn)
 * Time complexity to search in segment tree is O(logn)
 *
 * http://blog.csdn.net/metalseed/article/details/8039326
 */
public class SegmentTree {
    int[] segmentTree;

    /**
     * 线段树所需要的空间最多是原来input[]的4倍，最少是2倍(满二叉树时)
     */
    public SegmentTree(int size) {
        segmentTree = new int[size];
    }

    /**
     * 构建线段树
     * node从1开始才有意义, node所对应区间为[begin, end]
     */
    public void build(int node, int[] input, int begin, int end){
        if (begin == end) {
            segmentTree[node] = input[begin];
        } else{
            int mid = (begin + end) >> 1;
            //递归构造左右子树
            build(2 * node, input, begin, mid);
            build(2 * node + 1, input, mid + 1, end);
            //回溯到当前节点进行求和操作
            segmentTree[node] = segmentTree[2 * node] + segmentTree[2 * node + 1];
        }
    }

    /**
     * 查询线段树, 在总区间[begin, end]中查询区间[left, right]的和
     */
    public int query(int node, int begin, int end, int left, int right) {
        //总区间包含在查询区间内
        if (left <= begin && right >= end)
            return segmentTree[node];

        // 查询区间和总区间没有交集
        // 因为是求和操作,所以可以返回0
        if (left > end || right < begin)
            return 0;

        int mid = (begin + end) >> 1;
        int p = query(2 * node, begin, mid, left, right);
        int q = query(2 * node + 1, mid + 1, end, left, right);

        //p+q跟具体操作有关 此例是求和
        return p + q;
    }

    /**
     * 单节点更新,在idx位置更新至value值
     * 在某一点更新导致其父节点也更新
     */
    public void update(int node, int begin, int end, int idx, int value){
        if (begin == end && begin == idx){
            segmentTree[node] = value;
        } else {
            int mid = (begin + end) >> 1;
            if (idx <= mid) {
                //位于左子树则更新左子树
                update(2 * node, begin, mid, idx, value);
            } else {
                //更新右子树
                update(2 * node + 1, mid + 1, end, idx, value);
            }
            //最后回溯更新当前节点
            segmentTree[node] = segmentTree[2 * node] + segmentTree[2 * node + 1];
        }
    }

    /**
     * 区间更新 可以使用懒加载技术 只更新到满足要求的当前节点，而不更新当前节点的子节点，只有当这些子节点被用到时才更新
     * 先按照查询的方式将其划分成线段树中的结点，然后修改这些结点的信息，并给这些结点标上代表这种修改操作的标记
     * 在修改和查询的时候，如果我们到了一个结点p，并且决定考虑其子结点，那么我们就要看看结点p有没有标记，
     * 如果有，就要按照标记修改其子结点的信息，并且给子结点都标上相同的标记，同时消掉p的标记
     */
    public void update(int node, int begin, int end, int left, int right, int value) {

    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 3, 5, 7, 9};
        SegmentTree st = new SegmentTree(4 * input.length);
        st.build(1, input, 0, input.length - 1);

        int left = 3, right = 5;
        int value = st.query(1, 0, input.length - 1, left, right);
        System.out.format("query[%d %d]: %d\n", left, right, value);

        st.update(1, 0, input.length - 1, 3, 19);
        value = st.query(1, 0, input.length - 1, left, right);
        System.out.format("query[%d %d]: %d\n", left, right, value);

    }
}
