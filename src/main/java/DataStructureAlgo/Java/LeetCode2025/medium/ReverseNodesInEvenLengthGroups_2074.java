package DataStructureAlgo.Java.LeetCode2025.medium;

import DataStructureAlgo.Java.LeetCode.listToBST.ListBuilder;
import DataStructureAlgo.Java.LeetCode.templates.ListNode;
import DataStructureAlgo.Java.helpers.GenericPrinter;


/**
 * Author: Nitin Gupta
 * Date: 7/31/2024
 * Question Category: 2074. Reverse Nodes in Even Length Groups [Medium]
 * Description: https://leetcode.com/problems/reverse-nodes-in-even-length-groups/description/
 * <p>
 * You are given the head of a linked list.
 *
 * The nodes in the linked list are sequentially assigned to non-empty groups whose lengths form the sequence of the natural numbers (1, 2, 3, 4, ...). The length of a group is the number of nodes assigned to it. In other words,
 *
 * The 1st node is assigned to the first group.
 * The 2nd and the 3rd nodes are assigned to the second group.
 * The 4th, 5th, and 6th nodes are assigned to the third group, and so on.
 * Note that the length of the last group may be less than or equal to 1 + the length of the second to last group.
 *
 * Reverse the nodes in each group with an even length, and return the head of the modified linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [5,2,6,3,9,1,7,3,8,4]
 * Output: [5,6,2,3,9,1,4,8,3,7]
 * Explanation:
 * - The length of the first group is 1, which is odd, hence no reversal occurs.
 * - The length of the second group is 2, which is even, hence the nodes are reversed.
 * - The length of the third group is 3, which is odd, hence no reversal occurs.
 * - The length of the last group is 4, which is even, hence the nodes are reversed.
 * Example 2:
 *
 *
 * Input: head = [1,1,0,6]
 * Output: [1,0,1,6]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the second group is 2. The nodes are reversed.
 * - The length of the last group is 1. No reversal occurs.
 * Example 3:
 *
 *
 * Input: head = [1,1,0,6,5]
 * Output: [1,0,1,5,6]
 * Explanation:
 * - The length of the first group is 1. No reversal occurs.
 * - The length of the second group is 2. The nodes are reversed.
 * - The length of the last group is 2. The nodes are reversed.
 * <p>
 *
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link ReverseLinkedListII} {@link DataStructureAlgo.Java.LeetCode2025.hard.ReverseNodesInKGroup}
 * extension {@link DataStructureAlgo.Java.LeetCode2025.hard.ReverseNodesInKGroup}
 * <p>
 * Tags
 * -----
 * @medium
 * @LinkedList
 * @Recursion
 *
 * <p>
 * Company Tags
 * -----
 * @Editorial <a href="https://leetcode.com/problems/reverse-nodes-in-even-length-groups/solutions/5562445/easy-solution-extension-of-reversenodesinkgroup-o-n/">...</a>
 */
public class ReverseNodesInEvenLengthGroups_2074 {

    public static void main(String[] args) {
        boolean testResult = true;
        testResult &= test(new Integer[]{5,2,6,3,9,1,7,3,8,4}, new Integer[]{5,6,2,3,9,1,4,8,3,7});
        testResult &= test(new Integer[]{1,1,0,6,5}, new Integer[]{1,0,1,5,6});

        System.out.println("\nTest result ="+(testResult?"Pass":"Fail"));

    }


    private static boolean test(Integer[] elements, Integer[] expected ) {
        ListNode originalList = ListBuilder.arrayToSinglyList(elements);
        ListNode expectedList = ListBuilder.arrayToSinglyList(expected);
        System.out.println("\n Input :"+ GenericPrinter.print(originalList)  + "\nexpected :" + GenericPrinter.print(expectedList));

        Solution solution = new Solution();
        ListNode output =  solution.reverseEvenLengthGroups(originalList);
        boolean result = false;
        System.out.println(" Output :"+GenericPrinter.print(output) + " Result match : "+(result = GenericPrinter.equalsValues(expectedList,output)));
        return result;



    }

}


