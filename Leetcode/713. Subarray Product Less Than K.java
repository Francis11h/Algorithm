713. Subarray Product Less Than K



Your are given an array of "positive" integers nums.

Count and print the number of ("contiguous") subarrays where the product of all the elements in the subarray is less than k.

Example 1:

Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.




[10, 5, 2, 6]


// 第一遍的 错误版本
class Solution {
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		if (nums == null || nums.length == 0) return 0;
		int ans = 0;
		// the length of the ans
		for (int len = 1; len <= nums.length; len++) {
			//begin of the ans
			for (int i = 0; i <= nums.length - len; i++) {
				// calculate the product of the subarray
				int product = 1;
				for (int j = i; j < i + len; j++) {
					product *= nums[j];
				}
				if (product < k) ans++;
			}
		}
		return ans;
	}
}


Wrong Answer
Input
[10,9,10,4,3,8,3,3,6,2,10,10,9,3]
19
Output
21
Expected
18

len = 13 时
int越界 超过了 2147483647



//改进 int 改成 long 依旧不能满足 所有的 test case 所以必然可以优化 乘法过程 即剪枝 
class Solution {
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		if (nums == null || nums.length == 0) return 0;
		int ans = 0;
		// the length of the ans
		for (int len = 1; len <= nums.length; len++) {
			//begin of the ans
			for (int i = 0; i <= nums.length - len; i++) {
				// calculate the product of the subarray
				long product = 1;
				for (int j = i; j < i + len; j++) {
					product *= nums[j];
				}
				if (product < k) ans++;
			}
		}
		return ans;
	}
}



// 加了 剪枝 但是 当test case 巨大时 会TLE 
// 所以 肯定还有 更加优化的计算乘法的方式
// 现在 是 O(n ^ 3) 即每次 算一个 product 的时候 都有大量重复的计算 
// 我们可以 每新来一个nums[j] 就用原来的 product 除以nums[要去掉的] 再乘以nums[j] 得到新的product 这样子 可以 O(n ^ 3) ---> O(n ^ 2)
class Solution {
	public int numSubarrayProductLessThanK(int[] nums, int k) {
		if (nums == null || nums.length == 0) return 0;
		int ans = 0;
		// the length of the ans
		for (int len = 1; len <= nums.length; len++) {
			//begin of the ans
			int product = 1;
			for (int i = 0; i <= nums.length - len; i++) {
				// calculate the product of the subarray
				if (i == 0) {
					for (int j = i; j < i + len; j++) {
						if (Integer.MAX_VALUE / nums[j] < product) {
							product = k;
							break;
						}
						product *= nums[j];
					}
				} else {
					product /= nums[i - 1];
					if (Integer.MAX_VALUE / nums[i + len - 1] < product) {
						product = k;
					} else {
						product *= nums[i + len - 1];
					}
				}
				
				if (product < k) ans++;
			}
		}
		return ans;
	}
}

但是 上面的 做法还是 会有问题 也是错误的 因为 还是 int 越界问题 
if (Integer.MAX_VALUE / nums[j] < product) {
	product = k;
	break;
}

这一行 如果越界 之前的 product 就会丢失 导致后面的全部错误。  
所以问题并没有解决 一个矛盾点就是 我们每次 都试图 算出来 现有长度对应的 product   但是 这len很大之后 就往往很容易越界 
所以 我们考虑 没有必要 算出来, 而是 利用题目给的 k 这个 界限



标答



1.The idea is "always keep an max-product-window less than K";
2.Every time shift window by adding a new number on the right(j), 
	if the product is greater than k, then try to reduce numbers on the left(i), 
		until the subarray product fit less than k again, (subarray could be empty);

3.Each step introduces x new subarrays, where x is the size of the current window (j + 1 - i);
example:
for window (5, 2), when 6 is introduced, it add 3 new subarray: (5, (2, (6)))

        (6)
     (2, 6)
  (5, 2, 6)

最重要的思想 不要根据len来加 就 这个思想桎梏是致命的 要新来一个元素 加 j - i + 1 个 每种长度加一个
这就很新奇 也是i这道题 two pointers 的精髓所在 甚至 可以简化 O(n ^ 2) ----> O(n)
就尼玛离谱。。。

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return 0;
        int ans = 0;
        int product = 1;
        for (int i = 0, j = 0; j < nums.length; j++) {
        	product *= nums[j];
        	while (i <= j && product >= k) {
        		product /= nums[i];
                i++;	//这里 记得。。
        	}
        	ans += j - i + 1;
        }
        return ans;        
    }
}




[10, 5, 2, 6]
i j

[10, 5, 2, 6]
i 		j

[10, 5, 2, 6]
	 i 	j

[10, 5, 2, 6]
	 i 	   j
