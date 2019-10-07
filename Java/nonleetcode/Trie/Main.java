package Java.nonleetcode.Trie;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> dictionary = new LinkedList<>();
        dictionary.add("are");
        dictionary.add("area");
        dictionary.add("basement");
        dictionary.add("base");
        dictionary.add("cat");
        dictionary.add("cater");
        dictionary.add("children");
        Trie trie = new Trie(dictionary);
        trie.buildTrie();
        trie.printTrie();
        System.out.println("\n\n\n");

        System.out.println(trie.prefixMatch("caterer"));
        System.out.println(trie.prefixMatch("basemexy"));
        System.out.println(trie.prefixMatch("child"));


    }

//    test() {
//        SinglyLinkedListNode temp = listHead;
//
//        SinglyLinkedListNode prev = null;
//
//
//        while (temp != null && temp.data > x) {
//
//            listHead = temp.next;
//
//            temp = listHead;
//
//        }
//
//
//        while (temp != null) {
//
//
//            while (temp != null && temp.data <= x) {
//
//                prev = temp;
//
//                temp = temp.next;
//
//            }
//
//
//            if (temp == null)
//
//                return null;
//
//
//            prev.next = temp.next;
//
//
//            temp = prev.next;
//
//        }
//
//        return listHead;
//    }
}
