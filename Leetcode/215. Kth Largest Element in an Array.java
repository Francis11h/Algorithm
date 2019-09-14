215. Kth Largest Element in an Array

Solution 1 Sort
    O(NlogN) O(1)

improve time complexity by using additional space

Solution 2 minHeap

    keeping the size of heap always less or equal to K
    O(NlogK) O(K)

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



Solution 3 quickSelect 
    O(N) in average O(N^2) in the worst case
    discard half each time n+(n/2)+(n/4)..1 = n + (n-1) = O(2n-1) = O(n)
    O(1) space

随机选下标index 作为 pivot的下标
遍历数组交换 左边都比 pivot 小 右边都比 pivot 大


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

