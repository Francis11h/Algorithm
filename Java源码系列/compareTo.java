
两个字符串按 lexicographically 比较

str1.compareTo(str2)





public final class String {
    implements java.io.Serializable, Comparable<String>, CharSequence {
    
    private final char value[];

    1.compareTo() 返回的是一个int  按字符逐个比较 有不同就 return difference
    			如果 str1 = str2 , return = 0
    			如果 str1 < str2 , return < 0
    			如果 str1 > str2 , return > 0


			public int compareTo(String anotherString) {
		        int len1 = value.length;
		        int len2 = anotherString.value.length;
		        int lim = Math.min(len1, len2);
		        char v1[] = value;
		        char v2[] = anotherString.value;

		        int k = 0;
		        while (k < lim) {
		            char c1 = v1[k];
		            char c2 = v2[k];
		            if (c1 != c2) {
		                return c1 - c2;
		            }
		            k++;
		        }
		        return len1 - len2;
		    }



	
    }
}



















