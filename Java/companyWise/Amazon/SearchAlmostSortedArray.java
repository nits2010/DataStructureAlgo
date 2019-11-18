package Java.companyWise.Amazon;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-22
 * Description: https://www.geeksforgeeks.org/search-almost-sorted-array/#_=_
 * Given an array which is sorted, but after sorting some elements are moved to either of the adjacent positions, i.e.,
 * arr[i] may be present at arr[i+1] or arr[i-1]. Write an efficient function to search an element in this array.
 * Basically the element arr[i] can only be swapped with either arr[i+1] or arr[i-1].
 * <p>
 * For example consider the array {2, 3, 10, 4, 40}, 4 is moved to next position and 10 is moved to previous position.
 * <p>
 * Example :
 * <p>
 * Input: arr[] =  {10, 3, 40, 20, 50, 80, 70}, key = 40
 * Output: 2
 * Output is index of 40 in given array
 * <p>
 * Input: arr[] =  {10, 3, 40, 20, 50, 80, 70}, key = 90
 * Output: -1
 * -1 is returned to indicate element is not present
 *
 * [AMAZON - AWS]
 */
public class SearchAlmostSortedArray {

    public static void main(String []args) {

        boolean pass =
                search(new int[]{3, 2, 10, 4, 40}, 4) == 3
                        && search(new int[]{3, 2, 10, 4, 40}, 2) == 1
                        && search(new int[]{3, 2, 10, 4, 40}, 12) == -1
                        && search(new int[]{3, 2, 10, 4, 40}, 3) == 0
                        && search(new int[]{3, 2, 10, 4, 40}, 40) == 4
                        && search(new int[0], 40) == 0;

        System.out.println(pass);

    }


    /**
     * Important observation here is the element can only be swapped with i-1 or i+1 at max.
     * So searching in i-1 AND i+1 space is sufficient
     *
     * @param a
     * @param x
     * @return
     */
    private static int search(int a[], int x) {

        if (null == a || a.length == 0)
            return 0;

        int low = 0;
        int high = a.length - 1;

        while (low <= high) {

            int mid = (low + high) >> 1;

            //if this element is at mid position
            if (a[mid] == x)
                return mid;

            //if this element is at mid-1 position ; since a element can be only swapped with neighbour
            if (mid > low && a[mid - 1] == x)
                return mid - 1;

            //if this element is at mid+1 position ; since a element can be only swapped with neighbour
            if (mid < high && a[mid + 1] == x)
                return mid + 1;


            //if none of above true, then search either left side or right side
            //since mid+2 or mid-2 is always sorted (in there space)
            if (a[mid] > x) //search on left side
                high = mid - 2;
            else
                low = mid + 2;
        }

        return -1;

    }
}
