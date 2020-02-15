496. Next Greater Element I

You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. 
Find all the next greater numbers for nums1‘s elements in the corresponding places of nums2.

The Next Greater Number of a number x in nums1 
  is the first greater number to its right in nums2. 
  If it does not exist, output -1 for this number.



Example 1:

Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
Output: [-1,3,-1]
Explanation:
    For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
    For number 1 in the first array, the next greater number for it in the second array is 3.
    For number 2 in the first array, there is no next greater number for it in the second array, so output -1.


Example 2:

Input: nums1 = [2,4], nums2 = [1,2,3,4].
Output: [3,-1]
Explanation:
    For number 2 in the first array, the next greater number for it in the second array is 3.
    For number 4 in the first array, there is no next greater number for it in the second array, so output -1.


Note:

All elements in nums1 and nums2 are unique.
The length of both nums1 and nums2 would not exceed 1000.


暴力解法 现在 nums1 里找num 再在 nums2 里找num对应的下标 再从该下标之后再找greater的 
O(mn)
O(m)

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return new int[0];
        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
          int num = nums1[i];
          int j = 0;
          for (; j < nums2.length; j++) {
            if (nums2[j] == num) break;
          }
          for (; j < nums2.length; j++) {
            if (nums2[j] > num) {
              ans[i] = nums2[j];
              break;
            }
          }
          if (j == nums2.length) ans[i] = -1;
        }
        return ans;
    }
}









更屌的办法 预处理 用 stack 和 map
O(n)

栈 
新的进来 里面比它小的全出来 放map里 
把 新的入栈
最后剩的就是 没有的 -1


In this approach, we make use of pre-processing first so as to make the results easily available later on

we make use of a stack and a hashmap.
map is used to store the result for every possible number in nums in the form of <element, next_greater_element>.
Now we look at how to makr entries in map.

we iterate over the nums array from the left to right.
we push every element nums[i] on the stack 
  if it is less than the previous element on the top of the stack [stack[top]].
No entry is made in map for such nums[i]‘s right now. This happens because the nums[i]‘s encountered so far are coming in a descending order.

if we encounter an element nums[i] such that nums[i] > stack[top], 
  we keep on popping all the elements from stack[top] until we encounter stack[k] such that stack[k] >= nums[i].
  For every element popped out of the stack stack[j], we put the popped element along with its next_greater_number(result) into the hashmap, in the form of <stack[j], nums[i]>.
  Now, it is obvious that the next greater element for all elements stack[j], such that k < j <= top is nums[i](since this larger element caused all the stack[j]‘s to be popped out)
  After doing steps above, we need to put this nums[i] into the stack because we also need to find its next_greater_number.

Thus an element is popped out of the stack whenever a next greater element is found for it.
Thus the elements remaining in the stack are the ones for which no next greater element exists in the nums array.
Thus at the end of the iteration over numss, we pop the remaining elements from the stack and put their entries in hash with a -1 as their corresponding results.








This is a perfect example of "monotonous stack"... Its important.



2 3 5 1 0 7 


class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return new int[0];
        int[] ans = new int[nums1.length];
        // build auxiliary stack and map
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        // iterate over the nums2 array from left -> right, to push/pop in stack, put the popped result in map
        for (int num: nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}



O(m + n)
O(m + n)



















