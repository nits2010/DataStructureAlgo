package DataStructureAlgo.Java.LeetCode2025.medium.List;

import DataStructureAlgo.Java.Node;
import DataStructureAlgo.Java.Pair;
import DataStructureAlgo.Java.helpers.GenericPrinter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 7/30/2024
 * Question Category: 138. Copy List with Random Pointer [medium]
 * Description: https://leetcode.com/problems/copy-list-with-random-pointer/description/
 *
 * <p>
 *A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 *
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.
 *
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 *
 * Return the head of the copied linked list.
 *
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * Example 2:
 *
 *
 * Input: head = [[1,1],[2,1]]
 * Output: [[1,1],[2,1]]
 * Example 3:
 *
 *
 *
 * Input: head = [[3,null],[3,0],[3,null]]
 * Output: [[3,null],[3,0],[3,null]]
 *
 *
 * Constraints:
 *
 * 0 <= n <= 1000
 * -104 <= Node.val <= 104
 * Node.random is null or is pointing to some node in the linked list.
 * <p>
 * File reference
 * -----------
 * Duplicate {@link DataStructureAlgo.Java.LeetCode.CloneListRandomPointer}
 * Similar {@link}
 * <p>
 * Tags
 * -----
 * @medium
 * @HashTable
 * @LinkedList
 *
 * <p>
 * Company Tags
 * -----
 * @Facebook
 * @Amazon
 * @Microsoft
 * @Bloomberg
 * @Google
 *
 * @Editorial https://leetcode.com/problems/copy-list-with-random-pointer/solutions/5558157/easy-multiple-solution-with-3-pass-and-without-space/
 */



public class CopyListWithRandomPointer_138 {

    public static void main(String[] args) {
        List<Pair<Integer,Integer>> input = List.of(
                new Pair<>(7,null),
                new Pair<>(13,0) ,
                new Pair<>(11,4) ,
                new Pair<>(10,2) ,
                new Pair<>(1,0)
        );

        test(List.of(new Pair<>(7,null), new Pair<>(13,0) ,  new Pair<>(11,4) , new Pair<>(10,2) , new Pair<>(1,0)),
                List.of(new Pair<>(7,null), new Pair<>(13,0) ,  new Pair<>(11,4) , new Pair<>(10,2) , new Pair<>(1,0)));
        test(List.of(new Pair<>(1,1), new Pair<>(2,1)), List.of(new Pair<>(1,1), new Pair<>(2,1)));



    }

    private static void test(List<Pair<Integer, Integer>> input, List<Pair<Integer, Integer>> expected) {
        Node inputList = GenericPrinter.listWithRandomNode(input);
        System.out.println("\n\ninput\n");
        GenericPrinter.print(inputList);

        CopyListRandomPointer3PhaseSol copyListRandomPointer3PhaseSol = new CopyListRandomPointer3PhaseSol();
        Node output = copyListRandomPointer3PhaseSol.copyRandomList(inputList);
        System.out.println("\n\nCopyListRandomPointer3PhaseSol output");
        GenericPrinter.print(output);

        CopyListRandomPointer3PhaseSol2 copyListRandomPointer3PhaseSol2 = new CopyListRandomPointer3PhaseSol2();
        output = copyListRandomPointer3PhaseSol2.copyRandomList(GenericPrinter.listWithRandomNode(input));
        System.out.println("\n\nCopyListRandomPointer2PhaseSol output");
        GenericPrinter.print(output);

        CopyListRandomPointer2PhaseSolUsingHashMap copyListRandomPointer2PhaseSolUsingHashMap = new CopyListRandomPointer2PhaseSolUsingHashMap();
        output = copyListRandomPointer2PhaseSolUsingHashMap.copyRandomList(GenericPrinter.listWithRandomNode(input));
        System.out.println("\n\nCopyListRandomPointer2PhaseSolUsingHashMap output");
        GenericPrinter.print(output);

    }


}

