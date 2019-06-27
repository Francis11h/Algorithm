17. Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].



第一步, 画 recursion tree

                                    root
level 0 : '2'           a             b             c

level 1 : '3'        ad ae af      bd be bf     cd ce cf


class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return ans;
        Map<Character, String> dict = constructWordDict();
        dfs(0, dict, digits, new StringBuilder(), ans);
        return ans;
    }

    //需要的参数: level--->digits第几位, dict--->digits和letter的映射字典, digits字符串, tmp用来添加删除的stringBuilder, ans
    private void dfs(int level, Map<Character, String> dict, String digits, StringBuilder tmp, List<String> ans) {
        if (level == digits.length()) {
            ans.add(tmp.toString());
            return;
        }
        //这里的 level 很关键     level的意义是digits的哪一位
        char ch = digits.charAt(level);
        String letter = dict.get(ch);                           //再根据这一位 取出其对应的String 然后再遍历下面的

        //遍历 当前层的 letter 字符串, 把其中每个字符 都取出来一次

        for (int i = 0; i < letter.length(); i++) {
            tmp.append(letter.charAt(i));
            dfs(level + 1, dict, digits, tmp, ans);
            tmp.deleteCharAt(tmp.length() - 1);                 //这的 stringBuffer函数, 没有remove
        }
    }

    private Map<Character, String> constructWordDict() {
        Map<Character, String> wordDict = new HashMap<>();
        wordDict.put('0', "");
        wordDict.put('1', "");
        wordDict.put('2', "abc");
        wordDict.put('3', "def");
        wordDict.put('4', "ghi");
        wordDict.put('5', "jkl");
        wordDict.put('6', "mno");
        wordDict.put('7', "pqrs");
        wordDict.put('8', "tuv");
        wordDict.put('9', "wxyz");
        return wordDict;
    }
}


本题核心 : 
    遍历 当前层的 letter 字符串, 把其中每个字符 都取出来一次


StringBuilder 删去最后一个元素
    sb.deleteCharAt(sb.length() - 1);



这么在new里写 put 更快, 不用再写个函数了,  
但是  new HashMap<Character, String>() 中, Character, String不能省。

    Map<String, String> phone = new HashMap<Character, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    


T : O(branch factor ^ depth) < O(4 ^ n)
S : O(n) (dict + StringBuilder)