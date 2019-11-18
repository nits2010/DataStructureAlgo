package Java.companyWise.Adobe;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-27
 * Description: https://www.geeksforgeeks.org/maximize-sum-selected-numbers-performing-following-operation/
 * Maximize the sum of selected numbers from an array to make it empty
 * Given an array of N numbers, we need to maximize the sum of selected numbers. At each step, you need to select a number Ai, delete one occurrence of it and delete all occurrences of Ai-1 and Ai+1 (if they exist) in the array. Repeat these steps until the array gets empty. The problem is to maximize the sum of the selected numbers.
 * <p>
 * Note: We have to delete all the occurrences of Ai+1 and Ai-1 elements if they are present in the array and not Ai+1 and Ai-1.
 * <p>
 * Examples:
 * <p>
 * Input : a[] = {1, 2, 3}
 * Output : 4
 * Explanation: At first step we select 1, so 1 and
 * 2 are deleted from the sequence leaving us with 3.
 * Then we select 3 from the sequence and delete it.
 * So the sum of selected numbers is 1+3 = 4.
 * <p>
 * <p>
 * Input : a[] =  {1, 2, 2, 2, 3, 4}
 * Output : 10
 * Explanation : Select one of the 2's from the array, so
 * 2, 2-1, 2+1 will be deleted and we are left with {2, 2, 4},
 * since 1 and 3 are deleted. Select 2 in next two steps,
 * and then select 4 in the last step.
 * We get a sum of 2+2+2+4=10 which is the maximum possible.
 * <p>
 * //===================================Example walk through ======================
 * {3,3,3,4}
 * If you apply based on value, then you'll end up having result as 4 only, since all 3 will be deleted
 * and if you apply based on occurrence then you'll get 9 as all 4 will be deleted
 * <p>
 * ----------
 * {1,1,1,1,2,3,4}
 * 1 has highest occurrence so lets choose 1
 * case 1: if you choose 1: -> 8 as output
 * 1[1,1,1,3,4]
 * 2[1,1,3,4]
 * 3[1,3,4]
 * 4[3,4] ; You stuck here now, you'll choose highest value so it will be 8 as output.
 * <p>
 * -----------
 * {1,2,2,2,3,3,4}
 * <p>
 * case ; choose 1
 * 1[3,3,4] -> 4 [3] -> 7
 * <p>
 * case : choose 2
 * 2[2,2,4] -> 10
 * <p>
 * case : choose 3
 * 3[1,3] => 7
 * <p>
 * case : choose 4
 * 4[1,2,2,2] -> 10
 * <p>
 * See both choosing highest value and highest occurrence gives answer but we have seen above highest value won't be the choice.
 * <p>
 * i.e. you always select the highest occurrence and when there is tie, you'll chose max value.
 * <p>
 * ------------
 * {1, 2, 2, 2, 3}
 * case 1: if you chose 1 : answer would be 4
 * 1 [3] : all a+1 => 1+1 = 2 will be deleted
 * 4
 * <p>
 * case 2: if you choose 3: answer would be 6
 * 2[2,2]
 * 6
 * <p>
 * case 3: if you choose 3 then ; answer would be 4
 * 3[1]
 * 4
 * <p>
 * So for input {1, 2, 2, 2, 3} output is 6
 * <p>
 * ------------
 * {1,2,2,2,3,3,4}
 * <p>
 * case ; choose 1
 * 1[3,3,4] -> 4 [3] -> 7
 * <p>
 * case : choose 2
 * 2[2,2,4] -> 10
 * <p>
 * case : choose 3
 * 3[1,3] => 7
 * <p>
 * case : choose 4
 * 4[1,2,2,2] -> 10
 * <p>
 * -------------
 * [3,3,3,4]
 * consider you choose 3 then
 * 3 [3,3] ; 4 will be deleted
 * 6[3] ; nothing deleted
 * 9[0] -> 9
 * <p>
 * while if you choose 4
 * 4[] ; all the element get deleted A-1 here 4-1= 3 ..so all 3 will be deleted.
 * -> 4
 * <p>
 * so you'll chose 3 only.
 * output 9
 * ------------
 * 1,1,1,1,1,1,2,2,2,2,2, 3
 * choose all 2 -> 10
 */
public class MaximizeSumSelectedNumbersFromArrayMakeEmpty {

