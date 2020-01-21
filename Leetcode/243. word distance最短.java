243. Shortest Word Distance

["practice", "makes", "perfect", "coding", "makes"]

Input: word1 = “coding”, word2 = “practice”
Output: 3
Input: word1 = "makes", word2 = "coding"
Output: 1



//             0          1           2         3        4
//         ["practice", "makes", "perfect", "coding", "makes"]
// word1 -1                 1                             4
// word2 -2                                    3

We can greatly improve on the brute-force approach by keeping two indices i1 and i2 
we store the most recent locations of word1 and word2. 

Each time we find a new occurrence of one of the words, we do not need to search the entire array for the other word, since we already have the index of its most recent occurrence.


class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || words.length < 2) return 0;
        
        int index1 = -1, index2 = -1;
        int ans = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++) {
            String str = words[i];
            if (str.equals(word1)) {
                index1 = i;
                if (index2 != -1) {
                    int dist = Math.abs(index1 - index2);
                    ans = Math.min(ans, dist);
                }
            } else if (str.equals(word2)) {
                index2 = i;
                if (index1 != -1) {
                    int dist = Math.abs(index1 - index2);
                    ans = Math.min(ans, dist);
                }
            }   
        }
        return ans;
    }
}

O(n) O(1)








brute force

A naive solution to this problem is to go through the entire array looking for the first word. Every time we find an occurrence of the first word, we search the entire array for the closest occurrence of the second word


public int shortestDistance(String[] words, String word1, String word2) {
    int minDistance = words.length;
    for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1)) {
            for (int j = 0; j < words.length; j++) {
                if (words[j].equals(word2)) {
                    minDistance = Math.min(minDistance, Math.abs(i - j));
                }
            }
        }
    }
    return minDistance;
}

O(n^2) O(1)
