import java.util.*;

public class BuildOffice {
    /*
     * Complete the 'findMinDistance' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER w
     *  2. INTEGER h
     *  3. INTEGER n
     */
    static int[] dx=new int[]{1,-1,0,0};
    static int[] dy=new int[]{0,0,-1,1};

    public static int findMinDistance(int w, int h, int n) {
        int[][] grid=new int[w][h];
        for(int i=0;i<w;i++){
            Arrays.fill(grid[i],-1);
        }
        return solve(n,w,h,0,0,grid);
    }

    static int bfs(int w,int h,int grid[][]){
        int[][] dist=new int[w][h];
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                dist[i][j]=grid[i][j];
            }
        }
        int maxDist=0;
        Queue<Location> q=new LinkedList<>();
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                if(dist[i][j]==0){
                    q.add(new Location(i,j));
                }
            }
        }
        while(!q.isEmpty()){
            int x=q.peek().first;
            int y=q.peek().second;
            maxDist=Math.max(maxDist,dist[x][y]);
            q.poll();
            for(int d=0;d<4;d++){
                int newX=x+dx[d];
                int newY=y+dy[d];
                if(newX>=w||newY>=h||newX<0||newY<0)
                    continue;
                if(dist[newX][newY]==-1){
                    dist[newX][newY]=dist[x][y]+1;
                    q.add(new Location(newX,newY));
                }
            }
        }
        return maxDist;
    }

    static int solve(int left,int w,int h,int row,int col,int[][]grid){
        if(left==0)
            return bfs(w,h,grid);
        int r=row,c=col;
        if(col>=h){
            r+=col/h;
            c=col%h;
        }
        int minDist=Integer.MAX_VALUE;
        for(int i=r;i<w;i++){
            for(int j=c;j<h;j++){
                grid[i][j]=0;
                int val=solve(left-1,w,h,i,j+1,grid);
                minDist=Math.min(minDist,val);
                grid[i][j]=-1;
            }
        }
        return minDist;
    }

    static class Location{
        int first,second;
        public Location(int a,int b){
            this.first=a;
            this.second=b;
        }
    }

    public static void main(String[] args) {
        System.out.println(findMinDistance(4,4,3));
        System.out.println(findMinDistance(3,3,2));
    }
}