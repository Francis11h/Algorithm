

Arrays.sort()


public class Arrays {



	1.Arrays.sort(要被排列的数组, 自定义的比较器);


			* @param <T> the class of the objects to be sorted 要被排序的对象属于的类(class) T代表范型 就是 各种类型但是现在哪种类型还不确定
			* @param a the array to be sorted
		
			* "？super T" 是说 这个参数可以是 T或者是T的父类



			public static <T> void sort(T[] a, Comparator<? super T> c) {
		        if (c == null) {
		            sort(a);
		        } else {
		            if (LegacyMergeSort.userRequested)
		                legacyMergeSort(a, c);
		            else
		                TimSort.sort(a, 0, a.length, c, null, 0, 0);
		        }
		    }




	2.  旧版归并排序
			private static <T> void legacyMergeSort(T[] a, int fromIndex, int toIndex,
                                            Comparator<? super T> c) {
		        T[] aux = copyOfRange(a, fromIndex, toIndex);
		        if (c==null)
		            mergeSort(aux, a, fromIndex, toIndex, -fromIndex);
		        else
		            mergeSort(aux, a, fromIndex, toIndex, -fromIndex, c);
		    }

}


		3. 	TimSort是改进后的归并排序，对归并排序在已经反向排好序的输入时表现为O(n2)的特点做了特别优化。对已经正向排好序的输入减少回溯。对两种情况（一会升序，一会降序）的输入处理比较好

			未完待续，，，，这太多了
			https://www.cnblogs.com/yuxiaofei93/p/5722711.html
			

			a 			数据数组,
			lo 			数据第一个元素索引,
			hi  		最后一个元素索引,
			c   		比较器对象,
			work 		工作空间数组,
			workBase	工作空间可用空间,
			workLen		工作集合的大小

			private TimSort(T[] a, Comparator<? super T> c, T[] work, int workBase, int workLen) {
		        this.a = a;
		        this.c = c;

		        // Allocate temp storage (which may be increased later if necessary)
		        int len = a.length;
		        int tlen = (len < 2 * INITIAL_TMP_STORAGE_LENGTH) ?
		            len >>> 1 : INITIAL_TMP_STORAGE_LENGTH;
		        if (work == null || workLen < tlen || workBase + tlen > work.length) {
		            @SuppressWarnings({"unchecked", "UnnecessaryLocalVariable"})
		            T[] newArray = (T[])java.lang.reflect.Array.newInstance
		                (a.getClass().getComponentType(), tlen);
		            tmp = newArray;
		            tmpBase = 0;
		            tmpLen = tlen;
		        }
		        else {
		            tmp = work;
		            tmpBase = workBase;
		            tmpLen = workLen;
		        }

		        int stackLen = (len <    120  ?  5 :
		                        len <   1542  ? 10 :
		                        len < 119151  ? 24 : 49);
		        runBase = new int[stackLen];
		        runLen = new int[stackLen];
    		}














