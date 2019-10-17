Given a String S and T, find the minimum Window in S 
which contains all the characters in T

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Input: S = "ADOBECODEBANC", T = "ABCC"
Output: "CODEBANC"


// two pointers
先找到一个substring，满足包含T中的所有字符，然后再从左边减少字符
L ------------------------ R , Suppose this is the window that contains all characters of T 
                          
        L----------------- R , this is the contracted window. We found a smaller window that still contains all the characters in T

When the window is no longer valid, start expanding again using the right pointer. 





class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null) return "";
        int[] countT = new int[256];
        
        int k = 0;          //kind of characters in T
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            countT[ch]++;
            if (countT[ch] == 1) {
                k++;
            }
        }
        
        int left = 0, right = 0;
        String ans = "";
        
        int[] countS = new int[256];
        int now = 0;            //S的substring中 和 T中的字符 种类+个数 一样的 字符种类数
        
        while (now < k && right < s.length()) {
            
            char ch = s.charAt(right);
            countS[ch]++;
            if (countS[ch] == countT[ch]) {
                now++;
            }
            right++;
            
            // now == k move left to narrow the window
            while (now == k) {
                if (ans == "" || ans.length() > right - left) {
                    ans = s.substring(left, right);
                }
                char rmv = s.charAt(left);
                if (countS[rmv] == countT[rmv]) {
                    now--;
                }
                countS[rmv]--;
                left++;
            }
        }
        return ans;
    }
}






hashMap sliding Window方法



class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return"";
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int count = 0, start = 0, substrLength = Integer.MAX_VALUE;
        int answerStart = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 0) count++;
            }
            
            while (count == map.size()) {                   // 这个 while 和之前的 sliding window 不太一样
                char rmv = s.charAt(start);
                if (map.containsKey(rmv)) {
                    map.put(rmv, map.get(rmv) + 1);
                    if (map.get(rmv) == 1) count--;
                }
                
                if (i - start < substrLength) {
                    substrLength = i - start + 1;
                    answerStart = start;
                }
                start++;                                 //while 必要有这个++ 
            }
        }
        if (substrLength == Integer.MAX_VALUE) return "";
        return s.substring(answerStart, answerStart + substrLength);
    }
}


