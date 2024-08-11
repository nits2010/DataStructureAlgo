package DataStructureAlgo.Java.LeetCode2025.medium.List;


import DataStructureAlgo.Java.helpers.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 2130. Maximum Twin Sum of a Linked List [Medium]
 * Description: https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.
 *
 * For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
 * The twin sum is defined as the sum of a node and its twin.
 *
 * Given the head of a linked list with even length, return the maximum twin sum of the linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [5,4,2,1]
 * Output: 6
 * Explanation:
 * Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
 * There are no other nodes with twins in the linked list.
 * Thus, the maximum twin sum of the linked list is 6.
 * Example 2:
 *
 *
 * Input: head = [4,2,2,3]
 * Output: 7
 * Explanation:
 * The nodes with twins present in this linked list are:
 * - Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
 * - Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
 * Thus, the maximum twin sum of the linked list is max(7, 4) = 7.
 * Example 3:
 *
 *
 * Input: head = [1,100000]
 * Output: 100001
 * Explanation:
 * There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is an even integer in the range [2, 105].
 * 1 <= Node.val <= 105
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
 * @TwoPointers
 * @Stack
 *
 *
 * <p>
 * Company Tags
 * -----
 * @Amazon
 *
 * @Editorial <a href="https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/solutions/5563826/interesting-solution-approach-towards-best-slowly-fully-explained/">...</a>
 */
public class MaximumTwinSumOfALinkedList_2130 {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{5,4,2,1}, 6);
        testResult &= test(new Integer[]{4,2,2,3}, 7);
        testResult &= test(new Integer[]{4,2,2,3,5,7,8,9,2,9}, 13);
        testResult &= test(new Integer[]{4,2}, 6);

        System.out.println("\nTest result = " + (testResult ? "Pass" : "Fail"));

    }


    private static boolean test(Integer[] elements,  int expected) {
        ListNode originalList = ListBuilder.arrayToSinglyList(elements);

        MaximumTwinSumOfALinkedListUsingHashMap.Solution usingHashMap = new MaximumTwinSumOfALinkedListUsingHashMap.Solution();
        MaximumTwinSumOfALinkedListUsingStacksAndTwoPointers.Solution usingStacks = new MaximumTwinSumOfALinkedListUsingStacksAndTwoPointers.Solution();
        MaximumTwinSumOfALinkedListTwoPointers.Solution usingTwoPointer = new MaximumTwinSumOfALinkedListTwoPointers.Solution();
        int outputUsingHashMap = usingHashMap.pairSum(originalList);
        int outputUsingStacks = usingStacks.pairSum(originalList);
        int outputTwoPointer = usingTwoPointer.pairSum(originalList);

        boolean result = expected == outputUsingHashMap;
        System.out.println("\noutputUsingHashMap :" + outputUsingHashMap + " Expected :" + expected +" Result match : " + result );
        System.out.println("---------------------------------------------------");
        result = expected == outputUsingStacks;
        System.out.println("\noutputUsingStacks :" + outputUsingStacks + " Expected :" + expected +" Result match : " + result );
        System.out.println("---------------------------------------------------");
        result = expected == outputTwoPointer;
        System.out.println("\noutputTwoPointer :" + outputTwoPointer + " Expected :" + expected +" Result match : " + result );
        System.out.println("---------------------------------------------------");

        return result;

    }
}


/**
 * Cache nodes and calculate sum
 * O(n) / O(n)
 * 37 ms  Beats 5.54% https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/submissions/1339445508/?envType=problem-list-v2&envId=m4ly4d57
 */
class MaximumTwinSumOfALinkedListUsingHashMap {
    static class Solution {
        public int pairSum(ListNode head) {
            if(head == null || head.next==null)
                return 0;

            Map<Integer, ListNode> mapOfIndexVsNode = new HashMap<>();

            //cache nodes in map and count length (n) -> index
            int index = 0;
            ListNode temp = head;
            while(temp!=null){
                mapOfIndexVsNode.put(index++, temp);
                temp = temp.next;
            }

            int n = index;
            int maxSum = Integer.MIN_VALUE;

            // if 0 <= i <= (n / 2) - 1.
            for (int i=0 ; i<(n/2); i++){
                int sum = mapOfIndexVsNode.get(i).val + mapOfIndexVsNode.get(n-1-i).val;
                maxSum = Math.max(sum, maxSum);
            }
            return maxSum;
        }
    }
}

