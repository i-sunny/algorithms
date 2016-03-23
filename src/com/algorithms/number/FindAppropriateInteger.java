package com.algorithms.number;

/**
 * Created by sunny on 16/3/23.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 编程之美 2.8
 * 在给定一个正整数N，求一个最小的正整数M，使得N*M的十进制表示里只含有1和0
 */


public class FindAppropriateInteger {
    private List<Integer>[] modTable;

    public List<Integer> findAppropriateInteger(int N) {
        //初始化余数信息表modTable
        modTable = new List[N];
        for (int i = 0; i < N; i++) {
            modTable[i] = new ArrayList<Integer>();
        }

        int k, j;
        for (k = 1, j = 10 % N; ; k++, j = (j * 10) % N) {
            int noUpdates = 0;
            boolean flag = false;
            
            //update 10^k
            if (modTable[j].size() == 0) {
                modTable[j].add(k);
            }
            //update modTable
            for (int t = 0; t < N; t++) {
                int x = (j + t) % N;
                if (modTable[t].size() != 0 && k > modTable[t].get(modTable[t].size() - 1) && modTable[x].size() == 0){
                    flag = true;
                    modTable[x].addAll(modTable[t]);
                    modTable[x].add(k);
                }
            }
            if (!flag)
                noUpdates++;
            if (noUpdates == N || modTable[0].size() != 0)
                break;
        }
        if (modTable[0].size() != 0){
            return modTable[0];
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        List<Integer> ret = new FindAppropriateInteger().findAppropriateInteger(995);
        for (int i = 0; i < ret.size(); i++){
            System.out.println(ret.get(i));
        }
    }
}
