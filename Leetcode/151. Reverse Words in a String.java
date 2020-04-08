151. Reverse Words in a String

Given an input string, reverse the string word by word.

Example 1:

Input: "the sky is blue"
Output: "blue is sky the"

Example 2:

Input: "  hello world!  "
Output: "world! hello"
Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:

Input: "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.






public class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String[] array = s.split("\\s+");

        StringBuffer sb = new StringBuffer();

        for (int i = array.length - 1; i >= 0; i--) {
            if (!array[i].equals("")) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(array[i]);
            }
        }
        return sb.toString();    
    }
}






public class Solution {
       public String reverseWords(String s) {
        if (s == null) return null;

        char[] a = s.toCharArray();
        int n = a.length;
        //step 1. reverse the whole string
        reverse(a, 0, n - 1);
        //step 2. reverse each word
        reverseWords(a, n);
        //step 3. clean up spaces
        return cleanSpaces(a, n);
    }

    //reverse a[] from a[i] to a[j]
    private void reverse(char[] a, int i, int j) {
        while (i < j) {
            char t = a[i];
            a[i++] = a[j];
            a[j--] = t;
        }
    }

    //two pointers
    private void reverseWords(char[] a, int n) {
        int i = 0, j = 0;

        while (i < n) {
            while (i < j || i < n && a[i] == ' ') i++;  //skip space
            while (j < i || j < n && a[j] != ' ') j++;  //skip non space
            reverse(a, i, j - 1);   //word from [i, j - 1], j is ' '.
        }
    }

    //trim leading, trailing and multiple spaces 修剪前导，尾随和多个空格
    String cleanSpaces(char[] a, int n) {
        int i = 0, j = 0;

        while (j < n) {
            while (j < n && a[j] == ' ') j++;   //skip spaces
            while (j < n && a[j] != ' ') a[i++] = a[j++];   //keep non spaces
            while (j < n && a[j] == ' ') j++;      //skip spaces
            if (j < n) a[i++] = ' ';        //keep only one space
        }
        return new String(a).substring(0, i);
    }
}