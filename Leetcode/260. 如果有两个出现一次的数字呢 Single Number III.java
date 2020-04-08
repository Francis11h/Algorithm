260. 如果有两个出现一次的数字呢 Single Number III



拆分 把这两个数 分别拆分到一个数组 然后 同 136 Single Number I


怎么拆分？ 

由于A，B肯定是不相等的，因此在二进制上必定有一位是不同的。根据这一位是0还是1可以将A，B分开到A组和B组。
而这个数组中其它数字要么就属于A组，要么就属于B组。

而要判断A，B在哪一位上不相同，只要根据A异或B的结果就可以知道了，这个结果在二进制上为1的位就说明A，B在这一位上是不相同的。


int a[] = {1, 1, 3, 5, 2, 2}
整个数组异或的结果为3^5，即 0x0011 ^ 0x0101 = 0x0110

对0x0110，第1位（由低向高，从0开始）就是1。因此整个数组根据第1位是0还是1分成两组。

a[0] =1  0x0001  第一组

a[1] =1  0x0001  第一组

a[2] =3  0x0011  第二组

a[3] =5  0x0101  第一组

a[4] =2  0x0010  第二组

a[5] =2  0x0010  第二组

第一组有{1,1,5}，第二组有{3,2,3}，然后对这二组分别执行“异或”解法就可以得到5和3了。








class Solution {
	public int[] singleNumber(int[] nums) {
		int[] ans = new int[2];
		if (nums == null || nums.length < 2) return ans;

		int xorRes = 0;
		for (int num : nums) xorRes ^= num;

		int temp = 1;
		while (true) {
			if ((xorRes & 1) == 1) {
				break;
			}
			temp <<= 1;
			xorRes >>= 1;				// 右移，从低到高
		}

		for (int num : nums) {
			if ((num & temp) == 0) ans[0] ^= num;		// 对应位是 0
			else ans[1] ^= num;
		}
		return ans;
	}
}





