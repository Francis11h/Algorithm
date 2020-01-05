68. Text Justification
文本对齐
Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.


一个单词数组,一个长度表示一行最长的长度,然后对齐文本

有下边几个规则

1.同一个单词只能出现在一行中,不能拆分
2.一行如果只能放下一个单词,该单词放在最左边，然后空格补齐，例如 "acknowledgement#####"，这里只是我为了直观，# 表示空格，题目并没有要求。
3.一行如果有多个单词，最左边和最右边不能有空格，每个单词间隙尽量平均，
    如果无法平均，把剩余的空隙从左边开始分配。
    例如，"enough###to###explain##to"，3 个间隙，每个 2 个空格的话，剩下 2 个空格，从左边依次添加一个空格。
4.最后一行执行左对齐，单词间只能有一个空格，末尾用空格补齐。

pack as many words as you can in each line
Pad extra spaces ' ' when necessary so that each line has "exactly" maxWidth characters.
Extra spaces between words should be distributed as "evenly" as possible.
    If the number of spaces on a line do not divide evenly between words, 
    the empty slots on the "left" will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and "no extra space" is inserted between words.



A word is defined as a character sequence consisting of non-space characters only.
Each word’s length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.



Example 1:

Input:
words = ["This", "is", "an", "example", "of", "text", "justification."]
maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]



Example 2:

Input:
words = ["What","must","be","acknowledgment","shall","be"]
maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be",
             because the last line must be left-justified instead of fully-justified.
             Note that the second line is also left-justified becase it contains only one word.



Example 3:

Input:
words = ["Science","is","what","we","understand","well","enough","to","explain",
         "to","a","computer.","Art","is","everything","else","we","do"]
maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]







就是 实现题 没有别的算法的东西

1. 找 当前这一行 最多能存几个单词 "Decide how many words could be put in the same line"
    要知道 每个单词的长度


2.然后计算单词的空隙是多少 "modify the spaces between words"
    是否是最后一行


parameters that we need

wordIndex: keep revord the index in words
last: the last word index that in the same line (exclusive)
totalChars: the total chars used in the line (one word + one space)

gaps: # of gaps between the wordIndex and last  (last - wordIndex - 1) (3 words 2 gaps)
sb




class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int n = words.length;
        int index = 0;

        while (index < n) {
            int totalChars = words[index].length();
            int last = index + 1;

            while (last < n) {
                if (totalChars + 1 + words[last].length() > maxWidth) break;
                //sum up
                totalChars += 1 + words[last].length();
                last++; 
            }
            // 比单词少一个
            int gaps = last - index - 1;
            StringBuilder sb = new StringBuilder();
            // corner case last line or just one words
            if (last == n || gaps == 0) {
                for (int i = index; i < last; i++) {
                    sb.append(words[i]);
                    sb.append(' ');
                }
                sb.deleteCharAt(sb.length() - 1);
                while (sb.length() < maxWidth) {
                    sb.append(' ');
                }
            } else {    // normal case
                int spaces = (maxWidth - totalChars) / gaps;
                int rest = (maxWidth - totalChars) % gaps;
                //last - 1 是因为 最后不能有空格
                for (int i = index; i < last - 1; i++) {
                    sb.append(words[i]);
                    sb.append(' ');
                    //有些时候要多加一个 
                    for (int j = 0; j < spaces + (i - index < rest ? 1 : 0); j++) {
                      sb.append(' ');
                    }
                }
                sb.append(words[last - 1]);
            }
            res.add(sb.toString());
            // update the index : we must upadte the value of the variable in each loop or we will have an infinite loop
            index = last;
        }
        return res;
    }
}


sb.deleteCharAt(sb.length() - 1); 
是因为这种 test case
Input
["ask","not","what","your","country","can","do","for","you","ask","what","you","can","do","for","your","country"]
16
Output
["ask   not   what","your country can","do  for  you ask","what  you can do","for your country "]
Expected
["ask   not   what","your country can","do  for  you ask","what  you can do","for your country"]

for your country
"正好 16个 就会多一个空格"

