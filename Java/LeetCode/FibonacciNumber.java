package Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-30
 * Description:
 */
public class FibonacciNumber {

    public int fib(int n) {
        int a = 1, b = 1;

        if (n == 1 || n == 2)
            return 1;

        int c = 0;
        for (int i = 0; i < n - 2; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

}
