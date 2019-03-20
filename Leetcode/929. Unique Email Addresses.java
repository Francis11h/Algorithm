929. Unique Email Addresses

Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  
For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, 
for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails? 


    加在      本地名称         域名
.  :          无影响         是不同的邮箱
+  :       第一个+后全忽略     是不同的邮箱

Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails



思路:
首先, 去重 => HashSet => 包含的元素是不会重复的    所以用一个HashSet来存结果很直观

@的处理 => @ 前后法则是不一样的，所以自然而然的想到把这两块儿分开, 但是 也可以不分开,不分开前后也要区别处理
. =>   @前,continue
+ =>   @前,动下标 直到找到@，然后把@及@后的字符全加上
@ =>   碰到, 就把@及@后的字符全加上, 结束循环, 这样子 @后的'.'和'+'就直接加入了不用再单独处理了

//不 split 方法
class Solution {
    public int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>();
        for (String email : emails) {
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < email.length(); i++) {
                char c = email.charAt(i);
                if (c == '.') {
                    continue;
                } else if (c == '+') {
                    while(email.charAt(i) != '@') {
                        i++;
                    }
                    tmp.append(email.substring(i));
                    break;
                } else if (c == '@') {
                    tmp.append(email.substring(i));
                    break;
                } else {
                    tmp.append(c);
                }
            }
            set.add(tmp.toString());
        }
    return set.size();
    }
}



substring() 方法:

public String substring(int beginIndex)

或

public String substring(int beginIndex, int endIndex)

要是只有一个参数就是 从这个参数（包括）到结尾
beginIndex -- 起始索引（包括）, 索引从 0 开始。
endIndex -- 结束索引（不包括）



// split

split()  方法:
public String[] split(String regex, int limit)

replaceAll() 方法:
public String replaceAll(String regex, String replacement)

class Solution {
    public int numUniqueEmails(String[] emails) {
        HashSet<String> set = new HashSet<>();
        for (String emial : emails) {
            String[] parts = email.split("@");  //split into local and domain parts.
            String[] local = parts[0].split("\\+"); //split local by '+'.
            set.add(local[0].replaceAll(".", "") + "@" + parts[1]);
        }
    return set.size();
    }
}

 '.', '|' 和 '*' 等转义字符，必须得加 \\
Java 转义 两个 \\

Q2: Why does Java regular expression use \\, instead of \, to escape metacharacter such as +, ., *, etc?

A2: I guess the reason is that the backslash character is an escape character in Java String literals already.
