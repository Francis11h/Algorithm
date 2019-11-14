package Convertion;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class NumToString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Map<Integer, Character> map = new HashMap<>();
		for (int i = 1; i <= 26; i++) {
			char c = (char) ('A' + i - 1);
			map.put(i, c);
		}
		
		while (scanner.hasNext()) {
			StringBuilder sb = new StringBuilder();
			String[] current = scanner.nextLine().split(" ");
			for (String num : current) {
				int i = Integer.parseInt(num);				
				sb.append(map.get(i));
				
			}
			System.out.println(sb.toString());
		}
		
		scanner.close();

	}

}
