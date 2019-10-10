1029. Two City Scheduling

There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0], and the cost of flying the i-th person to city B is costs[i][1].

Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.


Input: [[10,20],[30,200],[400,50],[30,20]]
Output: 110
Explanation: 
The first person goes to city A for a cost of 10.
The second person goes to city A for a cost of 30.
The third person goes to city B for a cost of 50.
The fourth person goes to city B for a cost of 20.

The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.


It is guaranteed that costs.length is even.



核心 greedy 要找 总共的最小 怎么局部最优 ？
    找 绝对值差值最大的 这个的优先级就靠前 然后取这个之中的最小的


class Solution {
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int[] b) {
                return (Math.abs(b[0] - b[1]) - Math.abs(a[0] - a[1]));
            }
        });
        
        int A = 0, B = 0;
        int ans = 0;
        for (int[] cost : costs) {
            if (Math.max(A, B) < costs.length / 2) {
                if (cost[0] <= cost[1]) {
                    ans += cost[0];
                    A++;
                } else {
                    ans += cost[1];
                    B++;
                }
            } else {
                ans += A < B ? cost[0] : cost[1];
            }
        }
        return ans;
    }
}




// [[10,20], [30,20], [30,200], [400,50]]

//  10     10      170     350
     
     
// 50         20
//     30         10


// [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]

//     511        394      259        45       722     108
     

//    B  118  54  667
     
//    A  259  577 184     


    
    
follow up what if the costs.length is not guaranteed to be even?

