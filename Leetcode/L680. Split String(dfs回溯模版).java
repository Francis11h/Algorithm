

680. Split String

Give a string, you can choose to split the string after one character or two adjacent characters, 
and make the string to be composed of only one character or two characters. 
Output all possible results.

Given the string "123", return [["1","2","3"],["12","3"],["1","23"]]




public class Solution {
    public List<List<String>> splitString(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null) return ans;
        dfs(0, s, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int level, String s, List<String> cur, List<List<String>> ans) {
        if (level == s.length()) {
            ans.add(new ArrayList<>(cur));
            return;
        }

        for (int i = level; i < level + 2 && i < s.length(); i++) {
            String subStr = s.substring(level, i + 1);
            cur.add(subStr);
            dfs(i + 1, s, cur, ans);
            cur.remove(cur.size() - 1);
        }
    }
}



笨方法 


public class Solution {
    
    public List<List<String>> splitString(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void dfs(String str, int index, List<String> cur, List<List<String>> res) {
        if (index == str.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        
        //从index 开始 搜 因为规定长度是1 或 2
        // 就 index 或者 index, index + 1
        cur.add(String.valueOf(str.charAt(index)));
        //cur.add(substring(index, index + 1));
        dfs(str,index + 1, cur, res);
        cur.remove(cur.size() - 1);
        
        //防止越界,index最多 length - 2
        if (index + 1 < str.length()) {
            cur.add(str.substring(index, index + 2));
            dfs(str, index + 2, cur, res);
            cur.remove(cur.size() - 1);            
        }
    }
}










