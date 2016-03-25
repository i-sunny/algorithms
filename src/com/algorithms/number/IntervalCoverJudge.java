package com.algorithms.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 编程之美 2.19
 */

class Interval {
    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class IntervalCoverJudge {

    public static boolean isCover(Interval[] t, Interval query) {
        List<Interval> target = new ArrayList<Interval>();

        //sort by start O(nlgn)
        Arrays.sort(t, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start < o2.start ? -1 : o1.start > o2.start? 1 : 0;
            }
        });

        int lastEnd = -1;
        Interval last;
        //合并重合区间 O(n)
        for (Interval interval : t) {
            if (interval.start > lastEnd){
                target.add(interval);
                lastEnd = interval.end;
            } else {
                //区间有重合
                if (interval.end > lastEnd) {
                    last = target.get(target.size() - 1);
                    last.end = interval.end;
                    lastEnd = interval.end;
                }
            }
        }

        int s1 = getInterval(target, query.start);
        int s2 = getInterval(target, query.end);
        return s1 == s2 && query.end <= target.get(s1).end;
    }

    //二分法 找到key所在区间index
    private static int getInterval(List<Interval> target, int key) {
        int begin = 0, end = target.size() - 1, mid = 0;
        while (begin <= end){
            mid = (begin + end) >> 1;
            if (target.get(mid).start < key) {
                begin = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return end;
    }

    public static void main(String[] args) {
        Interval[] t = new Interval[]{new Interval(1, 2), new Interval(2,3), new Interval(5, 8), new Interval(4, 9)};
        System.out.println(isCover(t, new Interval(2, 7)));
    }
}
