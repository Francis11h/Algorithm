coolFeature

a,b 两个 array, 一个query array
query 有两种type
1.  第一种是[target]查从a中取一个数b中取一个数, 求加起来等于target的情况有多少种
2.  第二种query是[index, num], 把b中在index位置的数字改成num， 这种query不需要输出
最后输出所有第一种query的result

Give three array a, b and query. 

This one is hard to explain. Just read the example.
Input:
a = [1, 2, 3]
b = [3, 4]
query = [[1, 5], [1, 1, 1], [1, 5]]
Output:
[2, 1]

Explain:
    Just ignore every first element in sub array in query.
    So we will get a new query like this query = [[5], [1, 1], [5]]
    Only record the result when meet the single number in new query array.

    And the rule of record is find the sum of the single number.
    The example above is 5 = 1 + 4 and 5 = 2 + 3, there are two result.

    So currently the output is [2]

    When we meet the array length is larger than 1, such as [1, 1]. 
    That means we will replace the b[x] = y, x is the first element, y is second element. 
    So in this example, the b will be modify like this b = [1, 4]

    And finally, we meet the [5] again. 
    So we will find sum again. 
    This time the result is 5 = 1 + 4.
    So currently the output is [2, 1]

    note: Donot have to modify the query array, just ignore the first element.






import java.util.*;

public class Test {

    public static void main(String[] args) {
        int[] A = new int[] {1, 2, 3};
        int[] B = new int[] {3, 4};
        int[][] query = new int[][] {{1, 5}, {1, 1, 1}, {1, 5}};

        System.out.println(helper(A, B, query).toString());

    }

    public static List<Integer> helper(int[] A, int[] B, int[][] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = 0;
            if (nums[i].length == 2) {
                for (int j = 0; j < A.length; j++) {
                    for (int k = 0; k < B.length; k++) {
                        if (A[j] + B[k] == nums[i][1]) cur++;
                    }
                }
            } else if (nums[i].length == 3) {
                int index = nums[i][1];
                int val = nums[i][2];
                B[index] = val;
            }
            if (cur != 0) {
                ans.add(cur);
            }
        }
        return ans;
    }

}



