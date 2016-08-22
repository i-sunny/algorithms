package com.algorithms.etc;

import java.util.Stack;

/**
 * 如何利用循环和栈代替递归
 * 参考文章:
 *  原文: http://www.codeproject.com/Articles/418776/How-to-replace-recursive-functions-using-stack-and#intro
 *  译文: http://www.cnblogs.com/wb-DarkHorse/archive/2013/11/15/3284228.html
 *
 * @author xiaoqi.sxq
 * @version $Id: RecursiveToLoopSample.java, v 0.1 2016-08-22 20:18 xiaoqi.sxq Exp $
 */
public class RecursiveToLoopSample {

    public static int fibNum(int n) {
        if (n < 1)
            return -1;
        if (1 == n || 2 == n)
            return 1;

        // think this as
        // int addVal = fibNum( n - 1);
        // addVal += fibNum(n - 2);
        // return addVal;
        return fibNum(n - 1) + fibNum(n - 2);
    }

    // (First rule)
    // this can be declared as local class since it will be only used within this function.
    static class Snapshot {
        int inputN;       // parameter that changes
        int addVal;       // the local variable that changes
        int stage;        // the stage variable to track where the Snapshot has taken

        Snapshot() {}
    }

    public static int fibNumLoop(int n) {


        // (Second rule)
        int returnVal = -1;        // the return value at the point

        // (Third rule)
        Stack<Snapshot> snapshotStack = new Stack<Snapshot>();

        // (Fourth rule)
        Snapshot currentSnapshot = new Snapshot();
        currentSnapshot.inputN = n;
        currentSnapshot.stage = 0; // as initial stage

        snapshotStack.push(currentSnapshot);

        // (Fifth rule)
        while (!snapshotStack.empty()) {
            currentSnapshot = snapshotStack.pop();

            // (Sixth rule)
            switch (currentSnapshot.stage) {
                // (Seventh rule)
                case 0:
                    if (currentSnapshot.inputN < 1) {
                        // (Eighth rule && Ninth rule)
                        returnVal = -1;
                        continue;
                    }
                    if (currentSnapshot.inputN == 1 || currentSnapshot.inputN == 2) {
                        // (Eighth rule && Ninth rule)
                        returnVal = 1;
                        continue;
                    } else {
                        // (Tenth rule)

                        // return ( fibNum(n - 1) + fibNum(n - 2)); this is actually two steps
                        //   (first calling itself with parameter n-1, and second calling itself with parameter n-2)
                        //   this is where we need make a Snapshot, and split into two different stages.
                        currentSnapshot.stage = 1;                        // current Snapshot is done processing and
                        //   only waiting for result of calling itself,
                        //   so becomes stage "1"
                        snapshotStack.push(currentSnapshot);

                        // Create a new Snapshot for calling itself
                        Snapshot newSnapshot = new Snapshot();
                        newSnapshot.inputN = currentSnapshot.inputN - 1; // give parameter as parameter given
                        //   when calling itself
                        //   (first case fibNum(n - 1))
                        newSnapshot.stage = 0;                         // since it will start from the beginning of
                        //   the function, give the initial stage
                        snapshotStack.push(newSnapshot);
                    }
                    break;
                // (Seventh rule)
                case 1:

                    // (Tenth rule)

                    currentSnapshot.addVal = returnVal;
                    currentSnapshot.stage = 2;                         // current Snapshot is done processing and
                    //   only waiting for result of calling itself,
                    //   so becomes stage "2"
                    snapshotStack.push(currentSnapshot);

                    // Create a new Snapshot for calling itself
                    Snapshot newSnapshot = new Snapshot();
                    newSnapshot.inputN = currentSnapshot.inputN - 2; // give parameter as parameter given when calling
                    //   itself (first case fibNum(n - 2))
                    newSnapshot.stage = 0;                          // since it will start from the beginning of
                    //   the function, give the initial stage
                    snapshotStack.push(newSnapshot);
                    break;
                case 2:
                    // (Eighth rule)
                    returnVal = currentSnapshot.addVal + returnVal;  // actual addition of ( fibNum(n - 1) + fibNum(n - 2) )

                    break;
            }
        }

        // (Second rule)
        return returnVal;
    }

    public static void main(String[] args) {
        System.out.println(fibNum(31));
        System.out.println(fibNumLoop(31));
    }

}
