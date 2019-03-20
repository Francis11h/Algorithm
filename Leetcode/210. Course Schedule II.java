210. Course Schedule II

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