package Java.graph;

import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 20/02/19
 * Description: https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/
 * <p>
 * Given a sorted dictionary (array of words) of an alien language, find order of characters in the language.
 * Examples:
 * <p>
 * Input:  words[] = {"baa", "abcd", "abca", "cab", "cad"}
 * Output: Order of characters is 'b', 'd', 'a', 'c'
 * Note that words are sorted and in the given language "baa"
 * comes before "abcd", therefore 'b' is before 'a' in output.
 * Similarly we can find other orders.
 * <p>
 * Input:  words[] = {"caa", "aaa", "aab"}
 * Output: Order of characters is 'c', 'a', 'b'
 */
public class AlienLanguageOrder {


    public static void main(String args[]) {

        String[] words = {"caa", "aaa", "aab"};
        int character = 3;

        findOrder(words, character);

        System.out.println("\n");

        String words1[] = {"baa", "abcd", "abca", "cab", "cad"};
        int character1 = 4;

        findOrder(words1, character1);


        System.out.println("\n");

        String words2[] = {"aba", "bba", "aaa"};
        int character2 = 2;

        findOrder(words2, character2);


    }

    private static void findOrder(String[] words, int characters) {

        final IGraph graph = new DirectedGraph(characters);

        //Push all edges
        for (int i = 0; i < words.length - 1; i++) {

            String word1 = words[i];
            String word2 = words[i + 1];

            for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {

                //create edges for mismatch chars
                if (word1.charAt(j) != word2.charAt(j)) {
                    graph.addEdge(word1.charAt(j) - 'a', word2.charAt(j) - 'a');
                    break;
                }
            }
        }

        List<Integer> integers = graph.topologicalSortKhanAlgo();
        if (integers.isEmpty()) {
            System.out.println("No order possible");
        } else

            integers
                    .stream()
                    .map(x -> (char) (x + 'a'))
                    .forEach(x -> System.out.println(x + " "));

    }

}


