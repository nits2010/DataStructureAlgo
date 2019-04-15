package Java;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 14/04/19
 * Description: https://www.careercup.com/question?id=5745302387556352
 * google-interview-questions7
 * <p>
 * Give an array A of n integers where 1 <= a[i] <= K.
 * Find out the length of the shortest sequence that can be constructed out of numbers 1, 2, .. k that is NOT a subsequence of A.
 * eg. A = [4, 2, 1, 2, 3, 3, 2, 4, 1], K = 4
 * All single digits appears. Each of the 16 double digit sequences, (1,1), (1, 2), (1, 3), (1, 4), (2, 1), (2, 2) ... appears. Because (1, 1, 2) doesn't appear, return 3.
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

    public static void main(String args[]) {


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

    //O(n) + O(n*logk) = O(n*logk)
    private static int shortestSequence(int[] array, int k) {

        if (null == array)
            return -1;

        //O(n)
        if (!ofSize1(array, k))
            return 1;

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

    //O(n)
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
