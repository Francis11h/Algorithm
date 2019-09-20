731. My Calendar II

A new event can be added if adding the event will not cause a Triple booking.

A triple booking happens when three events have some non-empty intersection 
(ie., there is some time that is common to all 3 events.)


MyCalendar();
MyCalendar.book(10, 20); // returns true
MyCalendar.book(50, 60); // returns true
MyCalendar.book(10, 40); // returns true
MyCalendar.book(5, 15); // returns false
MyCalendar.book(5, 10); // returns true
MyCalendar.book(25, 55); // returns true
Explanation: 
The first two events can be booked.  The third event can be double booked.
The fourth event (5, 15) can‘t be booked, because it would result in a triple booking.
The fifth event (5, 10) can be booked, as it does not use time 10 which is already double booked.	它并没有用到已经双重预定的10。
The sixth event (25, 55) can be booked, as the time in [25, 40) will be double booked with the third event;
the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.


标准的扫描线 问题
使用treemap实现可以避免每次排序，时间复杂度每次是O(n)

class MyCalendarTwo {
	TreeMap<Integer, Integer> map;
    public MyCalendarTwo() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        
        int count = 0;		//已有几重预定了
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        	count += entry.getValue();
        	//放进去不符合 就再拿出来 return false
        	if (count > 2) {	
        		map.put(start, map.get(start) - 1);
        		map.put(end, map.get(end) + 1);
        		return false;
        	}
        }
        return true;
    }
}


/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */









