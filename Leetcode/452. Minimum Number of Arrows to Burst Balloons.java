Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) 
				and another arrow at x = 11 (bursting the other two balloons).


同  435. Non-overlapping Intervals



解法 :

还是 按

1. Sort by end time.
2. always track the end of the current balloon, 
	and ignore all the balloons which end before it. 
3. Once the current balloon is ended (= the next balloon starts after the current balloon‘s end)
   one has to increase the number of arrows by one and start to track the end of the next balloon.


   class Solution {
    public int findMinArrowShots(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        int end = intervals[0][1];
        int arrow = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (end >= intervals[i][0]) {
                continue;
            }
            end = intervals[i][1];
            arrow++;
        }
        return arrow;
    }
}