return the count of substring of length K and exactly K distinct characters.
"abcabc" k=3 
ans:3   substrings: ["abc", "bca", "cab"]

"abacab" k=3
ans:2.  substrings: ["bac", "cab"]

//      map记字符个数(有key就break), set记结果(自动去重) : i,j 同向双指针

public class Solution {
    public int KSubstring(String stringIn, int k) {
        Set<String> ans = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();
        int n = stringIn.length();
        int j = 0;
        for (int i = 0; i < n - k + 1; i++) {
            while (j < n && j - i < k) {   //
                char c = stringIn.charAt(j);
                if (map.containsKey(c)) {
                    break;
                }
                map.put(c, 1);
                j++;
                if (map.size() == k) {
                    ans.add(stringIn.substring(i, j));
                }
            }
            map.remove(stringIn.charAt(i));
        }
        return ans.size();
    }
}





use a hashSet to store the qualified substrings. 因为Set具有天然的去重属性，当set中已有改值时无法写入相同的值。
and also can use a hashmap / count[256] to store # of the character 



public class Solution {
    public int KSubstring(String stringIn, int k) {
        Set<String> ans = new HashSet<>();
        int[] count = new int[256];
        int n = stringIn.length();
        int j = 0;
        int dist = 0;
        // i 是 开始的下标 j是结束的下标
        for (int i = 0; i < n - k + 1; i++) {
            while (j < n && dist < k) {
                char c = stringIn.charAt(j);
                if (count[c] == 1) {
                    break;
                }
                if (count[c] == 0) {
                    count[c]++;
                    dist++;
                }
                j++;
                if (dist == k) {
                    ans.add(stringIn.substring(i, j));
                    System.out.println(ans.toString());
                }
            }
            count[stringIn.charAt(i)]--;
            if (count[stringIn.charAt(i)] == 0) {
                dist--;
            }
        }
        return ans.size();
    }
}

