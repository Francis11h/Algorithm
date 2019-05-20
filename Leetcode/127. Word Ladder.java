127. Word Ladder

find the length of shortest transformation sequence from beginWord to endWord
Only one letter can be changed at a time.
Each transformed word must exist in the word list.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5
Explanation: As one shortest transformation is 
"hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.


问题1: List中单词怎么记录、查找、处理？   ===> 变成 set
问题2: 如何用bfs ===> 找一个单词可能变成的（每一位都有26种可能: wordlength * 26种）
                        同时还要看新的在不在给的dict中
                        相当于bfs中之前的4个方向，只不过现在的方向有最多 wordlength * 26 个）
问题3: 之前的 visited 数组保留已经走过 现在拿什么代替？（必须要不走回头路，否则搜索会巨大冗余）
        ===> 用一个新的set，每次把走过的加进入

具体实现的问题
问题4: nextWord 判定和result计算




class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0)
            return 0;
        // if (beginWord == endWord)
        //     return 1;
        Set<String> dict = new HashSet<>();
        for (String s : wordList) {
            dict.add(s);
        }
        if (!dict.contains(endWord))
            return 0;
        
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.offer(beginWord);
        set.add(beginWord);
        
        int result = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) {        //出队的时候比     word == endWord这么写不行
                    return result;
                }
                for (String nextWord : getNestWords(word, dict)) {
                    if (!set.contains(nextWord)) {
                        set.add(nextWord);
                        queue.offer(nextWord);
                    }
                }
            }
            result += 1;
        }
        return 0;
    }

    public List<String> getNestWords(String word, Set dict) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == word.charAt(i))
                    continue;
                char[] temp = word.toCharArray();
                temp[i] = c;
                String nextWord = new String(temp);
                //String.valueOf(temp); 这么写可以
                //temp.toString() 这么写为什么不行？ 下面有解答
                //Arrays.teString(temp)也不行 但两个理由不同
                if (dict.contains(nextWord)) {
                    ans.add(nextWord);
                }
            }
        }
        return ans;
    }
}





数组的toString()方法是Object的toString()方法.  直接用temp.toString()得到的是乱码
因为数组类中并没有对此方法重写(override)，仅仅是重载(overload)为类的静态方法
所以数组直接使用toString()的结果也是[类型@哈希值]（乱码）

数组转为字符串应写成：
Arrays.toString(temp),得到的是一个类似 [a, b, c] 
这种方法的toString()是带格式的 ，即这个的字符串 不是我们要的 所以还不行


如果仅仅想输出 abc 则需用以下两种方法：
方法1：直接在构造String时转换。
        char[] data = {'a', 'b', 'c'};
        String str = new String(data);
方法2：调用String类的方法转换。
        String.valueOf(char[] ch)
