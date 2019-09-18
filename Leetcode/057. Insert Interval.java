57. Insert Interval
 a set of non-overlapping intervals, insert a new interval into the intervals 
 (merge if necessary).

the intervals were initially sorted according to their start times.



Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


因为已经按照 start time 排好序了 而且不会有重叠

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //定位到区间集与待插入的区间开始重合的部分，然后开始求交集
        List<int[]> ans = new LinkedList<>();

        for (int[] interval : intervals) {
        	if (interval[1] < newInterval[0]) {
        		ans.add(interval);
        	} else if (interval[0] > newInterval[1]) {
        		ans.add(newInterval);
        		newInterval = interval;
        	} else {
        		newInterval[0] = Math.min(interval[0], newInterval[0]);
        		newInterval[1] = Math.max(interval[1], newInterval[1]);
        	}
        }
        ans.add(newInterval);
        return ans.toArray(new int[ans.size()][]);
    }
}