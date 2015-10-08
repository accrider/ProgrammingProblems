import java.util.Arrays;
import java.util.Scanner;

/*
Input:
3
aAb
abc
acba
 */
public class Problem195 {
	
	
    public static void perm(String s) { 
    	perm("", s); 
    }
    public static void perm(String prefix, String s) {
        int N = s.length();
        if (N == 0)
        	System.out.println(prefix);
        else {
            for (int i = 0; i < N; i++)
               perm(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, N));
        }
    }
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int num = Integer.parseInt(scan.nextLine());
		
		while (num-- > 0) {
			String input = scan.nextLine();
			char[] cArray = input.toCharArray();
			Arrays.sort(cArray);
			String sorted = String.valueOf(cArray);
			perm(sorted);
		}
		scan.close();
	}

}
