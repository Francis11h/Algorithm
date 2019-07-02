726. Number of Atoms 化学式原子数量

Given a chemical formula (given as a string), return the count of each atom.

输出按字典序

Input: 
formula = "H2O"
Output: "H2O"
Explanation: 
The count of elements are {'H': 2, 'O': 1}.

Input: 
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: 
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.

Input: 
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: 
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.





分析: 大写Char, 小写Char, 左括号, 右括号, 数字.

一个 完整的表达式 一定以 英文大写 or 左括号开头
当遍历到下一个字符是大写字母 or 左括号时 一个完整的 子表达式 结束。
--->> 分治 策略 : 依次求解一个长串中的子表达式, 子表达式再分解出 子子表达式, 直到最后分解的表达式中没有括号为止
然后 合并 一层层往上走再。

//把上面的想法 化成代码 框架是这样子的
class Solution {
    public String countOfAtoms(String formula) {
        Map<String, Integer> map = calculateParenthesis(1, formula);
        String result = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            
        }
        return result;
    }

    public static Map<String, Integer> calculateParenthesis(Integer multipler, String formula) {
        //这个不是返回值, 仅仅是有多个map 然后要list<Map<>>记录, 最后会全部遍历 放入新的map
        List<Map<String, Integer>> list = new ArrayList<>();
        //i 的意义 : 目前处理到字符串的第几位下标
        int i = 0;
        //仅处理 英文大写 or 左括号, 因为只有这两个才可能作为某个元素的开头
        while (i < formula.length()) {
            if (formula.charAt(i).equals('(')) {

            } else if (Character.isUpperCase(formula.charAt(i))) {  //大写字母开头 
                
            }
        }
        //遍历完 可能会出来很多个 map, 此时要合并他们 到一个 key按 字典序排列的resultMap
        Map<String, Integer> resultMap = new TreeMap<>(new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        //遍历list中的多个map, 把每个map中所有的<key, value>都拿出来, 还要乘倍数
        for (Map<String, Integer> map : list) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (resultMap.getKey(key) != null) {
                    resultMap.put(key, value * multipler + resultMap.getKey(key));
                } else {
                    resultMap.put(key, value * multipler);
                }
            }
        }
        return resultMap;
    }
}





完整的实现

class Solution {
    public static String countOfAtoms(String formula) {
        Map<String, Integer> map = calculateParenthesis(1, formula);
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                result.append(entry.getKey());
            } else {
                result.append(entry.getKey()).append(entry.getValue());        //String + Integer
            }
        }
        return result.toString();
    }

    public static Map<String, Integer> calculateParenthesis(Integer multipler, String formula) {
        //这个不是返回值, 仅仅是有多个map 然后要list<Map<>>记录, 最后会全部遍历 放入新的map
        List<Map<String, Integer>> list = new LinkedList<>();
        //i 的意义 : 目前处理到字符串的第几位下标
        int i = 0;
        //仅处理 英文大写 or 左括号, 因为只有这两个才可能作为某个元素的开头
        while (i < formula.length()) {
            ////左括号开头, 要递归 一层套一层, 后写
            if (String.valueOf(formula.charAt(i)).equals("(")) {
                //balance 表示余着几个左括号
                int balance = 1;
                int j = i + 1;
                while (j < formula.length() && balance > 0) {
                    if (String.valueOf(formula.charAt(j)).equals("(")) {
                        balance++;
                    } else if (String.valueOf(formula.charAt(j)).equals(")")) {
                        balance--;
                    }
                    j++;
                }
                //str 是 除了最边上左括号右括号 的里面的 substring
                String str = formula.substring(i+1, j-1);
                Integer count = 0;
                if (j < formula.length() && Character.isDigit(formula.charAt(j))) {
                    int k = j + 1;
                    while (k < formula.length() && Character.isDigit(formula.charAt(k))) {
                        k++;
                    }
                    count += Integer.valueOf(formula.substring(j, k));
                    i = k;
                } else {
                    count += 1;
                    i = j;
                }
                list.add(calculateParenthesis(count, str));
            } else if (Character.isUpperCase(formula.charAt(i))) {  //大写字母开头 不需要递归 先写这里
                Map<String, Integer> map = new HashMap<>();
                // 用j表示的是atom结束的位置; i是atom开始的位置,需要保留,因为后面substring要用
                int j = i + 1;
                //取一个原子, 如果一大写后面跟很多小写, 则看作是一个atom
                while (j < formula.length() && Character.isLowerCase(formula.charAt(j))) {
                    j++;
                }
                String atom = formula.substring(i, j);
                // 疑问 不应该是永远是 null？
                Integer count = map.get(atom);
                if (count == null) count = 0;
                //看后面跟着的数字是几位
                if (j < formula.length() && Character.isDigit(formula.charAt(j))) {
                    int k = j + 1;       //k表示 数字结束位置
                    while(k < formula.length() && Character.isDigit(formula.charAt(k))) {
                        k++;
                    }
                    count += Integer.valueOf(formula.substring(j, k));
                    i = k;
                } else {    //数字仅有一位
                    count += 1;
                    i = j;
                }
                map.put(atom, count);
                list.add(map);
            }
        }
        //遍历完 可能会出来很多个 map, 此时要合并他们 到一个 key按 字典序排列的resultMap
        Map<String, Integer> resultMap = new TreeMap<>(new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        //遍历list中的多个map, 把每个map中所有的<key, value>都拿出来, 还要乘倍数
        for (Map<String, Integer> map : list) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (resultMap.get(key) != null) {
                    resultMap.put(key, value * multipler + resultMap.get(key));
                } else {
                    resultMap.put(key, value * multipler);
                }
            }
        }
        return resultMap;
    }
}





Stack 实现递归 








