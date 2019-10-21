compare两个string，只有小写字母。 
每个stirng内部可以任意换位置，所以位置不重要。
每个string内部两个letter出现的频率也可以互换，所以这题只需要两个string每个frequency出现的次数要一样。
比如“babzccc” 和 “bbazzcz” 就返回“true”，因为z和c可以互换频率。 
但是“babzcccm” 和 “bbazzczl” 就不一样，因为m在第一个里出现过，第二个里没有出现过。

If two strings are close enough.
Given two rules to define two strings are close enough.
1. you can swap neighbor char any times. Ex. "abb" -> "bba"
2. If two strings have the same character, then you can change the character into another.
    Ex. If both strings contain "a" and "b", you can change all "a"s in the first string or change all "b"s in the first string. 
    same as the second string

    Ex.
    Input: S1 = "babzccc", S2 = "abbzczz"
    Output: True

    Sol: HashMap<Character, Integer>  counts
    Check if keySet() eqauls()
    HashMap<Integer, Integer> counts of counts, check if the same 


b --> 2
a --> 1
c --> 3
z --> 1


b --> 2
a --> 1
z --> 3
c --> 1



import java.util.HashMap;
import java.util.Map;

public class StringCloseEnough {
    public static void main(String[] args) {
        String s1 = "babzccc";
        String s2 = "czzabbz";
        System.out.println(isCloseEnough(s1, s2));
    }

    public static boolean isCloseEnough(String a, String b) {
        if (a == null || b == null) return false;
        if (a.length() != b.length()) return false;

        Map<Character, Integer> A = new HashMap<>();
        Map<Character, Integer> B = new HashMap<>();

        for (int i = 0; i < a.length(); i++) {
            char cha = a.charAt(i);
            char chb = b.charAt(i);

            A.put(cha, A.getOrDefault(cha, 0) + 1);
            B.put(chb, B.getOrDefault(chb, 0) + 1);
        }
        // 先比 keySet 是否相同
        if (!A.keySet().equals(B.keySet())) return false;
        // 再比 频率出现的次数 是否一样

        Map<Integer, Integer> countA = new HashMap<>();
        Map<Integer, Integer> countB = new HashMap<>();

        for (Integer freq : A.values()) {
            countA.put(freq, countA.getOrDefault(freq, 0) + 1);
        }

        for (Integer freq : B.values()) {
            countB.put(freq, countB.getOrDefault(freq, 0) + 1);
        }
        return countA.equals(countB);
    }
}

