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






与  79.Word Search 不一样的是 这里的 dfs 可以传入当前的整个字符串 而 Word Search dfs 传入的是 开始match的坐标
因为 我们 用到 Trie 所以 直接搜 str 中间也会 prune 



主体

    问题 dfs 返回值 要什么 void or boolean？ ===> void 仅需要在 dfs中 给 ans 添值 
    肯定不能原主函数中写dfs 要新写一个函数 ===> 因为 主函数中 要对每个点做dfs
    同时要用到 Trie  ===> 所以主函数中也得 建Trie

    dfs 入参 要写什么 1.当前的字符串              ""
                    2.用于搜索的二维矩阵         board[][]
                    3.表示走没走过的boolean数组  visited[][]
                    4.当前坐标                 i, j
                    5.Trie                    trie
重要 tips
    List不能去重复 dfs中搜索到后 先用Set存结果 再转为List

Trie部分

    Trie 需要用到 Trie类 及 TrieNode类

    TrieNode 表示 每个节点 需要存的信息有  
        ===> 1.当前节点所代表的从root到该节点的字符串(本题不需要这个)
             2.孩子指针 用 Map<Character, TrieNode> 存
             3.是否是叶子(这个属性本题用 isWord 表示)

    Tire 中 需要有的东西 root节点 insert() search() startWith() searchNode() 四个方法 searchNode()是全部match和前缀match公用的方法
        insert()就是插单词 插一个 最后一个node 叶子结点 记录下该单词的结束
        searchNode() 根据word找最后的节点 不管是不是 叶子结点
        startWith() 根据searchNode()找出来的节点 只要不是null就可以
        search() 要求更高 不光要求 searchNode()找出来的节点 不是null 还要求该节点必须是整个单词 即该节点的word属性不能是null




Trie + DFS
https://leetcode.com/problems/word-search-ii/discuss/59784/My-simple-and-clean-Java-code-using-DFS-and-Trie
----


class TrieNode {
    boolean isWord;
    Map<Character, TrieNode> children;
    public TrieNode() {
        this.isWord = false;
        this.children = new HashMap<>();
    }
}

class Trie {
    TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }
    //insert
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            node = node.children.get(ch);
        }
        node.isWord = true;
    }
    //find Node
    public TrieNode searchNode(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.children.containsKey(ch)) {
                return null;
            } else {
                node = node.children.get(ch);
            }
        }
        return node;
    }
    //find by prefix
    public boolean startWith(String prefix) {
        return searchNode(prefix) != null;
    }
    //find the exact word
    public boolean search(String word) {
        if (searchNode(word) == null || !searchNode(word).isWord) return false;
        return true;
    }
}


class Solution {
    //store ans without duplicate for cornercase    input: [["a","a"]]
    Set<String> ans = new HashSet<>();
    // main fucntion
    public List<String> findWords(char[][] board, String[] words) {
        //build Trie
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        //start dfs at every point at board
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs("", i, j, board, visited, trie);
            }
        }
        //ans
        return new ArrayList<>(ans);
    }
    
    //dfs
    public void dfs(String str, int x, int y, char[][] board, boolean[][] visited, Trie trie) {
        int m = board.length, n = board[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) return;
        // add char to current string
        str += board[x][y];
        //important statement to stop dfs earlier
        // can't find word with the prefix str then we do not need to search the whole word with the same prefix
        if (!trie.startWith(str)) return;
        // find the str is a word add it to ans
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









