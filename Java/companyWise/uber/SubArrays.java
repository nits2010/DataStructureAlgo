package Java.companyWise.uber;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 20/04/19
 * Question:
 * given array of integer count number of
 * sub array such that it has at most k odd elements and two sub array  differ if only
 * when they have at least one member differ
 */
public class SubArrays {


    public static void main(String []args) {


//        Scanner scanner = new Scanner(System.in);
//
//        int n = scanner.nextInt();
//        int array[] = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            array[i] = scanner.nextInt();
//        }
//
//        int k = scanner.nextInt();
        int x[] = {3, 2, 3, 4};
        System.out.println(subArraysBrute(x, 1));
        System.out.println(subArray(x, 1));
    }


    static int subArray(int arr[], int k) {
        int odds = 0;
        int oddsCount[] = new int[arr.length];
        int ans = 0;

        Arrays.fill(oddsCount, 0);

        for (int i = 0; i < arr.length; i++) {
            if (oddsCount[odds] == 0)
                oddsCount[odds] = 1;
            else
                oddsCount[odds]++;

            odds += arr[i] % 2 == 0 ? 0 : 1;

            if (odds >= k) {
                ans += oddsCount[odds - k];
            }

        }

        return ans;
    }


    //O(n^2)
    static int subArraysBrute(int arr[], int k) {


        int count = 0;
        Set<Integer> set = new HashSet<>();

        //Count single length
        for (int i = 0; i < arr.length; i++) {
            count += set.contains(arr[i]) ? 0 : 1;
            set.add(arr[i]);
        }

        int len = 2;
        int odd;

        Set<List<Integer>> setArray = new HashSet<>();
        while (len < arr.length) {

            setArray.clear();
            for (int i = 0; i < arr.length - len + 1; i++) {
                int j = i + len - 1;
                odd = 0;
                List<Integer> ar = new ArrayList<>();
                for (int x = i; x <= j; x++) {
                    if (arr[x] % 2 != 0)
                        odd++;
                    ar.add(arr[x]);
                }

                if (!setArray.contains(ar))
                    if (odd <= k)
                        count++;

                setArray.add(ar);
            }
            len++;

        }
        return count;

    }

}
