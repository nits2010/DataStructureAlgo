package Java;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/12/18
 * Description:
 * https://www.geeksforgeeks.org/prime-numbers-after-prime-p-with-sum-s/
 * Prime numbers after prime P with sum S
 * Given three numbers sum S, prime P and N, find all N prime numbers after prime P such that their sum is equal to S.
 * <p>
 * Examples :
 * <p>
 * Input :  N = 2, P = 7, S = 28
 * Output : 11 17
 * Explanation : 11 and 17 are primes after
 * prime 7 and (11 + 17 = 28)
 * <p>
 * Input :  N = 3, P = 2, S = 23
 * Output :
 * 3 7 13
 * 5 7 11
 * Explanation : 3, 5, 7, 11 and 13 are primes
 * after prime 2. And (3 + 7 + 13 = 5 + 7 + 11
 * = 23)
 * <p>
 * Input :  N = 4, P = 3, S = 54
 * Output :
 * 5 7 11 31
 * 5 7 13 29
 * 5 7 19 23
 * 5 13 17 19
 * 7 11 13 23
 * 7 11 17 19
 * Explanation : All are prime numbers and
 * their sum is 54
 */
public class PrimeSum {

    public static void main(String[] args) {

        System.out.println(primeSum(2, 7, 28));
        System.out.println(primeSum(3, 2, 23));
        System.out.println(primeSum(4, 3, 54));
    }

    private static List<List<Integer>> primeSum(int n, int p, int sum) {

        List<Integer> allPrimes = AllPrimeNumbers.generatePrimeNumber(p + 1, sum);
        List<List<Integer>> result = new LinkedList<>();
        primeSum(0, n, 0, sum, new ArrayList<>(), allPrimes, result);
        return result;
    }


    private static void primeSum(int currentSum, int n, int index, int s, List<Integer> temp, List<Integer> allPrimes, List<List<Integer>> result) {

        //if we run out of prime number, to backtrack
        //Or if total prime number added in set is more than required n
        if (index == allPrimes.size() || temp.size() > n || currentSum > s) {
            return;
        }

        //If total sum found of given sum
        if (currentSum == s && temp.size() == n) {
            result.add(new ArrayList<>(temp));
            return;
        }

        //include this number to our set to try if it make sum=s
        temp.add(allPrimes.get(index));
        primeSum(currentSum + allPrimes.get(index), n, index + 1, s, temp, allPrimes, result);

        //exclude this number to our set to try if it make sum=s
        temp.remove(allPrimes.get(index));
        primeSum(currentSum, n, index + 1, s, temp, allPrimes, result);


    }


}
