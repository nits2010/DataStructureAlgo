package DataStructureAlgo.Java.LeetCode2025.ProblemSet.Design;

import DataStructureAlgo.Java.helpers.CommonMethods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 9/29/2024
 * Question Category: [easy | medium | hard ]
 * Description:
 * <p><p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p><p>
 * Tags
 * -----
 *
 * <p><p>
 * Company Tags
 * -----
 * <p><p>
 *
 * @Editorial
 */
public class AllOOneDataStructure_432 {

    public static void main(String[] args) {
        boolean test = true;
        test &= test(new String[]{"AllOne","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","dec","dec","getMaxKey"},
                new String[]{"hello", "l", "l", "l","k", "k", "k", "j", "j", "j", "j","k"},
                new String[]{null,null,null,null,null,null,null,null,null,null,null,null,null,"l"});

        test &= test(new String[]{"AllOne", "inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","inc","getMinKey"},
                new String[]{"a","b" ,"c" ,"d" ,"a" ,"b" ,"c" ,"d" ,"c" ,"d" ,"d" ,"a"},
                new String[]{null,null,null,null,null,null,null,null,null,null,null,null,null,"b"});
        test &= test(new String[]{"AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"},
                new String[]{"hello", "hello", "leet"},
                new String[]{null, null, null, "hello", "hello", null, "hello", "leet"});

        CommonMethods.printResult(test);
    }

    private static boolean test(String[] operations, String[] keys, String[] expected) {

        System.out.println("-----------------------------------------------------------");
        System.out.println(" Operations : " + Arrays.toString(operations) + " keys : " + Arrays.toString(keys)
                + "\n expected : " + Arrays.toString(expected));

        int resultIndex = 0;
        int keyIndex = 0;
        AllOne allOne = new AllOne();
        String[] result = new String[operations.length];
        result[resultIndex] = null;

        for (String operation : operations) {

            if ("inc".equals(operation)) {

                allOne.inc(keys[keyIndex++]);
                result[resultIndex] = null;

            } else if ("dec".equals(operation)) {

                allOne.dec(keys[keyIndex++]);
                result[resultIndex] = null;

            } else if ("getMaxKey".equals(operation)) {

                String max = allOne.getMaxKey();
                result[resultIndex] = max;

            } else if ("getMinKey".equals(operation)) {

                String min = allOne.getMinKey();
                result[resultIndex] = min;

            }
            resultIndex++;
        }

        boolean pass = Arrays.deepEquals(result, expected);

        System.out.println(" Result : "+ Arrays.toString(result) + " pass : " + (pass ? "Pass" : "Fail"));
        return pass;

    }

    static class AllOne {

        static class DLLNode {
            String data;
            DLLNode prev, next;
            int count;

            DLLNode(String d) {
                this.data = d;
                prev = next = null;
                count = 1;
            }

        }

        final Map<String, DLLNode> keyVsNodeMap;
        DLLNode head, tail;

        public AllOne() {
            keyVsNodeMap = new HashMap<>();
            head = tail = null;
        }

        public void inc(String key) {

            DLLNode node = keyVsNodeMap.getOrDefault(key, null);
            //if the key does not exist, add it
            if (null == node) {
                node = new DLLNode(key);
                keyVsNodeMap.put(key, node);

                if (head == null) {
                    head = tail = node;
                }else{
                    tail.next = node;
                    node.prev = tail;
                    tail = node;
                }
            } else {

                //if key exists

                //1. check the head, if the head count is more than the current node counts, then no need to update head
                //otherwise update count of the current key and add it to head.
                node.count += 1;
                if (node!=head && node.count >= head.count) {
                    //need to update head;
                    updateHead(node);
                }

                //2. if this key were at the tail node than post updating it must have changed its position.
                //Since we are maintaining a sorted list in DLL; there will be only few positions back; it needs to shift at the correct position
                //which makes average O(1) time.

                //find its correct position
                if(node == tail)
                    updateTail(node);
            }


        }

        public void dec(String key) {
            DLLNode node = keyVsNodeMap.getOrDefault(key, null);
            //if the key does not exist, do nothing
            if(node == null)
                return;

            //if the key exists, then we need to decrement the count.
            node.count -= 1;

            //if the count becomes zero, then it needs to be removed
            if(node.count == 0){
                remove(node);
                keyVsNodeMap.remove(key);
            }

            //if head count got decresed then it may have lost its position
            if(node == head){
                DLLNode temp = head.next;
                while (temp!=null && temp!=tail){
                    if(temp.count > node.count)
                        temp = temp.next;
                    else break;
                }

                if(temp!=null){
                    remove(node);
                    DLLNode prev = temp.prev;


                    if(prev!=null){
                        prev.next = node;
                        node.prev = prev;
                    }

                    node.next = temp;
                    temp.prev = node;

                }

            }
        }

        public String getMaxKey() {
            if(head!=null)
                return head.data;
            return "";
        }

        public String getMinKey() {
            if(tail!=null)
                return tail.data;
            return "";
        }

        private void updateHead(DLLNode node){

            //remove this node from its place
            remove(node);

            //now this node is a standard node, add it to head
            if(node!=head){
                node.next = head;
                head.prev = node;
                head = node;
            }

        }

        private void updateTail(DLLNode node){
            //find its correct position at which it should be in the sorted list.
            DLLNode temp = node.prev;
            while (temp!=null && temp!=head){ //it should not reach at head ideally
                if(temp.count < node.count)
                    temp = temp.prev;
                else
                    break;
            }

            //that while loop completed either  at head or another node post-head
            if(temp!=null){
                remove(node);
                node.next = temp.next;

                if(temp.next!=null)
                    temp.next.prev = node;

                temp.next = node;
                node.prev = temp;
            }

            //if there are two nodes in DLL, then head might have become tail as well due to remove
            if(head == tail && head.next!=null)
                tail = head.next;

        }
        private void remove(DLLNode node){

            DLLNode prev = node.prev;
            DLLNode next = node.next;


            if(prev != null){
                prev.next = next;
            }

            if(next!=null){
                next.prev = prev;
            }

            if(node == head)
                head = head.next;

            if(tail == node)
                tail = tail.prev;

            //detach it to garbage collect
            node.prev = node.next = null;

        }
    }

}
