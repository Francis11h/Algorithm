L545. 前K大数 II


实现一个数据结构，提供下面两个接口
1.add(number) 添加一个元素
2.topk() 返回前K大的数

样例
样例1

输入: 
s = new Solution(3);
s.add(3)
s.add(10)
s.topk()
s.add(1000)
s.add(-99)
s.topk()
s.add(4)
s.topk()
s.add(100)
s.topk()
		
输出: 
[10, 3]
[1000, 10, 3]
[1000, 10, 4]
[1000, 100, 10]












直接用 pq做 本来可以 但是 没什么意思 不如我们直接用一个数组 来实现堆化等一系列操作


public class Solution {
    int[] A;
    int n = 0; //放堆的数组的目前元素个数 
    int size = 0; // heap size

    public Solution(int k) {
        A = new int[k];
        size = k;
    }

    public void add(int num) { //add a new number in the data structure
        if (n < size) { //判断k个元素的堆 满没满
            offer(num);
        } else if (num > A[0]) {
            poll();
            offer(num);
        }
    }

    public void offer(int num) { //往堆中插入元素
        A[n] = num; //n是A这个数组的容量，A[n-1]是其原有的最后一个，A[n]代表新来的一个
        int k = n;
        n++;// n还代表了 当前 整个数组元素的个数
        while (k > 0) { //上移动作
            int i = (k - 1) / 2;;//找到其父亲节点
            if (A[k] > A[i]) break;
            
            int tmp = A[i];
            A[i] = A[k];
            A[k] = tmp;
            
            k = i;          //它指向他的父亲节点，，再次上移
        }
    }

    public void poll() { //删除堆顶元素
        A[0] = A[n - 1]; //把末尾元素的值保存到堆顶
        n--; //删除末尾元素（即是删除堆顶元素，因为上一步堆顶元素的值已经删了）
        //堆化了该，就是 还要维持小跟堆的结构，就把新堆顶 shiftdown
        int k = 0; //要下移 元素的下标 ： 0   。 即是堆顶元素
        while (2 * k + 1 < n) {//k节点还是父亲节点的时候才进行
            int left = 2 * k + 1;
            int right = left + 1;
            int minIndex = k;
            if (left <= n && A[minIndex] > A[left]) {
                minIndex = left;
            }
            if (right <= n && A[minIndex] > A[right]) {
                minIndex = right;
            }
            if (minIndex == k) break;
            int tmp = A[minIndex];
            A[minIndex] = A[k];
            A[k] = tmp;
            k = minIndex;
        }
    }

    public List<Integer> topk() { //return the top k largest numbers in this data structure
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) { //这里的n应该就代表堆的大小，因为前面不足size的时候是n
                                        //足了size 一个poll操作 n--， 一个offer操作n++，n就是size了
            ans.add(A[i]);
        }
        Collections.sort(ans, Collections.reverseOrder());
        //本来不用排序的，但是题目要，就用java自带的 
        // Collections 是给list排序。
        //Arrays.sort是给定长数组，
        return ans;
    }

}





直接用pq做法

public class Solution {
    private Queue<Integer> pq;
    private int k;

    public Solution(int k) {
        pq = new PriorityQueue<>();
        this.k = k;
    }
    
    public void add(int num) {
        pq.offer(num);
        while (pq.size() > k) {
            pq.poll();
        }
    }

    public List<Integer> topk() {
        List<Integer> result = new ArrayList<>(pq);
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
}