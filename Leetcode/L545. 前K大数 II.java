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
    int now;                        //放堆的数组的目前元素个数 
    int size;                       // heap size
    
    public Solution(int k) {
        A = new int[k];
        now = 0;
        this.size = k;
    }

    
    public void add(int num) {
        if (now < size) {                       //判断k个元素的堆 满没满
            offer(num);
        } else if (num > A[0]) {
            poll();
            offer(num);
        }
    }

    private void offer(int num) {
        A[now] = num;                   //n是A这个数组的容量，A[n-1]是其原有的最后一个，A[n]代表新来的一个
        int k = now;
        now++;
        while (k > 0) {                     //k is the child's index when it not reach the top
            int parent = (k - 1) / 2;
            if (A[k] > A[parent]) break;
            int temp = A[k];
            A[k] = A[parent];
            A[parent] = temp;
            k = parent;                             //它指向他的父亲节点，，再次上移
        }
    }
    
    private void poll() {
        A[0] = A[now - 1];                          //把末尾元素的值保存到堆顶
        now--;                                      //删除末尾元素（即是删除堆顶元素，因为上一步堆顶元素的值已经删了）
        int k = 0;                                  //要下移 元素的下标 ： 0   。 即是堆顶元素
        while ((2 * k + 1) < now) {     //if the node has child
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int minIndex = left;
            if (right < now && A[right] < A[left]) minIndex = right;       
            if (A[minIndex] >= A[k]) break;
            int temp = A[minIndex];
            A[minIndex] = A[k];
            A[k] = temp;
            k = minIndex;
        }
    }
    
    public List<Integer> topk() {
        List<Integer> ans = new LinkedList<>();
        for (int i = 0; i < now; i++) {                     //这里的n应该就代表堆的大小，因为前面不足size的时候是n
            ans.add(A[i]);                                  //足了size 一个poll操作 n--， 一个offer操作n++，n就是size了
        }   
        Collections.sort(ans, Collections.reverseOrder());  //用java自带 Collections.sort 并反转 Collections.reversrOrder()
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