package Java;

import java.util.LinkedList;
import java.util.List;

class PalindromePartitionSolution {

	final String input;
	final List<List<String>> result = new LinkedList<>();

	public PalindromePartitionSolution(final String str) {
		input = str;
	}

	boolean isPalindrome(final String str, int i, int j) {
		if (str == null)
			return true;

		while (i < j) {
			if (str.charAt(i) != str.charAt(j))
				return false;

		}

		return true;
	}

	void palindromePartition() {
		palindromePartitionUtil(new LinkedList<>(), 0, this.input.length());
	}

	void palindromePartitionUtil(List<String> currL, int start, int n) {
		if (start >= n) {
			List<String> temp = new LinkedList<>();
			temp.addAll(currL);
			System.out.println(temp.toString());
			result.add(temp);
			return;
		}

		for (int i = start; i < n; i++) {
			if (isPalindrome(input, start, i)) {
				String curr ; 
				if (i != start)
					curr = input.substring(start, i - start + 1);
				else
					curr = input.substring(start, start + 1);
				currL.add(curr);
				
				palindromePartitionUtil(currL, i + 1, n);
				currL.remove(curr);
				
			}
		}
	}

}

class PalindromePartition {

	public static void main(String[] args) {
		PalindromePartitionSolution p = new PalindromePartitionSolution("niti");
		p.palindromePartition();
		System.out.println(p.result);
	}
}
