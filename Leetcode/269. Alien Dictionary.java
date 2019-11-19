269. Alien Dictionary

class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        int[] indegree = new int[26];
        Map<Character, Set<Character>> graph = buildGraph(words, indegree);
        return bfs(graph, indegree);
    }
    
    private Map<Character, Set<Character>> buildGraph(String[] words, int[] indegree) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        
        // n nodes n-1 edges
        for (int i = 0; i < words.length - 1; i++) {
            String from = words[i], to = words[i + 1];
            int len = Math.min(from.length(), to.length());
            
            for (int j = 0; j < len; j++) {
                char a = from.charAt(j), b = to.charAt(j);
                if (a != b) {
                    if (!graph.containsKey(a)) {
                        graph.put(a, new HashSet<>());
                    }
                    if (!graph.containsKey(b)) {
                        graph.put(b, new HashSet<>());
                    }
                    graph.get(a).add(b);
                    indegree[b - 'a']++;
                    break;
                }

            }
        }
        
        return graph;
    }
    
    private String bfs(Map<Character, Set<Character>> graph, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        
        for (int i= 0; i < indegree.length; i++) {
            char ch = (char)('a' + i);
            if (graph.containsKey(ch) && indegree[i] == 0) {
                queue.add(ch);
            }
        }
        
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            sb.append(ch);
            for (Character to : graph.get(ch)) {
                indegree[to - 'a']--;
                if (indegree[to - 'a'] == 0) {
                    queue.offer(to);
                }
            }
        }
        return sb.length() == graph.size() ? sb.toString() : "";
    }
}






Input["z","z"]
Output""
Expected"z"

bug 没有把所有的 在 words 里 出现的 char 都放入graph

修改之后 还有bug


class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        int[] indegree = new int[26];
        Map<Character, Set<Character>> graph = buildGraph(words, indegree);
        return bfs(graph, indegree);
    }
    
    private Map<Character, Set<Character>> buildGraph(String[] words, int[] indegree) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!graph.containsKey(c)) 
                    graph.put(c, new HashSet<>());
            }
        }
        
        // n nodes n-1 edges
        for (int i = 0; i < words.length - 1; i++) {
            String from = words[i], to = words[i + 1];
            int len = Math.min(from.length(), to.length());
            
            for (int j = 0; j < len; j++) {
                char a = from.charAt(j), b = to.charAt(j);
                if (a != b) {
                    graph.get(a).add(b);
                    indegree[b - 'a']++;
                    break;
                }
            }
        }
        
        return graph;
    }
    
    private String bfs(Map<Character, Set<Character>> graph, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        
        for (int i= 0; i < indegree.length; i++) {
            char ch = (char)('a' + i);
            if (graph.containsKey(ch) && indegree[i] == 0) {
                queue.add(ch);
            }
        }
        
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            sb.append(ch);
            for (Character to : graph.get(ch)) {
                indegree[to - 'a']--;
                if (indegree[to - 'a'] == 0) {
                    queue.offer(to);
                }
            }
        }
        return sb.length() == graph.size() ? sb.toString() : "";
    }
}

Input
["za","zb","ca","cb"]
Output
""
Expected
"abzc"


由于 重复出现 a->b 
 导致 indegree[b - 'a'] 被加了两次 这是重复的。。 去重


class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        int[] indegree = new int[26];
        Map<Character, Set<Character>> graph = buildGraph(words, indegree);
        return bfs(graph, indegree);
    }
    
    private Map<Character, Set<Character>> buildGraph(String[] words, int[] indegree) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!graph.containsKey(c)) 
                    graph.put(c, new HashSet<>());
            }
        }
        
        // n nodes n-1 edges
        for (int i = 0; i < words.length - 1; i++) {
            String from = words[i], to = words[i + 1];
            int len = Math.min(from.length(), to.length());
            
            for (int j = 0; j < len; j++) {
                char a = from.charAt(j), b = to.charAt(j);
                if (a != b) {
                    if (!graph.get(a).contains(b)) {
                        graph.get(a).add(b);
                        indegree[b - 'a']++;
                    }
                    break;
                }
            }
        }
        
        return graph;
    }
    
    private String bfs(Map<Character, Set<Character>> graph, int[] indegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        
        for (int i= 0; i < indegree.length; i++) {
            char ch = (char)('a' + i);
            if (graph.containsKey(ch) && indegree[i] == 0) {
                queue.add(ch);
            }
        }
        
        while (!queue.isEmpty()) {
            char ch = queue.poll();
            sb.append(ch);
            for (Character to : graph.get(ch)) {
                indegree[to - 'a']--;
                if (indegree[to - 'a'] == 0) {
                    queue.offer(to);
                }
            }
        }
        return sb.length() == graph.size() ? sb.toString() : "";
    }
}




S: O()
T: O







dfs beats 100%

class Solution {
    private final int N = 26;
    public String alienOrder(String[] words) {
        boolean[][] adj = new boolean[N][N];
        int[] visited = new int[N];
        buildGraph(words, adj, visited);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            if(visited[i] == 0) {                 // unvisited
                if(!dfs(adj, visited, sb, i)) return "";
            }
        }
        return sb.reverse().toString();
    }

    public boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
        visited[i] = 1;                            // 1 = visiting
        for(int j = 0; j < N; j++) {
            if(adj[i][j]) {                        // connected
                if(visited[j] == 1) return false;  // 1 => 1, cycle   
                if(visited[j] == 0) {              // 0 = unvisited
                    if(!dfs(adj, visited, sb, j)) return false;
                }
            }
        }
        visited[i] = 2;                           // 2 = visited
        sb.append((char) (i + 'a'));
        return true;
    }

    public void buildGraph(String[] words, boolean[][] adj, int[] visited) {
        Arrays.fill(visited, -1);                 // -1 = not even existed
        for(int i = 0; i < words.length; i++) {
            for(char c : words[i].toCharArray()) visited[c - 'a'] = 0;
            if(i > 0) {
                String w1 = words[i - 1], w2 = words[i];
                int len = Math.min(w1.length(), w2.length());
                for(int j = 0; j < len; j++) {
                    char c1 = w1.charAt(j), c2 = w2.charAt(j);
                    if(c1 != c2) {
                        adj[c1 - 'a'][c2 - 'a'] = true;
                        break;
                    }
                }
            }
        }
    }
}