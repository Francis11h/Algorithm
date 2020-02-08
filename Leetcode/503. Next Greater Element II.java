503. Next Greater Element II

Given a circular array (the next element of the last element is the first element of the array)
	print the Next Greater Number for every element.

The Next Greater Number of a number x 
	is the first greater number to its traversing-order next in the array, 
	which means you could search circularly to find its next greater number. 
	If it doesn‘t exist, output -1 for this number.

就是 相比 496. Next Greater Element I 这次 数组是个环儿 可以循环找


Example 1:

Input: [1,2,1]
Output: [2,-1,2]
The second 1‘s next greater number needs to search circularly, which is also 2.



遍历两遍完事儿 第一遍 有入栈 第二遍只出栈

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        // loop once 	数组存 下标 
        for (int i = 0; i < nums.length; i++) {
        	while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
        		ans[stack.pop()] = nums[i];
        	}
        	stack.push(i);
        }
        // loop twice  不入栈 仅出栈
        for (int num : nums) {
        	while (!stack.isEmpty() && num > nums[stack.peek()]) {
        		ans[stack.pop()] = num;
        	}
        }
        return ans;
    }
}



