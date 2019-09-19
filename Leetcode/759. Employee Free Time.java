759. Employee Free Time

Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
Output: [[3,4]]


Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
Output: [[5,6],[7,9]]


given a set of intervals, find all places where there are no intervals.

sweep line 标准 TreeMap解法





/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start,int _end) {
        start = _start;
        end = _end;
    }
};
*/


class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null) return null;
        Map<Integer, Integer> map = new TreeMap<>();
        for (List<Interval> list : schedule) {
        	for (Interval interval : list) {
        		int start = interval.start, end = interval.end;
        		map.put(start, map.getOrDefault(start, 0) + 1);
        		map.put(end, map.getOrDefault(end, 0) - 1);
        	}
        }

        int count = 0;
        int prevTimeStamp = -1;
        List<Interval> ans = new LinkedList<>();
        for (Integer key : map.keySet()) {
        	if (prevTimeStamp != -1) {
        		ans.add(new Interval(prevTimeStamp, key));
        		prevTimeStamp = -1;
        	}
        	count += map.get(key);
        	if (count == 0) prevTimeStamp = key;
        }
        return ans;
    }
}












