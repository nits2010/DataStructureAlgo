package DataStructureAlgo.Java.LeetCode2025.ProblemSet.List;


import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.helpers.templates.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 382. Linked List Random Node [Medium]
 * Description: https://leetcode.com/problems/linked-list-random-node
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 *
 * Implement the Solution class:
 *
 * Solution(ListNode head) Initializes the object with the head of the singly-linked list head.
 * int getRandom() Chooses a node randomly from the list and returns its value. All the nodes of the list should be equally likely to be chosen.
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 3, 2, 2, 3]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // return 1
 * solution.getRandom(); // return 3
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 3
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 *
 *
 * Constraints:
 *
 * The number of nodes in the linked list will be in the range [1, 104].
 * -104 <= Node.val <= 104
 * At most 104 calls will be made to getRandom.
 *
 *
 * Follow up:
 *
 * What if the linked list is extremely large and its length is unknown to you?
 * Could you solve this efficiently without using extra space?
 *
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link }
 * extension {@link }
 * <p>
 *
 * Tags
 * -----
 * @medium
 * @LinkedList
 * @Recursion
 * @Math
 * @ReservoirSampling
 * @Randomized
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Microsoft
 * @Amazon
 * @Google
 *
 * @Editorial <a href="https://leetcode.com/problems/linked-list-random-node/solutions/5562764/multiple-solution-with-without-extra-space-3-counting-length-without-counting-length-and-spa/">...</a>
 */
public class LinkedListRandomNode_382 {

    public static void main(String[] args) {
        boolean testResult = test(new Integer[]{1,2,3});
        testResult &= test(new Integer[]{1});
        testResult &= test(new Integer[]{});
        System.out.println("\nTest result ="+(testResult?" Pass":" Fail"));
    }
    private static boolean test(Integer[] input){
        ListNode head = ListBuilder.arrayToSinglyList(input);
        LinkedListRandomNodeWithoutExtraSpaceWithoutLength.Solution solution = new LinkedListRandomNodeWithoutExtraSpaceWithoutLength.Solution(head);
        // Collect results from getRandom
        Set<Integer> results = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            results.add(solution.getRandom());
        }

        //assert list
        ListNode temp = head;
        while (temp!=null){
            if(!results.contains(temp.val))
                return false;
            temp = temp.next;
        }

        return true;
    }
}

/**
 *
 * Using extra space { hashmap, array etc}
 * Cache nodes in hashmap and then get random node from hashmap between [0,length)
 * O(n)/O(n)
 * O(n) is to cache hashmap, random method is O(1)
 *
 * 11 ms Beats 52.80% https://leetcode.com/problems/linked-list-random-node/submissions/1339362911/?envType=problem-list-v2&envId=m4ly4d57
 *
 */
class LinkedListRandomNodeUsingHashMap {

    static class Solution {

        final Random random = new Random();
        final Map<Integer, ListNode> mapIndexVsNode ;
        int length = 0 ;
        public Solution(ListNode head) {
            mapIndexVsNode = new HashMap<>();
            int index = 0;

            if(head == null)
                return;

            while (head != null) {
                mapIndexVsNode.put(index, head);
                head = head.next;
                index++;
            }
            length = index;

        }

        public int getRandom() {
            if(mapIndexVsNode.isEmpty())
                return -1;


            int index = random.nextInt(length);
            return mapIndexVsNode.get(index).val; //O(1)
        }
    }

}

/**
 *
 * Using extra space { hashmap, array etc}
 * Cache nodes in hashmap and then get random node from hashmap between [0,length)
 * O(n)/O(n)
 * O(n) is to cache hashmap, random method is O(1)
 *
 * 11 ms Beats 52.80% https://leetcode.com/problems/linked-list-random-node/submissions/1339362911/?envType=problem-list-v2&envId=m4ly4d57
 *
 */
class LinkedListRandomNodeUsingArray {

    static class Solution {

        int [] nodeVal = new int[10000] ;
        int length = 0 ;
        Random random = new Random();
        public Solution(ListNode head) {
            if(head == null)
                return;
            while (head != null) {
                nodeVal[length] = head.val;
                head = head.next;
                length++;
            }

        }

        public int getRandom() {
            if(nodeVal.length==0 || length == 0)
                return -1;
            int index = random.nextInt(length);
            return nodeVal[index];
        }
    }

}

/**
 * 10 ms Beats  85.68%
 */
class LinkedListRandomNodeUsingArray2 {

    static class Solution {

        ArrayList<Integer> nodeVal   ;

        Random random = new Random();

        public Solution(ListNode head) {
            nodeVal = new ArrayList<>();
            if(head == null)
                return;

            while (head != null) {
                nodeVal.add(head.val);
                head = head.next;
            }



        }

        public int getRandom() {
            if(nodeVal.isEmpty())
                return -1;
            int index = random.nextInt(nodeVal.size());
            return nodeVal.get(index);
        }
    }

}


/**
 * Without extra space
 * 1. Calculate length
 * 2. Generate random number between [0,length)
 * 3. Reach to the element in the list.
 * O(n)/O(1)
 * O(n) is for random method and Constructor
 * 10 ms Beats 85.68% https://leetcode.com/problems/linked-list-random-node/submissions/1339371737/
 */
class LinkedListRandomNodeWithoutExtraSpace {

    static class Solution {

        int length = 0 ;
        ListNode head;
        Random random = new Random();
        public Solution(ListNode head) {
            int index = 0;
            this.head = head;

            if(head == null)
                return;

            while (head != null) {
                head = head.next;
                index++;
            }
            length = index;

        }

        public int getRandom() {
            if(length==0)
                return -1;

            int index = random.nextInt(length);
            ListNode temp = head;
            while (index>0){
                temp = temp.next;
                index--;
            }
           return temp.val;
        }
    }
}


/**
 * idea: https://www.geeksforgeeks.org/reservoir-sampling/
 */
class LinkedListRandomNodeWithoutExtraSpaceWithoutLength {
    static class Solution {
        Random random = new Random();
        int k = 1;
        int []reservoir ;
        ListNode head;
        public Solution(ListNode head) {
            if(head == null)
                return;
            reservoir = new int[k];
            reservoir[0] = head.val;
            this.head = head;

        }

        public int getRandom() {
            if(reservoir == null)
                return -1;
            ListNode curr = head;
            int i = k;

            //Keep generating random number till we can cache it at reservoir
            while(curr!=null){
                i++;
                int j = random.nextInt(i);

                if(j<k){
                    reservoir[j] = curr.val;
                }
                curr = curr.next;
            }
            return reservoir[0];
        }
    }

}