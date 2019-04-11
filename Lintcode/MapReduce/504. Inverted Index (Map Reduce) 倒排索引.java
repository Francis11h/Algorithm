504. Inverted Index (Map Reduce)

倒排索引（Inverted index）


以英文为例，下面是要被索引的文本：

    T0 = "it is what it is"
    T1 = "what is it"
    T2 = "it is a banana"

我们就能得到下面的反向文件索引：
    "a":      {2}
    "banana": {2}
    "is":     {0, 1, 2}
    "it":     {0, 1, 2}
    "what":   {0, 1}

检索的条件"what", "is" 和 "it" 将对应这个集合
    {0,1} ∩ {0,1,2} ∩ {0,1,2} = {0,1} 


* Definition of OutputCollector:
* class OutputCollector<K, V> {
*     public void collect(K key, V value);
*         // Adds a key/value pair to the output buffer
* }

* Definition of Document:
* class Document {
*     public int id;
*     public String content;
* }


public class InvertedIndex {

    public static class Map {
        public void map(String _, Document value,
                        OutputCollector<String, Integer> output) {
            
            int id = value.id;
            StringTokenizer tokenizer = new StringTokenizer(value.content);
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                output.collect(word, id);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, List<Integer>> output) {

            List<Integer> results = new ArrayList<>();
            //同一id中可能有某个单词出现多次，在Reduce时需要去重复。由于Reduce中Input是排序过的，所以只需跟前一个相比即可
            int previous = -1;
            while (values.hasNext()) {
                int now = values.next();
                if (previous != now) {
                    results.add(now);
                }
                previous = now;
            }
            output.collect(key, results);
        }
    }
}