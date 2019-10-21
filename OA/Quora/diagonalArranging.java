diagonal arranging
给一个char[][] matrix，按对角线取字符组成字符串，并排序
input：[ ['b','b'],
         ['c','a']]



按对角线从左下角开始：
1.cc (只有一个c，补齐到两位为cc)
2.ba
3.bb(只有一个b，补齐到两位为bb)

排序：
2.ba
3.bb
1.cc

output [2,3,1]

我的思路是：
按要求 按对角线 提取出各个string
保存在一个hashmap里
key 对应 string, value 对应 List<Integer>
再对keyset排序
最后输出









给一个n*n 的字符数组，由字母组成，按对角线拿数，并且每个长度都为n（不够从头来取）然后返回它们字典排序后对应的位置
eg,  matrix:
c,b
a,a

从左下往右上读（对应1~2n-1）
s1 = aa（n为2但是只有一个数，所以重复读了一个a）
s2 = ca
s3 = bb（n为2但是只有一个数，所以重复读了一个b）
排序后 [aa,bb,ca]
返回[1,3,2]
sol: 做的时候没有想到更优的解法，就做成翻译题了

用List来存每个字符串，然后分成 1~n -1 n~2n-1 来读
然后用hashMap<String, List<Integer>> 来保存，因为可能会有重复的字符串，这里起点为1
然后对hashmap的keyset排序，最后遍历keyset找出对应的integer加入ans中



Collections.sort(keyset)
for(keyset)
for(int j : hashmap.get(keyset))
ans = j
i++






import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
//        char[][] matrix = new char[][] {{'c', 'b'}, {'a', 'a'}};
        char[][] matrix = new char[][] {{'b', 'b'}, {'c', 'a'}};
        char[][] matrix1 = new char[][] {{'c', 'b', 'a'}, {'a', 'b', 'c'}, {'c', 'a', 'b'}};

        System.out.println(diagonalArranging(matrix));
    }

    public static List<Integer> diagonalArranging(char[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        int n = matrix.length;

        List<String> list = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();

        int diff = n - 1;
        while (diff >= 0) {
            StringBuilder str = new StringBuilder();
            int total = n;
            while (total > 0) {
                for (int i = diff; i <= n - 1; i++) {
                    if (total == 0) break;
                    char ch = matrix[i][i - diff];
                    str.append(ch);
                    total--;
                }
            }
            list.add(str.toString());
            diff--;
        }

        while (diff > -n) {
            StringBuilder str = new StringBuilder();
            int total = n;
            while (total > 0) {
                for (int i = 0; i <= n - 1 + diff; i++) {
                    if (total == 0) break;
                    char ch = matrix[i][i - diff];
                    str.append(ch);
                    total--;
                }
            }
            list.add(str.toString());
            diff--;
        }


        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (!map.containsKey(str)) {
                map.put(str, new ArrayList<>());
            }
            map.get(str).add(i + 1);
        }

        List<String> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList);

        for (String key : keyList) {
            for (int index : map.get(key)) {
                ans.add(index);
            }
        }
        return ans;
    }

}
















































