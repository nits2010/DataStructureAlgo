package Java.companyWise.Adobe;


/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-25
 * Description:
 * Find the index of pivot point
 * <p>
 * {@link Java.LeetCode.sortedrotated.MinimumRotatedSortedArrayFindPivot}
 */
public class FindPivotSortedRotedArray {


    public static void main(String []args) {
        FindPivot min = new FindPivot();
        System.out.println(min.search(new int[]{4, 5, 6, 7, 8, 9, 1, 2, 3}) + " expected " + 6);
        System.out.println(min.search(new int[]{4, 5, 6, 7, 0, 1, 2}) + " expected " + 4);
        System.out.println(min.search(new int[]{1, 2, 3, 4, 5, 6}) + " expected " + 0);
        System.out.println(min.search(new int[]{6}) + " expected " + 0);
        System.out.println(min.search(new int[]{3, 1, 2}) + " expected " + 1);
        System.out.println(min.search(new int[]{5, 1, 2, 3, 4}) + " expected " + 1);
    }

    static class FindPivot {

        public int search(int[] nums) {
            if (nums == null || nums.length == 0)
                return Integer.MAX_VALUE;

            int low = 0, high = nums.length - 1;

            if (nums[low] < nums[high])
                return low;

            while (low <= high) {
                int mid = (low + high) >> 1;

                if (mid > low && nums[mid] < nums[mid - 1])
                    return mid;

                if (mid < high && nums[mid] > nums[mid + 1])
                    return mid + 1;

                if (nums[mid] > nums[low])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            return low;
        }
    }
}
