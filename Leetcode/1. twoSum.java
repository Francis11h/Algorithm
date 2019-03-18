Given nums = [2, 7, 11, 15], target = 9;
Beacuse the num[0] + num[1] = 2 + 7 = 9, return [0, 1].
input array may not sorted

// [2, 7, 11, 15] target = 9
// 2 + 7 = 9 		return [0, 1]

// [2, 7, 11, 15] target = 22
// 2 + 7 = 9
// 2 + 11 = 13
// 2 + 15 = 17
// 7 + 11 = 18
// 7 + 15 = 22		 return[1, 3]

//brute force  O(n^2)
import java.util.Arrays;
class Solution {
	public static int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length - 1; i++) {		//we only want to go to the second last value
			for (int j = i + 1; j < nums.length; j++) {	// j begins at i+1 and end at the last value
				if (nums[i] + nums[j] == target) {
					return new int[] {i, j};
				}
			}
		}
		return new int[] {-1, -1};
	}

	public static void main(String[] args) {
		int[] ans = twoSum(new int[]{2, 7, 11, 15}, 22);
		System.out.println(Arrays.toString(ans)); //所以数组转为字符串应写成 Arrays.toString()
	}
}





// [2, 7, 11, 15] target = 22
// x + 7 = 22, x = 22 - 7 = 15

// HM:(20,0) (15,1) (11,2) (7,3)


// Hashmap. O(n)
class Solution {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(nums[i])) {
				return new int[]{map.get(nums[i]), i};
			} else {
				map.put(target - nums[i], i);
			}
		}
		return new int[]{-1, -1};
	}
}


