384. Shuffle an Array

Shuffle a set of numbers without duplicates.


// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);


Any permutation of [1,2,3] must "equally likely" to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();
// Returns the random shuffling of array [1,2,3].
solution.shuffle();











Approach #1 Brute Force [Accepted]

Intuition
If we put each number in a "hat" and draw them out at random, the order in which we draw them will define a random ordering.

Implementation

Mechanically, this is performed by copying the contents of array into a "second auxiliary array" named aux 
                before overwriting each element of array with a randomly selected one from aux.

             After selecting each random element, it is removed from aux to prevent duplicate draws. 

Correctness
            The correctness of the algorithm follows from the fact that an element is equally likely to be selected during all iterations of the for loop. 
            No matter on which draw an element is drawn, it is drawn with a "1/n" chance, so each array permutation is equally likely to arise.


T: O(n^2)   quadratic time complexity arises from the calls to list.remove(),which run in linear time. 
                                n linear list removals occur, which results in a fairly easy quadratic analysis.

S:O(n)      Because the problem also asks us to implement reset, we must use linear additional space to store the original array. Otherwise, it would be lost upon the first call to shuffle. 否则会在第一次洗牌时丢失

 
class Solution {
    private int[] array;
    private int[] original;

    private Random rand = new Random();         // Creates a new random number generator.  随机数 生成器

    public Solution(int[] nums) {
        array = nums;                           // 拷贝 reference 改array就会改nums
        original = nums.clone();                // 新开内存 原nums内容被完全保留到 original 中
    }
    
    public int[] reset() {
        array = original;
        original = original.clone();    //clone() 首先是要分配内存空间的 不仅仅是拷贝reference 而是完全的新的
        return array;
    }
    
    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();

        for (int i = 0; i < array.length; i++) {
            //Generates the next Int pseudorandom number. , within the bound
            int removeIdx = rand.nextInt(aux.size());
            array[i] = aux.get(removeIdx);
            aux.remove(removeIdx);
        }

        return array;
    }

    //convert int[] to list<Integer>
    private List<Integer> getArrayCopy() {
        List<Integer> asList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            asList.add(array[i]);
        }
        return asList;
    }
}




Brute Force 改进 只用一个 辅助数组  每次shuffle的时候才 clone() 一个新的出来

class Solution {
    private int[] nums;
    private Random rand;         

    public Solution(int[] nums) {
        this.nums = nums;                         
        rand = new Random();            
    }
    
    public int[] reset() {
        return nums;
    }
    
    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();
        int[] array = nums.clone();
        for (int i = 0; i < array.length; i++) {
            //Generates the next Int pseudorandom number. , within the bound
            int removeIdx = rand.nextInt(aux.size());
            array[i] = aux.get(removeIdx);
            aux.remove(removeIdx);
        }

        return array;
    }

    //convert int[] to list<Integer>
    private List<Integer> getArrayCopy() {
        List<Integer> asList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            asList.add(nums[i]);
        }
        return asList;
    }
}








Approach #2 Fisher-Yates Algorithm [Accepted]
交换法

by swapping elements around within the array itself, we can avoid the linear space cost of the auxiliary array and the linear time cost of list modification.

class Solution {
    private int[] nums;
    private Random rand;         

    public Solution(int[] nums) {
        this.nums = nums;                         
        rand = new Random();            
    }
    
    public int[] reset() {
        return nums;
    }
    
    public int[] shuffle() {

        int[] array = nums.clone();
        
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(array, i, j);
        }

        return array;
    }
    
    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

Fisher–Yates shuffle Algorithm works in T:O(n) time complexity. The assumption here is, we are given a function rand() that generates random number in O(1) time.

The idea is to start from the last element, 
    swap it with a randomly selected element from the whole array (including last). 
Now consider the array from 0 to n-2 (size reduced by 1), 
    and repeat the process till we hit the first element.

每次循环确定一个位置的元素 从后往前 

Prove the correctness:
    The probability that "ith" element(including the last one) goes to "last position" is "1/n",
        because we randomly pick an element in our first iteration. (int j = rand.nextInt(i + 1)) (chose one element[0,n])

    The probability that "ith" element goes to "second last position" can be proved to be "1/n" by dividing it in two cases:
        1. i = n-1 (i is the index of last element)
            the probability of last element going to second last position is =
            (probability that last element doesn‘t stay at its otiginal position) *
            (probability that the index picked in previous step is picked again so that the original last element is swapped to original second last position)
            第一层 nums[n-1] 换了 * 第二层 该数换到的位置 又被取到了 
            --->
            (n-1)/n  *  1/(n-1) = 1/n

        2. 0 < i < n-1 (index of non-last)

            The probability of ith element going to second last position = 
            (probability that ith element is not picked in previous iteration) *
            (probability that ith element is picked in this iteration)
            --->

            ((n-1)/n) x (1/(n-1)) = 1/n

    .... we can prove that ith element will equally to be put at any position.















源码

Returns a pseudorandom, uniformly distributed value
"between 0 (inclusive) and the specified value (exclusive)", 
        [0, bound)
drawn from this random number generator‘s sequence. 

//Random类中的 nextInt(bound) 方法
public int nextInt(int bound) {
    if (bound <= 0)
        throw new IllegalArgumentException(BadBound);

    int r = next(31);
    int m = bound - 1;
    if ((bound & m) == 0)  // i.e., bound is a power of 2
        r = (int)((bound * (long)r) >> 31);
    else {
        for (int u = r;
            u - (r = u % bound) + m < 0;
            u = next(31));
    }
    return r;
}

//Random类中的 next(bit) 方法
protected int next(int bits) {
    long oldseed, nextseed;
    AtomicLong seed = this.seed;
    do {
        oldseed = seed.get();
        nextseed = (oldseed * multiplier + addend) & mask;
    } while (!seed.compareAndSet(oldseed, nextseed));
    return (int)(nextseed >>> (48 - bits));
}










随机生成 0-99 放入数组
class Solution {
    public static void main(String[] args) {
        int[] nums=new int[100];
        for (int i=0;i<nums.length;i++){    //生成数据
            nums[i]=i;
        }
        Random ran=new Random();
        for (int i=0;i<nums.length;i++){
            int j=ran.nextInt(nums.length); //打乱顺序
            swap(nums,i,j);
        }
        for (int i:nums){
            System.out.print(i+"\t");
        }
    }
    public static void swap(int nums[],int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
