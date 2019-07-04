253. Meeting Rooms II

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

给一系列开始时间,找到所需要的最少的房间个数

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

Input: [[7,10],[2,4]]
Output: 1


用的room最少
所以我们要把之前开完会的room分配给之后需要开会的
所以我们需要知道会议开始时间的先后顺序,------> sort by starting time
第二步就是考虑如何分配了 :  策略就两个 没meeting room available => allocate a new one '/' 有room has freed up(释放),不用新加

我们 不care 哪个room gets freed up 仅仅是有就行

我们知道现在已经分配的room, 同时知道这些room的解冻时间
We already know the rooms we have allocated till now and we also know when are they due to get free because of the end times of the meetings going on in those rooms. 
我们只需要找到所有被分配的room中 结束时间最早的, 把该room分给新来的 meeting 就可以。
We can simply check the room which is due to get vacated the earliest amongst all the allocated rooms.

所以我们可以建个小根堆, 存结束时间,方面我们每次找可以最先空出来的
要是老room释放的时间 < 新meeting开始的 , 老的poll
每次 新加新的 meeting 的 endtime
堆中元素的个数 就是我们需要的meeting room的数量

Following up on the previous hint, we can make use of a min-heap to store the end times of the meetings in various rooms. 

If the room we extracted from the top of the min heap isnot free, then no other room is. 
So, we can save time here and simply allocate a new room.




Input: [[0, 30],[5, 10],[15, 20]]
Output: 2

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare (int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i][1]);
        }
        return minHeap.size();
    }
}







旧版本

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start - b.start;
            }
        });

        minHeap.offer(intervals[0].end);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= minHeap.peek()) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i].end);
        }
        return minHeap.size();
    }
}

