/*Given a String S and T, find the minimum Window in S 
which contains all the characters in T

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"

Input: S = "ADOBECODEBANC", T = "ABCC"
Output: "CODEBANC"


// two pointers
先找到一个substring，满足包含T中的所有字符，然后再从左边减少字符
L ------------------------ R , Suppose this is the window that contains all characters of T 
                          
        L----------------- R , this is the contracted window. We found a smaller window that still contains all the characters in T

When the window is no longer valid, start expanding again using the right pointer.*/ 

class Solution {
    public String minWindow(String ss, String tt) {
        if (tt == null || tt == "") return "";
        char[] s = ss.toCharArray();
        char[] t = tt.toCharArray();

        int[] cntS = new int[256];
        int[] cntT = new int[256];

        int k = 0;      //kind of characters in T
        for (char c : t) {
            cntT[c]++;          //count every kind of char's #
            if (cntT[c] == 1) {
                k++;
            }
        }

        String minStr = "";
        int now = 0;    //S的substring中 和 T中的字符 种类+个数 一样的 字符种类数
        int l = 0, r = 0;
        while (l < s.length) {
            while (r < s.length && now < k) {   //先找到长的
                cntS[s[r]]++;
                if (cntS[s[r]] == cntT[s[r]]) {
                    now++;
                }
                r++;
            }
            if (now == k){                  
                if (minStr == "" || r - l < minStr.length()){
                    minStr = ss.substring(l, r);
                }
            }
            //再从左边缩小
            cntS[s[l]]--;
            if (cntS[s[l]] == cntT[s[l]] - 1) {
                now--;
            }
            l++;
        }
        return minStr;
    }
}