    public static void main(String[] args) {
        test(new int[]{1, 3, 5, 7, 9, 11}, 36);
        test(new int[]{1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3}, 10);
        test(new int[]{3, 3, 3, 4}, 9);
        test(new int[]{1, 2, 2, 2, 3, 3, 4}, 10);
        test(new int[]{1, 2, 2, 2, 3}, 6);
        test(new int[]{1, 2, 2, 2, 3, 3, 4}, 10);
        test(new int[]{1, 1, 1, 1, 2, 3, 4}, 8);
        test(new int[]{1, 1, -1, 1, -2, 3, -4}, -2);
        test(new int[]{4, 1, -1, 1, -2, 3, -4}, -2);


    }

    private static void test(int[] nums, int expected) {
        System.out.println("\n Greedy " + maxSumSmartGreedy(nums) + " expected " + expected);
        try {
            System.out.println("Frequency " + maxSumByMaximumValue(nums) + " expected " + expected);
        } catch (InvalidArgumentException e) {
           System.out.println(e.getMessage());
        }
    }

    /**
     * After above dry run on example ,we can see that we need to choose element based on frequency.
     * But its is not necessary that a number with high frequency will give you the result
     * 1,1,1,1,1,1,2,2,2,2,2, 3
     * Here 1-> 6 while 2->5
     * if you choose 1 then you'll have 6+3 = 9
     * while if you choose 2 then you'll get 10
     * Why? because 2*5 > 1 * 6
     * <p>
     * So apparently you choose element based on below criteria
     * 'Frequency of number * number '
     * <p>
     * Worst case occurs when {1, 3, 5, 7, 9, 11}, you have to go through all elements
     * O(n*logn) time
     *
     * @param nums
     * @return
     */
    public static int maxSumSmartGreedy(int nums[]) {
        int n = nums.length;


        Map<Integer, int[]> freq = new HashMap<>();

        //O(n)
        for (int e : nums) {
            if (!freq.containsKey(e))
                freq.put(e, new int[]{e, 0});

            int[] item = freq.get(e);
            item[1]++;
            freq.put(e, item);

        }

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(((o1, o2) -> o2[0] * o2[1] - o1[0] * o1[1]));

        //O(n)
        priorityQueue.addAll(new ArrayList<>(freq.values()));


        int maxSum = 0;

        while (!freq.isEmpty()) { //O(n)

            //O(log(n))
            int item[] = priorityQueue.poll();
            if (freq.containsKey(item[0])) {
                maxSum += item[0] * item[1];

                freq.remove(item[0]);
                freq.remove(item[0] - 1);
                freq.remove(item[0] + 1);
            }
        }


        return maxSum;

    }

    /**
     * If above solution we understood that we should choose element based on 'Frequency of number * number '
     * if Elements in array are small but array size is big we can use O(Max) complexity to achieve
     * <p>
     * Let say
     * freq[i] : denotes the maximum sum can be obtained by i'th element ;
     * <p>
     * Base case:
     * whatever element we choose, its is sure that at least that number we'll get as our sum { freq(i) * i}
     * <p>
     * <p>
     * we'll run through evey element between 1 to Max -> n
     * at each place we'll have two choices
     * 1. Either select 'n' as our potential number
     * if so, then we'll get freq[n] * n and as well as freq[n-2] since all elements n-1 and n+1 will be removed so we'll end of having only n and previous solution which is n-2.
     * <p>
     * 2. Don't select 'n' as our potential number then we'll have n-1 as our potential solution
     * <p>
     * Hence
     * freq[i] = Max ( freq[i-1] , freq[i-2] + freq[i]*i }
     * where 2<=i<=MAX_ELEMENT
     * <p>
     * THIS solution does not work for -ve Numbers
     *
     * @param nums
     * @return
     */
    public static int maxSumByMaximumValue(int nums[]) throws InvalidArgumentException {


        int maxElement = Arrays.stream(nums).max().getAsInt();

        int freq[] = new int[maxElement + 1];

        //Base case: whatever element we choose, its is sure that at least that number we'll get as our sum { freq(i) * i}
        for (int e : nums) {
            if (e < 0)
                throw new InvalidArgumentException(new String[]{"Neg number not supported"});
            freq[e]++;
        }

        for (int i = 2; i <= maxElement; i++)
            freq[i] = Math.max(freq[i - 1], freq[i - 2] + freq[i] * i);

        return freq[maxElement];
    }
}

