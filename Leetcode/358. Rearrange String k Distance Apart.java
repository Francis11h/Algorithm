358. Rearrange String k Distance Apart

Given a non-empty string s and an integer k, 
rearrange the string such that the same characters are at least distance k from each other.

Input: s = "aabbcc", k = 3
Output: "abcabc" 
Explanation: The same letters are at least distance 3 from each other.

Input: s = "aaabc", k = 3
Output: "" 
Explanation: It is not possible to rearrange the string.

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other.






Every time we want to find the best candidate: which is the character with the largest remaining count.

One count array to store the remaining count of every character

Another array to keep track of the most left position that one character can appear.

We will iterated through these two array to find the best candidate for every position





// every time we find the most suitable character to insert 
// what is the most suitable? the char with the largest remaining count!!
// sounds like greedy

// hence we need to auxiliary array
// count[] to store the remaining count of each char
// valid[] to store the next valid position for each char


class Solution {
    
    public String rearrangeString(String s, int k) {
        int[] count = new int[26];
        int[] valid = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            int candidate = find(count, valid, i);
            if (candidate == -1) return "";
            count[candidate]--;
            valid[candidate] = i + k;
            sb.append((char)('a' + candidate));
        }
        return sb.toString();
    }
    // find the most suitable char for position index 
    private int find(int[] count, int[] valid, int index) {
        int max = Integer.MIN_VALUE;
        int ans = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && index >= valid[i] && count[i] > max ) {
                ans = i;
                max = count[i];
            }
        }
        return ans;
    }
}






Since the array is fixed size(26), it will take constant time to find max
So the Time Complexity is O(N)
Space is O(26)

