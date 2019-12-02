Course Schedule
# of courses labeled from 0 to n

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] matrix = new int[numCourses][numCourses];   //建邻接矩阵是为了找到对应的边，pre->ready, 后面减入度时要判断
        int[] indegree = new int[numCourses];
        //第一步 找到入度
        for (int i = 0; i < prerequisites.length; i++) {
            int ready = prerequisites[i][0];
            int pre = prerequisites[i][1];
            if (matrix[pre][ready] == 0) {      //防止 prerequisites 有重复
                indegree[ready]++;
            }
            matrix[pre][ready] = 1;             //建边
        }
        Queue<Integer> queue = new LinkedList<>();
        //加入 入度 为0的点
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        //能修的门数（能出队列 则代表能修）
        int count = 0;
        //删入度为0的，加新的入度为0的
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            for (int i = 0; i < indegree.length; i++) {
                if (matrix[course][i] != 0) {   //该课程 得是 i的先修课
                    indegree[i]--;              //i的入度（先修课门数减一）
                    if (indegree[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }
        return count == numCourses;
    }
}

Time O(n ^ 2)
space O(n ^ 2)



// 用临接表（本题临接数组即可）表示 省空间 （数组 套 数组 表示图）
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List[] graph = new ArrayList[numCourses];   //建大小为V的主“表” 这里 知道了 有多少个 vertex 直接建 固定大小的 array
        int[] indegree = new int[numCourses];
        //初始化临接表
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        //第一步 找到入度
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);    //每个V链出去一串儿E
        }

        Queue<Integer> queue = new LinkedList<>();
        //加入 入度 为0的点
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        //能修的门数（能出队列 则代表能修）
        int count = 0;
        //删入度为0的，加新的入度为0的
        while (!queue.isEmpty()) {
            int course = queue.poll();
            count++;
            int n = graph[course].size();
            for (int k = 0; k < n; k++) {
                int i = (int)graph[course].get(k);
                indegree[i]--;              //i的入度（先修课门数减一）
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }
    return count == numCourses;
    }
}
Time O(V+E)
space O(V+E)




最新的解法 2019.11.19


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] pre : prerequisites) {
            int from = pre[1], to = pre[0];
            // add edge for our directed graph
            // there are no duplicate edges in the input prerequisites.
            graph[from].add(to);
            indegree[to]++;
        }
        // do bfs
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int from = queue.poll();
            count++;
            for (int k = 0; k < graph[from].size(); k++) {
                int to = (int) graph[from].get(k);              //这里要注意下 ArrayList里面存的是 Integer 要转变成 int
                indegree[to]--;
                if (indegree[to] == 0) {
                    queue.offer(to);
                }
            }
        }
        return count == numCourses;
    } 
}

vertex : numCourses = V
edge : prerequisites.length = E

S: O(2V + E)     List-Array(V) + indegree(V) +  total Adjacency-Lists(E)

T: O(2V + 2E)    buildGraph(V + E) + bfs(V + E)




2019.12.2 List<Integer>[] graph 建图 就不用 再 Object 转 int 了 目前最优解

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] pre : prerequisites) {
            int from = pre[1], to = pre[0];
            // add edge for our directed graph
            // there are no duplicate edges in the input prerequisites.
            graph[from].add(to);
            indegree[to]++;
        }
        // do bfs
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int from = queue.poll();
            count++;
            for (int k = 0; k < graph[from].size(); k++) {
                int to = graph[from].get(k);
                indegree[to]--;
                if (indegree[to] == 0) {
                    queue.offer(to);
                }
            }
        }
        return count == numCourses;
    } 
}
