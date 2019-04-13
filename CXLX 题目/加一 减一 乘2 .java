import java.util.*;

public class Main {
    public static void main(String []args)
    {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        String[] strs = str.split(",");
        int a = Integer.valueOf(strs[0]), b = Integer.valueOf(strs[1]);

        HashMap<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a);
        map.put(a, 0);

        while(!queue.isEmpty())
        {
            int i = queue.poll(), num = map.get(i);
            if(i == b)
            {
                System.out.println(num);
                return;
            }

            int x= i*2 , y = i-1, z = i+1;
            if(map.get(x) == null) {
                queue.offer(x);
                map.put(x, num+1);
            }
            if(map.get(y) == null) {
                queue.offer(y);
                map.put(y, num+1);
            }
            if(map.get(z) == null) {
                queue.offer(z);
                map.put(z, num+1);
            }
        }
    }
}
