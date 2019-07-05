278. First Bad Version


have n versions [1, 2, ..., n]
find out the first bad one

You are given an API bool isBadVersion(version) which will return whether version is bad.

Implement a function to find the first bad version. 
You should minimize the number of calls to the API.


Example:
    Given n = 5, and version = 4 is the first bad version.

    call isBadVersion(3) -> false
    call isBadVersion(5) -> true
    call isBadVersion(4) -> true

    Then 4 is the first bad version. 


oooxxxxxxxx 型 二分
l    m    r

ooooooxxxxx
l    m    r


/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        if (n <= 0) return 1;

        int left = 1, right = n;                    //下标从1开始
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (isBadVersion(left)) return left;
        if (isBadVersion(right)) return right;
        return 1;
    }
}






