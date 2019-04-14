//4
//0 2 6 5
//2 0 4 4
//6 4 0 2
//5 4 2 0


import java.util.Scanner;

public class Main {
    public int n;
    public int[] x;
    public int[] bestx;
    public int bestc;
    public int cc;
    public int[][] a;

    public Main(int[][] aa, int nn) {
        n = nn;
        x = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            x[i] = i;
        }
        bestc = 1000;
        bestx = new int[n + 1];
        cc = 0;
        a = aa;

    }

    public void swap(int[] x, int i, int j) {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }

    public void backtrack(int i) {
        if (i == n) {
            if (a[x[n - 1]][x[n]] != -1 && a[x[n]][1] != -1 && (bestc == 1000) || cc + a[x[n - 1]][x[n]] + a[x[n]][1] < bestc) {
                for (int j = 1; j <= n; j++) {
                    bestx[j] = x[j];

                }
                bestc = cc + a[x[n - 1]][x[n]] + a[x[n]][1];
            }
        } else {
            for (int j = i; j <= n; j++) {
                if (a[x[i - 1]][x[j]] != -1 && (bestc == 1000 || cc + a[x[i - 1]][x[j]] < bestc)) {
                    swap(x, i, j);
                    cc += a[x[i - 1]][x[i]];
                    backtrack(i + 1);
                    cc -= a[x[i - 1]][x[i]];
                    swap(x, i, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[][] a = new int[n + 1][n + 1];
            for (int i = 0; i < n; i++) {
                a[i][0] = 0;
            }
            for (int i = 0; i < n; i++) {
                a[0][i] = 0;
            }
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    a[i][j] = sc.nextInt();
                }
            }
            Main b=new Main(a,n);
            b.backtrack(2);
            System.out.println(b.bestc);

        }

    }
}