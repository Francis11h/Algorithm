anagrams

找anagram有多少对
给一个array,问其中有多少个pair是digit anagram的
digit anagram的定义是 可以用过变换a中数字的位置得到b

给你一个int型list，形如[25,35,212,281,53,182,821]

返回有多少对anagram
上述有4对 {1,4},{3,5},{3,6},{5,6}


类似于 group anagrams leetcode
但是不同的是里面是数字不是String
例如 25 和 52 就是一组的
让你求最终有多少个pair
    思路就是求出每组有m个
    然后遍历 res += m(m-1)/2



281 可以对 182 和 821

我的思路 还是要 求 每组的 所有可能 就是 先想办法 把每一组规定一种形式 可以代表整个组 

class Solution {

    public static int countPairs(int[] nums) {
        Map<String, List<Integer>> map = new HashMap<>();
        for(int num : nums) {
            int temp = num;
            int[] count = new int[10];
            while (temp != 0) {
                count[temp % 10]++;
                temp /= 10;
            }
            String key = Arrays.toString(count);
            List<Integer> list = map.getOrDefault(key, new LinkedList<>());
            list.add(num);
            map.put(key, list);
        }
        int ans = 0;
        for (String key : map.keySet()) {
            int len = map.get(key).size();
            ans += len * (len - 1) / 2;
        }
        return ans;
    }
}


本题类似 

49. Group Anagrams
用一个 key 来表示 一组 数字 是关键 









这个是 必须要 满足 反转 即 281 一定要对  182

class Solution {

    public static int countPairs(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (set.contians(nums[i])) ans++;
            else set.put(reverse(nums[i]));
        }
        return ans;
    }

    public static int reverse(int num) {
        int ans = 0;
        while (num != 0) {
            int digit = num % 10;
            num /= 10;

            if (ans > Integer.MAX_VALUE / 10) return 0;
            if (ans < Integer.MIN_VALUE / 10) return 0;
            //if (num < Integer.MIN_VALUE / 10) return 0; 这么写不行 test case : 1534236469 要反转  反转之后的数不能越界 所以要用ans判断 ： 乘10  不能越界

            ans = ans * 10 + digit;
        }
        return ans;
    }
}















