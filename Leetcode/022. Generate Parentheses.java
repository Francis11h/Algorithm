22. Generate Parentheses

Given n pairs of parentheses, generate all combinations of well-formed parentheses。

n = 3;
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]


全排列问题, -----> DFS


dfs 需要的最重要参数 : 左右括号的个数

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfs(0, 0, "", n, result);
        return result;
    }

    public void dfs(int nleft, int nright, String cur, int n, List<String> result) {
        if (nleft == n && nright == n) {
            result.add(cur);
            return;
        }

        if (nleft < n) {
            dfs(nleft + 1, nright, cur + "(", n, result);
        }

        //nright < nleft 右括号要比左括号少才能加右括号 这是本题目最关键, 这样递归保证先插左括号!
        if (nright < n && nright < nleft) {
            dfs(nleft, nright + 1, cur + ")", n, result);
        }
    }
}


T : worst case O(2 ^ 2n)
S : O(n)



回溯 逐个字符添加 生成每一种组合

一个状态要记录的有 当前字符串本身, 左括号数量 右括号数量

递归过程中解决:
 如果当前右括号数量 = 总共的对数n ---> 是一种组合 放入res
 如果当前左括号数量 = 总共的对数n ---> 那么该字符串后续填满右括号 是一种组合 放入res
 如果当前左括号数量 < n
    如果 #左 > #右 ---> 此时可以添加一个 左或者右括号, 递归进入下一层
    如果 #左 = #右 ---> 此时只能添加一个 左括号, 递归进入下一层
    (#左 不可能小于 #右, 因为这样子状态不合法)