class ReverseNodesInEvenLengthGroupsGroupLengthBasedIterative {

    public ListNode reverseEvenLengthGroups(ListNode head) {

        if(head == null || head.next == null)
            return head;

        ListNode prevGroupTail = null;
        ListNode currentGroupHead = null;
        ListNode currentGroupTail = null;
        ListNode nextGroup = null;
        ListNode temp = head;
        int sequence = 1;

        while (temp!=null){

            //odd sequence, move ahead and update pointers
            if(sequence%2!=0){

                int moveHeadCount = sequence - 1 ; //current node counted as 1

                while (temp!=null && moveHeadCount >=0){
                    prevGroupTail = temp;
                    temp = temp.next;
                    moveHeadCount--;
                }

            }else {

                currentGroupHead = temp;
                currentGroupTail = getKNodes(temp, sequence);

                //if there is not enough nodes
                if (currentGroupTail == null) {
                    break;
                }

                //detach lists
                prevGroupTail.next = null;
                nextGroup = currentGroupTail.next;
                currentGroupTail.next = null;

                currentGroupTail = currentGroupHead; //post reversal they will swap position
                currentGroupHead = reverse(currentGroupHead);

                //attach lists
                prevGroupTail.next = currentGroupHead;
                currentGroupTail.next = nextGroup;

                //move next group
                temp = nextGroup;
            }

            sequence++;
        }


        return head; // since we need to reverse only even sequence and first node is always odd sequence.
    }

    private ListNode getKNodes(ListNode head, int k) {
        k = k-1; //head is counted as one
        while (head != null && k>0){
            head = head.next;
            k--;
        }
        return head;
    }

    private ListNode reverse(ListNode head){
        if(head == null || head.next == null)
            return head;

        ListNode n_1_list = head.next;
        head.next = null;

        ListNode newHead = reverse(n_1_list);
        n_1_list.next = head;
        return newHead;
    }
}

class Solution {

    //Sample run
    // 5 -> 2 -> 6 -> 3 -> 9 -> 1 -> 7 -> 3 -> 8 -> 4
    // pt = 5, next=2, , seq=2, temp = 2, ch = 2, ct=null, length=1
    // 1. -> {ch=2, temp=2, length=1, seq=2, length < seq}
    // 1. -> {ch=2, temp=6, length=2, seq=2, length == seq}; break
    // 1. -> {ch=2, ct=6,next=3, pt=5, length=2, seq=2, length == seq};
    // 1. -> { [2->6 => 6->2],ct=2 ch=6, next=3, pt=5, length=2, seq=2, length == seq; pt.next=ch, ct.next=nextGroup, };
    // 1. -> {5 -> 6 -> 2 -> 3 -> 9 -> 1 -> 7 -> 3 -> 8 -> 4};
    // 1. -> {temp=3(temp=nextGroup), pt=2(pt=ct), seq=3}
    // pt = 2, nextGroup=xx, , seq=3, temp = 3, ch = 3, ct=null, length=1
    // 2. -> {ch=3, temp=3, length=1, seq=3, length < seq}
    // 2. -> {ch=3, temp=9, length=2, seq=3, length < seq}
    // 2. -> {ch=3, temp=1, length=3, seq=3, length == seq}; break
    // 2. -> {ch=3, ct=1, nextGroup=7,pt=2 , temp=1, length=3, seq=3, length == seq}; break
    // 2. -> {ch=3, ct=1, nextGroup=7,pt=1(pt=ct) , temp=7(temp=nextGroup), length=3, seq=4, length == seq}; break
    // 2. -> {5 -> 6 -> 2 -> 3 -> 9 -> 1' -> 7 -> 3 -> 8 -> 4};
    // pt = 1, nextGroup=xx, , seq=4, temp = 7, ch = 7, ct=null, length=1, seq=4
    // 3. -> {ch=7, temp=7, length=1, seq=4, length < seq}
    // 3. -> {ch=7, temp=3, length=2, seq=4, length < seq}
    // 3. -> {ch=7, temp=8, length=3, seq=4, length < seq}
    // 3. -> {ch=7, temp=4, length=4, seq=4, length < seq}; break
    // 3. -> {ch=7, ct=4, nextGroup=null,pt=1 , temp=4, length=4, seq=4, length == seq}; reverse
    // 3. -> {5 -> 6 -> 2 -> 3 -> 9 -> 1'  7 -> 4 -> 8 -> 3 }; [7 -> 4 -> 8 -> 3] => [3 -> 8 -> 4 -> 7] {ct=7, ch=3, nextGroup=null, pt=1, temp=3, length=4, seq=4, length == seq}; pt.next=ch, ct.next=nextGroup
    // 3. -> {5 -> 6 -> 2 -> 3 -> 9 -> 1 -> 3 -> 8 -> 4 -> 7 }; pt.next=ch, ct.next=nextGroup


