210. Course Schedule II

//临接表 表示出来图 5ms

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList[] graph = new ArrayList[numCourses];
        int[] indegree = new int[numCourses];
        int[] ans = new int[numCourses];

        //initialize the graph
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<Integer>();
        }

        // find the # of each course's pre and build the graph 
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        //find the course without pre
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int index = 0;
        //remove these course and find the new course with 0 indegree
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            ans[index++] = tmp;
            int n = graph[tmp].size();
            for (int k = 0; k < n; k++) {
                int i = (int) graph[tmp].get(k);        //object -> int
                indegree[i]--;
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        return (index == numCourses) ? ans : new int[0];    //new empty array
    }
}
Time 
O(V+E)
Space
O(V+E)



//不把图表示出来   34ms 反而慢
// 慢的原因是 每次while 要遍历一遍prerequisites，这个可能很长，冗余
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        int[] ans = new int[numCourses];

        // find the # of each course's pre and build the graph 
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        //find the course without pre
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int index = 0;
        //remove these course and find the new course with 0 indegree
        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            ans[index++] = tmp;

            for (int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == tmp) {   //有 从tmp指向i的边 => tmp是i的先修课
                    indegree[prerequisites[i][0]]--;
                    if (indegree[prerequisites[i][0]] == 0) {
                        queue.offer(prerequisites[i][0]);
                    }
                }
            }
        }
        return (index == numCourses) ? ans : new int[0];    //new empty array
    }
}

Time complexity: O(V^2)
outer for loop for each course: O(V)
inner for loop for all the neighbors by visiting the adjacency list O(V): at most connect to V - 1 courses
O(V*(V - 1)) = O(V^2)

Space complexity: O(V)
