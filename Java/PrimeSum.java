package Java;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/12/18
 * Description:
 * https://www.geeksforgeeks.org/prime-numbers-after-prime-p-with-sum-s/
 */
public class PrimeSum {

    public static void main(String args[]) {

        int s, p, n;

        n = 2;
        p = 7;
        s = 28;

        System.out.println(isPrimeSum(primeSum(n, p, s), s));


        n = 2;
        p = 3;
        s = 54;

        System.out.println(isPrimeSum(primeSum(n, p, s), s));



    }

    private static List<List<Integer>> primeSum(int n, int p, int s) {

        List<Integer> allPrimes = AllPrimeNumbers.generatePrimeNumber(p + 1, s);

        Set<Integer> numbers = new HashSet<>();

        List<List<Integer>> result = new LinkedList<>();

        primeSum(0, n, 0, s, numbers, allPrimes, result);

        return result;
    }


    private static void primeSum(int totalSum, int n, int index, int s, Set<Integer> numbers, List<Integer> allPrimes, List<List<Integer>> result) {


        //If total sum found of given sum
        if (totalSum == s) {
            result.add(new LinkedList<>(numbers));

            return;
        }

        //if we run out of prime number, to backtrack
        //Or if total prime number added in set is more than required n
        if (index == allPrimes.size() || numbers.size() > n) {
            return;
        }


        //include this number to our set to try if it make sum=s
        numbers.add(allPrimes.get(index));
        primeSum(totalSum + allPrimes.get(index), n, index + 1, s, numbers, allPrimes, result);

        //exclude this number to our set to try if it make sum=s
        numbers.remove(allPrimes.get(index));
        primeSum(totalSum, n, index + 1, s, numbers, allPrimes, result);


    }

    public static boolean isPrimeSum(List<List<Integer>> primeSumNbrs, int s) {

        boolean response = true;
        for (List<Integer> l : primeSumNbrs) {
            int sum = 0;
            for (Integer i : l)
                sum += i;

            response &= (sum == s);

        }
        if (response)
            System.out.println(primeSumNbrs);
        return response;

    }
}
