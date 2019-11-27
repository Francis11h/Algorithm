126. Word Ladder II

Given two words (beginWord and endWord), and a dictionary‘s word list, 
find all shortest transformation sequence(s) from beginWord to endWord, 
such that:
	Only one letter can be changed at a time
	Each transformed word must exist in the word list. 
	Note that beginWord is not a transformed word.

All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.


Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]




BFS + DFS

BFS 求从 beginword 到 endword 最短距离, 经过哪些单词, 建个图, 同时记录离 beginword 的距离。
	 图怎么存 就是 Map<String, List<String>> 一个map每个拉一条串儿出去

DFS 求从 beginword 到 endword 有哪些路径




class Solution {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordList_set = new HashSet<>(wordList);
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();					//least steps from beginWord to a Word
        //corner case
        if (!wordList_set.contains(endWord)) return ans;
        //
        bfs(beginWord, endWord, wordList_set, graph, distance);
        //
        dfs(beginWord, endWord, 0, new ArrayList<>(Arrays.asList(beginWord)), graph, distance, ans);
        return ans;
    }

    // bfs 是为了 建立两个map 一个是路径 一个是距离
    public void bfs(String beginword, String endword, Set<String> wordList_set, Map<String, List<String>> graph, Map<String, Integer> distance) {
    	//initinalize graph
    	graph.put(beginword, new ArrayList<>());
    	for (String word : wordList_set) {
    		graph.put(word, new ArrayList<>());
    	}
    	//
    	Queue<String> queue = new LinkedList<>();
    	queue.offer(beginword);
    	distance.put(beginword, 0);
    	int step = 0;
    	boolean isReach = false;

    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		step++;
    		for (int i = 0; i < size; i++) {
    			String curWord = queue.poll();

    			for (String nextWord : getNextWords(curWord, wordList_set)) {
    				if (nextWord.equals(endword)) {
    					isReach = true;
    				}
    				//要把该nextWord加到图中curWord对应的那条List里面
    				graph.getOrDefault(curWord, new ArrayList<>()).add(nextWord);
    				//距离要算的是 beginWord到nextWord 的距离, 就是nextWord最早添加进map的时候计算, 所以每次添加前要判断map中之前知否有nextWord, 有的话代表之前可以用更少的步数走到
    				//也是每个单词只走一遍 不走回头路 所以 distance 这个map 还有去重（防止走回头路的作用）
    				if (!distance.containsKey(nextWord)) {
    					distance.put(nextWord, step);
    					queue.offer(nextWord);
    				}
    			}
    		}
    		if (isReach) {
    			break;
    		}
    	}
    }

    //bfs 做完 图有了 距离有了, 该组合打印了, 需要的参数 	1.开始位置 2.结束位置 3.现在cur 4.图 5.距离 6.结果
    public void dfs(String beginword, String endword, int step, List<String> cur, Map<String, List<String>> graph, Map<String, Integer> distance, List<List<String>> ans) {
    	//递归出口
    	if (cur.get(cur.size() - 1).equals(endword)) {
    		ans.add(new ArrayList<>(cur));
    		return;
    	}
    	for (String word : graph.get(beginword)) {
    		cur.add(word);
    		//
    		if (distance.get(word) == step + 1) {
    			dfs(word, endword, step + 1, cur, graph, distance, ans);
    		}
    		cur.remove(cur.size() - 1);
    	}
    }

    //helper function to find directions that a word can transform
    public List<String> getNextWords(String word, Set<String> wordList_set) {
    	List<String> nextWords = new ArrayList<>();

    	for (int i = 0; i < word.length(); i++) {
    		for (char ch = 'a'; ch <= 'z'; ch++) {
    			if (word.charAt(i) == ch) continue;

    			char[] temp = word.toCharArray();
    			temp[i] = ch;
    			String newWord = new String(temp);

    			if (wordList_set.contains(newWord)) {
    				nextWords.add(newWord);
    			}
    		}
    	}
    	return nextWords;
    }
}










new ArrayList<>(Arrays.asList(beginWord)) 解释

java.util包下的ArrayList的构造方法, 只有3种

1. public ArrayList(int initialCapacity)
2. public ArrayList()
3. public ArrayList(Collection<? extends E> c)
	创建ArrayList对象的时候，可以传递一个Collection类或者是其子类
	这个时候会将Collection的对象赋值给ArrayList, 长度为Collection的长度大小



例子

String[] str = new String[]{"1","2","3"};
ArrayList al = new ArrayList(Arrays.asList(str));	//将数组元素添加到集合的一种快捷方式



Arrays.asList() 是把数组转为 List
	
	String[] myArray = { "Apple", "Banana", "Orange" }； 
	List<String> myList = Arrays.asList(myArray);
	或者
	List<String> myList = Arrays.asList("Apple", "Orange");











迭代加深算法




public class Solution {
    
    //图
    Map<String, List<String>> graph = new HashMap<>();
    List<List<String>> ans = new ArrayList<>();
    //至少需要多少步，其下限
    Map<String, Integer> lb = new HashMap<>();
    
