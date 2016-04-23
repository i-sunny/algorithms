package com.algorithms.recursion;

/**
 * Created by sunny on 4/23/16.
 */

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 马的遍历问题
 * 在 n×n 方格的棋盘上，从任意指定方格出发，为马寻找一条走遍棋盘每一格并且只经过一次的一条路径
 * 思路：1)暴力的基于深度优先搜索解法，递归实现
 * 2)对下一条路径进行选择，优先选择出路最少的点作为下一个点
 * 两种方法的时间复杂度都是指数级的O(8^n)
 *
 * 参考：http://www.cnblogs.com/wonderKK/archive/2011/11/07/2240368.html
 */
public class TraversalChessBoard {
    //马可能移动的8种方向
    private static final int[] xMove = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};
    private int[][] chessBoard;
    private int sz;
    private int cnt = 0;

    public TraversalChessBoard(int n) {
        chessBoard = new int[n][n];
        sz = n;
    }

    /**
     * 1)暴力的基于深度优先搜索解法，递归实现
     */
    public void travelsal(int x0, int y0) {
        chessBoard[x0][y0] = ++cnt;
        if (dfs(x0, y0)) {
            printResult();
        } else {
            System.out.println("Fail to find a path!");
        }
    }

    private boolean dfs(int x, int y) {
        //递归出口 已经走完棋盘每一格
        if (cnt == sz * sz) {
            return true;
        }
        //递归所有可能走法
        //一旦找到一条路径则提前退出
        int nx, ny;
        for (int i = 0; i < xMove.length; i++) {
            nx = x + xMove[i];
            ny = y + yMove[i];
            if (nx >= 0 && nx < sz && ny >= 0 && ny < sz && chessBoard[nx][ny] == 0) {
                chessBoard[nx][ny] = ++cnt;
                if (dfs(nx, ny))
                    return true;
                chessBoard[nx][ny] = 0;    //0表示没有被走过
                cnt--;
            }
        }
        return false;
    }

    /**
     * 2)对下一条路径进行选择，优先选择出路最少的点作为下一个点
     */
    private static class Node {
        int x;
        int y;
        int ways;

        public Node(int x, int y, int ways) {
            this.x = x;
            this.y = y;
            this.ways = ways;
        }
    }

    public void travelsal2(int x0, int y0) {
        chessBoard[x0][y0] = ++cnt;
        if (dfs2(x0, y0)) {
            printResult();
        } else {
            System.out.println("Fail to find a path!");
        }
    }

    private boolean dfs2(int x, int y) {
        //递归出口 已经走完棋盘每一格
        if (cnt == sz * sz) {
            return true;
        }

        //最小优先级队列
        PriorityQueue<Node> minQueue = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.ways - o2.ways;
            }
        });
        int nx, ny, nWays;
        for (int i = 0; i < xMove.length; i++) {
            nx = x + xMove[i];
            ny = y + yMove[i];
            nWays = getWayNum(nx, ny);
            if (nWays > -1) {
                minQueue.add(new Node(nx, ny, nWays));
            }
        }

        while (!minQueue.isEmpty()) {
            Node nxtNode = minQueue.poll();
            nx = nxtNode.x;
            ny = nxtNode.y;
            chessBoard[nx][ny] = ++cnt;
            if (dfs2(nx, ny))
                return true;
            chessBoard[nx][ny] = 0;    //0表示没有被走过
            cnt--;
        }
        return false;
    }

    private int getWayNum(int x, int y) {
        int i, count = 0, nx, ny;
        if (x < 0 || y < 0 || x >= sz || y >= sz || chessBoard[x][y] > 0)
            return -1;         //-1表示该结点非法或者已经跳过了
        for (i = 0; i < xMove.length; i++) {
            nx = x + xMove[i];
            ny = y + xMove[i];
            if (nx < 0 || ny < 0 || nx >= sz || ny >= sz)
                continue;
            if (chessBoard[nx][ny] == 0)
                ++count;
        }
        return count;
    }


    private void printResult() {
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                System.out.print(chessBoard[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TraversalChessBoard tcb = new TraversalChessBoard(7);
        long startTime = System.currentTimeMillis();
        tcb.travelsal(2, 0);
        long endTime = System.currentTimeMillis();
        System.out.format("RunTime 1: %d millis", (endTime - startTime));

        tcb = new TraversalChessBoard(7);
        System.out.println();
        startTime = System.currentTimeMillis();
        tcb.travelsal2(2, 0);
        endTime = System.currentTimeMillis();
        System.out.format("RunTime 2: %d millis", (endTime - startTime));
    }

}
