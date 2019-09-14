quickSelect
找数组中 第k小的元素




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








找数组中 第k大的元素

仅仅是 交换的时候顺序变 选择时候并不变



class Solution {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return -1;
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    
    // return to the kth largest num in the array with the index between low and high.  
    private int quickSelect(int[] nums, int low, int high, int k) {
        int i = low, j = high;
        int pivot = nums[low + (high - low) / 2];
        // pivot 前面要放 大于它的 所以如果 大于 符合 移动到下一个
        // pivot 后面要放 小于它的 所以如果 小于 符合 移动到下一个
        // 交换过程 结束的条件是 i > j 
        while (i <= j) {
            //前面直到找到小于等于pivot的num
            while (i <= j && nums[i] > pivot) {
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                j--;
            }
            // 现在 nums[i] >= pivot nums[j] <= pivot 所以要交换
            if (i <= j) {
                int temp = nums[i];
                nums[i++] = nums[j];
                nums[j--] = temp;
            }
        }
        //后半部分[j+1 - high]不影响
        if (low + k - 1 <= j) {
            return quickSelect(nums, low, j, k);
        }
        //前半部分已经包含了(i - low)个大得了, 后半部分[i, high]里继续找第k - (i - low) 个
        if (low + k - 1 >= i) {
            return quickSelect(nums, i, high, k - (i - low));
        }
        return nums[j + 1];		// return nums[i - 1]亦可
    }
}



如果基准值选择上佳，搜索范围每次能够指数级减少，这样一来所耗时间是线性的(即O(n))。
	每次扔掉一半 :T(N) =n + n/2 + n/4 +...+ n/2^k = 2N => O(n)
但如果基准值选择非常不好，使得每次只能将搜索范围减少一个元素，则最糟的总体时间复杂度是平方级的O(n^2)

类似快速排序我们认为是 O(nlogn)的，因为我们算的是平均时间复杂度，也就是平摊一下各种情况的概率和时间复杂度。
因为并不一定每次都运气那么不好，比如你用随机的方式挑选 pivot的话，这样我们认为 quick select 的时间复杂度均摊是 O(n)的。