    // limit 层数限制是多少, x:即将做第几次变换，word ：当前即将要做变换的词
    private void dfs(int limit, int x, String word, String end, List<String> path) {
        //退出条件
        if (x == limit + 1) { //+1是因为 x是我即将要做的那一次变换，即前面limit次变换已经做完
            if (word.equals(end)) {
                //要深拷贝，因为原path值会不断变化
                ans.add(new ArrayList<>(path));
            }
            return;
        }
        
        //前面已经做了x-1步
        //再加上剩余的至少步数，还大于limit 直接剪枝
        if (x - 1 + lb.get(word) > limit) {
            return;
        }
        
        for (String next : graph.get(word)) {
            path.add(next);
            dfs(limit, x + 1, next, end, path);
            path.remove(path.size() - 1);
        }
        
        //让 lb动态更新。走到这里时，从word出发的所有的路已经试完了且还没找到结果的话
            // ans 是空 表示 从word出发还没没到达终点, word 已经走了 limit-(x-1) 步

        if (ans.isEmpty()) {
            // 已经走了limit-(x-1) 步 还没到终点，那么至少要走limit - (x - 1) + 1步
            //动态更新lb，下界不断提高！
            lb.put(word, Math.max(lb.get(word), limit - (x - 1) + 1));
        }
        
    }
    
    //找与每个word 连接的 且在字典中的所有边
    private List<String> getNext(String word, Set<String> dict) {
        List<String> res = new ArrayList<>();
        
        //改变单词每一位的个数
        for (int i = 0; i < word.length(); i++) {
            char[] sc = word.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                sc[i] = c;
                String next = String.valueOf(sc);
                if (dict.contains(next) && !word.equals(next)) {
                    res.add(next);
                }
            }
        }
        return res;
    }
    
    private int getDiff(String a, String b) {
        int ret = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                ret++;
            }
        }
        return ret;
    }
    
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        
        //建图的过程
        dict.add(start);
        dict.add(end);
        for (String word : dict) {
            graph.put(word, getNext(word, dict));
            lb.put(word, getDiff(word, end));
        }
        
        //加深，深度
        int limit = 0; 
        //path声明
        List<String> path = new ArrayList<>();
        path.add(start);
        
        
        while (ans.isEmpty()) {
            dfs(limit, 1, start, end, path); //即将做第一次变换
            limit++;
        }
        return ans;
    }
}













2019.11.22 错误的版本 根据 127 word Ladder 修改的 
该版本 只能返回一个 串儿 而不是 全部

错误在于 建图的时候 bfs 碰到最短的 路径 就直接 return 了 

这样子 graph 就建 的 不完全了 少了 "log" --> "cog" 这条本来该有的边
所以 不能    if (cur.equals(endword)) return true; 
            和
            for (String nei : getNextWords(cur, dict)) {
               if (!distance.containsKey(nei)) {
                    graph.get(cur).add(nei);
                    distance.put(nei, step);
                    queue.offer(nei);
                } 
            }  
        这两处 就不能这么写 

        因为 nei 边 应该可以被 多次加入 图中 所以 这里 不正确 应该 改为 

        在判断重复的上头 先加入边 
        for (String nei : getNextWords(cur, dict)) {
            // 应该在这 加 先加了边 再说 后面 dfs 再去重
            graph.get(cur).add(nei);
            if (!distance.containsKey(nei)) {
                distance.put(nei, step);
                queue.offer(nei);
            }
        }


        "dot" ----> "hot", "lot", "dog"  然后 后面再根据 dfs中的  if (distance.get(nei) + 1 == cur.size()) 再判断是。进下一层还是回溯







import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        findLadders(beginWord, endWord, wordList);
    }
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();

        if (bfs(beginWord, endWord, dict, graph, distance)) {
            dfs(beginWord, endWord, graph, distance, new ArrayList<>(Arrays.asList(beginWord)), ans);
        }
        return ans;
    }

    public static boolean bfs(String beginword, String endword, Set<String> dict, Map<String, List<String>> graph, Map<String, Integer> distance) {
        graph.put(beginword, new ArrayList<>());
        for (String word : dict) {
            graph.put(word, new ArrayList<>());
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginword);
        distance.put(beginword, 0);
        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(endword)) {
                    return true;
                }
                for (String nei : getNextWords(cur, dict)) {
                    if (!distance.containsKey(nei)) {
                        graph.get(cur).add(nei);
                        distance.put(nei, step);
                        queue.offer(nei);
                    }

                }
            }

        }
        return false;

    }

    private static  void dfs(String beginWord, String endWord, Map<String, List<String>> graph, Map<String, Integer> distance, List<String> cur, List<List<String>> ans) {
        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (String nei : graph.get(beginWord)) {
            cur.add(nei);
            if (distance.get(nei) + 1 == cur.size()) {
                dfs(nei, endWord, graph, distance, cur, ans);
            }
            cur.remove(cur.size() - 1);
        }
    }

    public static List<String> getNextWords(String word, Set<String> wordList_set) {
        List<String> nextWords = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (word.charAt(i) == ch) continue;

                char[] temp = word.toCharArray();
                temp[i] = ch;
                String newWord = new String(temp);

                if (wordList_set.contains(newWord)) {
                    nextWords.add(newWord);
                }
            }
        }
        return nextWords;
    }
}