class CopyListRandomPointer2PhaseSolUsingHashMap {
    /**
     * O(2n)/O(n)
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        Map<Node, Node> originalToClone = new HashMap<>();

        createMapOriginalToClone(head, originalToClone);
        Node cloneHead = originalToClone.get(head);
        updateNextRandomOfClone(head,originalToClone);
        return cloneHead;
    }

    private void createMapOriginalToClone(Node head, Map<Node, Node> map) {

        while(head!=null){
            Node clone = new Node(head.val, null, null);
            map.put(head,clone);
            head = head.next;
        }
    }

    private void updateNextRandomOfClone(Node head, Map<Node, Node> originalToClone) {
        while (head!=null){

            Node clone = originalToClone.get(head);
            clone.next = originalToClone.get(head.next);
            clone.random = originalToClone.get(head.random);

            head = head.next;
        }
    }


}

class CopyListRandomPointer3PhaseSol2 {

    /**
     * O(3n)/O(1)
     * https://leetcode.com/problems/copy-list-with-random-pointer/submissions/1338314196/
     * 0  ms
     * Beats 100.00%
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        //Phase 1; copy current node (cloneNode) and insert between current node and next node
        copyCurrentNodeInsertInBetween(head);
        Node cloneHead = head.next;

        //Phase 2; get clone node by taking next of current node, fix clone.random by current.random.next
        fixRandom(head);

        //Phase 3: gix next of both list
        fixNext(head);

        return cloneHead;
    }




    private void copyCurrentNodeInsertInBetween(Node head) {
        Node temp = head;

        while (temp!=null){

            //cache next node for joining and further processing
            Node next = temp.next;

            //create clone
            Node clone = new Node(temp.val,null,null);

            //insert clone in between of current and its next
            temp.next = clone;
            clone.next = next;

            temp = next;
        }
    }

    private void fixRandom(Node head) {
        Node temp = head;

        while (temp!=null){

            //get cloneNode;
            Node clone = temp.next;

           //fix clone random
            if(temp.random!=null)
                clone.random = temp.random.next;

           temp = clone.next;
        }
    }

    private void fixNext(Node head) {

        Node temp = head;

        while (temp!=null){
            Node clone = temp.next;

            if(clone!=null) {
                temp.next = clone.next;

                if (clone.next != null)
                    clone.next = clone.next.next;
            }

            temp = temp.next;

        }

    }
}

class CopyListRandomPointer3PhaseSol {


    /**
     * O(3n)/O(1)
     * https://leetcode.com/problems/copy-list-with-random-pointer/submissions/1338192961/
     * 0  ms
     * Beats 100.00%
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;

        copyNext(head);
        Node finalCloneHead =  head.next; //as head next pointing to the node of clone list head
        fixRandom(head);
        fixNext(head);
        return finalCloneHead;
    }


    /**
     * Pass 1: Create a deep copy of node and use original list next pointer to point this node and copy node next will point original list next node
     */
    private void copyNext(Node head) {
        Node originalHead = head;

        while (originalHead != null) {

            //clone node
            Node cloneHead = new Node(originalHead.val, null, null);

            //capture next node of original list
            Node originalNext = originalHead.next;

            //cache originalHead with clone next
            cloneHead.next = originalNext;

            //cache clone head with original head next
            originalHead.next = cloneHead;

            originalHead = originalNext; //move ahead for second node
        }


    }

        //pass 2: For each copied node, set its random using clone next random's next
    private void fixRandom(Node head) {
        Node originalHead = head;

        while (originalHead != null) {

            //clone head
            Node cloneHead = originalHead.next;

            if(originalHead.random!=null)
                cloneHead.random = originalHead.random.next;

            originalHead = cloneHead.next;

        }

    }

    //pass 3: Adjust back the next of original node by asking copy node next's and set copy node next by asking original next's next.
    private void fixNext(Node head) {
        Node originalHead = head;

        while (originalHead!=null){

            Node cloneHead = originalHead.next;
            originalHead.next = cloneHead.next;

            if(cloneHead.next!=null)
                cloneHead.next = cloneHead.next.next;

            originalHead = originalHead.next;


        }
    }

}