208. Implement Trie (Prefix Tree)

Implement a trie with insert, search, and startsWith methods.

Trie trie = new Trie();

trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");   
trie.search("app");     // returns true


TireNode节点 三元素
    1. char c 节点存的字符
    2. HashMap<Character, TrieNode> children 节点存的一个hash, 通过这个找他的孩子
    3. isWord 判断是不是有效单词(为了前缀搜索).

Tire类:
    root 是 整个类的属性，要放在constructor外头.

insert():
    当前节点的孩子中, 不包含 ch 这个字符, 那么要在当前节点的孩子中 插入(put) 一个 cur.children.put(ch, new TrieNode(ch));
        插完/原本就 包含, 则通过取出 ch 这个字符对应的 TrieNode, 往下走一层. : cur = cur.children.get(ch);
        走到最后一层时,标记下 是个单词 : cur.isWord = true。

searchNode():
    由于 search/prefixSearch 差不多, 不同就是判断下最后一个 TrieNode 是不是合法单词。
    所以 找 最后一个字符 对应的 TrieNode的过程可以一起写为 public TrieNode searchNode(String str)。
        找的过程就很简单了, 孩子包含 ch,走向下一个孩子, 不包含 代表不存 直接返回 null。
        最后返回 最后一个字符 对应的 TrieNode : cur.


方法一 用 HashMap<Character, TrieNode> 存children

class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<>();        //这里直接要定义出来是 HashMap, 否则 后头两个constructor里都要写
    boolean isWord;
    public TrieNode() {                                  //这是 root 节点的 constructor 必须得有

    }
    public TrieNode(char c) {
        this.c = c;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!cur.children.containsKey(ch)) {
                cur.children.put(ch, new TrieNode(ch));
            }
            cur = cur.children.get(ch);            
            if (i == word.length() - 1) {
                cur.isWord = true;
            }
        }
        //cur.isWord = true; 这么写就不用写判断 if(i == word.length() - 1) 了
        // 因为 出了for(), cur 必指向最后一个字符 对应的 TrieNode, 直接设置即可
    }

    public boolean search(String word) {
        if (searchNode(word) == null || !searchNode(word).isWord) {
            return false;
        }
        return true;
    }
    public boolean startsWith(String prefix) {
        if (searchNode(prefix) != null) {
            return true;
        }
        return false;
    }

    //为什么需要这个？？ 因为我需要把最后的那个TireNode找到, 因为我后面要知道最后一个字符对应Node的isWord属性, 所以必须返回一个word最后那个字符的Node
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






方法二 : 用 TrieNode[] 数组存 因为一般只有 26个字母[a - z];

class TrieNode {
    char c;
    TrieNode[] children = new TrieNode[26];
    boolean isWord;
    public TrieNode() {

    }
    public TrieNode(char c) {
        this.c = c;
    }
}

class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNode(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        if (searchNode(word) == null || !searchNode(word).isWord) {
            return false;
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        if (searchNode(prefix) != null) {
            return true;
        }
        return false;
    }

    public TrieNode searchNode(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (cur.children[ch - 'a'] != null) {
                cur = cur.children[ch - 'a'];
            } else {
                return null;
            }
        }
        return cur;
    }
}

