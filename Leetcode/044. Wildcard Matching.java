44. Wildcard Matching


Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

Input:
s = "acdcb"
p = "a*c?b"
Output: false




基本上 和    10. Regular Expression Matching 一样 但是 本题直接传 substring 会 TLE



//'?' Matches any single character. '*' Matches any sequence of characters (including the empty sequence).

class Solution {
    public boolean isMatch(String s, String p) {
        if (s.length() == 0) return isEmpty(p);
        if (p.length() == 0) return false;
        
        char s1 = s.charAt(0);
        char p1 = p.charAt(0);
        
        if (isSame(s1, p1)) {
            return isMatch(s.substring(1), p.substring(1));
        } else if (p1 == '*') {
            // 谁去一个都行 因为 * 可带表无数个
            return isMatch(s.substring(1), p) || isMatch(s, p.substring(1));
        } else {
            return false;
        }
    }
    
    private boolean isSame(char a, char b) {
        return a == b || b == '?';
    }
    
    private boolean isEmpty(String p) {
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}








不用 substring 只传位置 仍旧超时。。。

class Solution {
    public boolean isMatch(String s, String p) {
        return Match(s, p, 0, 0);
    }

    public boolean Match(String s, String p, int x, int y) {
        if (s.length() == x) return isEmpty(p, y);
        if (p.length() == y) return false;
        
        char s1 = s.charAt(x);
        char p1 = p.charAt(y);
        
        if (isSame(s1, p1)) {
            return Match(s, p, x + 1, y + 1);
        } else if (p1 == '*') {
            return Match(s, p, x + 1, y) || Match(s, p, x, y + 1);
        } else {
            return false;
        }
    }
    
    private boolean isSame(char a, char b) {
        return a == b || b == '?';
    }
    
    private boolean isEmpty(String p, int y) {
        for (int i = y; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}





dp solution

class Solution 
{
    public boolean isMatch(String s, String p) 
    {   
        // init base cases
        int m = s.length(), n = p.length();
        boolean[][] f = new boolean[m + 1][n + 1];
        f[m][n] = true;
        for (int j = n - 1; j >= 0; --j) if (p.charAt(j) == '*') f[m][j] = true; else break;
        
        // DP
        for (int i = m - 1; i >= 0; --i)
        {
            for (int j = n - 1; j >= 0; --j)
            {
                if (p.charAt(j) == '*') f[i][j] = f[i][j + 1] || f[i + 1][j];
                else if (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)) f[i][j] = f[i + 1][j + 1];
            }
        }
        
        return f[0][0];
    }
}


