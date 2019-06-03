

Method 1
brute force
Sorting the all points by their distance to the origin point directly
O(NlogN)
pro: short, easy to implement
con: 1.not very efficient and 2.have to know all of the points previously, so it is unable to 
                                deal with real-time(onine) case, it is an off-line solution.
        
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>(){
            public int compare(int[] a, int [] b) {
                return a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1]; 
            }
        });
        return Arrays.copyOfRange(points, 0, K);
    }
} 



Method 2
O(NlogK)
ad: this solution can deal with real-time(online) stream data. It doesnot have to know the size of data previously.
dis: it is not the most efficient solution


class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]; 
            }
        });

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }

        int[][] result = new int[K][2];
        while(K > 0) {
            result[--K] = maxHeap.poll();
            //这里写 K-- 不行，因为要先减
        }
        return result;
    }
}

Method 3
quickSelect solution
O(N)
each iteartion we choose a pivot and then find the position P the pivot should be.
Then we compare P with K, if the P < K,meaning the all element on the left of the pivot are all proper candidates but it is not adequate, we have to do the same thing on right side, and vice versa
If the p is exactly equal to the K, meaning that we've found the K-th position. Therefore, we just return the first K elements, since they are not greater than the pivot.

ad: 快
disadvatage :of this solution are it is neither an online solution nor a stable one. 
    And the K elements closest are not sorted in ascending order. 最接近的K个元素不按升序排序




class Solution {
    public int[][] kClosest(int[][] points, int K) {
        int len = points.length, l = 0, r = len - 1;
        while (l < r) {
            int mid = helper(points, l, r);
            //mid下标 的意义是 mid下标之前都比 points[mid]小, 后面的都比它大
            if (mid == K - 1) break;
            if (mid < K - 1) {      //如果不够再后面再找几个
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }
    //做一次helper 因为pivot取得是A[l] 所以结果 =  比A[l]小的数 + A[l] + 比A[l]大的数
    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            //右边找比p小的
            while (l < r && compare(A[r],pivot) >= 0) r--;
            A[l] = A[r];
            //左边找到比p大的
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;   
        return l;       //返回的是Pivot最后落在的下标，此时 l == r == pivot下标
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }
}
















