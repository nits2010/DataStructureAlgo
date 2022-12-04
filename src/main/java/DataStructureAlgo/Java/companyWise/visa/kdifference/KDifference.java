package DataStructureAlgo.Java.companyWise.visa.kdifference;

import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 10/11/19
 * Description:
 */
public class KDifference {

    public static int kDifference(List<Integer> arr, int k) {
        int count = 0;

        Collections.sort(arr);
        int l = 0;
        int r = 0;
        while (r < arr.size()) {
            if (arr.get(r) - arr.get(l) == k) {
                count++;
                l++;
                r++;
            } else if (arr.get(r) - arr.get(l) > k)
                l++;
            else // arr[r] - arr[l] < sum
                r++;
        }

        return count;

    }
}
