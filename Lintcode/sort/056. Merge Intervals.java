56. Merge Intervals

merge all overlapping intervals.

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].


Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


分析 : 要合并 需要顺序 ------> sort by beginning
        怎么判断可以合并: 每次比 两个int[] 的 a1.end 与 a2.start 
        但是怎么合并？？ 本题卡在这了
            就是 还是先建 List<int[]> 再转 int[][], 因为肯定是动态的 要可变的


class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][0];
        List<int[]> ans = new ArrayList<>();

        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] >= intervals[i + 1][0]) {
                intervals[i + 1][0] = intervals[i][0];
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
            } else {
                ans.add(intervals[i]);
            }
        }
        ans.add(intervals[intervals.length - 1]);

        //List 转 Array 最后是 new int[][]
        return ans.toArray(new int[ans.size()][]);
    }
}


本题目补充 

List 转 Array

调用 toArray(new 数组类型[数组大小]) 方法,  

List<String> list = new ArrayList<String>();
String[] array = list.toArray(new String[list.size()]);



List<int[]> ans = new ArrayList<>();

int[][] res = ans.toArray(new int[ans.size()][]);






旧版本


//按 start time sorting
//time complexity : sort : O(nlogn) + O(n)
//space O(n)-final result storage
class Solution {
    //也可以写个 class 重新定义 
    /*private class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
        }
    }*/

    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        //[1,3],[5,10],[17,19],[2,6],[2,7],[15,18]
        //after sort
        //[1,3],[2,6],[2,7],[5,10],[15,18],[17,19]
        //merge one-by-one
        //[1,3],[2,6] -> [1,6][2,7] ->[1,7][5,10] -> [1,10]   [15,18],[17,19] -> [15,19]
        //[1,10] [15,19]

        LinkedList<Interval> merged = new LinkedList<>();
        for (Interval interval : intervals) {
            //getLast()取出之前 merged结果的最后一个 和后面没merge的 判断
            //无 overlapped， 直接加
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            } else {    //merge end 的时间，开始 时间不变
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }
        return merged;
    }
}


 class Solution {
    /**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> ans = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) return ans;
        Collections.sort(intervals, new Comparator<Interval>(){
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });
        
        for (int i = 0; i < intervals.size() - 1; i++) {
            if (intervals.get(i).end >= intervals.get(i + 1).start) {
                intervals.get(i + 1).start = intervals.get(i).start;
                intervals.get(i + 1).end = Math.max(intervals.get(i).end, intervals.get(i + 1).end);
            } else {
                ans.add(intervals.get(i));
            }
        }
        ans.add(intervals.get(intervals.size() - 1));
        return ans;
    }
}