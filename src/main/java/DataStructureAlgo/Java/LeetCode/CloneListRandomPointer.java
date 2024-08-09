package DataStructureAlgo.Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-22
 * Description:
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 * <p>
 * Return a deep copy of the list.
 * <p>
 * https://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/
 * https://www.geeksforgeeks.org/clone-linked-list-next-arbit-pointer-set-2/
 * https://www.geeksforgeeks.org/clone-linked-list-next-random-pointer-o1-space/
 *
 *
 * All
 * https://leetcode.com/problems/clone-graph/discuss/341276/Clone-single-Graph-multi-component-graph.-Clone-List-.-Clone-Binary-Tree
 *
 * @Editorial https://leetcode.com/problems/copy-list-with-random-pointer/solutions/5558157/easy-multiple-solution-with-3-pass-and-without-space/
 */

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {
    }

    public Node(int _val, Node _next, Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}

public class CloneListRandomPointer {
}

/**
 * Three pass algo;
 * Pass 1: Create a deep copy of node and use original list random pointer to point this node and copy node next will point original  random node
 * pass 2: For each copied node, set its random using clone next random's next
 * pass 3: Adjust back the random of original node by asking copy node next's and set copy node next by asking original next's random.
 */
class CopyListRandomPointerThreePhaseSol1 {

    public Node copyRandomList(final Node head) {

        if (null == head)
            return null;

        //Pass 1: Create a deep copy of node and use original list random pointer to point this node and copy node next will point original list random node
        creteCopyAdjustNextRandomPointer(head);

        Node cloneHead = head.random;//as head random's pointing to the node of clone list head


        //pass 2: For each copied node, set its random using clone next random's next
        adjustRandoms(head);


        //pass 3: Adjust back the random of original node by asking copy node next's and set copy node next by asking original next's random.
        adjustNext(head);


        return cloneHead;


    }


    //Pass 1: Create a deep copy of node and use original list random pointer to point this node and copy node next will point original  random node
    private void creteCopyAdjustNextRandomPointer(final Node head) {

        Node original = head;

        while (original != null) {

            //Create a new node for this node
            Node clone = new Node(original.val, null, null);

            clone.next = original.random; //copy node next will point original random  node

            original.random = clone; //original list next pointer to point this node

            original = original.next;

        }
    }

    //pass 2: For each copied node, set its random using clone next random's next
    private void adjustRandoms(final Node head) {
        Node original = head;
        Node clone;

        while (original != null) {
            clone = original.random;

            if (clone.next != null)
                clone.random = clone.next.random;  //each copied node, set its random using clone next random's next
            else
                clone.random = null;

            original = original.next;

        }


    }

    // pass 3: Adjust back the random of original node by asking copy node next's and set copy node next by asking original next's random.
    private void adjustNext(Node head) {
        Node original = head;
        Node clone;

        while (original != null) {
            clone = original.random;

            original.random = clone.next;

            if (original.next != null)
                clone.next = original.next.random;
            else
                clone.next = null;

            original = original.next;
        }
    }
}

/**
 * This is another interesting idea.
 * Phase 1: Create a copy node and insert it in-between on original nodes (first node and second node)
 * Phase 2: Adjust copy's random; copy random (copy is original.next) is original.random.next [ since each node next is pointing to copy node )
 * Phase 3: Adjust next of both; simply chain them original.next = copy.next; and copy.next = copy.next.next
 */

class CopyListRandomPointerThreePhaseSol2 {


    public Node copyRandomList(final Node head) {
        if (null == head)
            return null;

        cloneNode(head);
        adjustRandom(head);
        return adjustNext(head);
    }

    //Phase 3: Adjust next of both; simply chain them original.next = copy.next; and copy.next = copy.next.next
    private Node adjustNext(Node head) {
        Node original = head;
        Node cloneHead = head.next;
        Node clone;

        while (original != null) {
            clone = original.next;

            original.next = clone.next;
            if (clone.next != null)
                clone.next = clone.next.next;

            original = original.next;

        }
        return cloneHead;
    }

    //Phase 2: Adjust copy's random; copy random (copy is original.next) is original.random.next [ since each node next is pointing to copy node )
    private void adjustRandom(Node head) {
        Node original = head;

        while (original != null) {

            Node clone = original.next;
            if (original.random != null)
                clone.random = original.random.next;
            else
                clone.random = null;

            original = clone.next;
        }

    }

    //Phase 1: Create a copy node and insert it in-between on original nodes (first node and second node)
    private void cloneNode(Node head) {

        Node original = head;
        while (original != null) {
            Node next = original.next;

            Node clone = new Node(original.val, null, null);

            original.next = clone; //insert it in-between on original nodes
            clone.next = next;
            original = next;
        }
    }
}