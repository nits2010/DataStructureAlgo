package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Heap._1405;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: Nitin Gupta
 * Date:16/10/24
 * Question Category: 1405. Longest Happy String
 * Description: https://leetcode.com/problems/longest-happy-string/description
 * A string s is called happy if it satisfies the following conditions:
 * <p>
 * s only contains the letters 'a', 'b', and 'c'.
 * s does not contain any of "aaa", "bbb", or "ccc" as a substring.
 * s contains at most a occurrences of the letter 'a'.
 * s contains at most b occurrences of the letter 'b'.
 * s contains at most c occurrences of the letter 'c'.
 * Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".
 * <p>
 * A substring is a contiguous sequence of characters within a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 * Example 2:
 * <p>
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It is the only correct answer in this case.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= a, b, c <= 100
 * a + b + c > 0
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 * @medium
 * @String
 * @Greedy
 * @Heap(PriorityQueue) <p>
 * Company Tags
 * -----
 * @Wayfair
 *
 * @Editorial
 * @OptimalSoltuion {@link SolutionSimple}
 */

public class LongestHappyString_1405 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(1, 1, 7, List.of("ccaccbcc"));
        test &= test(7, 1, 0, List.of("aabaa"));
        test &= test(0, 0, 7, List.of("cc"));
        test &= test(7, 7, 7, List.of("acbcbabacacbcbabacacb", "abcabcabcabcabcabcabc"));
        test &= test(1, 1, 1, List.of("acb", "abc"));
        test &= test(100, 100, 100, List.of("acbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacbcbabacacb", "abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc"));
        test &= test(0, 8, 11, List.of("ccbccbcbcbcbcbcbcbc"));

        CommonMethods.printResult(test);

    }

    private static boolean test(int a, int b, int c, List<String> expected) {
        System.out.println("--------------------------------");
        System.out.println("Input:" + a + " " + b + " " + c + " expected:" + expected);
        String output;
        boolean pass, finalPass = true;

        SolutionPQ solutionPQ = new SolutionPQ();
        output = solutionPQ.longestDiverseString(a, b, c);
        pass = expected.contains(output);
        System.out.println("PQ: " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        SolutionMimicPQ solutionMimicPQ = new SolutionMimicPQ();
        output = solutionMimicPQ.longestDiverseString(a, b, c);
        pass = expected.contains(output);
        System.out.println("Solution Mimic PQ: " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;

        SolutionSimple solutionSimple = new SolutionSimple();
        output = solutionSimple.longestDiverseString(a, b, c);
        pass = expected.contains(output);
        System.out.println("Solution Simple: " + output + " Pass : " + (pass ? "Passed" : "Failed"));
        finalPass &= pass;
        return finalPass;
    }
    //O(n)/O(1)
    static class SolutionSimple {

        public String longestDiverseString(int a, int b, int c) {
            StringBuilder s = new StringBuilder();
            int maxResultSize = a + b + c;

            //count of a,b,c
            int continuousA = 0, continuousB = 0, continuousC = 0;
            boolean choose = false;

            for (int i = 0; i < maxResultSize; i++) {

                //we can choose `a`
                if (continuousA != 2 && a > 0 && ((a >= b && a >= c) || continuousB == 2 || continuousC == 2)) {
                    s.append('a');
                    a--;
                    continuousA++;
                    continuousB = 0;
                    continuousC = 0;
                    choose = true;
                } else if (continuousB != 2 && b > 0 && ((b >= a && b >= c) || continuousA == 2 || continuousC == 2)) {
                    s.append('b');
                    b--;
                    continuousB++;
                    continuousA = 0;
                    continuousC = 0;
                    choose = true;
                } else if (continuousC != 2 && c > 0 && ((c >= a && c >= b) || (continuousA == 2 || continuousB == 2))) {
                    s.append('c');
                    c--;
                    continuousC++;
                    continuousA = 0;
                    continuousB = 0;
                    choose = true;
                }


                if (!choose)
                    break;

                choose = false;


            }
            return s.toString();


        }

    }


    //O(n) / O(1) PQ is constant size
    static class SolutionPQ {
        static class Pair {
            public char item;
            public int count;

            Pair(char i, int c) {
                this.item = i;
                this.count = c;
            }

        }

        public String longestDiverseString(int a, int b, int c) {
            StringBuilder s = new StringBuilder();


            PriorityQueue<Pair> available = new PriorityQueue<>((p1, p2) -> p2.count - p1.count);

            if (a > 0)
                available.offer(new Pair('a', a));

            if (b > 0)
                available.offer(new Pair('b', b));

            if (c > 0)
                available.offer(new Pair('c', c));


            while (!available.isEmpty()) {
                Pair pair = available.poll();

                //check last two char are same as this pair.char or not
                if (s.length() >= 2 && s.charAt(s.length() - 1) == pair.item && s.charAt(s.length() - 2) == pair.item) {
                    //last poll was already in the string 2 times, we have to use another one

                    //poll another char
                    if (available.isEmpty())
                        break;

                    Pair temp = available.poll();

                    //use this as this is different form last 2 char
                    s.append(temp.item);
                    temp.count--;

                    //offer it for later
                    if (temp.count > 0)
                        available.offer(temp);
                } else {
                    //not same as last 2 char, offer it
                    s.append(pair.item);
                    pair.count--;
                }

                if (pair.count > 0)
                    available.offer(pair);


            }

            return s.toString();
        }
    }


    //O(n)/  O(1) Pair object is constant size
    static class SolutionMimicPQ {

        static class Pair {
            public char item;
            public int count;

            Pair(char i, int c) {
                this.item = i;
                this.count = c;
            }

        }

        private Pair poll(Pair a, Pair b, Pair c, Pair exclude) {
            if (exclude == null) {
                if (a.count >= b.count && a.count >= c.count)
                    return a;
                if (b.count >= a.count && b.count >= c.count)
                    return b;
                return c;
            } else if (exclude == a) {
                if (b.count >= c.count)
                    return b;
                return c;
            } else if (exclude == b) {
                if (a.count >= c.count)
                    return a;
                return c;
            } else {
                if (a.count >= b.count)
                    return a;
                return b;
            }
        }

        private boolean isEmpty(Pair a, Pair b, Pair c, Pair exclude) {
            if (exclude == null)
                return a.count == 0 && b.count == 0 && c.count == 0;
            if (exclude == a)
                return b.count == 0 && c.count == 0;
            if (exclude == b)
                return a.count == 0 && c.count == 0;
            if (exclude == c)
                return b.count == 0 && a.count == 0;

            return false;
        }

        public String longestDiverseString(int a, int b, int c) {
            StringBuilder s = new StringBuilder();

            Pair aP = new Pair('a', a);
            Pair bP = new Pair('b', b);
            Pair cP = new Pair('c', c);


            while (!isEmpty(aP, bP, cP, null)) {
                Pair pair = poll(aP, bP, cP, null);

                //check last two char are same as this pair.char or not
                if (s.length() >= 2 && s.charAt(s.length() - 1) == pair.item && s.charAt(s.length() - 2) == pair.item) {
                    //last poll was already in the string 2 times, we have to use another one

                    //poll another char
                    if (isEmpty(aP, bP, cP, pair))
                        break;

                    Pair temp = poll(aP, bP, cP, pair);

                    //use this as this is different form last 2 char
                    s.append(temp.item);
                    temp.count--;

                    //offer it for later

                } else {
                    //not same as last 2 char, offer it
                    s.append(pair.item);
                    pair.count--;
                }

            }

            return s.toString();
        }
    }


}
