9. Palindrome Number

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true

Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Follow up:

Coud you solve it without converting the integer to a string?


Beware of overflow when you reverse the integer.


Solution 1:
变成 string 但是会花额外的空间 是不允许的
The first idea that comes to mind is to convert the number into String, and check if the string is a palindrome, but this would require extra non-constant space for creating the string which is not allowed by the problem description.



Solution 2:
Second idea would be reverting the number itself, and then compare the number with original number, if they are the same, then the number is a palindrome. However, if the reversed number is larger than 
int.MAX, we will hit integer overflow problem.

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int num = x;
        int temp = 0;
        while (num != 0) {
            int digit = num % 10;
            num /= 10;
            if (temp <= (Integer.MAX_VALUE - digit) / 10) {  //handle for overflow
                temp = temp * 10 + digit; 
            } else {
                return false;
            }
        }
        return temp == x;
    }
}



Solution 3.  只翻转一半
Following the thoughts based on the second idea, to avoid the overflow issue of the reverted number, 
what if we only "revert half of the int number"?


Now the question is, how do we know that we‘ve reached the half of the number?

Since we divided the number by 10, and multiplied the reversed number by 10, 
"when the original number is less than the reversed number, "
it means we‘ve processed half of the number digits.


class Solution {
    public boolean isPalindrome(int x) {
        // Special cases:
        // As discussed above, when x < 0, x is not a palindrome.
        // Also if the last digit of the number is 0, in order to be a palindrome,
        // the first digit of the number also needs to be 0.
        // Only 0 satisfy this property.
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            int digit = x % 10;
            x /= 10;
            revertedNumber = revertedNumber * 10 + digit; 
        }

        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        
        return x == revertedNumber || x == revertedNumber / 10;
    }
}


