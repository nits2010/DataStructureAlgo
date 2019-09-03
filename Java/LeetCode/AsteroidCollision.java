package Java.LeetCode;

import Java.HelpersToPrint.Printer;

import java.util.Arrays;
import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-09-03
 * Description: https://leetcode.com/problems/asteroid-collision/
 * 735. Asteroid Collision
 * We are given an array asteroids of integers representing asteroids in a row.
 * <p>
 * For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 * <p>
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation:
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * Example 2:
 * <p>
 * Input:
 * asteroids = [8, -8]
 * Output: []
 * Explanation:
 * The 8 and -8 collide exploding each other.
 * Example 3:
 * <p>
 * Input:
 * asteroids = [10, 2, -5]
 * Output: [10]
 * Explanation:
 * The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in 10.
 * Example 4:
 * <p>
 * Input:
 * asteroids = [-2, -1, 1, 2]
 * Output: [-2, -1, 1, 2]
 * Explanation:
 * The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet each other.
 * Note:
 * <p>
 * The length of asteroids will be at most 10000.
 * Each asteroid will be a non-zero integer in the range [-1000, 1000]..
 *
 * https://leetcode.com/problems/asteroid-collision/discuss/374043/Java-or-Full-thought-process-or-Brute-force-to-100-beat-or-Various-solutions
 */
public class AsteroidCollision {

    public static void main(String[] args) {

        test(new int[]{-2, 1, 1, -1}, new int[]{-2, 1});
        test(new int[]{-2, 2, 1, -2}, new int[]{-2});
        test(new int[]{5, 10, -5}, new int[]{5, 10});
        test(new int[]{8, -8}, new int[]{});
        test(new int[]{10, 2, -5}, new int[]{10});
        test(new int[]{-2, -1, 1, 2}, new int[]{-2, -1, 1, 2});
        test(new int[]{5, 8, -2, 8, -10, 2, -6, 3, 7}, new int[]{-10, -6, 3, 7});
        test(new int[]{5, 8, -2, 8, -10, -2, -6, 3, -7, -10, -32, 90, -90, -29}, new int[]{-10, -2, -6, -7, -10, -32, -29});

    }

    private static void test(int[] asteroids, int[] expected) {
        System.out.println("\n Input :" + Printer.toString(asteroids) + " expected :" + Printer.toString(expected));

        AsteroidCollisionSolution bruteForce = new AsteroidCollisionBruteForce();
        AsteroidCollisionSolution stack = new AsteroidCollisionStack();
        AsteroidCollisionExplicitStack explicitStack = new AsteroidCollisionExplicitStack();

        test(Arrays.copyOfRange(asteroids, 0, asteroids.length), bruteForce, "bruteForce");
        test(Arrays.copyOfRange(asteroids, 0, asteroids.length), stack, "stack");
        test(Arrays.copyOfRange(asteroids, 0, asteroids.length), explicitStack, "Explicit stack");

    }

    private static void test(int[] asteroids, AsteroidCollisionSolution solution, String type) {
        System.out.println(type + " -> " + Printer.toString(solution.asteroidCollision(asteroids)));
    }
}


interface AsteroidCollisionSolution {
    int[] asteroidCollision(int[] asteroids);
}

/**
 * A positive value will be zero if and only if there is negative value on right side of it that is greater(equal) than this value abs
 * A negative value will be zero if and only if there is positive value of left side of it that is greater(equal) than this value abs
 * <p>
 * Brute force:
 * 1. For each element see on right side
 * case 1: if this value is Positive , see the negative value on right side till you find bigger than this abs,
 * case 2: if this value is -ve value, see the positive value on left side till you find bigger than this abs.
 * Every time there is collision, mark the smaller asteroid dead or if they are equal, mark both dead
 * <p>
 * 2. Re-iterate on array, collect non-dead asteroid
 *
 * <p>
 * Complexity: O(n^2) / O(1)
 * Runtime: 12 ms, faster than 52.37% of Java online submissions for Asteroid Collision.
 * Memory Usage: 38.5 MB, less than 100.00% of Java online submissions for Asteroid Collision.
 */
