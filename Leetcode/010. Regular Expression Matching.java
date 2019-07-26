10. Regular Expression Matching

'.' Matches any single character.                   
'*' Matches zero or more of the preceding element. 必须是前一个字符 但是可以 0次 1次 或者 多次

The matching P should cover the entire input string S (not partial).

要 P 与 S 完全匹配 两者 都不能多或者少！！ 他这个题目有歧义

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

s = "aa" p = "a*" true

s = "ab" p = ".*" true
Explanation: ".*" means "zero or more (*) of any character (.)".
这是 一开始没理解的地方 因为可以重复无数次 即可以为无数个....... 所以 ".*" 可以表示任何字符

s = "aab" p = "c*a*b" true 
Explanation : "c" can be repeated 0 times, a can be repeated 1 time.
这个 也是最后迷惑的地方 "*" 可以让前一个字符消失。。。 "c*a" 可以等于 "a" /。。。 太狗了。。。

s = "mississippi"
p = "mis*is*p*"     false
Explanation : p = "mississpp" false

"aaa"
"a*" 
true

"ab"
".*c"
false 因为c后头没有* 所以这个c消不掉。

递归方法

class Solution {
    public boolean isMatch(String s, String p) {
        //两个出口 任何一个长度为0 停止
        if (s.length() == 0) {
            return checkEmpty(p);
        }
        if (p.length() == 0) {          //p.equals("")可以 p == ""不可以 字符串内容比较一定用.equals
            return false;
        }

        char s1 = s.charAt(0);
        char p1 = p.charAt(0);
        char p2 = '0';
        if (p.length() > 1) {
            p2 = p.charAt(1);
        }
        // 4种情况
        if (p2 == '*') {
            if (isCompared(s1, p1)) {
                /** isMatch(s.substring(1), p) 这个很关键 代表了 p的第一位可以重复无数次！！*/
                return isMatch(s.substring(1), p) || isMatch(s, p.substring(2));
            } else {
                return isMatch(s, p.substring(2));
            }
        } else {
            if (isCompared(s1, p1)) {               //这里也要判断
                return isMatch(s.substring(1), p.substring(1));
            } else {
                return false;
            }
        }

    }

    public boolean isCompared(char s, char p) {
        // if (s == p || p == '.') return true;
        // return false;                                这么写就有些蠢了
        return s == p || p == '.';
    }

    public boolean checkEmpty(String p) {
        if ((p.length() & 1) == 1) {
            return false;
        }
        for (int i = 1; i < p.length(); i += 2) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}

T : O((T+P)2 ^ (T + p/2)).

S : O((T+P)2 ^ (T + p/2))




DP solution 

cause this has optimal substructure, it is natural to catch intermediate results.

we use dp[i, j] to stands for whether s[i:] and p[j:] match, to saving us expensive string-building operations,
and allow us to cache the intermediate results.


if s.charAt(i) == p.charAt(j) dp[i][j] = dp[i - 1][j - 1];
if p.charAt(j) == '.' dp[i][j] = dp[i - 1][j - 1];
if p.charAt(j) == '*'
    1. if p.charAt(j - 1) != s.charAt(i).  dp[i][j] = dp[i][j - 2];
    2. if p.charAt(j - 1) == s.charAt(i) or p.charAt(j - 1) == '.':
              dp[i][j] = dp[i - 1][j]    //in this case, a* counts as multiple a 
           or dp[i][j] = dp[i][j - 1]   // in this case, a* counts as single a
           or dp[i][j] = dp[i][j - 2]   // in this case, a* counts as empty



dp[i][j] means that the char of String s in i index compare with the char of String p in j index. if same, then dp[i][j]= true,else dp[i][j]= false.

If p.charAt(j) == '*':

1 dp[i][j] = dp[i-1][j]
//in this case, a* counts as multiple a
for example: s="aaa"; p ="a*"
a a a
a *
dp[0][0]=true;
dp[1][1]=dp[i][j-1]=dp[1][0]=true;
dp[2][1]=dp[1][1]=true;

2 or dp[i][j] = dp[i][j-1]
// in this case, a* counts as single a
for example: s="aa"; p ="a*"
a a
a *
dp[0][0]=true;
dp[1][1]=dp[i][j-1]=dp[1][0]=true;

3 or dp[i][j] = dp[i][j-2]
// in this case, a* counts as empty
for example: s="b"; p ="a*b"
b
b a *
dp[0][0]=true;
dp[0][1]=false;
dp[0][2]=dp[0][2-2]=dp[0][0]=true;











