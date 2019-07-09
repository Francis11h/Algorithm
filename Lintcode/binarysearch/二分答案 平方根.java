
69. 求一个非负整数的平方根 Sqrt(x)

二分答案 ： sqrt 没有二分数组———>自己构造一个二分数组。  1 - X
            什么是要二分枚举的？ 最终的答案
            什么是要算出来的？ 枚举数的平方

// x 是 非负 整数
class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;                   //corner case x = 0
        int left = 1, right = x;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (mid == x / mid) return mid;
            if (mid < x / mid) left = mid;
            else right = mid;
        }
        if (right > x / right) return left;
        return right;
    }
}




367. Valid Perfect Square

        二分答案  1. 二分数组  1 - num  
                 2. 什么是要最终枚举的 ： 最终的完全平方数
                 3. 什么是要计算的 枚举数的平方
            
        我们会 二分法找一个数的近似整数完全平方根 Sqrt(x), 
            我们只需要找到这个数 然后判断该数的平方是否等于num即可
            

class Solution {
    public boolean isPerfectSquare(int num) {
        //num is positive and a integer
        // binary search

        int left = 1, right = num, sqrt = 0;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (mid == num / mid) {
                return (mid * mid) == num;
            }
            if (mid < num / mid) left = mid;
            else right = mid;
        }
        if (right > num / right) sqrt = left;
        else sqrt = right;
        
        return (sqrt * sqrt) == num;
        
    }
}