package DataStructureAlgo.Java.companyWise.Google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Nitin Gupta
 * Date: 16/04/19
 * Description: https://www.careercup.com/question?id=5747832811159552
 *
 * You are given an array of strings. For example, ["AB", "BC", "FOO", "ZA", "BAZ"]
 * - Output strings where you can get from one to the other using any ROT transformation.
 *
 * ROT_1(AB) = BC
 * ROT_1(BC) = CD
 * ROT_25(AB) = ZA
 * AB,BC you can go from one to the other using ROT_1
 * Input: list of strings
 * Output: strings where you can get from one to the other using any ROT transformation.
 * Example:
 * Input : ["AB", "BC", "FOO", "ZA", "BAZ"]
 * Output: [ [ab, bc] , [ab, za] ]
 * AB,BC because you can go from one to the other using ROT_1
 * AB,ZA because you can go from one to the other using ROT_25
 * Do not return FOO, BAZ you can’t get from one to the other.
 *
 * [Google]
 */
public class StringRotationMatch {

    static class Pair {
        String s1, s2;

        @Override
        public String toString() {
            return "{" + s1 + "," + s2 + '}';
        }

        public Pair(String s1, String s2) {
            this.s1 = s1;
            this.s2 = s2;
        }
    }

    public static void main(String []args) {
        String[] arr1 = {"AB", "BC", "FOO", "ZA", "BAZ"};
        System.out.println(rotationPairsBruteForce(arr1));
        System.out.println(rotationPairs(arr1));

        String[] arr2 = {"AB", "BC", "FOO", "ZA", "BAZ", "CBA"};
        System.out.println(rotationPairsBruteForce(arr2));
        System.out.println(rotationPairs(arr2));
    }


    //O(Ln^2)
    private static List<Pair> rotationPairsBruteForce(String arr[]) {
        String str = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        List<Pair> out = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) { //O(n)
            for (int j = i + 1; j < arr.length; j++) { //O(n)


                String s1 = arr[i];
                String s2 = arr[j];

                if (s1.length() != s2.length())
                    continue;

                char c1 = s1.charAt(0);
                char c2 = s2.charAt(0);
                int diff = getDiff(c1, c2, str);
                int k;
                for (k = 1; k < s1.length(); k++) { //O(L)

                    if (diff != getDiff(s1.charAt(k), s2.charAt(k), str))
                        break;
                }

                if (k == s1.length()) {
                    out.add(new Pair(s1, s2));
                }

            }
        }
        return out;
    }

    private static int getDiff(char c1, char c2, String str) { //O(1)

        int index1 = str.indexOf(c1);
        int index2 = str.indexOf(c2);

        if (index2 < index1)
            return 26 - (index1 - index2);
        return index2 - index1;
    }

    //Optimized-> O(nL)
    private static List<Pair> rotationPairs(String[] arr) {

        List<Pair> out = new ArrayList<>();
        List<String> transformed = transform(arr); //O(nL)

        Map<String, List<Integer>> duplicates = new HashMap<>();

        int i = 0;
        //This loop will run at most O(n) time since, in the inner loop if all of them map to one entry only, then each element will touch at most 2 times
        for (String s : transformed) { //O(n)

            List<Integer> entries = duplicates.getOrDefault(s, new ArrayList<>());


            for (Integer entry : entries) {

                out.add(new Pair(arr[entry], arr[i]));
            }
            entries.add(i);
            duplicates.put(s, entries);
            i++;
        }


        return out;
    }

    //O(nL)
    private static List<String> transform(String ar[]) {
        List<String> transformed = new ArrayList<>();

        for (int i = 0; i < ar.length; i++) { //O(n)

            String x = ar[i];

            int delta = 'A' - x.charAt(0);

            StringBuilder str = new StringBuilder();
            for (int j = 0; j < x.length(); j++) { //O(L)
                char current = x.charAt(j);

                current = (char) ((int) current + delta);

                if (current < 'A')
                    current = (char) ((int) current + 26);

                str.append(current);
            }
            transformed.add(str.toString());
        }

        return transformed;

    }
}
