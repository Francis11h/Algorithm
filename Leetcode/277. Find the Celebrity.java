277. Find the Celebrity

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

public class Solution extends Relation {
    public int findCelebrity(int n) {
        int candidate = 0;
        // first pass to find possible candidate 
        // if A knows B, A can't be the celebrity
        // if A don't know B, B can't be the celebrity
        for (int i = 0; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }
        // verify the candidate
        for (int i = 0; i < n; i++) {
            //know any other or any other doesnot know him return
            if ((i != candidate && knows(candidate, i)) || !knows(i, candidate))
                return -1;
        }
        return candidate;
    }
}

O(N)

Two pass
