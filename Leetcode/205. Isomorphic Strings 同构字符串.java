205. Isomorphic Strings 同构字符串

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if "the characters in s can be replaced to get t".

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.




Example 1:

Input: s = "egg", t = "add"
Output: true
Example 2:

Input: s = "foo", t = "bar"
Output: false
Example 3:

Input: s = "paper", t = "title"
Output: true


错误代码 理解错了题目
// It is natural to think of using a hash table to count the frequency of each character.
// use another hashMap to record the count of each frequency
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        int match = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        Map<Integer, Integer> freq = new HashMap<>();
        for (Integer val : map.values()) {
            freq.put(val, freq.getOrDefault(val, 0) + 1);
        }

        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map2.put(ch, map2.getOrDefault(ch, 0) + 1);
        }

        Map<Integer, Integer> freq2 = new HashMap<>();
        for (Integer val : map2.values()) {
            freq2.put(val, freq2.getOrDefault(val, 0) + 1);
        }

        return freq.equals(freq2);
    }
}



Input
"aba"
"baa"
Output
true
Expected
false


就是说 不光要 频率一样 还要保证 相对顺序一样

这两句话的理解
All occurrences of a character must be replaced with another character but a character may map to itself.
所有出现的字符"必须"替换为另一个字符 or 自己
while preserving the order of characters. 
同时"保留字符顺序"






错误代码 
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if (!map.containsKey(ch2)) {
                map.put(ch2, ch1);
            }
            if (ch1 != map.get(ch2)) return false;
        }
        return true;
    }
}

s中一个字符被多次映射 违反了规则 因该 直走一次 加个 visited set

Your input
"foo"
"bar"
Output
true
Expected
false




正确的 加了个 set 保证 一个字符不会被 用两次

class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        Map<Character, Character> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if (!map.containsKey(ch2)) {
                if (set.contains(ch1)) return false;
                set.add(ch1);
                map.put(ch2, ch1);
            }
            if (ch1 != map.get(ch2)) return false;
        }
        return true;
    }
}

O(N)
O(N)



两个数组的解法 
public boolean isIsomorphic(String s, String t) {
    int[] map = new int[128];
    int[] book = new int[128];
    for (int i = 0; i < s.length(); i++) {
        int cs = (int) s.charAt(i);
        int ts = (int) t.charAt(i);
        if (map[cs] == 0 && book[ts] == 0) {
            map[cs] = ts;
            book[ts] = 1;
        } else if (map[cs] != ts)
            return false;
    }
    return true;
}