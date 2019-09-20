729. My Calendar I

Implement a MyCalendar class to store your events. 
A new event can be added if adding the event will not cause a double booking.

Your class will have the method, book(int start, int end). 
Formally, this represents a booking on the half open interval [start, end), 
the range of real numbers x such that start <= x < end.

A double booking happens when two events have some non-empty intersection 
(ie., there is some time that is common to both events.)

For each call to the method MyCalendar.book, 
return true if the event can be added to the calendar successfully without causing a double booking. 
Otherwise, return false and do not add the event to the calendar.


MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(15, 25); // returns false
MyCalendar.book(20, 30); // returns true
Explanation: 
The first event can be booked.  The second can‘t because time 15 is already booked by another event.
The third event can be booked, as the first event takes every time less than 20, but not including 20.




hint : Store the events as a sorted list of intervals. 
       If none of the events conflict, then the new event can be added.


Solution 1 
use a list to store the intervals and everytime go through the whole list

判断两个区间相不相交 :  最大的开始时间 < 最小的结束时间

not overlapping
[5, 10]  [10, 20]
[10, 20] [25, 30]

overlapping
[10, 20] 
   [15, 25]


class MyCalendar {
    List<int[]> books;
    public MyCalendar() {
        books = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for (int[] book : books) {
            if (Math.max(book[0], start) < Math.min(book[1], end)) return false;
        }
        books.add(new int[]{start, end});
        return true;
    }
}

T:O(n^2)
S:O(n)
每添加一个时间段，要遍历的list长度分别为0，1，....，N - 1，求和得到(N - 1) * N / 2



Solution 2
Keep existing books sorted and only check 2 books start right before & after the new book starts
让现在的 intervals 保持一种排序 然后只检查可能相交的 那两个intervals

Keep the intervals sorted,
if the interval started right before the new interval contains the start, or
if the interval started right after the new interval started within the new interval.


    floor      ceiling
... |----| ... |----| ...
       |---------|
      s         e

if s < floor.end Or e > ceiling.start, there is an overlap.



[10, 20] [25, 30] 
          s    e
          
[10, 20] 
   [15, 25]
     s  e






T:O(N * logN)，N是添加成功的时间段数量，对于每一个时间段的搜索需要O(logN)
