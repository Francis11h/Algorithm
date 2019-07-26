Substring


public final class String {
	implements java.io.Serializable, Comparable<String>, CharSequence {

	// value[] is used for Character storage 
	// String类带有char[] value成员，它是String的主要存储结构
	private final char value[];



	1.只有一个参数, 表示从这个下表开始取的子串(包含这个下标), 截取到末尾

	"123".subString(1) ---> "23"

			public String substring(int beginIndex) {
				//开始的下标小于0 异常
			    if (beginIndex < 0) {
			        throw new StringIndexOutOfBoundsException(beginIndex);
			    }
			    int subLen = value.length - beginIndex;]
			    //开始的下标大于整个字符串长度 异常
			    if (subLen < 0) {
			        throw new StringIndexOutOfBoundsException(subLen);
			    }
			    //开始的下标等于0 还是原来的字符串 不new 否则new一个新的
			    return (beginIndex == 0) ? this : new String(value, beginIndex, subLen);
			}


	2. 从beginIndex位置开始截取字符串到endIndex,包括beginIndex,但是不包括endIndex

	"123".subString(1,2) ---> "2"

			public String substring(int beginIndex, int endIndex) {
		        if (beginIndex < 0) {
		            throw new StringIndexOutOfBoundsException(beginIndex);
		        }
		        if (endIndex > value.length) {
		            throw new StringIndexOutOfBoundsException(endIndex);
		        }
		        int subLen = endIndex - beginIndex;
		        if (subLen < 0) {
		            throw new StringIndexOutOfBoundsException(subLen);
		        }
		        return ((beginIndex == 0) && (endIndex == value.length)) ? this
		                : new String(value, beginIndex, subLen);
		    }



	3. String中的某一种构造方法 String(char value[], int offset, int count)

	根据 char[] value 数组作为源数组 新建一个String 从offset位置开始(包括该位) 一共截取 count 个字符.

	String test = new String(new char[]{'1','2','3','4'}, 1, 2);
	test ------> "23"

			public String(char value[], int offset, int count) {
		        if (offset < 0) {
		            throw new StringIndexOutOfBoundsException(offset);
		        }
		        if (count <= 0) {
		            if (count < 0) {
		                throw new StringIndexOutOfBoundsException(count);
		            }
		            //复制长度等于0且开始位置小于等于value长度时返回空的String
		            if (offset <= value.length) {
		                this.value = "".value;
		                return;
		            }
		        }
		        // Note: offset or count might be near -1>>>1.
		        // 开始位 + 长度 大于 源char数组 的长度 抛出异常
		        if (offset > value.length - count) {
		            throw new StringIndexOutOfBoundsException(offset + count);
		        }
		        //调用Arrays.copyRange进行复制
		        this.value = Arrays.copyOfRange(value, offset, offset + count);
		    }
	}
}




	4.  Arrays.copyOfRange()

		Arrays的共有方法，按范围复制并返回一个新的 char[]数组 ,从from位置开始（包括from），到to位置结束（不包括to）

		char[] test = Array.copyOfRange(new char[]{'1','2','3'}, 1, 2);

               test ------> {2}


			public static char[] copyOfRange(char[] original, int from, int to) {
		        int newLength = to - from;
		        if (newLength < 0)
		            throw new IllegalArgumentException(from + " > " + to);
		        char[] copy = new char[newLength];
		        System.arraycopy(original, from, copy, 0,
		                         Math.min(original.length - from, newLength));
		        return copy;
		    }





    5. System.arraycopy()	该方法是一个 Native 方法 就是 不是拿Java语言实现的 方法 和底层打交道吧

    public static native void arraycopy(Object src,  int  srcPos,
                                        Object dest, int destPos,
                                        int length);
















