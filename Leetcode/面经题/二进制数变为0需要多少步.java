https://www.geeksforgeeks.org/number-of-steps-required-to-convert-a-binary-number-to-one/ 
变为1需要多少步


given an interger as a binary string, 
keep shifting the string by the following rule until it reaches 0,
output the number of steps needed.


if the integer is odd, "substract" it by 1.
if the integer is even, divide it by 2.

example "00101" output 4



直接 变成10进制 完事儿了
public class convertBinaryToZero {
    public static int convert(String s) {
        if (s == null) return -1;
        int ans = 0;
        // convert the str to binary int, which represented by an array
        int num = Integer.parseInt(s, 2);
        while (num > 0) {
            if ((num & 1) == 1) {
                num -= 1;
            } else {
                num /= 2;
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(convert("000101"));
    }
}


------------------------------------------------------------------------------------------------------------
相同的题 
就是 要变成 1, 同时 奇数变为加

Number of steps required to convert a binary number to one
Given a binary string str, the task is to print the numbers of steps required to convert it to one by the following operations:

If ‘S’ is odd "add" 1 to it.
If ‘S’ is even divide it by 2.


Input: str = “101110”
Output: 8

	Number ‘101110’ is even, after dividing it by 2 we get an odd number ‘10111’ 
	so we will add 1 to it. Then we’ll get ‘11000’ which is even 
		and can be divide three times continuously in a row and get ’11’ which is odd, 
	adding 1 to it will give us ‘100’ which is even and can be divided 2 times in a row. 
	As, a result we get 1.
	So 8 times the above two operations were required in this number.


public class convertBinaryToZero {
    public static int convert(String s) {
        if (s == null) return -1;
        int ans = 0;
        // convert the str to binary int, which represented by an array
        int num = Integer.parseInt(s, 2);
        while (num != 1) {
            if ((num & 1) == 1) {
                num += 1;
            } else {
                num /= 2;
            }
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(convert("101110"));
    }
}




