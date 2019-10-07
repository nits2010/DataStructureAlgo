package Java.nonleetcode;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class GearSelection_submited {

	static class Data implements Comparable<Data> {

		public int radius;
		public int cost;
		public int index;

		public Data() {

		}

		@Override
		public int compareTo(Data o) {
			if(this.radius == o.radius)
				return this.cost - o.cost; 
			return this.radius - o.radius;
		}

	}

	static int[] Circles(int distance, int[] radius, int[] cost) {

		Data[] items = new Data[radius.length];

		for (int i = 0; i < radius.length; i++) {
			Data d = new Data();
			items[i] = d;
			items[i].radius = radius[i];
			items[i].cost = cost[i];
			items[i].index = i + 1;
		}

		final Comparator<Data> comparator = Data::compareTo;

		Arrays.sort(items, comparator);


		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i].radius + "," + items[i].cost + "," + items[i].index);

		}

		
		
		return getResult(items, distance);
	}

	public static int[] getResult(Data items[], int distance) {

		int res[] = new int[items.length + 1];

		for (int i = 0; i < items.length; i++) {
			int temp = distance - items[i].radius;
			if(items[i].radius == 3 && items[i].index == 4){
				System.out.println();
			}

			if (temp < 0) {
				res[items[i].index-1] = 0;
				continue;
			}

			int ridx = bs(temp, items);
			if (ridx != -1) {
				int minCostIndex = minCost(ridx, items);
				res[items[i].index - 1] = items[minCostIndex].index;
				System.out.println();

			}
		}

		int finalOutput[] = new int[items.length];

		for (int i = 0; i < res.length - 1; i++) {
			finalOutput[i] = res[i];
		}

		return finalOutput;

	}

	public static int minCost(int ridx, Data items[]) {
		int res = ridx;

		for (int i = ridx + 1; i < items.length; i++) {
			if (items[i].cost < items[res].cost)
				res = i;

			if (items[i].cost == items[res].cost) {
				if (items[i].radius == items[res].radius) {
					if (items[i].index < items[res].index)
						res = i;
				}
				if (items[i].radius > items[res].radius)
					res = i;
			}

		}
		return res;
	}

	public static int bs(int r, Data items[]) {
		int start = 0, end = items.length - 1;

		int mid = (start + end) / 2;

		while (start <= end) {
			if (mid != 0 && items[mid].radius >= r && items[mid - 1].radius < r)
				return mid;
			if (mid == 0 && items[mid].radius >= r)
				return mid;
			
			if (r > items[mid].radius)
				start = mid + 1;
			else
				end = mid - 1;
			mid = (start + end) / 2;
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		// final String fileName = System.getenv("OUTPUT_PATH");
		// BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		int[] res;
		int _radius_size = 0;
		_radius_size = in.nextInt();

		int _distance;
		_distance = in.nextInt();

		int[] _radius = new int[_radius_size];
		int _radius_item;
		for (int _radius_i = 0; _radius_i < _radius_size; _radius_i++) {
			_radius_item = in.nextInt();
			_radius[_radius_i] = _radius_item;
		}

		int _cost_size = _radius_size;
		int[] _cost = new int[_cost_size];
		int _cost_item;
		for (int _cost_i = 0; _cost_i < _cost_size; _cost_i++) {
			_cost_item = in.nextInt();
			_cost[_cost_i] = _cost_item;
		}

		res = Circles(_distance, _radius, _cost);

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}

	}
}
