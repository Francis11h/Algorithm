quickSelect

找数组中 第k大的元素

Input: [3,2,5,1,6,4] and k = 2
Output: 5

    [3,2,5,1,6,4]
         i
               j
    [3,2,4,1,6,5]
             i 
           j
           
    [3,2,4,1]       
     i     j       
    [1,2,4,3]       
       i
         j
    [1,2,4,3]
         i
     j
     

关键 选择过程 :

	if (j >= left + k - 1) {										//选左边
        return quickSelect(nums, left, j, k);						//继续recursive
    }	
    if (i <= left + k - 1) {										//选右边, 同时更新k,因为要把左边的个数减掉
        return quickSelect(nums, i, right, k - (i - left));			//继续recursive
    }
    return nums[j + 1];												//第k大, 永远是nums[j + 1]






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




如果基准值选择上佳，搜索范围每次能够指数级减少，这样一来所耗时间是线性的(即O(n))。
	每次扔掉一半 :T(N) =n + n/2 + n/4 +...+ n/2^k = 2N => O(n)
但如果基准值选择非常不好，使得每次只能将搜索范围减少一个元素，则最糟的总体时间复杂度是平方级的O(n^2)

类似快速排序我们认为是 O(nlogn)的，因为我们算的是平均时间复杂度，也就是平摊一下各种情况的概率和时间复杂度。
因为并不一定每次都运气那么不好，比如你用随机的方式挑选 pivot的话，这样我们认为 quick select 的时间复杂度均摊是 O(n)的。
