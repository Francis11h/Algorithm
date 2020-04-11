
常见的 1快速排序、2归并排序、3堆排序、4冒泡排序 5插入排序 6选择排序 等属于比较排序。
在排序的最终结果里，"元素之间的次序依赖于它们之间的比较"。每个数都必须和其他数进行比较，才能确定自己的位置。

在冒泡排序之类的排序中，问题规模为n，又因为需要比较n次，所以平均时间复杂度为"O(n²)"。
在归并排序、快速排序之类的排序中，问题规模通过"分治法"消减为logN次，所以时间复杂度平均"O(nlogn)"。

比较排序的优势是，适用于各种规模的数据，也不在乎数据的分布，都能进行排序。可以说，比较排序适用于一切需要排序的情况。

-----------------------------------------------------------------------------------------------------  
7计数排序、8基数排序、9桶排序 则属于非比较排序。
非比较排序是"通过确定每个元素之前，应该有多少个元素"来排序。

针对数组arr，计算arr[i]之前有多少个元素，则唯一确定了arr[i]在排序后数组中的位置。
非比较排序只要确定每个元素之前的已有的元素个数即可，所有一次遍历即可解决。算法时间复杂度"O(n)"。

非比较排序时间复杂度底，但由于非比较排序需要占用空间来确定唯一位置。所以对数据规模和数据分布有一定的要求。



动图演示
https://www.cnblogs.com/guoyaohua/p/8600214.html

----------------------  
1. 冒泡排序 Bubble Sort
----------------------







----------------------  
9. 桶排序（Bucket Sort）
----------------------  
桶排序的基本思想是：把数组 arr 划分为n个大小相同子区间（桶），每个子区间各自排序，最后合并。


步骤:
人为设置一个BucketSize，作为每个桶所能放置多少个不同数值
（例如当BucketSize==5时，该桶可以存放｛1,2,3,4,5｝这几种数字，但是容量不限，即可以存放100个3）
遍历输入数据，并且把数据一个一个放到对应的桶里去；
对每个不是空的桶进行排序，可以使用其它排序方法，也可以递归使用桶排序；
从不是空的桶里把排好序的数据拼接起来


桶排序最好情况下使用线性时间O(n)，桶排序的时间复杂度，取决与对各个桶之间数据进行排序的时间复杂度，因为其它部分的时间复杂度都为O(n)。
很显然，桶划分的越小，各个桶之间的数据越少，排序所用的时间也会越少。但相应的空间消耗就会增大。 

最佳情况：T(n) = O(n+k) k是桶内单拍用的时间 基本当成线性



// 1.找出待排序数组中的最大值max、最小值min
// 2.我们使用 动态数组ArrayList 作为桶，桶里放的元素也用 ArrayList 存储。 桶的数量为(max-min)/arr.length+1
// 3.遍历数组 arr，计算每个元素 arr[i] 放的桶
// 4.每个桶各自排序
// 5.遍历桶数组，把排序好的元素放进输出数组

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {
    public static void bucketSort(int[] arr){

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        //桶数
        int bucketNum = (max - min) / arr.length + 1;
        List<List<Integer>> bucketArr = new ArrayList<>(bucketNum);

        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<>());
        }
        //将每个元素放入桶
        for(int i = 0; i < arr.length; i++){
            int num = (arr[i] - min) / (arr.length);
            bucketArr.get(num).add(arr[i]);
        }
        //对每个桶进行排序 可以用快排
        for(int i = 0; i < bucketArr.size(); i++){
            Collections.sort(bucketArr.get(i));
        }
        System.out.println(bucketArr.toString());

    }
    public static void main(String[] args) {
        bucketSort(new int[] {2, 5, 6, 7, 9, 10, 1, 3, 6, 4});
    }
}






-----------------  

-----------------  




-----------------  

-----------------  



-----------------  

-----------------  