/**
 * [0    1    2    3    4    5    6    7    8    9] -> index; n=10, n/2=5 { (0,9)=13, (1,8)=4, (2,7)=11, (3,6)=11, (4,5)=12 } => 13
 *  4 -> 2 -> 2 -> 3 -> 5 -> 7 -> 8 -> 9 -> 2 -> 9
 *
 * One thing to observe that, the twin of a node is always at equidistant from the node itself. This distance is calculated by (n-1-i).
 * This form a pattern ( as shown in above run ), which brings algo to two pointer approach.
 * Since, we can't traverse back from singly list, hence we need to define a way for it. The best DS is stack.
 * Algo:
 * We can push half of the list to stack and then start traversing from mid to end and compare the value with stack top.
 *
 * O(n) / O(n/2)
 * 20 ms Beats 14.29%: https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/submissions/1339482378/?envType=problem-list-v2&envId=m4ly4d57
 */
class MaximumTwinSumOfALinkedListUsingStacksAndTwoPointers {
    static class Solution {

        public int pairSum(ListNode head) {
            if(head == null || head.next==null)
                return 0;

            //cache nodes in map and count length (n) -> index; using two pointer approach to count nodes.
            int n = 0;
            ListNode slow = head;
            ListNode fast = head;
            while(fast!=null){
                fast = fast.next.next ; //we can safely do this since nodes are even
                slow = slow.next;
                n+=2;
            }

            int maxSum = Integer.MIN_VALUE;
            Stack<ListNode> stack = new Stack<>();
            int i = 0;
            ListNode temp = head;
            while(i<n/2){
                stack.push(temp);
                temp =temp.next;
                i++;
            }

            while(!stack.isEmpty()){
                int sum = stack.pop().val + temp.val;
                maxSum = Math.max(sum, maxSum);
                temp = temp.next;
            }
            return maxSum;
        }
    }
}


/**
 * [0    1    2    3    4    5    6    7    8    9] -> index; n=10, n/2=5 { (0,9)=13, (1,8)=4, (2,7)=11, (3,6)=11, (4,5)=12 } => 13
 *  4 -> 2 -> 2 -> 3 -> 5 -> 7 -> 8 -> 9 -> 2 -> 9
 *
 * One thing to observe that, the twin of a node is always at equidistant from the node itself. This distance is calculated by (n-1-i).
 * This form a pattern ( as shown in above run ), which brings algo to two pointer approach.
 * Since, we can't traverse back from singly list, hence we need to define a way for it. The best DS is stack.
 * Algo:
 * reverse the linked list and compute sum.
 * The one way of doing this to create duplicate list and reverse it then compute it. But it will take memory and more time the previous solution.
 * But since the idea is correct, which means that finding a way to do this inplace.
 *
 * Surprisingly, we can do this by first reversing the first half of the list and then start computing the value of first half with second half.
 * But how to reverse first half of the list in the same call we are trying to find the length ?
 * Can we utilize the behaviour of above solution, where we compute length using fast/slow and using this just reverse the list as well?
 *

 *
 * Best
 * O(n) / O(1)
 * 4 ms Beats 96.48%
 */
class MaximumTwinSumOfALinkedListTwoPointers {
    static class Solution {

        public int pairSum(ListNode head) {
            if(head==null || head.next == null)
                return -1;

            ListNode fast = head;
            ListNode slow = head;
            ListNode prev = null;
            ListNode next;

            while (fast!=null){
                fast = fast.next.next;
                //reverse list
                {
                    //cache next to move slow one step later
                    next = slow.next;

                    //reverse list
                    slow.next = prev;
                    prev = slow;

                    slow = next;
                }


            }

            //at this point, prev will be holding the head of first list
            // while next and slow both holding the head of second list;
            ListNode p1 = prev;
            ListNode p2 = slow;

            int max = Integer.MIN_VALUE;

            //now slow and next will be twin
            while (p1!=null && p2!=null){
                max = Math.max(max, p1.val + p2.val);
                p1 = p1.next;
                p2 = p2.next;
            }

            return max;
        }
    }
}