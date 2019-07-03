340. Longest Substring with At Most K Distinct Characters


class Solution {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s == null || s.length() == 0) return 0;
		int ans = 0, left = 0, right = 0, distCount = 0;
		int[] count = new int[256];
		while (right < s.length()) {
			count[s.charAt(right)]++;
			if (count[s.charAt(right)] == 1) distCount++;


			while (distCount > k) {
				count[s.charAt(left)]--;
				if (count[s.charAt(left)] == 0) distCount--;
				left++;
			}

			ans = Math.max(ans, right - left + 1);
			right++;
		}
		return ans;
	}
}