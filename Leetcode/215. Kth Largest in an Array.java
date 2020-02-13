215. Kth Largest Element in an Array

Solution 1 Sort
    O(NlogN) O(1)


improve time complexity "by using additional space"

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



    
找数组中 第Kth小 (非本题)
    //Kth大的话 仅仅是 交换的过程符号换下 大的在pivot前面即可


class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    
    // return to the kth smallest num in the array with the index between low and high.  
    private int quickSelect(int[] nums, int low, int high, int k) {
        int i = low, j = high;
        int pivot = nums[low + (high - low) / 2];
        // pivot 前面要放 小于它的 所以如果 小于 符合 移动到下一个
        // pivot 后面要放 大于它的 所以如果 大于 符合 移动到下一个
        // 交换过程 结束的条件是 i > j 
        while (i <= j) {
            //前面直到找到小于等于pivot的num
            while (i <= j && nums[i] < pivot) {                             
                i++;
            }
            while (i <= j && nums[j] > pivot) {
                j--;
            }
            // 现在 nums[i] >= pivot nums[j] <= pivot 所以要交换
            if (i <= j) {
                int temp = nums[i];
                nums[i++] = nums[j];
                nums[j--] = temp;
            }
        }
        // low + k - 1 这个下标很关键 代表 第kth 元素的下标
        //后半部分[j+1 - high]不影响
        if (low + k - 1 <= j) {
            return quickSelect(nums, low, j, k);
        }
        //前半部分已经包含了(i - low)个小得了, 后半部分[i, high]里继续找第k - (i - low) 个
        if (low + k - 1 >= i) {
            return quickSelect(nums, i, high, k - (i - low));
        }
        return nums[j + 1];		// return nums[i - 1]亦可
    }
}



=======




举例子来证明 


[5,4,3,2,1] 找第3小


low = 0, high = 4, k = 3
pivot = 3

[5,4,3,2,1]

i
		 j

[1,4,3,2,5]

   i
	   j

[1,2,3,4,5]

     i
	 j

[1,2,3,4,5]

       i h
l  j


--> i = 3 都不满足 正好落在[j,pivot,i]这个pivot上
return nums[index of pivot] = nums[j+1] = 3


现在 low-j 代表的是 比 pivot 小/等 的部分
	i-high 代表的是 比 pivot 大/等 的部分

[low, j, pivot, i, high]  or  [low, j, i, high]
								  pivot


// 第kth小的位置就在 low + k - 1 的位置	
// [low, low + k - 1] 前kth小的

// 如果 pivot 位置 就在 此 那么他就是
// 可是 pivot可能是 j 也可能是 j+1 所以不确定 
// 但是能肯定的是 pivot的位置 不是j 就是 j+1 就是i
// 所以 j到i 确定了 pivot的位置 
// 如果 j >= low + k - 1 证明 j左边有足够的数满足 所以j右边就不需要了 只需要在 low-j 中继续找第k小的
// 如果 i <= low + k - 1 证明 可以舍去 low到i-1 同时 在i-high中找第 k-(i-1-low+1) 小的 





[4,3,2,1]  找第3小


low = 0, high = 3, k = 3
pivot = 3

[4,3,2,1]

i
		j

[1,3,2,4]

   i
	 j

[1,2,3,4]

 l j
	 i h

k = 3, 第k小的坐标 low + k - 1 = 2

j = 1, i = 2

low + k - 1 = 2 <= i ---> 取右边同时第k, k减去 左边的个数
quickSelect(nums, i, high, k - (i - low));
quickSelect(nums, 2, 3, 1); ---> 在右边找第一小

[3,4]
low = 2, high = 3, k = 1
pivot = 3

[3,4]
 l
   h
   i
 j

k = 3, 第k小的坐标 low + k - 1 = 2 + 1 - 1 = 2
j = 2, i = 3
low + k - 1 = 2 >= j ----> 去左边 右边不影响
return quickSelect(nums, low, j, k);
return quickSelect(nums, 2, 2, 1);	

[3]
low = 2, high = 2, k = 1
pivot = 3
[3]
l
h
交换完之后
i = 3
j = 1

low + k - 1 = 2 + 1 - 1 = 2
[j, pivot, i]
结果就是 return nums[j + 1] = nums[1 + 1] = 3






worst case

find 4th min 找第1小的 每次pivot取最后

[1, 2, 3, 4]    k = 1 ,pivot = 4
       j     i


return quickSelect(nums, low, j, k)
       quickSelect(nums, 0, 3, 1);


[1, 2, 3]       k = 1 ,pivot = 3
    j     i
return quickSelect(nums, low, j, k)
       quickSelect(nums, 0, 2, 1);

[1, 2]
 j     i
return quickSelect(nums, low, j, k)
       quickSelect(nums, 0, 1, 1);

  [1] 
j-1  i 1    low + k - 1 = 0
return nums[j + 1] / nums[i - 1] = 1


每一轮只减少一个 所以最坏情况是 O(N^2)  







--------------------------------------------------------------------------------------------------
2020.2.13



带 pivot 的 快速选择写法

[3, 2, 1, 5, 6, 4]
k = 2, answer = 5 第2大的数字是5      这是 1-based
现在 转换成 0-based
即 第1大的数是5  即 第 6-2 = 4, 即第4小的数是5 这里的这个4就和pivot的下标能对上了

https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/



class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;
        // kth largest = (N - k)th smallest
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    //find the k_th smallest element from the array
    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];

        int pivot_index = partition(nums, left, right);

        if (k == pivot_index) return nums[k];
        else if (k < pivot_index) return quickSelect(nums, left, pivot_index - 1, k); 
        return quickSelect(nums, pivot_index + 1, right, k);
    }

    //  after partitioning --> [small, pivot, large].  return the index of the pivot 
    private int partition(int[] nums, int left, int right) {
        //assume each time pivot is the last element of the array
        int pivot = nums[right];

        int final_pivot_index = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, final_pivot_index, i);
                final_pivot_index++;
            }
        }
        swap(nums, final_pivot_index, right);
        return final_pivot_index;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}




优化之 随机选取pivot

class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];

        Random rand = new Random();
        int pivot_index = left + rand.nextInt(right - left);        // left +   这两个定不能少 
        pivot_index = partition(nums, left, right, pivot_index);

        if (k == pivot_index) return nums[k];
        else if (k < pivot_index) return quickSelect(nums, left, pivot_index - 1, k); 
        return quickSelect(nums, pivot_index + 1, right, k);
    }


    private int partition(int[] nums, int left, int right, int pivot_index) {
        int pivot = nums[pivot_index];  
        swap(nums, pivot_index, right);             // 先把随机 pivot 换到最右边 

        int final_pivot_index = left;
        for (int i = left; i < right; i++) {
            if (nums[i] < pivot) {
                swap(nums, final_pivot_index, i);
                final_pivot_index++;
            }
        }
        swap(nums, final_pivot_index, right);
        return final_pivot_index;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}