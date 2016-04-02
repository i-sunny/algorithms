package com.algorithms.number;

import com.algorithms.sort.QuickSort;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by sunny on 4/2/16.
 *
 * 编程之美 2.5
 * 寻找最大的K个数
 */
public class FindKth {
    /**
     * O(nlgk)
     * 根据快排的思路将数组a[]分成两部分a[]
     * 注意: 该find()寻找第k小的数
     */
    public static int find(int[] a, int low, int high, int k){
        if (k <= 0 || low > high)
            return -1;
        int part = QuickSort.partition(a, low, high);

        int kk = k - ((part + 1) - low);
        //刚好在part位置
        if (kk == 0){
            return a[part];
        } else if (kk < 0) {
            return find(a, low, part - 1, k);  // 位于a的左边部分
        } else {
           return find(a, part + 1, high, kk);  //位于a的右边部分
        }
    }

    /**
     * O(nlgk)
     * 维持尺寸为k的最大堆
     * 注意: 该find()寻找第k小的数
     */
    public static int find(int[] a, int k){
        PriorityQueue<Integer> maxQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        for (int i : a){
            if (maxQueue.size() < k){
                maxQueue.add(i);
            } else if(maxQueue.peek() > i){
                maxQueue.remove();
                maxQueue.add(i);
            }
        }
        return maxQueue.peek();
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 1, 5, 8, 1, 6};
        //返回第k大的数
        int k = 3;
        System.out.println(FindKth.find(a, 0, a.length - 1, a.length + 1 - k));

        System.out.println(FindKth.find(a, a.length + 1 - k));
    }
}
