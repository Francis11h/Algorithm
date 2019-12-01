3. 最长无重复字符子串 Longest Substring Without Repeating Characters

Given a string, find the length of the longest substring without repeating characters.

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.



sliding window

外层while右指针先动, 某个字符个数超过1了,内层while左指针再动

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] count = new int[256];
        int ans = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char ch = s.charAt(right);
            count[ch]++;
            while (count[ch] > 1 && left < right) {
                char rmv = s.charAt(left);
                count[rmv]--;
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

T : O(N)
S : O(26)  



2019.11.30 重新写 一遍过的版本
// we can use one pass solution, we need two pointers one moves first until the substring has a repeated character, 
// then move another pointer to eliminate the duplicate, during this process record the length of the substrings without repeating characters

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        // initialize two pointers 
        int left = 0, right = 0;
        //use a map to record the # of each char
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        while (right < s.length()) {
            // count the new char 
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            
            // if occur a duplicate begin to remove
            while (map.get(ch) > 1) {
                char rmv = s.charAt(left);
                map.put(rmv, map.get(rmv) - 1);
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }
}



follow up: instead of returning count, return the longest substring itself


记录起点坐标 
// if we not only want the length, but we also want the substrings itself, 
// we can add a set to record the begin indexes of the substrings

class Solution {
    public List<String> lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int left = 0, right = 0;
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        // add a set to record  the begin indexes of the longest substrings
        Set<Integer> set = new HashSet<>();
        while (right < s.length()) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            
            while (map.get(ch) > 1) {
                char rmv = s.charAt(left);
                map.put(rmv, map.get(rmv) - 1);
                left++;
            }
            // record or clear when we have a longer length
            if (right - left + 1 > max) {
                set.clear();
                max = right - left + 1;
                set.add(left);
            } else if (right - left + 1 == max) {
                set.add(left);
            }
            right++;
        }
        
        Set<String> ans = new HashSet<>();
        for (Integer i : set) {
            System.out.println(s.substring(i, i + max));
            ans.add(s.substring(i, i + max));
        }
        return new ArrayList<>(ans);
    }
}

