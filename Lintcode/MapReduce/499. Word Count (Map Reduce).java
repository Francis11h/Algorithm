499. Word Count (Map Reduce)

使用 map reduce 来计算单词频率

官方文档:
https://hadoop.apache.org/docs/r1.2.1/mapred_tutorial.html#Example%3A+WordCount+v1.0

input:
    chunk1: "Google Bye GoodBye Hadoop code"
    chunk2: "lintcode code Bye"
output: 
    Bye: 2
    GoodBye: 1
    Google: 1
    Hadoop: 1
    code: 2
    lintcode: 1

Map过程需要继承 org.apache.hadoop.mapreduce 包中 Mapper 类，并重写其map方法。


StringTokenizer是Java工具包中的一个类，用于将字符串进行拆分——默认情况下使用空格作为分隔符进行分割

WordCount详细解析
https://songlee24.github.io/2015/07/29/mapreduce-word-count/




* Definition of OutputCollector:
* class OutputCollector<K, V> {
*     public void collect(K key, V value);
*         // Adds a key/value pair to the output buffer
* }


public class WordCount {

    public static class Map {
        public void map(String key, String value, OutputCollector<String, Integer> output) {
            
            StringTokenizer tokenizer = new StringTokenizer(value);
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                output.collect(word, 1);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, Integer> output) {
            int sum = 0;
            while (values.hasNext()) {
                sum += values.next();
            }
            output.collect(key, sum);
        }
    }
}



map 用 StringTokenizer，这个可以直接分隔字符串
    The following is one example of the use of the tokenizer. The code:

        StringTokenizer st = new StringTokenizer("this is a test");
        while (st.hasMoreTokens()) {
         System.out.println(st.nextToken());
        }
     
    prints the following output:

        this
        is
        a
        test

map 的时候每个词就统计一次, 建立 < key , 1 > 的映射关系

来到 reduce 的时候，传进来一个 Iterator，对应一个词的信息流,
因此我们需要做的就是数 Iterator 里面有多少个元素，然后输出就可以了。

reduce函数的第二个参数类型为 Iterable<IntWritable>, 这是一堆 value的集合 ，他们具有相同的key，reduce函数的意义就是将这些value加起来。

    例如（”hello“， 1）和（”hello“， 1）聚合为（”hello“， 2），后者可能再次和（”hello“， 3） （”hello“， 1），聚合为（”hello“， 7）

    可以通过控制values的大小，防止内存溢出，合理使用内存。

    reduce函数的结果存储到磁盘上，就是我们最终的结果。
