13. sum of string Add Strings
给两串字符串
每个char就是一个digit
然后从后往前加起来
把结果放到一个字符串输出
'99' + '99' = '1818' 

如果写Java的话最好用StringBuilder, String 会 TLE

从后往前 遍历 然后 处理 carry

class Solution {
    public String addStrings(String nums1, String nums2) {
        int i = nums1.length() - 1, j = nums2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        
        while (i >= 0 || j >= 0 || carry != 0) {
            if (i >= 0) carry += nums1.charAt(i--) - '0';
            if (j >= 0) carry += nums2.charAt(j--) - '0';
            sb.append(carry % 10);
            carry /= 10;
        } 
        return sb.reverse().toString();
    }
}