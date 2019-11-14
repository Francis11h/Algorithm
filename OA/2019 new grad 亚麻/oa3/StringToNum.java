package Convertion;

import java.util.Scanner;
import java.util.*;


public class StringToNum {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Map<Character, Integer> map = new HashMap<>();
		for (char c = 'a'; c <= 'z'; c++) {
			map.put(c, c - 'a' + 1);
		}

        for (char c = 'A'; c <= 'Z'; c++) {
            map.put(c, c - 'A' + 1);
        }
		
		while (scanner.hasNext()) {
			StringBuilder sb = new StringBuilder();
			String current = scanner.next();
			for (char c : current.toCharArray()) {
				sb.append(map.get(c));
				sb.append(" ");
			}
			System.out.println(sb.toString());
		}
		
		scanner.close();

	}

}
