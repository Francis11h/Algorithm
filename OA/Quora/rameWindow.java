rameWindow

Given an int n, print the *** window frame of the number
Example: input -> n = 6
output -> 

[
    "********",     --> 8 *
    "*      *",     -> 2 * 加 六个 ' ' (space)
    "*      *",
    "*      *",
    "*      *",
    "********"
]


Input -> n = 3;
Output -> 
[
    "***",
    "* *",
    "***"
]



import java.util.*;

public class Test {

    public static void main(String[] args) {
//        int[] array = {0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 1};
        List<String> ans = helper(8);
        for (String a : ans) {
            System.out.println(a);
        }
    }

    public static List<String> helper(int n) {
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringBuilder str = new StringBuilder();
            if (i == 0 || i == n - 1) {
                for (int j = 0; j < n; j++) str.append("*");
            } else {
                str.append("*");
                for (int j = 1; j < n - 1; j++) str.append(" ");
                str.append("*");
            }
            ans.add(str.toString());
        }
        return ans;
    }

}






