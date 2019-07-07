30. Substring with Concatenation of All Words

You are given a string, s, and a list of words, words, that are all of the same length. 
Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.



Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.


Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []






S的substring必须连续包含words的所有word

这会儿 就需要两个 map了 一个存words里所有的单词及他们对应的个数

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        //words are all of the same length
        if (s == null || s.length() == 0 || words.length == 0 || words[0].length() == 0 || s.length() < words.length * words[0].length()) return ans; 
        Map<String, Integer> map = new HashMap<>(), curMap = new HashMap<>();

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        int N = s.length();
        int M = words.length;
        int wordLength = words[0].length();

        String str = null, rmv = null;
        for (int i = 0; i < wordLength; i++) {          //bar foo.     // arf oot
            int count = 0;
            int start = i;
            for (int r = i; r < N - wordLength + 1; r += wordLength) {  // 0->3->6.     1->4->7
                str = s.substring(r, r + wordLength);
                //only it in the words array we calculate
                if (map.containsKey(str)) {
                    curMap.put(str, curMap.getOrDefault(str, 0) + 1);
                    // a new match
                    if (curMap.get(str) <= map.get(str)) count++;
                    
                    while (curMap.get(str) > map.get(str)) {
                        rmv = s.substring(start, start + wordLength);
                        curMap.put(rmv, curMap.get(rmv) - 1);
                        start += wordLength;
                        // if we rmv a word that has been matched
                        if (curMap.get(rmv) < map.get(rmv)) count--;
                    }

                    if (count == M) {
                        ans.add(start);
                        rmv = s.substring(start, start + wordLength);
                        curMap.put(rmv, curMap.get(rmv) - 1);
                        start += wordLength;
                        count--;
                    }
                } else {
                    curMap.clear();                         //empty the map
                    start = r + wordLength;                 //就是 从 r 往后移, 之前写成 start += wordLength了 nullpointer Error
                    count = 0;
                }  
            }
            curMap.clear();
        }
        return ans;
    }
}

 0          12 14
"barfoothefoobar",  N = 15  

["foo","bar"]       wl = 3

























