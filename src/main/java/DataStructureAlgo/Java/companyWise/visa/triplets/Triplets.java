package DataStructureAlgo.Java.companyWise.visa.triplets;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta
 * Date: 10/11/19
 * Description:
 */
public class Triplets {

    // Complete the triplets function below.
    public static long triplets(long t, List<Integer> nums) {


        if (nums.isEmpty())
            return 0;

        Collections.sort(nums);

        int count = 0;
        int n = nums.size();

        for (int i = 0; i < n - 2; i++) {
            int j = i + 1, k = n - 1;

            while (j < k) {

                int sum = nums.get(i) + nums.get(j) + nums.get(k);

                if (sum > t)
                    k--;
                else {
                    count += (k - j);
                    j++;

                }
            }

        }

        return count;

    }



}
