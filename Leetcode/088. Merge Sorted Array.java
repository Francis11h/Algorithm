88. Merge Sorted Array

Given two sorted integer arrays nums1 and nums2, 
merge nums2 into nums1 as one sorted array.

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] < nums2[j]) {
                nums1[i + j + 1] = nums2[j];
                j--;
            } else {
                nums1[i + j + 1] = nums1[i];
                i--;
            }
        }
        while (j >= 0) {        //if all num in nums1 has been moved to end, put nums2 one by one. 
            nums1[i + j + 1] = nums2[j];
            j--;
        }
        //if all num in nums2 has been moved, do nothing and exit the loop
    }
}



T : O(m + n)
S : O(1)



//思路：两个指针都从最后开始往前扫，维护一个k=i+j+1是插入位置，相当于把两个数组中的数全插一遍到第一个数组
class Solution{
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = i + j + 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] <= nums2[j]) nums1[k--] = nums2[j--];
            else nums1[k--] = nums1[i--];
        }
        while (i < j) {
            nums1[k--] = nums2[j--];             
        }
    }
}

