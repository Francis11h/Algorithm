
780. Reaching Points




First line:
if 2 target points are still bigger than 2 starting point, we reduce target points.
Second line:
check if we reduce target points to (x, y+kx) or (x+ky, y)

class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty) {
            if (tx > ty) 
                tx %= ty;
            else
                ty %= tx;
        }
        return sx == tx && sy <= ty && (ty - sy) % sx == 0 ||
               sy == ty && sx <= tx && (tx - sx) % sy == 0;
    }
}


https://leetcode.com/problems/reaching-points/discuss/230588/Easy-to-understand-diagram-and-recursive-solution