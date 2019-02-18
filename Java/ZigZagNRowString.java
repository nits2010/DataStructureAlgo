package Java;


import java.util.ArrayList;
import java.util.List;

public class ZigZagNRowString {

	public ZigZagNRowString(String str, int n) {
		int temp = 0;
		if(n == 0){
			System.out.println("invalid");
			return ;
		}
			
		if (n == 1 || str.length() <= n) {
			System.out.println(str);
		} else {
			List<StringBuffer> res = new ArrayList<>(n);
			boolean flag = false;
			for (int i = 0; i < n; i++)
				res.add(new StringBuffer());

			for (int i = 0; i < str.length(); i++) {

				StringBuffer s = res.get(temp);
				s.append(str.charAt(i));
				if (!flag)
					temp++;
				else
					temp--;

				if (temp == n) {
					flag = true;
					temp -= 2;
				}
				if (temp == 0)
					flag = false;

			}
			StringBuffer ss = new StringBuffer();

			for (int i = 0; i < n; i++)
				ss.append(res.get(i));
			System.out.println(ss.toString());
		}
	}

	public static void main(String[] args) {
		new ZigZagNRowString("ABCDEFGH", 2);
		new ZigZagNRowString("GEEKSFORGEEKS", 3);
		new ZigZagNRowString("GEEKSFORGEEKS", 14);
		new ZigZagNRowString("GEEKSFORGEEKS", 0);
		
		System.out.println("");
	}

}
