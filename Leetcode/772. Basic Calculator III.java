772. Basic Calculator III

"+ - * / ( ) "   7种 operator(还有空格)

"1+1" = 2
"6-4/2" = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6*3+5-(3*14/7+2)*5)+3" = -12






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
                
