6.	Find the most common elements in a list.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        int[] A = new int[]{2, 2, 3, 3, 5};
        System.out.println(helper(A));
    }

    public static List<Integer> helper(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int maxCount = 0;
        for (Integer val : map.values()) {
            maxCount = Math.max(maxCount, val);
        }
        for (Integer key : map.keySet()) {
            if (map.get(key) == maxCount) {
                ans.add(key);
            }
        }
        return ans;
    }
}
