212. Word Search II
给出一个由小写字母组成的矩阵和一个字典,找出所有同时在字典和矩阵中出现的单词.
一个单词可以从矩阵中的任意位置开始,可以向左/右/上/下四个相邻方向移动.
一个字母在一个单词中只能被使用一次.且字典中不存在重复单词.


Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]



You would need to optimize your backtracking to pass the larger test. 你需要优化回溯算法以通过更大数据量的测试。
Could you stop backtracking earlier?    如何尽早的停止 dfs ?

如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯
If the current candidate does not exist in all words‘prefix, you could stop backtracking immediately. 
What kind of data structure could answer such query efficiently? 
Does a hash table work? Why or why not? 
How about a Trie? 
Implement Trie (Prefix Tree) (leetcode 208)			Tire  : insert() search() startWith()






Trie + DFS
https://leetcode.com/problems/word-search-ii/discuss/59784/My-simple-and-clean-Java-code-using-DFS-and-Trie



class TrieNode {
    String word = "";            //  = ""  这个必须要有 就是相当于给个reference 否则word会是null 后面就会有空指针                                    //记录从root 到该节点的单词 我们后面需要拿出来这个String 去比较
	Map<Character, TrieNode> children;
	public TrieNode() { 
        this.children = new HashMap<>();
	}
}

class Trie {
	TrieNode root;
    public Trie() {
        root = new TrieNode();
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
		cur.word = word;
	}

	public boolean search(String word) {
        if (searchNode(word) == null || !searchNode(word).word.equals(word)) {
            return false;
        }
        return true;
    }
    
    public boolean startsWith(String prefix) {
        if (searchNode(prefix) != null)
            return true;
        return false;
    }
    
    public TrieNode searchNode(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (cur.children.containsKey(ch)) {
                cur = cur.children.get(ch);
            } else {
                return null;
            }
        }
        return cur;
    } 
}


class Solution {

    Set<String> ans = new HashSet<String>();

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs("", i, j, board, visited, trie);
            }
        }
        return new ArrayList<>(ans);
    }
    
    public void dfs(String str, int x, int y, char[][] board, boolean[][] visited, Trie trie) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
        if (visited[x][y]) return;
        
        str += board[x][y];
        //这里就是让dfs更早返回的地方:如果当前单词 不存在于words所有单词的前缀中 立即返回
        if (!trie.startsWith(str)) return;
        //这里是和words中某个单词完整匹配
        if (trie.search(str)) {
            ans.add(str);
        }

        visited[x][y] = true;
        dfs(str, x + 1, y, board, visited, trie);
        dfs(str, x - 1, y, board, visited, trie);
        dfs(str, x, y + 1, board, visited, trie);
        dfs(str, x, y - 1, board, visited, trie);
        visited[x][y] = false;
        
    }
}














终极 优化

https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)









