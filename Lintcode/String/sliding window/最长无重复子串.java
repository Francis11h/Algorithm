3. 最长无重复字符子串 Longest Substring Without Repeating Characters

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.




外层while右指针先动, 某个字符个数超过1了,内层while左指针再动


class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chars = s.toCharArray();
        int[] count = new int[256]; //ASCII a-z A-Z < 256
        int ans = 0;
        int l = 0, r = 0;
        while (r < chars.length) {
            count[chars[r]]++;
            //用一个count数组统计每个字符出现的个数,超过1了就动左指针,直到=1
            while (count[chars[r]] > 1) {
                count[chars[l]]--;
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }
}

T:O(N)
S:O(256)






HashMap 版


class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int count = 0, start = 0, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i); 
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            if (map.get(ch) > 1) count++;
            
            while (count >= 1) {
                char rmv = s.charAt(start);
                map.put(rmv, map.get(rmv) - 1);
                if (map.get(ch) == 1) count--;
                start++;
            }
            ans = Math.max(ans, i - start + 1);
        }
        return ans;
    }
}




















