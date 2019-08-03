238. Product of Array Except Self(without division)

Given an array nums of n integers where n > 1,  
return an array output such that output[i] is equal to 
the product of all the elements of nums except nums[i].

Input:  [1,2,3,4]
Output: [24,12,8,6]

Note : Please solve it without division and in O(n).

Could you solve it with constant space complexity? 
    (The output array does not count as extra space 
        for the purpose of space complexity analysis.)


本题要求不能用 除法, 这就有点尴尬了

Use tmp to store temporary multiply result by two directions.
    Then fill it into result. Bingo!

用tmp来存储暂时的双方向的乘积, 

    nums =  [1, 2, 3, 4]
     ----------->
  result =  [1, 1, 2, 6]        此时数组存储的是 当前元素的左边所有元素 乘积 (不含当前元素)

     <-----------
       k =   24 12 4  1         k为该数右边的乘积。
     
  result =  [24,12,8, 6]        result[i] = result[i](当前数左边的乘积) * k(当前数右边的乘积)
    

乘积 = 当前数左边的乘积 * 当前数右边的乘积,

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int k = 1;
        for(int i = 0; i < res.length; i++){
            res[i] = k;
            k = k * nums[i]; // 此时数组存储的是除去当前元素左边的元素乘积
        }
        k = 1;
        for(int i = res.length - 1; i >= 0; i--){
            res[i] *= k; // k为该数右边的乘积。
            k *= nums[i]; // 此时数组等于左边的 * 该数右边的。
        }
        return res;
    }
}

O(n), O(1)



方法二 很不推荐

用两个数组, 一个存 nums[i] 左边的 一个存右边的
left[i] = nums[0] * nums[1] * ... * nums[i - 1];
right[i] = nums[nums.length - 1] * ... * nums[i + 1];
这样 res[i] = left[i] * right[i];



    nums  =  [1, 2, 3, 4]
    
    left  =  [1, 1, 2, 6]
    right =  [24, 12, 4, 1]

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] res = new int[n];

        left[0] = 1;
        right[n - 1] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
            right[n - 1 - i] = right[n - i] * nums[n - i];
        }

        for (int i = 0; i < n; i++) {
            res[i] = left[i] * right[i];
        }
        return res;
    }
}

O(n), O(n)




方法三
(能用除法的话)

不符合本题要求

简单想法: 计算所有的乘积power, 再挨个除以 nums[i] => 有0的话 power 为0  
            => 如何 handle 0

很正常的思路, 分三种情况讨论 : 
    有超过1个0
    只有一个 0
    没有0

class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int[] res = new int[nums.length];
        int power = 1;
        int count0 = 0;
        for (int num : nums) {
            if (num != 0)
                power *= num;
            else 
                count0++;
        }
        if (count0 > 1) {                               // (# of 0) > 1
            Arrays.fill(res, 0);
            return res;
        } else if (count0 == 1) {                       // (# of 0) == 1
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0)
                    res[i] = 0;
                else 
                    res[i] = power;
            }
            return res;
        }
                                                        // (# of 0) == 0
        for (int i = 0; i < nums.length; i++) {
            res[i] = power / nums[i];
        }
        return res;
    }
}

O(n), O(1)

