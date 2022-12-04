package DataStructureAlgo.Java.companyWise.Google;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Author: Nitin Gupta
 * Date: 14/04/19
 * Description: https://www.careercup.com/question?id=5745302387556352
 * google-interview-questions7
 * <p>
 * Give an array A of n integers where 1 <= a[i] <= K.
 * Find out the length of the shortest sequence that can be constructed out of numbers 1, 2, .. k that is NOT a sub-sequence of A.
 * eg. A = [4, 2, 1, 2, 3, 3, 2, 4, 1], K = 4
 * All single digits appears. Each of the 16 double digit sequences, (1,1), (1, 2), (1, 3), (1, 4), (2, 1), (2, 2) ... appears.
 * Because (1, 1, 2) doesn't appear, return 3.
 *
 *
 * Solution:
 *
 * Seeing no one is talking about progressively how to approach the problem, i though to take the chance and present a way to approach the problem;
 * now lets take a example;
 * A = [4, 2, 1, 2, 3, 3, 2, 4, 1], K = 4 => X[1,2,3,4]
 *
 * Brute Force:
 * Here what we are trying to do is basically try to find is there any subsequence of length (L) which present in X but not in A, right. What about if we just generate
 * all subsequence of length 1 to k and check does it present in A or not, the one which does not present is your answer;
 *
 * Algo:
 * 1. first check does all numbers are present in A or not ( L=1); if not return 1 otherwise go to step 2;
 * 2. Start generating different subsequence of L = 2 ... k, Let say this subsequence name is Y
 * 2.1 for each subsequence check LCS(A,Y)!=len(Y) [ we find the longest common subsequence of A and Y and if there is a subsequence in A of Y length then this is not your answer]
 * otherwise its your answer len(Y)
 * 3. Keep doing it until you find a seq Y which follow the rule LCS(A,Y)!=len(Y) ; if no one then answer is -1;
 *
 * Complexity:
 * Step 1: O(n)
 * Step 2: Generating all the subsequence of each length is big, which is in a nutshell generating all the subsequence, In k length array there are K^k subsequence.
 * Step 2.1 checking each in A, will take O(n*K)
 * Hence; O(K^k*n*K) = O(n*K^(k+1)) = O(n*K^k)
 *
 * Bottle neck; K^k
 *
 * Brute Force 2: with slight improvement
 * Instead blindly generating of every length subsequence from X, first find the longest common subsequence in LCS(A,X), why? this will guarantee to you that
 * this length subsequence is present in A and all the shorter than this also present in A (this is what LCS does).
 *
 * Algo:
 * 0. first check does all numbers are present in A or not ( L=1); if not return 1 otherwise go to step 1;
 * 1. Find the LCS(A,X) = len
 * 2. generate all the subsequence of length len from X, Say Y and keep matching them in A; LCS(A,Y)!=len(Y); then one don't match is your answer;
 * Complexity:
 * Step 1: O(n*k)
 * Step 2: Generating all the subsequence of len length is also big, which is in a nutshell generating all the subsequence of length (len), In k length array there are len^k subsequence.
 * Step 2.1 checking each in A, will take O(n*len)
 * Hence; O(n*k) + O(n*len*len^k) = O(n*len^(k+1))
 * This is just a slight improvement though the worst case is still O(n*K^k) [ i guess; correct me if i'm wrong]
 *
 * Bottle neck; len^k
 *
 * Bleed Force :
 * What we are doing wrong in above brute force is generating blindly & checking all subsequence of length len in A, right? We can avoid them
 *
 * 1. first check does all numbers are present in A or not ( L=1); if not return 1 otherwise go to step 1;
 * 2. Find the LCS(A,X) = len
 * 3. Backtrack the LCS array and find out all the subsequence of length len and keep them in a set;
 * 4. generate all the subsequence of length len from X, Say Y which is not in set and keep matching them in A; LCS(A,Y)!=len(Y); then one don't match is your answer;
 *
 * Complexity:
 * Step 1: O(n*k)
 * Step 2: Generating all the subsequence of len length is also big, which is in a nutshell generating all the subsequence of length (len), In k length array there are len^k subsequence.
 * Step 2.1 checking each in A, will take O(n*len)
 * Hence; O(n*k) + O(n*len*(len^k)/fact(len)) [ simplify it :P ]
 * This is just a slight improvement though the worst case is still O(n*K^k) [ i guess; correct me if i'm wrong]
 *
 * Still ; Bottle neck; len^k
 *
 *
 * Better solution:
 * If we observe carefully, what we are doing is just finding the subsequence of len size and checking them in A, which is nothing but marking out the bad possibility of subsequence of length len;
 * if we some how mark them in bit faster way, we can find our SCS is much faster way.
 *
 * Algo:
 * 1. Create an frequency array of size K+1; call it 'Y'
 * 2. count the frequency of each item of A and store them in the Y. [ this will tell all the subsequence of length present A of Y, each element tells the subsequence made by him from A ]
 * 3. Find the minimum and return min+1; Since all the subsequence of len=min is present in A and all the subsequence of length > min is also present in A [ there could be some combination which is not present but we are interested in smallest]
 *
 * Complexity:
 * Step 2: O(n);
 * Step 3: O(k)
 *
 * Complexity: O(n+k); yupee
 *
 * Can we do more better? Yes we can (slightly)
 *
 * Best Solution:
 * If we observe carefully, what we are doing is just finding the subsequence of len size and checking them in A, which is nothing but marking out the bad possibility of subsequence of length len;
 * if we some how mark them in bit faster way, we can find our SCS is much faster way.
 *
 * Algo:
 * 1. Create an min heap size K; heap; it contains two element in each node ( element, frequency) and heapify based on frequency; also keep a map for cross reference so that you can update the value in heap in constant time; Map<Integer, Node>
 * 2. now, start doing step 2 of above algo, every time you increase the frequency of element (from 1...k) update it in heap too and heapify.
 * 3. Find the minimum and return min+1; constant time
 *
 * Complexity:
 * Step 2: O(n*logk) amortized;
 * Step 3: O(1)
 * Complexity: O(nlogk); amortized; yupee
 */
public class ShortestSequence {


    static class Node implements Comparable<Node> {
        int item;
        int freq;


        public Node(int item, int freq) {
            this.item = item;
            this.freq = freq;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.freq, o.freq);
        }
    }

    public static void main(String []args) {


        int array[] = {4, 2, 1, 2, 3, 3, 2, 4, 1};

        test(array, 4);
        test(array, 3); //does not follow the rule

        int array2[] = {1, 2, 3, 4, 1, 2, 3, 4};
        test(array2, 4);

        int array3[] = {1, 1, 3, 4, 2, 2, 3, 4};
        test(array3, 4);

        int array4[] = {1, 1, 3, 2, 2, 2, 3, 3};
        test(array4, 4); //should return 1

        int array5[] = {1, 1, 2, 3, 4, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4}; //all till 4 length seq kicked out
        test(array5, 4);


    }

    private static void test(int array[], int k) {
        System.out.println(shortestSequence(array, k));
    }

    /**
     * Question asked to us to find the "Shortest seq". the Shortest seq start from 1 length to K length.
     *
     * @param array
     * @param k
     * @return
     */
    //O(n) + O(n*logk) = O(n*logk)
    private static int shortestSequence(int[] array, int k) {

        if (null == array)
            return -1;

        /**
         * Find is there any 1 length seq exist.
         * To find, if any of the number from 1 to K is not available in given array than it will make shortest sequence
         * O(n)
         */
        if (!ofSize1(array, k))
            return 1;

        /**
         * Now in order to make shortest sequence from 1 to k only, that is not present in A. All the element in A should be within the range of
         * 1 to k. Because any number which is not in the range would be
         */
        if (!validateInput(array, k)) {

            PriorityQueue<Node> pq = new PriorityQueue<>(k); //create a pq of size k only;
            Map<Integer, Node> map = new HashMap<>();

            for (int i = 0; i < array.length; i++) {
                if (map.containsKey(array[i])) {
                    Node n = map.get(array[i]);

                    n.freq++; //since this same node is also present in pq, so pq will automatically hepify it self due to cross reference

                } else {
                    Node n = new Node(array[i], 1);
                    map.put(array[i], n);
                    pq.offer(n);
                }
            }

            if (pq.isEmpty())
                return -1;
            Node n = pq.poll();


            return n.freq + 1;

        }

        return -1;
    }


    /**
     * O(n)
     * To find, if any of the number from 1 to K is not available in given array than it will make shortest sequence
     *
     * @param array
     * @param k
     * @return
     */
    private static boolean ofSize1(int[] array, int k) {
        IntStream stream = IntStream.range(1, k + 1);
        Set<Integer> set = stream.boxed().collect(Collectors.toSet());
        for (int i = 0; i < array.length; i++)
            if (set.contains(array[i]))
                set.remove(array[i]);


        return set.isEmpty();
    }


    private static boolean validateInput(int[] array, int k) {
        return Arrays.stream(array).filter(x -> !(x >= 1 && x <= k)).count() > 0;
    }
}
