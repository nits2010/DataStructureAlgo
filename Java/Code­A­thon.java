package headout;

import java.io.IOException;

public class Code­A­thon {
	private final static int MOD = 1000000007;
	private final static int SCORE_GAP = 2;
	private final static int LEAST_SCORE = 25;
	private final static int TIE = 24;

	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int A = Integer.parseInt(br.readLine());
//		int B = Integer.parseInt(br.readLine());
//		
		int A = 3, B= 25; 
		
		System.out.println(nCr(B,A,MOD));
		
		
		System.out.print(getNumPossibleGames(A, B));
	}

	private static int getNumPossibleGames(int s, int B) {
		// Make A the larger number
		if (s < B) {
			int temp = s;
			s = B;
			B = temp;
		}

		// Make sure score is valid end game:

		// Winning team has at least 25 points
		if (s < LEAST_SCORE) {
			return 0;
		}

		// If exactly 25 points
		if (s == LEAST_SCORE) {
			// They must have won by atleast 2
			// If so, get the number of combinations for score
			return (s - B < SCORE_GAP) ? 0 : nCr(s - 1, B, MOD);
		}

		// Otherwise, their score is 26+ and they must have won by exactly 2
		if (s - B != SCORE_GAP) {
			return 0;
		}

		// In this case, the team must have reached 24-24 at some point
		// in the game, so get the number of combinations for that
		int numGames = nCr(TIE, TIE, MOD);

		// From here on out, the max gap in score is 1 since any more
		// and the game is over. This means if one team gets the lead,
		// the other team ties, all the way until losing team gets to
		// score B.
		// So for each lead-then-tie there are 2 possibilities:
		// A leads and B ties, or B leads and A ties.
		// This happens (B - 24) times
		// Thus, 2 ^ (B - 24) possible combinations from 24-24 to A-B
		numGames = (int) ((((long) numGames) * pow(2, B - 24, MOD)) % MOD);
		return numGames;
	}

	private static int nCr(int n, int r, int mod) {
		// Swap if n less than r
		if (n < r) {
			int temp = n;
			n = r;
			r = temp;
		}

		// Return 1 if value too small
		if (n < 1 || r < 1) {
			return 1;
		}

		// Use Pascal's triangle

		// Initialize
		int[] row = new int[r];
		for (int i = 0; i < r; row[i++] = 1) {
		}

		// Get up to row with width r
		for (int i = 2; i < r; ++i) {
			for (int j = i - 1; j > 0; --j) {
				row[j] = (row[j] + row[j - 1]) % mod;
			}
		}

		// Now, for the next n rows,
		// sum values in position (r-1)
		--r;
		int sum = row[r];
		for (int i = 0; i < n; ++i) {
			for (int j = r; j > 0; --j) {
				row[j] = (row[j] + row[j - 1]) % mod;
			}
			sum = (sum + row[r]) % mod;
		}

		return sum;
	}

	// This method is simplified for this problem
	private static int pow(int base, int exponent, int mod) {
		if (exponent < 2) {
			return (exponent < 1) ? 1 : base;
		}
		long product = pow(base, exponent >> 1, mod);
		product = (product * product) % mod;
		return ((exponent & 1) == 1) ? (int) ((product * base) % mod) : (int) (product);
	}
}