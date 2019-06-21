package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-21
 * Description:
 */
public class LongestIncreasingSubSequence {

    static class LIS {
        int length;
        int[] lis;
    }

    public static void main(String args[]) {
        int items[] = {10, 9, 2, 5, 3, 7, 101, 18};

        int items2[] = {2, 5, 3, 7, 11, 8, 10, 13, 6};

        int items3[] = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};


        lengthOfLIS(items);
        lengthOfLIS(items2);
        lengthOfLIS(items3);
    }


    private static int lengthOfLIS(int nums[]) {
        System.out.println("\n input ");
        for (int i = 0; i < nums.length; i++)
            System.out.print(" " + nums[i]);

        System.out.println("\n LIS -> ");
        LIS lis = lengthOfLISHelper(nums);
        for (int i = 0; i < lis.length; i++)
            System.out.print(" " + lis.lis[i]);
        return lis.length;
    }

    private static LIS lengthOfLISHelper(int nums[]) {

        if (nums == null || nums.length == 0)
            return null;
        int n = nums.length;
        int lis[] = new int[n];
        int lisLength = 1;


        lis[0] = nums[0];

        for (int i = 1; i < n; i++) {

            int item = nums[i];
            //if smallest amoung, then start a new subsequence
            if (item < lis[0])
                lis[0] = nums[i];

            else if (item > lis[lisLength - 1]) {
                //if this is largest element in among last element of all lengthOfLIS, then extend this lengthOfLIS with new element
                lis[lisLength++] = item;
            } else if (item < lis[lisLength - 1]) {
                //if this is smaller then among last element of all lengthOfLIS, find the right place, copy that list,
                // replace the last element and discard the same length lengthOfLIS

                int index = ceilIndex(lis, 0, lisLength - 1, item);
                lis[index] = item;

            }
        }

        LIS obj = new LIS();
        obj.length = lisLength;
        obj.lis = lis;
        return obj;

    }

    private static int ceilIndex(int a[], int l, int r, int item) {
        while (l < r) {
            int mid = (l + r) >> 1;

            if (a[mid] >= item)
                r = mid;
            else
                l = mid + 1;
        }
        return r;
    }
}
