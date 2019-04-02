215. Kth Largest Element in an Array

minHeap

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int val : nums) {
            minHeap.offer(val);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}

O(nlogn)

//     [3,2,5,1,6,4]
//          i
//                j
//     [3,2,4,1,6,5]
//              i 
//            j
           
//     [3,2,4,1]       
//      i     j       
//     [1,2,4,3]       
//        i
//          j
//     [1,2,4,3]
//          i
//      j


quickSelect

class Solution {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    
    private int quickSelect(int[] nums, int left, int right, int k) {
        int pivot = nums[left + (right - left) / 2];
        int i = left, j = right;
        
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                j--;
            }
            if (i <= j) {
                int temp = nums[i];
                nums[i++] = nums[j];
                nums[j--] = temp;
            }
        }
        
        if (j >= left + k - 1) {
            return quickSelect(nums, left, j, k);
        }
        if (i <= left + k - 1) {
            return quickSelect(nums, i, right, k - (i - left));
        }
        return nums[j + 1];
    }
}

O(n);