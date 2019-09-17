347. Top K Frequent Elements

Given a non-empty array of integers, return the k most frequent elements.

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]



Solution 1 Use Heap and MinHeap


class Solution {
	public List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> ans = new LinkedList<>();
		Map<Integer, Integer> map = new HashMap<>();

		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		//本题 pq 写法很巧妙 本质上还是比两个 Integer 但是这两个Integer每次都是从 map中取得的
		//或者用 lambda表达式
		//PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b)->(map.get(a) - map.get(b)));
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(new Comparator<Integer>(){
			@Override
			public int compare(Integer a, Integer b) {
				return map.get(a) - map.get(b);
			}
		});

		for (int key : map.keySet()) {
			minHeap.offer(key);
			if (minHeap.size() > k) {
				minHeap.poll();
			}
		}

		while (minHeap.size() > 0) {
			ans.add(0, minHeap.poll());
		}
		return ans;
	}
}


O(NlogK)
O(K)



Solution bucket sort 




O(N)
O(N)




class Solution {
	public List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer>[] bucket = new List[nums.length + 1];
		Map<Integer, Integer> map = new HashMap<>();
        
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
        
        for (int key : map.keySet()) {
            int frequence = map.get(key);
            if (bucket[frequence] == null) {
                bucket[frequence] = new ArrayList<>();
            }
            bucket[frequence].add(key);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != null) {
                ans.addAll(bucket[i]);
            }
            if (ans.size() >= k) break;
        }
        return ans;
    }
}



1. how to new a List<Integer>[] in java ???


	You can create an array of lists but you Cannot use type during Initalization.

			List<Integer>[] lists=new List[10];

			//insertion
			for(int i=0;i<10;i++){
			    lists[i]=new ArrayList<Integer>();
			    lists[i].add(i);
			    lists[i].add(i+1);
			}

			//printing
			for(List<Integer> list:lists){
			    System.out.println(list.size());
			}

	Why this works? Because the lists variable points to an array data structure whose data type is List<Integer>. This array contains a set of references to different objects of type List<Integer>, and this is why if we try to run lists[i]=new ArrayList<String>(); it will not compile. However when we initialize the array itself we don't need to provide the type of the List objects as List since from JVM point of view a List of Integer objects and a List of Object objects will require the same number of bytes as logn as their sizes is same. The only constraint comes when we set a array member to a value (of type List - it has to be List<Integer> not anything else)

	You can type cast the List[] to a List<Integer>[] but the end result and the JVM behavior is the same.



2. addAll

    因为 bucket[frequence] 可能有两个 arrayList 比如 输入为 [1, 2] k = 2 我们这时 需要给它全加进来









类似的题目 

K Smallest In Unsorted Array

A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}

class Solution {
	public int[] kSmallest(int[] array, int k) {
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>(){
			@Override
			public int compare(Integer a, Integer b) {
			return b - a;
			}
		});

		maxHeap.offer(num);
		if (maxHeap.size() > k) {
			maxHeap.poll();
		}
		
		int[] ans = new int[k];
		for (int i = 0; i < k; i++) {
			ans[k - 1 - i] = maxHeap.poll();
		}
		return ans;
	}
}
