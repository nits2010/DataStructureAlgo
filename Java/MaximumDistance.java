package Java;


import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

class MaximumDistance {
	public static double maximumDistance(int N, int speeds[], int consumption[], double availableFuel) {
		if (availableFuel == 0)
			return 0;

		double max = 0;

		for (int i = 0; i < N; i++) {
			double distance = (double) (availableFuel / consumption[i]) * speeds[i];

			if (max < distance)
				max = distance;
		}

		return max;
	}

	public static void main(String []args) throws Exception {

		final Scanner in = new Scanner(System.in);
		try {
			int N = in.nextInt();
			int speeds[] = new int[N];
			int consumption[] = new int[N];

			for (int i = 0; i < N; i++) {
				speeds[i] = in.nextInt();
			}
			for (int i = 0; i < N; i++) {
				consumption[i] = in.nextInt();
			}
			int availableFuel = in.nextInt();

			double result = maximumDistance(N, speeds, consumption, availableFuel);
			DecimalFormat df = new DecimalFormat(".000");
			df.setRoundingMode(RoundingMode.DOWN); // Note this extra step
			System.out.println(df.format(result));
		} finally {
			in.close();
		}

	}
}
