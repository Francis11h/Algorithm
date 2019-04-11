import java.util.ArrayList;
import java.util.Scanner;

public class heap {
    private static ArrayList<Point> A = new ArrayList<>();
    private static int x = 1;             //power of 2 : bitShifting
    private static int count = 0;         // # of comparison

    private static void insertValue(Point p) {
        A.add(p);
        int k = A.size() - 1;
        if (k == 0) {
            return;
        }
        while (k > 0) {                                   // shiftup
            int i = ((k + (1 << x) - 1) >> x) - 1;       //find k's parent
            count++;
            if (A.get(k).key < A.get(i).key) {
                Point tmp = A.get(i);
                A.set(i, A.get(k));
                A.set(k, tmp);
            } else {
                break;
            }
            k = i;                                      // point to its parent node, then shiftup
        }
    }

    private static Point removeMin() {                   //remove the top element on the heap(min element)
        Point result = A.get(0);
        if (A.size() == 1) {
            A.remove(0);
        } else {
            A.set(0, A.get(A.size() - 1));              //copy the last element to the top
            A.remove(A.size() - 1);
            boolean needHeapify = true;
            int i = 0;                                  //the index of the element need to be shiftdown

            while (needHeapify) {                       //i has child   i <= (A.size() >> x) - 1
                int minChildIndex = 1;                  // minChildIndex
                needHeapify = false;
                // traverse the child element of current element and find the min child
                for (int k = 2; k <= (1 << x); k++) {
                    int j = (i << x) + k;               // j : currentChildIndex
                    if (j >= A.size()) break;
                    count++;
                    if (A.get((i << x) + minChildIndex).key > A.get(j).key) {
                        minChildIndex = k;
                    }
                }

                if (((i << x) + 1) >= A.size()) {
                    break;
                }

                int currentMinChildIndex = (i << x) + minChildIndex;
                count++;

                if (A.get(currentMinChildIndex).key < A.get(i).key) {
                    // swap current element with child element
                    needHeapify = true;
                    Point temp = A.get(i);
                    A.set(i, A.get(currentMinChildIndex));
                    A.set(currentMinChildIndex, temp);
                }
                i = currentMinChildIndex;
            }
        }
        return result;
    }


    //log2n
    private static int log2(int num) {
        int ans = 0;
        while (num > 1) {
            num >>= 1;
            ans++;
        }
        return ans;
    }

    //log2n recursive
    private static int log2recursive(int num) {
        if (num == 1) return 0;                         // outdoor of recursive
        return 1 + log2recursive(num >> 1);
    }

    public static void main(String[] args) {
        int commandLineArgLength = args.length;
        int num = 2;
        if (commandLineArgLength > 1) {
            System.out.println("Too many command line argument input, allow only one");
            return;
        } else if (commandLineArgLength == 1) {
            try {
                num = Integer.parseInt(args[0]);
            } catch (Exception e){
                num = 2;
            }
        }
        
        if ((num & (num - 1)) == 0) {       // if num is 2^n, num & num-1 should equal 0
            System.out.println("branching factor is " + num + "; " +"bitShifting is " + log2(num));
        } else {
            System.out.println("The branching factor is not a power of 2");
            return;
        }
        x = log2(num);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {

            String str = sc.nextLine();
            String[] tmp = str.split(" ");

            if (tmp.length == 2) {
                int[] nums = new int[tmp.length];
                for (int i = 0; i < 2; i++) {
                    nums[i] = Integer.parseInt(tmp[i]);
                }
                insertValue(new Point(nums[0], nums[1]));
            } else if (tmp.length == 1) {
                Point ans = removeMin();
                //System.out.println(ans.key + " " + ans.value);
            }
        }
        System.out.println("key comparisons: " + count);
    }
}

class Point {
    int key, value;
    Point(int key, int value) {
        this.key = key;
        this.value = value;
    }
}