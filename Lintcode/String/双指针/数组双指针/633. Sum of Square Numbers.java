633. Sum of Square Numbers

Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5


class Solution {
    public boolean judgeSquareSum(int c) {
        int sqrt = (int) Math.sqrt(c);
        int left = 0, right = sqrt;
        while (left <= right) {
            int cur = left * left + right * right;
            if (cur < c) left++;
            else if (cur > c) right--;
            else return true;
        }
        return false;
    }
}



// 两个数的平方和 两根指针