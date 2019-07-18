771. Jewels and Stones

class Solution {
    public int numJewelsInStones(String J, String S) {
        if (J == null || S == null) return 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < J.length(); i++) {
            set.add(J.charAt(i));
        }
        int ans = 0;
        for (int i = 0; i < S.length(); i++) {
            if (set.contains(S.charAt(i))) ans++;
        }
        return ans;
    }
}



正则表达式 解法

class Solution {
    public int numJewelsInStones(String J, String S) {
        return S.replaceAll("[^" + J + "]", "").length();
    }
}
replaceAll(String regex, String replacement) is a method of String objects that replaces any parts of the string that matches the provided regular expression (regex) with a replacement. 
In regex, 

"[ ]" matches characters that in the bracket; 
            e.g. "[abc]" matches any "a", "b", or "c". 
"[^ ]" matches characters not in the bracket; 
            e.g. "[^ab]" will match "c" in String "abc". String J was concatenated to "[^" and "]" to create the regex "[^ (all characters in J) ]" in order to replace any characters of S that is not in J with a blank "".


