772. Basic Calculator III

"+ - * / ( ) "   7种 operator(还有空格)

"1+1" = 2
"6-4/2" = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6*3+5-(3*14/7+2)*5)+3" = -12





Calculator 通用解法
       prev, num, sum, prevOp
核心思想是 "calculate delay"



class Solution {
    public int calculate(String s) {
        Queue<Character> q = new LinkedList<>();
        //preprocessing remove whitespaces
        for (char c : s.toCharArray()) {
            if (c != ' ')
                q.offer(c);
        }
        // add place holder otherwise the last operator will be omission
        q.offer(' ');
        return helper(q);
    }
    
    private int helper(Queue<Character> q) {
        int prev = 0, num = 0, sum = 0;
        char prevOp = '+';
        
        while (!q.isEmpty()) {
            char c = q.poll();
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '(') {  
                num = helper(q);
            } else {
                switch (prevOp) {
                    case '+':
                        sum += prev;
                        prev = num;
                        break;
                    case '-':
                        sum += prev;
                        prev = -num;
                        break;
                    case '*':
                        prev *= num;
                        break;
                    case '/':
                        prev /= num;
                        break;
                }
                if (c == ')') break;
                prevOp = c;
                num = 0;
            }
        }
        return sum + prev;
    }
}





//when c is a operator, do calculation according to prevOp (previous!!!!)
    //+-
    //if the previous operator is +, merge(add) the prev to sum, then assign num to prev
    //if the previous operator is -, merge(add) the prev to sum, then assign -num to prev
    //*/ 
    //do not modify the sum cause "3+2*2" when we first meet * wo can't deal with the prev, which is 2
    //if the previous operator is *, modify the prev by multiplying the prev by num
    //if the previous operator is /, modify the prev by dividing the prev by num
    //''
    //placeholder, calculate the remain previous operator, then break;
    //and then update the prevOp
                

queue:  (3 + (4 - 2)) * 2 ''

prev    num     sum     prevOp
 0       0       0       '+'            
 0      递归                             1.  '('


queue:  3 + (4 - 2)) * 2 ''

prev    num     sum     prevOp
 0       0       0       '+'            
 0       3       0       '+'             2.  '3'
 3       0       0       '+'             3.  '+'
 3      递归                              4.  '('



queue:  4 - 2)) * 2 ''
prev    num     sum     prevOp
 0       0       0       '+'             
 0       4       0       '+'             5.  '4'
 4       0       0       '+'             6.  '-' 
 4       2       0       '-'             7.  '2'
 -2      0       4       ')'             8.  ')'

return sum + prev = 2



queue:  ) * 2 ''
prev    num     sum     prevOp
 3    递归 = 2    0       '+'               4.  '('  
 2       0       3        ')'               9.   ')'

 return sum + prev = 5



queue:  * 2 ''
prev    num     sum     prevOp
 0       0       0       '+'            
 0     递归 = 5   0       '+'                 1.  '('
 5       0       0       '*'                 10.  '*'
 5       2       0       '*'                 11. '2'
 10      0       0       ' '                 12.  ' ' (placeholder)
 return sum + prev = 10

