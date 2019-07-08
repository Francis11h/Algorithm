567. Permutation in String

Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
In other words, one of the first string‘s permutations is the substring of the second string.


Input: s1 = "ab" s2 = "eidbaooo"
Output: True
Explanation: s2 contains one permutation of s1 ("ba").

Input:s1= "ab" s2 = "eidboaoo"
Output: False



两个 HashMap 存字符及其出现的次数 然后比较时候一样 (笨方法)

class Solution {
    public boolean checkInclusion(String s1, String s2) {
    	if (s2.length() < s1.length()) return false;

    	Map<Character, Integer> map = new HashMap<>(), curMap = new HashMap<>();
    	for (int i = 0; i< s1.length(); i++) {
    		char ch = s1.charAt(i);
    		map.put(ch, map.getOrDefault(ch, 0) + 1);
    	}

    	// i : start of the substring in s2
    	for (int i = 0; i < s2.length() - s1.length() + 1; i++) {
    		for (int j = 0; j < s1.length(); j++) {
    			char ch = s2.charAt(i + j);
    			curMap.put(ch, curMap.getOrDefault(ch, 0) + 1);
    		}
    		if (isMatch(map, curMap)) return true;
    		curMap.clear();
    	}
    	return false;
    }

    public boolean isMatch(Map<Character, Integer> map, Map<Character, Integer> curMap) {
        for (char key: map.keySet()) {
            if (map.get(key) - curMap.getOrDefault(key, -1) != 0)
                return false;
        }
        return true;
    }
}

 T: O(l1 + 26 * (l2 - l1) * l1)  HashMap contains at most 26 characters
 S: O(26)




sliding window
最经典的做法

class Solution {
    public boolean checkInclusion(String s1, String s2) {
    	if (s2.length() < s1.length()) return false;

    	Map<Character, Integer> map = new HashMap<>();
    	for (int i = 0; i< s1.length(); i++) {
    		char ch = s1.charAt(i);
    		map.put(ch, map.getOrDefault(ch, 0) + 1);
    	}

    	int count = 0;

    	for (int i = 0; i < s2.length(); i++) {
    		char ch = s2.charAt(i);
    		if (map.containsKey(ch)) {
    			map.put(ch, map.get(ch) - 1);
    			if (map.get(ch) == 0) count++;
    		}

    		if (i >= s1.length()) {
    			char rmv = s2.charAt(i - s1.length());
    			if (map.containsKey(rmv)) {
    				map.put(rmv, map.get(rmv) + 1);
    				if (map.get(rmv) == 1) count--;
    			}
    		}

    		if (count == map.size()) return true;
    	}
    	return false;
    }
}

T : O(l1 + l2)





最经典做法之数组代替HashMap

class Solution {
    public boolean checkInclusion(String s1, String s2) {
    	if (s2.length() < s1.length()) return false;
    	//character hash implemented by array
    	int[] hash = new int[256];
    	for (int i = 0; i < s1.length(); i++) {
    		char ch = s1.charAt(i);
    		hash[ch]++;
    	}
    	//or 这么写
		// for (char c : s1.toCharArray()) {
		// 	hash[c]++;
		// }

    	int count = 0;

    	for (int i = 0; i < s2.length(); i++) {
    		char ch = s2.charAt(i);
    		//move right everytime, if the character exists in p's hash, increase the count
        	//current hash value >= 1 means the character is existing in p
    		if (hash[ch] >= 1) {
    			count++;
    		}
            hash[ch]--;

    		//if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
    		//++ to reset the hash because we kicked out the left
    		//the count >= 0 indicate it was original in the hash, cuz it won't go below 0
    		if (i >= s1.length()) {
    			char rmv = s2.charAt(i - s1.length());
    			if (hash[rmv] >= 0) {
    				count--;
    			}
                hash[rmv]++;
    		}

    		if (count == s1.length()) return true;

    	}
    	return false;
    }
}











辣鸡官方题解

public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map))
                return true;
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            s2map[s2.charAt(i) - 'a']--;
        }
        return matches(s1map, s2map);
    }
    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i])
                return false;
        }
        return true;
    }
}



 T: O(l1 + 26 * (l2 - l1)) 




 public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length())
            return false;
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        int count = 0;
        for (int i = 0; i < 26; i++)
            if (s1map[i] == s2map[i])
                count++;
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            int r = s2.charAt(i + s1.length()) - 'a', l = s2.charAt(i) - 'a';
            if (count == 26)
                return true;
            s2map[r]++;
            if (s2map[r] == s1map[r])
                count++;
            else if (s2map[r] == s1map[r] + 1)
                count--;
            s2map[l]--;
            if (s2map[l] == s1map[l])
                count++;
            else if (s2map[l] == s1map[l] - 1)
                count--;
        }
        return count == 26;
    }
}


 T: O(l1 + (l2 - l1)) 