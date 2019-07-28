package Java.companyWise.facebook;

import com.sun.tools.corba.se.idl.InvalidArgument;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 03/04/19
 * Description:
 * similar to this {@link SortStringsBasedCustomAlphabetOrder}
 */
public class WordOrdering {

    public static void main(String args[]) {

        String words[] = {"cc", "cb", "bb", "ac"};
        char ordering[] = {'c', 'b', 'a'};
        char ordering2[] = {'b', 'c', 'a'};


        try {
            boolean test1 = areFollowOrder(words, ordering);
            System.out.println(test1);
            boolean test2 = areFollowOrder(words, ordering2);
            System.out.println(test2);
        } catch (InvalidArgument invalidArgument) {
            invalidArgument.printStackTrace();
        }


    }

    private static boolean areFollowOrder(String[] words, char[] ordering) throws InvalidArgument {

        Map<Character, Integer> characterOrderIndexMap = new HashMap<>();

        /**
         * Build char vs order in map
         */
        for (int i = 0; i < ordering.length; i++) {
            if (characterOrderIndexMap.containsKey(ordering[i]))
                throw new InvalidArgument("Can't have order duplicated");
            characterOrderIndexMap.put(ordering[i], i);
        }

        return checkForOrder(words, characterOrderIndexMap);

    }

    //O(n*m)
    private static boolean checkForOrder(String[] words, Map<Character, Integer> characterOrderIndexMap) {

        //Check for each words, are the follow order or not, if the apparently whole string does
        for (int i = 0; i < words.length - 1; i++) { //O(n)
            String fromWord = words[i];
            String toWord = words[i + 1];

            //Get the first character where this two word are uncommon

            //O(m) where m is the second highest string size
            int firstMisMatchCharIndex = IntStream.range(0, Math.min(fromWord.length(), toWord.length())).filter(x -> fromWord.charAt(x) != toWord.charAt(x)).findFirst().orElse(-1);

            //if any char mismatch, if so then they should be in order
            if (firstMisMatchCharIndex != -1) {
                char charAtMismatchIndexFrom = fromWord.charAt(firstMisMatchCharIndex);
                char charAtMismatchIndexTo = toWord.charAt(firstMisMatchCharIndex);

                //are this character are in order defined by order[]
                if (characterOrderIndexMap.get(charAtMismatchIndexFrom) > characterOrderIndexMap.get(charAtMismatchIndexTo))
                    return false;
            }
        }

        return true;
    }
}
