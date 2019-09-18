56. Merge Intervals

merge all overlapping intervals.

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].


Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.


分析 : 要合并 需要顺序 ------> sort by beginning 为什么排序？   如果我们按照区间的 start 大小排序，那么在这个排序的列表中可以合并的区间一定是连续的

        怎么判断可以合并: 每次比 两个int[] 的 a1.end 与 a2.start 
        但是怎么合并？？ 本题卡在这了
            就是 还是先建 List<int[]> 再转 int[][], 因为肯定是动态的 要可变的




如果我们按照区间的 start 大小排序，那么在这个排序的列表中可以合并的区间一定是连续的
If we sort by the start size of the interval,
Then the intervals that can be merged in this sorted list must be continuous.

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
                // for case [1,4] [2,3] 第二个区间的 end 也要变（如果第一个区间的end比第二个要大的话）
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
            } else {
                ans.add(intervals[i]);
            }
        }
        //最后一个要加上 必须要的 
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



TreeMap做法 不用sort

sweep line


https://leetcode.com/problems/meeting-rooms-ii/discuss/203658/HashMapTreeMap-resolves-Scheduling-Problem

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][0];
        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
        }
        List<int[]> ans = new LinkedList<>();
        int newStart = 0, count = 0;
        for (Integer key : map.keySet()) {
            if (count == 0) newStart = key;
            count += map.get(key);
            // this count == 0 means a full interval has been completed
            if (count == 0) {
                ans.add(new int[]{newStart, key});
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
}














旧版本
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