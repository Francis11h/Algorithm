package GoogleHashCode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MorePizze {
    static int MAX;
    static int Num;
    static int score = 0;
    static List<Integer> Input, Output;


    public static void main(String[] args) throws FileNotFoundException {
        String[] fileNames = new String[] {
                "a_example",
                "b_small",
                "c_medium",
                "d_quite_big",
                "e_also_big"
        };

        for (String fileName : fileNames) {
            getInputFromFile(fileName);

            Output = orderPizz(MAX, Input);
            Collections.reverse(Output);

            WriteIntoFile(fileName);
            System.out.println("\nscore : " + score + "\n");
        }
    }

    private static List<Integer> orderPizz(int Max, List<Integer> input) {
        List<Integer> ans = new ArrayList<>();
        score = 0;
        int j, i = input.size() - 1;
        boolean test1 = true, test2 = true;

        while (i >= 0 && test1) {
            List<Integer> temp = new ArrayList<>();
            j = i;
            int sum = 0;
             while (j >= 0 && test2) {
                 int cur = input.get(j);
                 if (sum + cur < Max) {
                     sum += cur;
                     temp.add(j);
                 } else if (sum + cur == Max) {
                     sum += cur;
                     temp.add(j);
                     test1 = false;
                     test2 = false;
                 }
                 j--;
             }
             if (score < sum) {
                 score = sum;
                 ans = temp;
             }

             if (ans.size() == input.size()) {
                 test1 = false;
             }
             i--;
        }
        return ans;
    }


    private static void getInputFromFile(String fileName) throws FileNotFoundException {
        try {
            Input = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader("/Users/hanzirun/Desktop/Algorithm/OA/GoogleHashCode/input/" + fileName + ".in"));
            String line, firstLine;
            firstLine = br.readLine();
            String[] vars = firstLine.split(" ");

            MAX = Integer.parseInt(vars[0]);    // Maximum pizza slies required
            Num = Integer.parseInt(vars[1]);    // Number (type) of pizzas availavle
            // create the pizzza list by reading the file
            System.out.println("-----input of " + fileName);
            System.out.println(MAX + " " + Num);

            while ((line = br.readLine()) != null) {
                String[] letters = line.split(" ");
                for (String letter : letters) {
                    Input.add(Integer.parseInt(letter));
                    System.out.print(letter + " ");
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void WriteIntoFile(String fileName) throws FileNotFoundException {
        try {

            System.out.println("\n------output of " + fileName);
            PrintWriter pw = new PrintWriter("/Users/hanzirun/Desktop/Algorithm/OA/GoogleHashCode/output/" + fileName + ".out", "UTF-8");

            pw.println(Output.size());
            System.out.println(Output.size());

            for (int i = 0; i < Output.size(); i++) {
                pw.print(Output.get(i) + " ");
                System.out.print(Output.get(i) + " ");
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