按上述 修改后的 正确版本 

class Solution {
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();

        if (bfs(beginWord, endWord, dict, graph, distance)) {
            dfs(beginWord, endWord, graph, distance, new ArrayList<>(Arrays.asList(beginWord)), ans);
        }
        return ans;
    }

    public static boolean bfs(String beginword, String endword, Set<String> dict, Map<String, List<String>> graph, Map<String, Integer> distance) {
        graph.put(beginword, new ArrayList<>());
        for (String word : dict) {
            graph.put(word, new ArrayList<>());
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginword);
        distance.put(beginword, 1);
        int step = 1;
        boolean isReach = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (cur.equals(endword)) {
                    isReach = true;
                }
                for (String nei : getNextWords(cur, dict)) {
                    graph.get(cur).add(nei);
                    if (!distance.containsKey(nei)) {
                        distance.put(nei, step);
                        queue.offer(nei);
                    }

                }
            }
            if (isReach) {
                break;
            }
        }

        return isReach;
    }

    private static void dfs(String beginWord, String endWord, Map<String, List<String>> graph, Map<String, Integer> distance, List<String> cur, List<List<String>> ans) {
        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (String nei : graph.get(beginWord)) {
            cur.add(nei);
            if (distance.get(nei) == cur.size()) {
                dfs(nei, endWord, graph, distance, cur, ans);
            }
            cur.remove(cur.size() - 1);
        }
    }

    public static List<String> getNextWords(String word, Set<String> wordList_set) {
        List<String> nextWords = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (word.charAt(i) == ch) continue;

                char[] temp = word.toCharArray();
                temp[i] = ch;
                String newWord = new String(temp);

                if (wordList_set.contains(newWord)) {
                    nextWords.add(newWord);
                }
            }
        }
        return nextWords;
    }
}







2019 .11.27 又写一遍

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Set<String> dict = new HashSet<>(wordList);
        // first we need to calculate the minimum length ot the transformation by using bfs
        // bfs should return boolean to indicate whether there is a sequence
        // bfs also need to build a graph and a distance map, cause our dfs need to use them 
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        if (bfs(beginWord, endWord, dict, graph, distance)) {
            //then we can do dfs to generate all possible kinds of 
            dfs(beginWord, endWord, 0, new ArrayList<>(Arrays.asList(beginWord)), graph, distance, ans);
        }
        
        return ans;
    }
    
    // parameters : 1.startnode 2.endnode 3.graph to build 4.diatance to remove repeated traversal and record the node distance to the startnode 
    private boolean bfs(String beginWord, String endWord, Set<String> dict, Map<String, List<String>> graph, Map<String, Integer> distance) {
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        int step = -1;
        boolean isReach = false;
        
        // do the level traversal cause we need to calculate the smallest step
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                graph.put(cur, new ArrayList<>());
                for (String nei : getNextWords(cur, dict)) {
                    // build graph by adding edge that from cur to nei
                    graph.get(cur).add(nei);
                    if (nei.equals(endWord)) {
                        isReach = true;
                    }
                    // we need the minimum distance 
                    // So only record the distance when we first discover a node 
                    // only add the node to queue when we first discover a node --> bfs
                    if (!distance.containsKey(nei)) {
                        distance.put(nei, step + 1);
                        queue.offer(nei);
                    }
                }
                // if in this level we reached, we can break cause we just need the minimum step
                if (isReach) break;
            }
            
        }
        return isReach;
    }
    
    // parameters : 1.startnode 2.endnode 3. current step 4 current 5.graph 6.distance 7.ans
    private void dfs(String beginWord, String endWord, int step, List<String> cur, Map<String, List<String>> graph, Map<String, Integer> distance, List<List<String>> ans) {
        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<>(cur));
            return;
        }
        for (String nei : graph.get(beginWord)) {
            cur.add(nei);
            if (distance.get(nei) == step + 1) {        
                // only the distance of nei to origin is current step + 1, go into next level dfs
                dfs(nei, endWord, step + 1, cur, graph, distance, ans);
            }
            cur.remove(cur.size() - 1);
        }
    }
   
    
    private List<String> getNextWords(String word, Set<String> dict) {
        List<String> ans = new ArrayList<>();

        if (word == null || word.length() == 0) return ans;
        // for each word, every char
        for (int i = 0; i < word.length(); i++) {
            char[] array = word.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (word.charAt(i) == ch) continue;
                array[i] = ch;
                String now = String.valueOf(array);
                if (dict.contains(now)) {
                    ans.add(now);
                }
            }
        }
        return ans;
    }
}

T: O (list.length * wordLength * 25 + )

DFS: I suppose the time complexity of DFS part depends on how many shortest paths that can start from beginWord and ends at endWord.
    O(bfs:  n*25*L + dfs: V+E 点边只走一次)