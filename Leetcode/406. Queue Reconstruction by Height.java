406. Queue Reconstruction by Height

假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

输入:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

输出:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]


class Solution {
    public int[][] reconstructQueue(int[][] people) {
        
        Arrays.sort(people, new Comparator<int[]>(){
           @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0])
                    return b[0] - a[0];
                else
                    return a[1] - b[1];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] cur : people) {
            list.add(cur[1], cur);
        }
        return list.toArray(new int[people.length][]);       
    }
}

 
// sort first and then insert
// what order to insert?  insert at where? 
// First insert the highest or the shortest?
// ----> k means k people in front of the persion h >= his height
//        so if we insert the people at position k we should control the latter can't affect the previous 

// 身高低的人无法影响身高高的人的k值，即使身高低的人插入到了比它高的人的前面
// 所以 我们先插高的 再插低的 这样子 后插的就不会影响先插的k
// 如果身高相同 先插k值小的 因为插入的位置 是 第k位 后插k大的 的也不会影响前面的k 

// greedy 
// sorted [[7,0],[7,1],[6,1],[5,0],[5,2],[4,4]]

//[[7,0]]
//[[7,0], [7,1]]
//[[7,0], [6,1], [7,1]]
//[[5,0], [7,0], [6,1], [7,1]]
//[[5,0], [7,0], [5,2], [6,1], [7,1]]
//[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]


// new Comparator<这里面必须得声明类型>(){}

// list to array for two dimensional
// list.toArray(new int[这里得把多少行定义了][])




// T O(N^2) insert n times and each at worst O(n)
// S O(N)