class AsteroidCollisionBruteForce implements AsteroidCollisionSolution {

    public int[] asteroidCollision(int[] asteroids) {
        if (null == asteroids || asteroids.length == 0)
            return asteroids;

        final int DEAD = Integer.MAX_VALUE;

        for (int i = 0; i < asteroids.length; i++) {

            /**
             * If this is already dead, skip this
             */
            if (asteroids[i] == DEAD)
                continue;

            /**
             * This is positive, we need to find a neg on right side >= this
             */
            if (asteroids[i] > 0) {

                for (int j = i + 1; j < asteroids.length; j++) {

                    int temp = asteroids[j];

                    /**
                     * If this is already dead, skip this
                     */
                    if (temp == DEAD)
                        continue;

                    /**
                     * Check does this our potential asteroids which destroy i'th asteroid
                     */
                    if (temp < 0) {

                        if (Math.abs(temp) >= asteroids[i]) {

                            if (Math.abs(temp) == Math.abs(asteroids[i]))
                                asteroids[j] = DEAD;

                            asteroids[i] = DEAD;

                            break;
                        } else {
                            //means j'th asteroid is smaller then on right side, then it will die fighting with asteroids[i]
                            asteroids[j] = DEAD;
                        }
                    }
                    //if this is positive, break for i, as this asteroid will protect i'th asteroid
                    if (temp > 0)
                        break;
                }

            }
            //same as +ve one
            if (asteroids[i] < 0) {

                for (int j = i - 1; j >= 0; j--) {

                    int temp = asteroids[j];
                    if (temp == DEAD)
                        continue;

                    if (temp > 0) {

                        if (temp >= Math.abs(asteroids[i])) {

                            if (Math.abs(temp) == Math.abs(asteroids[i]))
                                asteroids[j] = DEAD;

                            asteroids[i] = DEAD;

                            break;
                        } else {
                            asteroids[j] = DEAD;
                        }
                    }
                    if (temp < 0)
                        break;
                }

            }

        }

        //Collect non-dead asteroid
        int output[] = new int[asteroids.length];
        int x = 0;
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != DEAD)
                output[x++] = asteroids[i];
        }

        return Arrays.copyOfRange(output, 0, x);
    }
}

/**
 * In above {@link AsteroidCollisionBruteForce} the biggest problem is we need to keep iterate left and right for every asteroid in order to see
 * does it will dies or not (or destroy some other).
 * <p>
 * Since
 * A positive value will be zero if and only if there is negative value on right side of it that is greater(equal) than this value abs
 * A negative value will be zero if and only if there is positive value of left side of it that is greater(equal) than this value abs
 * <p>
 * This means, if we know is there any asteroid on left side of current  asteroid[i] (and -ve) which is smaller(equal) to this asteroid[i], then we can take the decision about this asteroid[i].
 * Otherwise we'll wait till we find a negative asteroid which comes and destroy it.
 * <p>
 * This reminds us a similar problem {@link Java.SmallerElementOnLeftSide}.
 * <p>
 * To find efficiently information about the left side asteroid, we can use stack.
 * <p>
 * Algo:
 * 1. if this is positive element, push it to stack and wait till you find a negative asteroid which can kill this
 * 2. if this is negative element, see in stack how many asteroid it can destroy. Important point is, if you find any two asteroid which are opposite in sign but abs value is same. we stop there
 * <p>
 * O(n)/O(n)
 * Runtime: 13 ms, faster than 41.54% of Java online submissions for Asteroid Collision.
 * Memory Usage: 38.8 MB, less than 100.00% of Java online submissions for Asteroid Collision.
 */
class AsteroidCollisionStack implements AsteroidCollisionSolution {


