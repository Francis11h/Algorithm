longestEqualSubArray


int fun(int[] a), a 由 1 和 0 组成. 
求 0,1 个数相同的subarray 最大长度.
​​​​​​​​​​​​​​​​​​​


int[] array = {0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1};

前缀和 做


    public static void main(String[] args) {
        int[] array = {0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1};
        System.out.println(longestEqualSubArray(array));
    }

    public static int longestEqualSubArray(int[] a) {
        for(int i = 0; i < a.length; i++) {
            if(a[i] == 0) a[i] = -1;
        }

        int[] prefixSum = new int[a.length + 1];

        for(int i = 0; i < a.length; i++) {
            prefixSum[i+1] = prefixSum[i] + a[i];
        }

        int res = 0;
        for(int i = 1; i <= a.length; i++) {
            for(int j = 0; j < i; j++) {
                if(prefixSum[i] == prefixSum[j]) {
                    res = Math.max(res, i - j);
                }
            }
        }
        return res;
    }




