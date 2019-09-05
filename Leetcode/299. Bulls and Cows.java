299. Bulls and Cows

错误解法


    
input       "1122"
            "1222"
output      "3A1B"
expected    "3A0B"

总归是 有问题 一个hash表记录 就是 记不全。。。因为 不在一个位置 真的有 许多种

class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            char A = secret.charAt(i);
            map.put(A, map.getOrDefault(A, 0) + 1);
        }
        for (int i = 0; i < secret.length(); i++) {
            char A = secret.charAt(i), B = guess.charAt(i);
            if (A == B) {
                bulls++;
                map.put(B, map.get(B) - 1);
            } else {
                if (map.containsKey(B) && map.get(B) > 0) {     // 就这个条件 完全无法确定
                    cows++;
                    map.put(B, map.get(B) - 1);
                }
            }
        }
        return bulls + "A" + cows + "B";
    }
}


改进 只能用 2个 来分别记
只有不一样的时候记 并且 第二遍pass 时 取secret 对应的 map 中 有的key 


class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();


        for (int i = 0; i < secret.length(); i++) {
            char A = secret.charAt(i), B = guess.charAt(i);
            if (A == B) {
                bulls++;
            } else {
                map1.put(A, map1.getOrDefault(A, 0) + 1);
                map2.put(B, map2.getOrDefault(B, 0) + 1);
            }
        }

        for (char temp : map1.keySet()) {
            int cnt1 = map1.get(temp);
            int cnt2 = map2.containsKey(temp) ? map2.get(temp) : 0;
            cows += Math.min(cnt1, cnt2);
        }
        return bulls + "A" + cows + "B";
    }
}


map可以用 int[10] 来代替 因为只有数字


class Solution {
    public String getHint(String secret, String guess) {
        int bulls = 0, cows = 0;
        int[] nums1 = new int[10];
        int[] nums2 = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            char A = secret.charAt(i), B = guess.charAt(i);
            if (A == B) {
                bulls++;
            } else {
                nums1[A - '0']++;
                nums2[B - '0']++;
            }
        }

        for (int i = 0; i < 10; i++) {
            cows += Math.min(nums1[i], nums2[i]);
        }
        return bulls + "A" + cows + "B";
    }
}



但是 我们可以再优化 和0 比较 
two pass 可以优化为 one pass


这里 明天再写 很牛逼！