    /**
     * <a href="https://leetcode.com/problems/reverse-nodes-in-even-length-groups/solutions/5562445/easy-solution-extension-of-reversenodesinkgroup-o-n/">...</a>
     * The idea is to keep track of the previous group tail, and the next group head and its tail and next group that will be consider later. Additionally, since last
     * group may have lesser element then sequence which can turn to be even length, hence we need to track the length of each group we are considering.
     *
     * Algo:
     * 1. Skip first element, as its always odd. Keep this element cache for next group. prevGroupTail = head; and nextGroup = head.next;
     * 2. Start counting the length of current group and keep its head and tail in track. Keep in mind, if the last group has less element then seq and its even, we need to reverse it.
     * Hence, we'll go till length < seq && this is the last element (temp.next !=null)
     * 3. Once you have group details, with current group head and tail, check does this group needed to be reverse or not,
     * if so, then detach the list from prevGroupTail and nextGroup. Reverse the list.
     * 4. Continue this activity for next group as well.
     *
     * 7 ms Beats 42.71% https://leetcode.com/problems/reverse-nodes-in-even-length-groups/submissions/1339322108/?envType=problem-list-v2&envId=m4ly4d57
     * @param head
     * @return
     */
    public ListNode reverseEvenLengthGroups(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode prevGroupTail = head;
        ListNode nextGroup = head.next;
        ListNode currentGroupHead;
        ListNode currentGroupTail;
        int seq = 2;

        ListNode temp = nextGroup;
        while (temp!=null){

            //get next group head and tail, get length of group too
            int length = 1; //current node counted
            currentGroupHead = temp;
            while (temp.next!=null && seq > length){
                temp = temp.next;
                length++;
            }
            currentGroupTail = temp;
            //cache nextGroup
            nextGroup = temp.next;

            //this group is even, hence reverse
            if(Math.min(length, seq)%2==0){

                //detach list
                prevGroupTail.next = null;
                currentGroupTail.next = null;

                //reverse
                currentGroupTail = currentGroupHead; // post reversal they will swap positions
                currentGroupHead = reverseIterative(currentGroupHead);

                //re-attach list
                prevGroupTail.next = currentGroupHead;
                currentGroupTail.next = nextGroup;


            } // if odd, then skip the group
            //move next group, update prevGroupTail
            temp = nextGroup;
            prevGroupTail = currentGroupTail;
            seq++;
        }

        return head;
    }

    private ListNode reverse(ListNode head){
        if (head==null || head.next==null)
            return head;

        ListNode n_1_list = head.next;
        head.next = null;
        ListNode newHead = reverse(n_1_list);
        n_1_list.next = head;
        return newHead;
    }

    private ListNode reverseIterative(ListNode head){
        if (head==null || head.next==null)
            return head;

        ListNode current = head;
        ListNode next = head.next;
        current.next = null; // assume this is the only node to reverse

        // 1-2-3-4
        //c=1,n=2; 1 , 2-3-4
        //c=1,n=2,ca=3, 2->1 ; c=2,n=3
        //ca=4, 3->2-1; c=3,n=4
        //ca=null, 4->3->2->1; c=4, n=null
        while (next != null) {

            ListNode cache = next.next; //cache next node

            //swap current
            next.next = current;
            current = next;
            next = cache;

        }

        return current;


    }
}
