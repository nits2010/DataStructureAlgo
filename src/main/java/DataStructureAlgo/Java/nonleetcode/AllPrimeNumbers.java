package DataStructureAlgo.Java.nonleetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 20/12/18
 * Question Title: All Prime Numbers
 * Link: https://leetcode.com/problems/all-prime-numbers/
 * Description:
 * Description: Print all the prime number between [s,e]
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
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public class AllPrimeNumbers {

    public static void main(String[] args) {
        test(0, 50);
        test(8, 28);
    }

    private static void test(int start, int end) {
        System.out.println("Start :" + start + " end :" + end);
        List<Integer> primes = generatePrimeNumber(start, end);
        System.out.println(" Primes :" + primes);

    }


    public static List<Integer> generatePrimeNumber(int start, int end) {

        boolean[] primeNumberTemp = new boolean[end + 1];
        List<Integer> primeNumbers = new ArrayList<>();

        //generate list of number from 2 to n
        for (int it = 0; it <= end; it++) {
            primeNumberTemp[it] = true;
        }


        for (int prime = 2; prime <= end; prime++) {

            //if this number is prime
            if (primeNumberTemp[prime]) {
                if (prime >= start)
                    primeNumbers.add(prime);

                //mark all the multiple of it
                markMultiple(primeNumberTemp, prime);
            }
        }


        return primeNumbers;
    }

    private static void markMultiple(boolean[] primeNumberTemp, int prime) {
        for (int i = prime * prime; i < primeNumberTemp.length; i += prime)
            primeNumberTemp[i] = false;

    }
}
