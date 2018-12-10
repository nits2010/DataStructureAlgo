package crowdfire;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	static int dp[][][] = new int[5001][5001][5];

	static int  possiblites(int n, int d, int num) {

		if (d == 0 && n == 0)
			return 1;
		if (n <= 0 || d <= 0)
			return 0;
		if (dp[n][num][d] != 0)
			return dp[n][num][d];

		int ans = 0;
		for (int i = num; i >= 1; i--)
			ans += possiblites(n - i, d - 1, i);
		return dp[n][num][d] = ans;
	}

}

class SumGame2 {

	public static void main(String args[]) throws Exception {

		// Read input from stdin and provide input before running

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int t = Integer.parseInt(line);
		int input[] = new int[t];
		int result[] = new int[t];
		for (int i = 0; i < t; i++) {
			input[i] = Integer.parseInt(br.readLine());
			result[i] = 0;
		}
		final int sumOfNumbers = 4;
		for (int i = 0; i < t; i++) {
			result[i] = Solution.possiblites(input[i], sumOfNumbers, input[i]);
			System.out.println(result[i]);
		}

	}
}
