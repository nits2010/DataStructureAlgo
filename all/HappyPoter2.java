package crowdfire;

import java.io.IOException;
import java.util.Scanner;

class HarryPoter {

	public static String maximumDamage(int n, int spells[], int attacks[]) {
		attacks[0] = 0;
		attacks[1] = Math.max(spells[1], 0);

		for (int i = 2; i <= n; i++)
			attacks[i] = Math.max((spells[i] + attacks[i - 2]), attacks[i - 1]);

		if (attacks[n] == 0)
			return "DANGER";
		else
			return "" + attacks[n];

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		final Scanner in = new Scanner(System.in);
		try {

			int t = in.nextInt();

			String str1[] = new String[t];
			int k = 0;

			for (int v = 0; v < t; v++) {
				int n = in.nextInt();

				int spells[] = new int[(n + 1)];
				int attacks[] = new int[(n + 1)];

				for (int i = 1; i <= n; i++) {
					spells[i] = in.nextInt();
				}

				attacks[0] = 0;
				attacks[1] = Math.max(spells[1], 0);

				str1[k++] = maximumDamage(n, spells, attacks);

			}

			for (String s : str1)
				System.out.println(s);
		} finally {
			in.close();
		}
	}

}
