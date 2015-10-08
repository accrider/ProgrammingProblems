
public class Problem136 {

	public static void main(String[] args) {
		//Print 1,500th ugly number
		int i = 0;
		int curNum = 1;
		while (i < 1500) {
			if (curNum % 2 == 0 || curNum % 3 == 0 || curNum % 5 == 0)
				i++;
			if (i%100 == 0) {
				System.out.println("ON : " + curNum);
			}
			curNum++;
		}
		System.out.println(curNum);
	}

}
