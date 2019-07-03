273. Integer to English Words

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"


数组创建的方法
int[] num = num int[] {1, 2, 3};




class Solution {

    public static String[] IN_TWENTY = new String[] {
        "",
        "One",
        "Two",
        "Three",
        "Four",
        "Five",
        "Six",
        "Seven",
        "Eight",
        "Nine",
        "Ten",
        "Eleven",
        "Twelve",
        "Thirteen",
        "Fourteen",
        "Fifteen",
        "Sixteen",
        "Seventeen",
        "Eighteen",
        "Nineteen"
    };
    public static String[] IN_HUNDRED = new String[] {
        "",
        "Ten",
        "Twenty",
        "Thirty",
        "Forty",
        "Fifty",
        "Sixty",
        "Seventy",
        "Eighty",
        "Ninety"
    };
    public static String[] OVER_THOUSAND = new String[] {
        "",
        "Thousand",
        "Million",
        "Billion"
    };


    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String res = "";
        int i = 0;

        while (num > 0) {
            String prefix = helper(num % 1000);
            if (prefix != "") {
                res = prefix + " " + OVER_THOUSAND[i] + " "+ res;
            }
            num /= 1000;
            i++;
        }
        return res.trim();
    }

    public String helper(int n) {
        String s;
        if (n < 20) {
            s = IN_TWENTY[n];
        } else if (n < 100) {
            s = IN_HUNDRED[n / 10] + " " + IN_TWENTY[n % 10];
        } else {
            s = IN_TWENTY[n / 100] + " Hundred " + helper(n % 100);
        }
        return s.trim();                            //删除头尾空白符的字符串
    } 
}