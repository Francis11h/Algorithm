149. Max Points on a Line

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.

Example 1:

Input: [[1,1],[2,2],[3,3]]
Output: 3
Explanation:
^
|
|        o
|     o
|  o  
+------------->
0  1  2  3  4
Example 2:

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
Explanation:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6





数学法 三点共线 O(n^3)

(y2 - y1)(x3 - x1) == (y3 - y1)(x2 - x1)
y2x3 - y1x3 - y2x1 + y1x1 == y3x2 - y1x2 - y3x2 + y1x1
y2x3 - y1x3 - y2x1 == y3x2 - y1x2 - y3x2
x1*y2 + x2*y3 + x3*y1 - x3*y2 - x2*y1 - x1*y3 == 0


class Solution {
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) return 0;
        if (points.length <= 2) return points.length;
        
        int ans = 0;
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int count = 0;
                int[] o = points[i], a = points[j];
                long x1 = o[0], y1 = o[1];
                long x2 = a[0], y2 = a[1];
                for (int k = 0; k < points.length; k++) {
                    long x3 = points[k][0], y3 = points[k][1];
                    if (x1*y2 + x2*y3 + x3*y1 - x3*y2 - x2*y1 - x1*y3 == 0) {
                        count++;
                    }
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}
 
注意 要都弄成 Long    否则 [[0,0],[1,65536],[65536,0]] 会越界 2147483647

但是有 bug

Input
    [[84,250],[0,0],[1,0],"[0,-70],[0,-70]",[1,-1],[21,10],[42,90],[-42,-230]]
Output
    9
Expected
    6

原因 
    [0,-70],[0,-70] 两个 重复的点 会导致 三点一线变成两点一线 就需要在第二个for的时候判断是不是 两个确定斜率的点是一个点 

改进
class Solution {
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0 || points[0].length == 0) return 0;
        if (points.length <= 2) return points.length;
        
        int ans = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int duplicate = 1;
            for (int j = i + 1; j < points.length; j++) {
                int count = 0;
                int[] o = points[i], a = points[j];
                long x1 = o[0], y1 = o[1];
                long x2 = a[0], y2 = a[1];
                if (x1 == x2 && y1 == y2) {
                    duplicate++;                //这里就是精髓 
                    continue;
                }
                for (int k = 0; k < points.length; k++) {
                    long x3 = points[k][0], y3 = points[k][1];
                    if (x1*y2 + x2*y3 + x3*y1 - x3*y2 - x2*y1 - x1*y3 == 0) {
                        count++;
                    }
                }
                ans = Math.max(ans, count);
            }
            ans = Math.max(ans, duplicate);
        }
        return ans;
    }
}





Map存个斜率然后搞 O(n^2)

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {
        if(points.length <= 0) return 0;
        if(points.length <= 2) return points.length;
        int result = 0;
        for(int i = 0; i < points.length; i++){
            HashMap<Double, Integer> hm = new HashMap<Double, Integer>();
            int samex = 1;
            int samep = 0;
            for(int j = 0; j < points.length; j++){
                if(j != i){
                    if((points[j].x == points[i].x) && (points[j].y == points[i].y)){
                        samep++;
                    }
                    if(points[j].x == points[i].x){
                        samex++;
                        continue;
                    }
                    double k = (double)(points[j].y - points[i].y) / (double)(points[j].x - points[i].x);
                    if(hm.containsKey(k)){
                        hm.put(k,hm.get(k) + 1);
                    }else{
                        hm.put(k, 2);
                    }
                    result = Math.max(result, hm.get(k) + samep);
                }
            }
            result = Math.max(result, samex);
        }
        return result;
    }
}
