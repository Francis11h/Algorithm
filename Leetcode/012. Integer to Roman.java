12. Integer to Roman

Input: 3
Output: "III"

Input: 9
Output: "IX"

Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.



范围 只有 1 - 3999 

每一位都可以分别表示

1. 千位只能 0-3
2. 百位 0-9
3. 十位 0-9
4. 各位 0-9
依次对应的字符串找出来 4个 数组一建 就完事了


class Solution {
    public String intToRoman(int n) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[n / 1000] + C[(n / 100) % 10] + X[(n / 10) % 10] + I[n % 10];
    }
}



class Solution {
    public String intToRoman(int num) {

        String[] M = new String[] {"", "M", "MM", "MMM"};   //几千 0-3
        String[] C = new String[] {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}; //几百 0-9
        String[] X = new String[] {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}; //几十 0-9
        String[] I = new String[] {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}; //几 0-9

        return M[num / 1000] + C[(num % 1000) / 100] +  
        X[(num % 100) / 10] + I[num % 10];
    }
}