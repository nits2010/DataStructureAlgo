package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-18
 * Description: https://www.geeksforgeeks.org/find-the-element-that-appears-once/
 * <p>
 * Given an array where every element occurs three times, except one element which occurs only once. Find the element that occurs once. Expected time complexity is O(n) and O(1) extra space.
 * Examples :
 * <p>
 * Input: arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3}
 * Output: 2
 * In the given array all element appear three times except 2 which appears once.
 * <p>
 * Input: arr[] = {10, 20, 10, 30, 10, 30, 30}
 * Output: 20
 * In the given array all element appear three times except 20 which appears once.
 */
public class NumberOccurringOnce {

    public static void main(String args[]) {
        int arr[] = {12, 1, 12, 3, 12, 1, 1, 2, 3, 3};
        System.out.println(numberOccurringOnce(arr, 3));
        System.out.println(numberOccurringOnceFaster(arr, 3));

        int arr2[] =  {10, 20, 10, 30, 10, 30, 30};
        System.out.println(numberOccurringOnce(arr2, 3));
        System.out.println(numberOccurringOnceFaster(arr2, 3));

        int arr3[] =  {5,5,5,8};
        System.out.println(numberOccurringOnce(arr3, 3));
        System.out.println(numberOccurringOnceFaster(arr3, 3));

    }

    private static int numberOccurringOnce(int[] arr, int n) {

        int result = 0;
        int sum ;

        for (int i = 0; i < 32; i++) {

            sum = 0;
            int x = 1 << i;
            for (int j = 0; j < arr.length; j++) {

                if ((arr[j] & x) != 0)
                    sum++;

            }
            sum = sum % n;
            if (sum != 0)
                result = result | x;
        }

        return result;
    }

    private static int  numberOccurringOnceFaster(int[] arr, int n){

        int occurringOnce = 0;
        int occurringTwice = 0;

        for (int i = 0; i<arr.length; i++){

            occurringOnce = occurringOnce^arr[i] & ~occurringTwice;
            occurringTwice = occurringTwice^arr[i] & ~occurringOnce;

        }
        return occurringOnce;
    }
}
