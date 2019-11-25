373 Find K Pairs with Smallest Sums


You are given two integer arrays nums1[] and nums2[] sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]] 
Explanation: The first 3 pairs are returned from the sequence: 
             [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]


Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [1,1],[1,1]
Explanation: The first 2 pairs are returned from the sequence: 
             [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]


Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [1,3],[2,3]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]






思路 同 376 变成 矩阵 matrix 然后 heap


  | 1 |	4 |	7 |			row = 0
---------------
2 |	s |	  |	  |
---------------
4 |	  |	  |	  |
---------------
6 |	  |	  |big|




class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 ) return ans;
        
        int n1 = nums1.length, n2 = nums2.length;
        
        PriorityQueue<Tuple> pq = new PriorityQueue<>(new Comparator<Tuple>(){
        	@Override
        	public int compare(Tuple a, Tuple b) {
        		return a.val - b.val;
        	}
        });

        for (int j = 0; j < n1; j++) pq.offer(new Tuple(0, j, nums2[0] + nums1[j]));
        
        if (k > n1 * n2) k = n1 * n2;
        
        while (k > 0) {
        	Tuple cur = pq.poll();
            ans.add(Arrays.asList(nums1[cur.y], nums2[cur.x]));
        	k--;
        	if (cur.x == n2 - 1) continue;
        	pq.offer(new Tuple(cur.x + 1, cur.y, nums2[cur.x + 1] + nums1[cur.y]));
        }
        return ans;
    }
}

class Tuple {
	int x, y, val;
	Tuple(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
}



T:O(Klogn + n)
S:O(n)




另一种写法

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0 ) return ans;
        
        int n1 = nums1.length, n2 = nums2.length;
        
        PriorityQueue<Tuple> pq = new PriorityQueue<>();

        for (int j = 0; j < n1; j++) pq.offer(new Tuple(0, j, nums2[0] + nums1[j]));
        
        if (k > n1 * n2) k = n1 * n2;
        
        while (k > 0) {
        	Tuple cur = pq.poll();
            ans.add(Arrays.asList(nums1[cur.y], nums2[cur.x]));
        	k--;
        	if (cur.x == n2 - 1) continue;
        	pq.offer(new Tuple(cur.x + 1, cur.y, nums2[cur.x + 1] + nums1[cur.y]));
        }
        return ans;
    }
}

class Tuple implements Comparable<Tuple>{
	int x, y, val;
	Tuple(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
	@Override
	public int compareTo(Tuple b) {
		return this.val - b.val;
	}
}



