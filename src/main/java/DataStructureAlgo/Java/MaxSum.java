package DataStructureAlgo.Java;

import DataStructureAlgo.Java.helpers.*;

import java.util.*;


/**
 * Author: Nitin Gupta
 * Date: 3/18/2026
 * Question Title:
 * Link:
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 * <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 *
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class MaxSum {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<Integer>();
        nums.add(2);
        nums.add(10);
        nums.add(13);
        nums.add(4);
        nums.add(9);
        nums.add(11);
        nums.add(3);
        nums.add(5);
        nums.add(7);

        //2, 10, 13, 4, 9, 11, 3, 5, 7
        List<List<Integer>> result = max_middle_sum(nums);

        for (int i = 0; i<result.size(); i++){
            for (int j = 0; j<3; j++){
                System.out.print(result.get(i).get(j) + " , ");
            }

            System.out.println();
        }

    }

    private static List<List<Integer>> max_middle_sum(List<Integer> nums){
        if (nums == null || nums.isEmpty()){
            return new ArrayList<>();
        }

        int n = nums.size();
        if (n % 3 != 0){
            return null;
        }

        Collections.sort(nums);


        int left = 0;
        int right = n-1;
        List<List<Integer>> subLists = new ArrayList<>();

        while (left < right) {

            int smallest = nums.get(left);
            int middle = nums.get(right-1);
            int largest = nums.get(right);

            List<Integer> temp = new ArrayList<>();
            temp.add(smallest);
            temp.add(middle);
            temp.add(largest);

            subLists.add(temp);

            // move pointers
            left += 1;
            right -=2;
        }

        return subLists;




    }
}
