//merge Sort
public class Solution {
    public void sortIntegers2(int[] A) {
        if (A == null || A.length == 0) return;
        helper(A, 0, A.length - 1);
    }

    public void helper(int[] A, int left, int right) {
        if (left >= right) {        //递归出口
            return;
        }

        int mid = left + (right - left) / 2;

        helper(A, left, mid);                   //先拆分成小问题
        helper(A, mid + 1, right);

        //merge
        int[] tmp = new int[right - left + 1];      //新数组存
        int i = left, j = mid + 1;

        for (int k = 0; k < right - left + 1; k++) {        //双指针
            if (i <= mid && j > right || i <= mid && A[i] <= A[j]) {
                tmp[k] = A[i++];
            } else {
                tmp[k] = A[j++];
            }
        }

        for (int k = 0; k < right - left + 1; k++) {    //赋值到原数组
            A[left + k] = tmp[k];
        }
    }
}