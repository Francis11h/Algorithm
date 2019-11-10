Two Sum - Unique Pairs

Given an int array nums and an int target, 
	find how many Unique-Pairs in the array such that their sum is equal to target. 
	Return the number of pairs.


Input: nums = [1, 1, 2, 45, 46, 46], target = 47
Output: 2
Explanation:
1 + 46 = 47
2 + 45 = 47


Input: nums = [1, 1], target = 2
Output: 1
Explanation:
1 + 1 = 2


Input: nums = [1, 5, 1, 5], target = 6
Output: 1
Explanation:
[1, 5] and [5, 1] are considered the same.





import java.util.Arrays;

public class TwoSumUniquePairs {
    public static int twoSumUniquePairs(int[] nums, int target) {
        if (nums == null) return 0;
        Arrays.sort(nums);
        int count = 0;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] +  nums[right] < target) {
                left++;
            }
            if (left < right && nums[left] + nums[right] == target) {
                count++;
                left++;
                right--;
                while (left < right && nums[left] == nums[left - 1]) left++;
                while (left < right && nums[right] == nums[right + 1]) right--;
                continue;			//这里必须要加
            }
            right--;
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] nums1 = new int[] {1, 1, 2, 45, 46, 46};
        int[] nums2 = new int[] {1, 5, 1, 5, 3, 4, 2, 6};
//        System.out.println(twoSumUniquePairs(nums1, 47));
        System.out.println(twoSumUniquePairs(nums2, 7));
    }
}








// Java O(nlogn)
public static int getUniquePairs(int[] nums, int target){
    Arrays.sort(nums);
    int i = 0;
    int j = nums.length-1;
    int ans = 0;
    while (i < j){
        int sum = nums[i]+ nums[j];
        if (sum < target){
            i++;
        } else if (sum > target){
            j--;
        } else {
            ans++;
            i++;
            j--;
            while (i < j && nums[i] == nums[i-1]){
                i++;
            }
            while (i < j && nums[j] == nums[j+1]){
                j--;
            }
        }
    }
    return ans;
}




