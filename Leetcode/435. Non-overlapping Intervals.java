435. Non-overlapping Intervals

Given a collection of intervals, 
find the minimum number of intervals you need to remove 
to make the rest of the intervals non-overlapping.


Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.



解题思路 :

{all intervals} - {max compatible intervals} = minimum deleted intervals

所以就是 求 {max compatible intervals}

e.g. [ [1,4], [2,3], [3,4] ], the interval with early start might be very long and incompatible with many intervals. 
But if we choose the interval that ends early, we will have more space left to accommodate more intervals.





关键 就是一句话 if we choose the interval that ends early, we will have more space left to accommodate more intervals.
		所以 我们按 end time 排序 然后 找最大的可相容的interval集合 


class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        // sort by end time
        int end = intervals[0][1];
        int count = 1;
        
        for (int i = 1; i < intervals.length; i++) {
            //not overlapping
            if (end <= intervals[i][0]) {
                end = intervals[i][1];
                count++;
            } 
        }
        return intervals.length - count;
    }
}


