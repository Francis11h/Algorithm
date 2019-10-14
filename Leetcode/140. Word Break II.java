140. Word Break II

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
add spaces in s to construct a sentence where each word is a valid dictionary word. 
Return all such possible sentences.

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]


Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]



Brute Force

we check every possible prefix of that string(s) in the dictionary of words,
if it is found in the dictionary say(s1), then
	the recursive function is called for the remaining portion of that string(s - s1).

This function returns the prefix s1 appeneded by the result of the recursive call using the remaining portion of that string(s - s1),
	if the remaining portion is a substring which can lead to the formation of a valid sentence as per the dictionary.
	Otherwise, empty list is returned.





class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> Dict = new HashSet<>(wordDict);
        return dfs(s, Dict, 0);
    }

    private List<String> dfs(String s, Set<String> Dict, int start) {
    	List<String> ans = new LinkedList<>();
    	if (start == s.length()) {
    		ans.add("");
    	}

    	for (int end = start + 1; end <= s.length(); end++) {
    		if (Dict.contains(s.substring(start, end))) {
    			List<String> subList = dfs(s, Dict, end);
    			for (String sub : subList) {
    				ans.add(s.substring(start, end) + (sub.equals("") ? "" : " ") + sub);
    			}
    		}
    	}
    	return ans;
    }
}


Time complexity : O(N ^ N)
Consider the worst case where 
s = "aaaaaaaaaaaaaa" and every prefix of s is present in the dictionary of words, 
then the recursion tree can grow up to N^N

Space complexity : O(N ^ 3)

In worst case the depth of recursion tree can go up to N
and nodes can contain N strings each of length N




memo


class Solution {

	HashMap<Integer, List<String>> map = new HashMap<>(); // <Starting index, rst list>

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> Dict = new HashSet<>(wordDict);
        return dfs(s, Dict, 0);
    }

    private List<String> dfs(String s, Set<String> Dict, int start) {
    	//这里 找到直接返回 prune了
    	if (map.containsKey(start)) {
    		return map.get(start);
    	}

    	List<String> ans = new LinkedList<>();
    	if (start == s.length()) {
    		ans.add("");
    	}

    	for (int end = start + 1; end <= s.length(); end++) {
    		if (Dict.contains(s.substring(start, end))) {
    			List<String> subList = dfs(s, Dict, end);
    			for (String sub : subList) {
    				ans.add(s.substring(start, end) + (sub.equals("") ? "" : " ") + sub);
    			}
    		}
    	}
    	// 这里 每次dfs 结果存一下。

    	map.put(start, ans);
    	return ans;
    }
}



Time complexity : O(N^3)

Size of recursion tree can go up to O(N^2)
The creation of list takes O(N)


Space complexity : O(N^3)

The depth of the recursion tree can go up to N
and each activation record can contains a string list of size N.




// 最开始的 笨b做法。。。。 打扰了 dfs 还是 要有返回值的。。。
// class Solution {
//     public List<String> wordBreak(String s, List<String> wordDict) {
//         List<String> ans = new ArrayList<>();
//         Set<String> set = new HashSet<>(wordDict);
//         dfs(0, s, new StringBuilder(), set, ans);
//         return ans;
//     }
    
//     private void dfs(int level, String s, Set<String> wordDict) {
//         if (level == s.length()) {
//             ans.add(sb.trim().toString());
//             return;
//         }
        
//         for (int i = 0; i < s.length(); i++) {
//             String subStr = s.substring(level, i + 1);
//             if (wordDict.contains(subStr)) {
//                 sb.append(" ").append(subStr);
                
//                 dfs(i + 1, s, sb, wordDict, ans);
                // 这里就 无法处理了 因为 stringbuilder 删 只能删最后一个 or 根据下标删 无法删指定string
//                 sb.delete
//             }
//         }
//     }
// }



