List<Integer> array = new ArrayList<>();

array.add(val);
array.remove(index); //传入的是下标 index !!!
array.size();
array.get(index);


addAll()	//addAll方法用于将指定 collection 中的所有元素添加到列表 相当于换了个容器

Arrays.asList()
//需要将一个数组转换为 List 以便进行更丰富的操作的话
//将需要转化的数组作为参数，或者直接把数组元素作为参数，都可以实现转换。

ArrayList.add(int index, E elemen)
在 index 处插入 elemen, 原本index处的元素及其之后的元素 向右移一个
e.g. 	tmp.add(0, cur.val)