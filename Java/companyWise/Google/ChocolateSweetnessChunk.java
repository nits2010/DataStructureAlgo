package Java.companyWise.Google;

import Java.nonleetcode.PainterPartitionProblem;

import java.util.Arrays;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-05
 * Description: https://leetcode.com/discuss/interview-question/350800/google-onsite-chocolate-sweetness
 * Given an array chocolate of n non-negative integers, where the values are sweetness levels of the chocolate. You are also given a value k which denotes the number of friends you will share this chocolate with. Your friends are greedy so they will always take the highest sweetness chunk. Find out what is the maximum sweetness level you could get.
 * <p>
 * tltr: Split the array into k non-empty continuous subarrays. Write an algorithm to maximize the minimum sum among these k subarrays.
 * <p>
 * Example:
 * <p>
 * Input: chocolate = [6, 3, 2, 8, 7, 5], k = 3
 * Output: 9
 * Explanation:
 * The values in array are sweetness level in each chunk of chocolate. Since k = 3, so you have to divide this array in 3 pieces,
 * such that you would get maximum out of the minimum sweetness level. So, you should divide this array in
 * [6, 3] -> 6 + 3 = 9
 * [2, 8] -> 2 + 8 = 10
 * [7, 5] -> 7 + 5 = 12
 * Your other two friends will take the sweetest chunk, so they will take 12 and 10. The maximum sweetness level you could get is 9.
 * <p>
 * Similar to {@link PainterPartitionProblem}
 */
public class ChocolateSweetnessChunk {

    public static void main(String[] args) {

        ChocolateSweetnessChunkSolution solution = new ChocolateSweetnessChunkSolution();

        System.out.println(solution.chocolateSweetnessChunk(new int[]{6, 3, 2, 8, 7, 5}, 3));
        System.out.println(solution.chocolateSweetnessChunk(new int[]{6, 3, 2, 8}, 3));

    }
}

/**
 * For explanation see {@link PainterPartitionProblem Dp solution}
 * M[i][k] defines the sweetness of chocolates i chocolate with k people
 * <p>
 * *       M[i][k] = Min {
 * *                        Max {
 * *                           M[j][k-1] where j varies from 0<= j <i
 * *                               + Sum [ chocolate[m] ; m from j to n-1 ]
 * *                           }
 * }
 */
class ChocolateSweetnessChunkSolutionDP {

}

/**
 * We know that when we have
 *
 * K people and n chocolates then sweetness would Minimum of (chocolate) -> low
 * 1 people and n chocolates then sweetness would be Sum (chocolates) -> High
 * Which means, our solution lies between [low, high]
 * we can apply binary search to find what is the value which suffices the condition.
 * But How we find?
 *
 * {10,20,30,40} l L: 40 , H = 100 => Mid = 70 How do we find that in 70 sweetness we will n chocolates with k people? =>
 * What we can do is sequentially adding the sweetness of chocolate to a people, as soon as we found this person can't have more
 * (which is 70 in this) then we start giving chocolate to new people and keep doing the same with new people...
 * this way we can find how many people would require to have this chocolate, assuming they can't have more than 70.
 *
 * 1. When number required person > available people then we need to increase our sweetness by low = mid+1
 * 2. otherwise reduce it by high = mid and its our potential solution.
 */
// O(n * log (sum - min)).
class ChocolateSweetnessChunkSolution {

    public int chocolateSweetnessChunk(int chocolates[], int people) {

        if (null == chocolates || chocolates.length == 0 || people == 0)
            return 0;

        int low = Arrays.stream(chocolates).min().getAsInt();
        int high = Arrays.stream(chocolates).sum();

        //O(n * log (sum - min)).
        while (low < high) {

            int sweetnessLevel = (low + high) >> 1;

            int requiredPeople = peopleRequired(chocolates, people, sweetnessLevel);

            if (requiredPeople > people) {
                low = sweetnessLevel + 1;


            } else {

                high = sweetnessLevel;

            }
        }

        return low;
    }

    //O(n)
    private int peopleRequired(int[] chocolates, int people, int sweetnessLevel) {

        int minimumPeople = 1;
        int currentSweetness = 0;

        for (Integer s : chocolates) {

            currentSweetness += s;

            if (currentSweetness > sweetnessLevel) {
                minimumPeople++;
                currentSweetness = 0;

                if (minimumPeople > people)
                    return minimumPeople;
            }

        }

        return minimumPeople;
    }
}