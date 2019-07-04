252. Meeting Rooms

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.

判断一个人是否可以参加全部meeting

Input: [[0,30],[5,10],[15,20]]
Output: false

Input: [[7,10],[2,4]]
Output: true

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
	public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) return false;
        //按开始时间排序
        Collections.sort(intervals, new Comparator<Interval>(){
        	@Override
        	public int compare(Interval a, Interval b) {
        		return a.start - b.start;
        	}
        });

        for (int i = 0; i < intervals.length - 1; i++) {
        	if (intervals[i].end > intervals[i + 1].start) return false;
        }
        return true;
    }
}

一个是 Collections.sort
一个是 Arrays.sort




class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return true;

        Arrays.sort(intervals, new Comparator<int[]>(){
        	@Override
        	public int compare(int[] a, int[] b) {
        		return a[0] - b[0];
        	}
        });
        for (int i = 0; i < intervals.length - 1; i++) {
        	if (intervals[i][1] > intervals[i + 1][0]) return false;
        }
        return true;
    }
}