    public int[] asteroidCollision(int[] asteroids) {
        if (null == asteroids || asteroids.length == 0)
            return asteroids;

        Stack<Integer> seenAsteroids = new Stack<>();

        for (int i = 0; i < asteroids.length; i++) {

            //Current asteroid moving in right direction, it won't destroy any previous asteroid
            if (asteroids[i] > 0) {
                seenAsteroids.push(asteroids[i]);
            } else {
                //Current asteroid moving in left direction, it may destroy any previous asteroid

                //when previous asteroid die ; previous < current
                while (!seenAsteroids.isEmpty() && seenAsteroids.peek() > 0 && seenAsteroids.peek() < Math.abs(asteroids[i]))
                    seenAsteroids.pop();

                //does current and previous asteroid both die ?
                if (!seenAsteroids.isEmpty() && seenAsteroids.peek() == Math.abs(asteroids[i])) {
                    seenAsteroids.pop();
                } else if (!seenAsteroids.isEmpty() && seenAsteroids.peek() > Math.abs(asteroids[i])) {
                    //does current asteroid will die ?
                    continue;
                } else {
                    seenAsteroids.push(asteroids[i]);
                }

            }
        }

        int output[] = new int[seenAsteroids.size()];
        int x = seenAsteroids.size() - 1;
        while (!seenAsteroids.isEmpty())
            output[x--] = seenAsteroids.pop();

        return output;

    }

    public int[] asteroidCollisionDescribed(int[] asteroids) {
        if (null == asteroids || asteroids.length == 0)
            return asteroids;
        Stack<Integer> seenAsteroids = new Stack<>();
        boolean dead;

        for (int i = 0; i < asteroids.length; i++) {
            dead = false;
            if (asteroids[i] < 0) {


                while (!seenAsteroids.isEmpty()) {


                    if (seenAsteroids.peek() > 0) {

                        //does current asteroid will die ?
                        if (seenAsteroids.peek() > Math.abs(asteroids[i])) {
                            dead = true;
                            break;
                        } else if (seenAsteroids.peek() < Math.abs(asteroids[i])) {
                            // previous asteroid will die
                            seenAsteroids.pop();
                        } else {
                            //both die
                            dead = true;
                            seenAsteroids.pop();
                            break;
                        }
                    } else
                        break;

                }
            }
            if (!dead)
                seenAsteroids.push(asteroids[i]);
        }

        int output[] = new int[seenAsteroids.size()];
        int x = seenAsteroids.size() - 1;
        while (!seenAsteroids.isEmpty())
            output[x--] = seenAsteroids.pop();

        return output;

    }


}


/**
 * O(n)/O(n)
 * Runtime: 2 ms, faster than 99.33% of Java online submissions for Asteroid Collision.
 * Memory Usage: 40.6 MB, less than 87.50% of Java online submissions for Asteroid Collision.
 */
class AsteroidCollisionExplicitStack implements AsteroidCollisionSolution {


    public int[] asteroidCollision(int[] asteroids) {
        if (null == asteroids || asteroids.length == 0)
            return asteroids;

        int stack[] = new int[asteroids.length];
        int top = -1;

        for (int i = 0; i < asteroids.length; i++) {

            //Current asteroid moving in right direction, it won't destroy any previous asteroid
            if (asteroids[i] > 0) {
                stack[++top] = asteroids[i];

            } else {
                //Current asteroid moving in left direction, it may destroy any previous asteroid
                while (top >= 0 && stack[top] > 0 && stack[top] < Math.abs(asteroids[i]))
                    --top;


                //does current and previous asteroid both die ?
                if (top >= 0 && stack[top] == Math.abs(asteroids[i]))
                    --top;
                else if (top >= 0 && stack[top] > Math.abs(asteroids[i]))
                    //does current asteroid will die ?
                    continue;
                else
                    stack[++top] = asteroids[i];


            }
        }

        return Arrays.copyOfRange(stack, 0, top + 1);

    }


}