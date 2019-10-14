131. Palindrome Partitioning
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.


Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]



class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(s, 0, new ArrayList<String>(), res);
        return res;
    }
    
    //index:从当前往下走 
    private void dfs(String str, int index, List<String> cur, List<List<String>> res) {
        //因为要完全分割完 才有可能是解
        if (index == str.length()) {
            //直接加 因为不满足的 不会让他走到最后
            res.add(new ArrayList<String>(cur));
            return;
        }
        
        //每次从index位置开始选，选多长：是到i
        //i = index 选1个字符； i= index + 1 选2个； i = 最大取 str.length - 1 ，那就一直算到最后
        for (int i = index; i < str.length(); ++i) {
            String subStr = str.substring(index, i + 1);	//[index, i + 1) 左闭右开
            if (isPalindrome(subStr)) {
                cur.add(subStr);
                // i 表示的是 最后用的字符的 下标 我们从它的下一个开始 进行递归
                dfs(str, i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }
    
    private boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}