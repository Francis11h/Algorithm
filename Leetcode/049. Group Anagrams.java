49. Group Anagrams

Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

建一个数组 来计数

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            int[] count = new int[256];
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                count[ch]++;
            }
            String key = Arrays.toString(count);
            if (!map.containsKey(key)) {
                map.put(key, new LinkedList<>());
            }
            map.get(key).add(str);
        }
        List<List<String>> ans = new ArrayList<>();
        for (String key : map.keySet()) {
            ans.add(map.get(key));
        }
        
        return ans;
    }
}

java.util.Arrays.toString(int[]) method returns a string representation of the contents of the specified int array. 
The string representation consists of a list of the array‘s elements, enclosed in square brackets ("[]"). 
Adjacent elements are separated by the characters ", " (a comma followed by a space).

The array is:
Number = 33
Number = 12
Number = 98
The string representation of array is:
[33, 12, 98]





直接把字符串 转换为数组 并排序该数组 记一个排好序的key

public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<>();
        
        for (String str : strs) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            String keyStr = String.valueOf(s);
            // String keyStr = Arrays.toString(s); 	
            if (!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<String>());
            }
            map.get(keyStr).add(str);
        }
        return new ArrayList<List<String>>(map.values());
    }
}



is there any difference between String.valueOf(Object) and Object.toString() ?
 
还有个 很重要的区别 跟数组里面是什么 有很大的关系 
是个坑。。。。

public class Test {

    public static void main(String[] args) {
        int[] test = {1,2,3};
        char[] test2 = {'a','b','c'};
        Integer[] test3 = {1,2,3};

        System.out.println("Arrays.toString(int整形数组)："+Arrays.toString(test));
        System.out.println("String.valueOf(int整形数组):"+String.valueOf(test));
        System.out.println("test.toString():"+test.toString());
        System.out.println("\n");

        System.out.println("Arrays.toString(字符数组):"+Arrays.toString(test2));
        System.out.println("String.valueOf(字符数组):"+String.valueOf(test2));
        System.out.println("test2.toString():"+test2.toString());
        System.out.println("\n");

        System.out.println("Arrays.toString(Integer数组):"+Arrays.toString(test3));
        System.out.println("String.valueOf(Integer数组):"+String.valueOf(test3));
        System.out.println("test2.toString():"+test3.toString());
    }
}

Arrays.toString(int整形数组)：[1, 2, 3]
String.valueOf(int整形数组):[I@2503dbd3					//int[] 打的是 地址 而不是 123
test.toString():[I@2503dbd3


Arrays.toString(字符数组):[a, b, c]
String.valueOf(字符数组):abc 								//char[] 打的是 字符的集合 而不是 地址
test2.toString():[C@4b67cf4d


Arrays.toString(Integer数组):[1, 2, 3]
String.valueOf(Integer数组):[Ljava.lang.Integer;@7ea987ac
test3.toString():[Ljava.lang.Integer;@7ea987ac








基本没区别 就是 String.valueOf 处理 null的时候 不会抛异常

String str = null;
System.out.println("String.valueOf gives : " + String.valueOf(str));//Prints null
System.out.println("String.toString gives : " + str.toString());//This would throw a NullPointerExeption


