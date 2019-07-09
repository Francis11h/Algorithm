11. Container With Most Water

给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

你不能倾斜容器，且 n 的值至少为 2


输入: [1,8,6,2,5,4,8,3,7]
输出: 49



    设置两指针i,j分别位于容器壁两端，逐渐向中间收缩并记录最大值；

    每次选定围成水槽两板height[i], height[j]中较小的对应指针，向中间收缩，这是因为：

    水槽的高度由两板中的短板决定，每次收缩，都会导致水槽底边宽度-1，

    因此，若想找到比当前最大值更大的水槽，那么水槽的短板高必须要高于上一个水槽短板高，而只有向内移动短板，有可能达成这一条件.

    若移动长板，下个水槽的面积一定小于当前水槽面积

    扩展一下：若要找面积最小的水槽，直接找高度height最小的板子，并且宽度为1即可。




class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1, maxArea = 0;
        while (left < right) {
            int leftHeight = height[left], rightHeight = height[right];
            if (leftHeight <= rightHeight) {                                // 相等不影响, 因为短边决定, 看底下几个 test case 都符合
                maxArea = Math.max(maxArea, (right - left) * leftHeight);
                left++;
            } else if (leftHeight > rightHeight) {
                maxArea = Math.max(maxArea, (right - left) * rightHeight);
                right--;
            } 
        }
        return maxArea;
    }
}



[2, 10 ,8, 2]

[2, 8, 10, 2]

[5, 1, 2, 5]






