查询矩阵中最小值
想象 n * m 的矩阵 M[i,j] = (i+1) * (j+1)
给一系列query，三种类型

一种是输出最小值
一种是disable某一列
一种是disable某一行

// priority_queue + set 可以做
// 每次看下现在最小的i和j是多少
// 判断有没有被disable
// 有的话就pop了重新看最小的

两个int n, m表示一个board这个board上每个点位的值都是index相乘，
index从一开始记比如最左上角的点index为1,
1. 然后给一个list，list里含多个list如果为[0]，则输出当前board上处于激活状态下最小的值，
如果为[1, i]则将row i上面所有的值deactive，
如果为[2, j]则将所有column j上的值deactive。
所有deactive的值不会被reactive。
最后输出的内容为每次给的list里面为[0]时输出的最小值的array。


TreeMap 可以做 因为 key 有序 



    public static List<Integer> findMin(int m, int n, int[][] queries) {
        TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
        int[][] matrix = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                matrix[i][j] = (i + 1) * (j + 1);
                map.put(matrix[i][j], map.getOrDefault(matrix[i][j], 0) + 1);
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int[] query : queries) {
            if(query.length == 1) {
                for(int key : map.keySet()) {
                    if(map.get(key) != 0) {
                        res.add(key);
                        break;
                    }
                }
            } else if (query.length == 2) {
                // consider the row
                if(query[0] == 1) {
                    int row = query[1];
                    for(int j = 0; j < n; j++) {
                        if(matrix[row][j] != -1 && map.containsKey(matrix[row][j])) {
                            map.put(matrix[row][j], map.get(matrix[row][j]) - 1);
                            matrix[row][j] = -1;
                        }
                    }
                } else {
                    int col = query[1];
                    for(int i = 0; i < m; i++) {
                        if(matrix[i][col] != -1 && map.containsKey(matrix[i][col])) {
                            map.put(matrix[i][col], map.get(matrix[i][col]) - 1);
                            matrix[i][col] = -1;
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void print2D(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
