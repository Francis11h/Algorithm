159. 最长至多有两种字符子串 Longest Substring with At Most Two Distinct Characters


Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.


之前做过的题（003）不能有重复字符 ：外层while右指针先动, 某个个数超过1了,内层while左指针再动

本题可以有重复,但是字符只能有两种 ： 也是 外层while动右指针, 再加个变量计现在有几个字符, 2个及以上字符就动左指针。
    
                                具体如何计数： 数组中某个值由 0--->1, dictCount++; 由 1--->0, dictCount--.


class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        int ans = 0, left = 0, right = 0;

        int[] count = new int[256];
        int distCount = 0;
        while (right < s.length()) {
            count[s.charAt(right)]++;
            if (count[s.charAt(right)] == 1) distCount++;

            while (distCount > 2) {
                count[s.charAt(left)]--;
                if (count[s.charAt(left)] == 0) distCount--;
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}

T:O(N)
S:O(256)




class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        int start = 0, count = 0, ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if (map.get(ch) == 1) count++;
            
            while (count > 2) {
                char rmv = s.charAt(start);
                map.put(rmv, map.get(rmv) - 1);
                if (map.get(rmv) == 0) count--;
                start++;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }
}
