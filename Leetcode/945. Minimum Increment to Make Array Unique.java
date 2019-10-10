945. Minimum Increment to Make Array Unique


TLE Solution

class Solution {
    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap();
        for (int a : A) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int count = 0;
        Arrays.sort(A);
        for (int i = 0; i < A.length; i++) {
            if (map.get(A[i]) > 1) {
                int temp = A[i];
                while (map.containsKey(A[i])) {
                    A[i]++;
                    count++;
                }
                map.put(A[i], 1);
                map.put(temp, map.get(temp) - 1);
            }
        }
        return count;
    }
}




Sort the input array.
Compared with previous number,
the current number need to be at least prev + 1.

Time Complexity: O(NlogN) for sorting
Space: O(1) for in-space sort




class Solution {
    public int minIncrementForUnique(int[] A) {
        if (A == null || A.length == 0) return 0;
        Arrays.sort(A);
        int ans = 0, prev = A[0]; 
        for (int i = 1; i < A.length; i++) {
            int expect = prev + 1;
            ans += A[i] > expect ? 0 : expect - A[i];
            prev = Math.max(expect, A[i]);
        }
        return ans;
    }
}

