266. Palindrome Permutation

Given a string, determine if a permutation of the string could form a palindrome.

Input: "code"
Output: false

Input: "aab"
Output: true


Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. 
How about character which occurs odd number of times? Only one odd is allowed.



class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int[] count = new int[256];
        int dist = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            count[ch]++;
            if ((count[ch] & 1) == 1) {
                dist++;
            } else {
                dist--;
            }
        }
        return dist <= 1 ;
    }
}