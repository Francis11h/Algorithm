472. Concatenated Words

Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as "a string that is comprised entirely of at least two shorter words in the given array".




Example:

Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: 

"catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
"dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".



The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.



class Solution {
    Set<String> ans = new HashSet<>();
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        
        for (String word : words) {
            dfs(word, 0, 0, trie);
        }
        return new ArrayList<>(ans);
    }

    private void dfs(String word, int startIndex, int count, Trie trie) {
        if (startIndex == word.length() && count > 1) {
            ans.add(word);
            return;
        }

        for (int i = startIndex; i < word.length(); i++) {
            String subStr = word.substring(startIndex, i + 1);
            if (!trie.startWith(subStr)) return;
            if (trie.search(subStr)) {
                dfs(word, i + 1, count + 1, trie);              //千万 别给老子写成 count++ 。。。。 debug 半小时。。 我吐了
            }
        }
    }
}

class TrieNode {
    boolean isWord;
    Map<Character, TrieNode> children;
    TrieNode() {
        this.isWord = false;
        children = new HashMap<>();
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!cur.children.containsKey(ch)) {
                cur.children.put(ch, new TrieNode());
            }
            cur = cur.children.get(ch);
        }
        cur.isWord = true;
    }

    public TrieNode searchNode(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!cur.children.containsKey(ch)) return null;
            cur = cur.children.get(ch);
        }
        return cur;
    }

    public boolean startWith(String prefix) {
        return searchNode(prefix) != null;
    }

    public boolean search(String word) {
        if (searchNode(word) == null || !searchNode(word).isWord) return false;
        return true;
    }
}



但是这个 过不了 test case ['a', 'aa', 'aaa', 'aaaa', ..... ,'a.....a']
因为这种 建Trie 永远是一条list 
怎么办？？


-----> 比较笨的办法  dfs 改为 return boolean 找到直接 return 稍微快一点。。 能过这个 test case



class Solution {
    Set<String> ans = new HashSet<>();
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        
        for (String word : words) {
            if (dfs(word, 0, 0, trie)) ans.add(word);
        }
        return new ArrayList<>(ans);
    }

    private boolean dfs(String word, int startIndex, int count, Trie trie) {
        if (startIndex == word.length() && count > 1) {
            return true;
        }

        for (int i = startIndex; i < word.length(); i++) {
            String subStr = word.substring(startIndex, i + 1);
            if (!trie.startWith(subStr)) continue;
            if (trie.search(subStr)) {
                if (dfs(word, i + 1, count + 1, trie)) {
                    return true;
                }
            }
        }
        return false;
    }
}


    // trie 中部分 改成这样子 稍微快一些

    TrieNode node = trie.searchNode(subStr);
    if (node == null) return false;
    if (node.isWord) {
        if (dfs(word, i + 1, count + 1, trie)) {
            return true;
        }
    }





Building Trie needs O(n * k), and validation needs O(n * k), 
where n is for the size of "words", k is for the size of a single word.






