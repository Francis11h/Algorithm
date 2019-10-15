282. Expression Add Operators

Given a string that contains only digits 0-9 and a target value, 
return all possibilities to add 	Binary Operators (not unary) +, -, or * 
between the digits so they evaluate to the target value.


Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]

Input: num = "3456237490", target = 9191
Output: []


Note that a number can contain multiple digits.

Since the question asks us to find all of the valid expressions,
we need a way to iterate over all of them. (Hint: Recursion!)

We can keep track of the expression string and evaluate it at the very end. 
But that would take a lot of time.
Can we keep track of the expression‘s value 
 as well so as to avoid the evaluation at the very end of recursion?

Think carefully about the multiply operator. It has a higher precedence than the addition and subtraction operators. 
1 + 2 = 3 
1 + 2 - 4 --> 3 - 4 --> -1 
1 + 2 - 4 * 12 --> -1 * 12 --> -12 (WRONG!)
1 + 2 - 4 * 12 --> -1 - (-4) + (-4 * 12) --> 3 + (-48) --> -45 (CORRECT!)


We simply need to keep track of the last operand in our expression 
and reverse it‘s effect on the expression’s value while considering the multiply operator.



class Solution {
    List<String> ans = new ArrayList<>();
    
    //main function just try every possible ways by dfs
    public List<String> addOperators(String num, int target) {
        dfs(num, target, 0, "", 0, 0);
        return ans;
    }
    
    //dfs need 1.original string num  2.target value  3. now index
    //         4.now String with operator  5. now sum
    // 6. * is specialwe should save the value that is to be multiplied in the next recursion
    
    public void dfs(String num, int target, int index, String cur, long sum, long multiply) {
        if (index == num.length()) {
            if (sum == target) {
                ans.add(cur);
            }
            return;
        }
        
        //decide how many digits to take one time
        // begin from index(start) 
        for (int i = index; i < num.length(); i++) {
            //fetch one or multiply digits 
            long operand = Long.parseLong(num.substring(index, i + 1));
            // begin we can't add operator
            if (index == 0) {
                // 一定都得是 i + 1 不是 index + 1, index + 1 就会出现 12-2*3 这种 就是重复用了数字
                // 这里的i表示的是 现在 算到哪一位了
                dfs(num, target, i + 1, cur + operand, sum + operand, operand);
            } else {
                dfs(num, target, i + 1, cur + "+" + operand, sum + operand, operand);
                dfs(num, target, i + 1, cur + "-" + operand, sum - operand, -operand);
                // 1 + 1 * 5
                dfs(num, target, i + 1, cur + "*" + operand, sum - multiply + multiply * operand, multiply * operand);
            }
            // corner case '05' 不能算个数 "105"
            if (operand == 0) {
                break;
            }
        }
    }
}



用 stringbuilder 代替 string 
每次 backtracking 的时候 把 sb 重新设置为之前的长度 这样子 就处理了 删除多个的问题！！！ 
sb.setLength(len);

class Solution {
    
    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        dfs(num, target, 0, new StringBuilder(), 0, 0, ans);
        return ans;
    }
    
    
    public void dfs(String num, int target, int index, StringBuilder sb, long sum, long multiply, List<String> ans) {
        if (index == num.length()) {
            if (sum == target) {
                ans.add(sb.toString());
            }
            return;
        }
        
        for (int i = index; i < num.length(); i++) {
            Long operand = Long.parseLong(num.substring(index, i + 1));
            int len = sb.length();
         
            // "0004" 这种直接 不走了
            if (i > index && num.charAt(index) == '0') break;
            
            if (index == 0) {
                dfs(num, target, i + 1, sb.append(operand), operand, operand, ans);
                sb.setLength(len);
            } else {
                dfs(num, target, i + 1, sb.append('+').append(operand), sum + operand, operand, ans);
                sb.setLength(len);
                dfs(num, target, i + 1, sb.append('-').append(operand), sum - operand, -operand, ans);
                sb.setLength(len);
                dfs(num, target, i + 1, sb.append('*').append(operand), sum - multiply + multiply * operand, multiply * operand, ans);
                sb.setLength(len);
            }
        }
        
    }
}





T:O(4^N) 
S:O(N)












