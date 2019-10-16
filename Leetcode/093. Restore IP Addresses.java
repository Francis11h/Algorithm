
93. Restore IP Addresses


Given a string containing only digits, restore it by returning all possible valid IP address combinations.


Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]



需要加一个参数 sec 来表示 到底切了 几个部分 我们只要 4个的
其余的 同排列 dfs


//(0~255).(0~255).(0~255).(0~255)

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        if (s.length() > 12) return ans;
        dfs(0, s, new StringBuilder(), ans, 0);
        return ans;
    }
    
    private void dfs(int level, String s, StringBuilder sb, List<String> ans, int sec) {
        if (sec == 4 && level == s.length()) {
            ans.add(sb.toString());
            return;
        }
        
        for (int i = level; i < level + 3 && i < s.length(); i++) {

            String section = s.substring(level, i + 1);
            if (section.length() > 1 && section.startsWith("0") || Integer.parseInt(section) > 255) return;

            int len = sb.length();
            if (sec == 0) sb.append(section); 
                else sb.append('.').append(section);
            dfs(i + 1, s, sb, ans, sec + 1);
            sb.setLength(len);
        }
    }
}





基本上一样的题 就是 下边这个 只能切 1-2 个 上面的 ip可以切 1-3个

Split String
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

