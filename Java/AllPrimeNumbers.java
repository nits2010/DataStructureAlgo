package Java;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/12/18
 * Description:
 */
public class AllPrimeNumbers {

    public static void main(String []args) {
        int n = 50;

//        List<Integer> primes = generatePrimeNumber(0, n);
//
//        for (Integer prime : primes) {
//            System.out.print(prime + ",");
//        }


        List<Integer> primes = generatePrimeNumber(8, 28);

        for (Integer prime : primes) {
            System.out.print(prime + ",");
        }
    }


    public static List<Integer> generatePrimeNumber(int start, int end) {

        boolean primeNumberTemp[] = new boolean[end + 1];
        List<Integer> primeNumbers = new ArrayList<>();

        //generate list of number from 2 to n
        for (int it = 0; it <= end; it++) {
            primeNumberTemp[it] = true;
        }


        for (int prime = 2; prime <= end; prime++) {

            //if this number is prime
            if (primeNumberTemp[prime]) {
                if (prime > start)
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
