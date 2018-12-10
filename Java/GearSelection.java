package headout;
import java.util.*;

public class GearSelection {

	public static void main(String[] args) {

		int distance = 8;
		int[] radius = { 1, 3, 6, 2, 5 };
		int[] cost = { 5, 6, 8, 3, 4 };

		int[] ans = Circles(distance, radius, cost);

		for (int i = 0; i < ans.length; i++)
			System.out.println(ans[i] + " ");
	}

	static int[] Circles(int distance, int[] radius, int[] cost) {
		int[] result = new int[radius.length];

		for (int i = 0; i < radius.length; i++) {
			List<Integer> tmp = new ArrayList<Integer>();

			for (int j = 0; j < radius.length; j++)
				if (radius[j] >= distance - radius[i])
					tmp.add(j);

			result[i] = getSmallest(radius, cost, tmp, i);
		}

		return result;
	}

	static int getSmallest(int[] radius, int[] cost, List<Integer> tmp, int i) {
		if (tmp.size() == 0)
			return 0;

		int result = tmp.get(0);
		int mincost = cost[i] + cost[tmp.get(0)];

		for (int j = 1; j < tmp.size(); j++)
			if (cost[i] + cost[tmp.get(j)] < mincost) {
				mincost = cost[i] + cost[tmp.get(j)];
				result = tmp.get(j);
			}
		return result + 1;
	}
}