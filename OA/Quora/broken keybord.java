broken keybord 
键盘的部分英文字母键坏了（注意只有字母键坏了） 

给定一个String 和 一个 char Array（没坏的字母键），输出String中能打出的字符串数。

input “hello,world!” ['i','e','o','l','h']; output: 1 (只能打出 hello 这个单词）

input “5 + 3 = 8” [] output: 5 (没有英文字母， 5， +， 3， =， 8 都可以打出）


//65  A     97  a
class BrokenBoard {
    public static void main(String[] args) {
      System.out.println(calculate("Hello, my dear friend!",new char[] {'h', 'e', 'l', 'o', 'm'}));
      //System.out.println(calculate("5 + 3 = 8", new char[]{}));
    }


    public static int calculate(String words, char[] board) {
        int ans = 0;

        Set<Character> dict = new HashSet<>();
        for (char c : board) {
            dict.add(c);
            dict.add((char)(c - 32));
        }

        String[] wordList = words.split(" ");

        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (Character.isLetter(ch)) {
                    if (!dict.contains(ch)) break;
                }
                if (i == word.length() - 1) {
                    ans++;
                    System.out.println(word);
                }
            }
        }

        return ans;
    }
}

