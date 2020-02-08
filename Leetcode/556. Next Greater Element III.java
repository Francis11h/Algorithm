556. Next Greater Element III
    同 第 031. Next Permutation

Given a positive 32-bit integer n, 
    you need to find the smallest 32-bit integer 
        which has exactly the same digits existing in the integer n 
            and is greater in value than n. 

If no such positive 32-bit integer exists, you need to return -1.

用相同的数字 重新排序 找比input大的最小的 没有返回-1

Example 1:

Input: 12
Output: 21
 

Example 2:

Input: 21
Output: -1


step 1 find the first not ascending number from right -> left
step 2 swap this first not ascending number with the number just larger than it from right to it
step 3 reverse the sting from index -> right


class Solution {
    public int nextGreaterElement(int n) {
        // change int to string then change it to char array
        char[] nums = String.valueOf(n).toCharArray();
        // step 1 find the first not ascending number from right -> left
        int index = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                index = i - 1;
                break;
            }
        }
        // the given is the largest permutation 
        if (index == -1) return -1;
        // step 2 swap this first not ascending number with the number just larger than it from right to it
        for (int i = nums.length - 1; i > index; i--) {
            if (nums[i] > nums[index]) {
                swap(nums, i, index);
                break;
            }
        }
        // step 3 reverse the sting from index -> right
        reverse(nums, index + 1, nums.length - 1);    
        
        //not overflow
        long ans = Long.parseLong(String.valueOf(nums));
        
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }
    
    private void reverse(char[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    
    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}



// 2   3   6   5   4   1








