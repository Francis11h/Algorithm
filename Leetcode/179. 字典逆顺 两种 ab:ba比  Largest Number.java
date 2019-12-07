179. Largest Number

https://zhuanlan.zhihu.com/p/93757835

首先，我们将每个整数变成字符串。
然后进行排序。如果仅按降序排序，有相同的开头数字的时候会出现问题。
比方说，样例 2 按降序排序得到的数字是 95343303，然而交换 3 和 30 的位置可以得到正确答案 9534330。
因此，"每一对数在排序的比较过程"中，我们"比较两种连接顺序哪一种更好"。


我们可以证明这样的做法是正确的：假设（不是一般性），某一对整数 a 和 b，
我们的比较结果是 a 应该在 b 前面，这意味着 a ⌢ b > b ⌢ a，其中 ⌢ 表示连接。
如果排序结果是错的，说明存在一个 c，b 在 c 前面且 c 在 a 的前面。
这产生了矛盾，因为 a ⌢ b > b ⌢ a 和 b ⌢ c > c ⌢ b 意味着 a ⌢ c > c ⌢ a。

换言之，我们的自定义比较方法保证了传递性，所以这样子排序是对的。
一旦数组排好了序，最“重要”的数字会在最前面。



有一个需要注意的情况是如果数组只包含 0，我们直接返回结果 0 即可。
否则，我们用排好序的数组形成一个字符串并返回。



错误代码

class Solution {
    // we can sort the num by reversed lexicographical order
    public String largestNumber(int[] nums) {
        // but we need compare them digit by digit, so we need to convert num to string
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++){
            strs[i] = String.valueOf(nums[i]);
        }
        // Sort strings according to custom comparator.
        Arrays.sort(strs, new NumberComparator());
        
        StringBuilder sb = new StringBuilder();
        for(String str : strs) {
            sb.append(str);
        }
        
        return sb.toString();
    }
    
    private class NumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return b.compareTo(a);
        }
    }
}


Input
    [3,30,34,5,9]
Output
    "9534303"
Expected
    "9534330"

[3 30], 
按正常 reversed lexicographical order 排序 303
但是 330 > 303 所以不能仅仅按这个排序

但是 这怎么解决呢？？？

一个神奇的方式！！！！！

两个可能的顺序 都比较下 就行了



改进 比较器后 两个顺序都比就行了

class Solution {
    // we can sort the num by reversed lexicographical order
    // [34, 5, 9] ---> [9, 5, 34]
    public String largestNumber(int[] nums) {
        // but we need compare them digit by digit, so we need to convert num to string
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++){
            strs[i] = String.valueOf(nums[i]);
        }
        // Sort strings according to custom comparator.
        Arrays.sort(strs, new NumberComparator());
        
        StringBuilder sb = new StringBuilder();
        for(String str : strs) {
            sb.append(str);
        }
        if (sb.charAt(0) == '0') return "0";  //corner case Input[0,0]Output"00"Expected"0"
        return sb.toString();
    }
    
    private class NumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
            return order2.compareTo(order1);
        }
    }
}



另一种写法 直接写Comparator 
class Solution {
    // we can sort the num by reversed lexicographical order
    // [34, 5, 9] ---> [9, 5, 34]
    public String largestNumber(int[] nums) {
        // but we need compare them digit by digit, so we need to convert num to string
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++){
            strs[i] = String.valueOf(nums[i]);
        }
        // Sort strings according to custom comparator.
        Arrays.sort(strs, new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                String order1 = a + b;
                String order2 = b + a;
                return order2.compareTo(order1);
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for(String str : strs) {
            sb.append(str);
        }
        if (sb.charAt(0) == '0') return "0";  //corner case Input[0,0]Output"00"Expected"0"
        return sb.toString();
    }
}




