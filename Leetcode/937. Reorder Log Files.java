937. Reorder Log Files


你有一个日志数组 logs。每条日志都是以空格分隔的字串。

对于每条日志，其第一个字为字母标识符。 the first word in each log is an alphanumeric identifier.

    标识符后面的每个字将仅由小写字母组成，或；
    标识符后面的每个字将仅由数字组成。
我们将这两种日志分别称为字母日志和数字日志。保证每个日志在其标识符后面至少有一个字。


将日志重新排序，使得所有字母日志都排在数字日志之前。
字母日志按内容 字典顺序 lexicographically 排序，忽略标识符; 在内容相同时，按标识符排序. 数字日志应该按原来的顺序排列.

返回日志的最终顺序。

输入：["a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"]
输出：["g1 act car", "a8 act zoo", "ab1 off key dog", "a1 9 2 3 1", "zo4 4 7"]


0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] 保证有一个标识符，并且标识符后面有一个word





基本上 考了一个 Comparator 的写法



class Solution {
    public String[] reorderLogFiles(String[] logs) {
        
        Comparator<String> myComparator = new Comparator<String>() {
            // 这个 compare 的返回值 如果 <= 0, 则代表不用换 > 0 代表两元素要换位置
            @Override
            public int compare(String str1, String str2) {
                //先把标识符和正文分开
                String[] split1 = str1.split(" ", 2);
                String[] split2 = str2.split(" ", 2);
                //判断是 字符log 还是 数字 log
                boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
                boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
                //都不是数字, 即都是字符型就直接lexicographically比, 相同在比 identifer
                if (!isDigit1 && !isDigit2) {
                    int difference = split1[1].compareTo(split2[1]);
                    if (difference != 0)
                        return difference;
                    return split1[0].compareTo(split2[0]);
                }
                return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
            }
        };

        Arrays.sort(logs, myComparator);
        return logs;
    }
}







Comparator<String> myComparator = new Comparator<>() 

split(" ", 2)

compareTo









直接使用Arrays.sort进行排序。使用lambda传Comparator。

public class Solution {
    /**
     * @param logs: the logs
     * @return: the log after sorting
     */
    public String[] logSort(String[] logs) {
        Arrays.sort(logs, (str1, str2) -> {
            int contentStart1 = str1.indexOf(" ");
            int contentStart2 = str2.indexOf(" ");
            
            boolean isDigit1 = Character.isDigit(str1.charAt(contentStart1 + 1));
            boolean isDigit2 = Character.isDigit(str2.charAt(contentStart2 + 1));
            if (isDigit1 && isDigit2) {
                return 0;
            }
            if (isDigit1) {
                return 1;
            }
            if (isDigit2) {
                return -1;
            }
            
            int compare = str1.substring(contentStart1 + 1).compareTo(str2.substring(contentStart2 + 1));
            if (compare != 0) {
                return compare;
            }
            return str1.substring(0, contentStart1).compareTo(str2.substring(0, contentStart2));
        });
        
        return logs;
    }
}











