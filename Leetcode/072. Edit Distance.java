72. Edit Distance


Let following be the function definition :

f(i, j) := minimum cost (or steps) required to convert first i characters of word1 to first j characters of word2

Case 1: word1[i] == word2[j], i.e. the ith the jth character matches.
    f(i, j) = f(i - 1, j - 1)

Case 2: word1[i] != word2[j], then we must either insert, delete or replace, whichever is cheaper
    f(i, j) = 1 + min { f(i, j - 1), 
                        f(i - 1, j), 
                        f(i - 1, j - 1) 
                      }

    f(i, j - 1)     represents insert operation
    f(i - 1, j)     represents delete operation
    f(i - 1, j - 1) represents replace operation

Here, we consider any operation from word1 to word2. 
It means, when we say insert operation, we insert a new character after word1 that matches the jth character of word2. 
So, now have to match i characters of word1 to j - 1 characters of word2. 
Same goes for other 2 operations as well.


Base Case:
    f(0, k) = f(k, 0) = k

result
    f(m, n);



import java.util.*;
public class Main {
    public static int shortestDistance(String A, String B) {
        int m = A.length(), n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            dp[i][0] = i;
        for (int j = 0; j <= n; j++)
            dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]),dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String A = sc.next();
            String B = sc.next();
            System.out.println(shortestDistance(A, B));
        }
    }
}
