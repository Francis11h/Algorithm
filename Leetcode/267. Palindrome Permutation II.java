267. Palindrome Permutation II

Given a string s, return all the palindromic permutations (without duplicates) of it. 
Return an empty list if no palindromic permutation could be form.


Input: "aabb"
Output: ["abba", "baab"]

Input: "abc"
Output: []




class Solution {
    public List<String> generatePalindromes(String s) {
    	// 同 266. Palindrome Permutation
        int[] count = new int[256];
        int odd = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            count[ch]++;
            if ((count[ch] & 1) == 1 ) {
                odd++;
            } else {
                odd--;
            }
        }
        
        if (odd > 1) return new ArrayList<>();
        
        // now a palindromic permutation is possible for s
        // but how can we generating them? ----> we can generate only those permutations which are already palindromes
        // ---> generate only the first half of palindromic string and to append its reverse string to itself
        // ----> create a new string that contians all the characters of s but with the number of half
        // then we generate all the permutations of this new string append the reverse of this permuted str
        
        // if s with odd length, one of the charactersin s must occur an odd number of times, we keep track of this character and place this character to the middle of result str
        
        // but there will be a lot of duplicates  but the step is same as the permutations 
        String mid = "";
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < 256; i++) {
            if (count[i] == 0) continue;
            char ch = (char) (i);
            if ((count[i] & 1) == 1) {
                mid += ch;
            }
            for (int k = 0; k < count[i] / 2; k++) {
                list.add(ch);
            }
        }
        
        List<String> ans = new ArrayList<>();
        permutation(list, 0, new boolean[list.size()], new StringBuilder(), ans, mid);
        return ans;
    }
    
    //same as 47. Permutations II
    private void permutation(List<Character> list, int level, boolean[] visited, StringBuilder sb, List<String> ans, String mid) {
        if (level == list.size()) {
            String cur = sb.toString() + mid + sb.reverse().toString();
            //这里 还要 再变回来
            sb.reverse();
            ans.add(cur);
            return;
        }
        
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i).equals(list.get(i - 1)) && !visited[i - 1]) continue;
            if (!visited[i]) {
                char ch = list.get(i);
                visited[i] = true;
                sb.append(ch);
                
                permutation(list, level + 1, visited, sb, ans, mid);
                
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }
    }  
}







