package facebook;

import com.sun.tools.corba.se.idl.InvalidArgument;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 03/04/19
 * Description: https://www.careercup.com/question?id=5634222671790080
 * <p>
 * <p>
 * Given a string as input, return the list of all the patterns possible:
 * <p>
 * '1' : ['A', 'B', 'C'],
 * '2' : ['D', 'E'],
 * '12' : ['X']
 * '3' : ['P', 'Q']
 * Example if input is '123', then output should be
 * [ADP, ADQ, AEP, AEQ, BDP, BDQ, BEP, BEQ, CDP, CDQ, CEP, CEQ, XP, XQ]
 */
public class ListCombination {

    public static void main(String args[]) {


        Map<String, Character[]> input = new HashMap<>();

        input.put("1", new Character[]{'A', 'B', 'C'});
        input.put("2", new Character[]{'D', 'E'});
        input.put("12", new Character[]{'X'});
        input.put("3", new Character[]{'P', 'Q'});

        String test = "123";
        try {
            List<String> output = generateCombination(input, test);
            System.out.println(output);
        } catch (InvalidArgument invalidArgument) {
            invalidArgument.printStackTrace();
        }


    }

    private static List<String> generateCombination(Map<String, Character[]> input, String test) throws InvalidArgument {

        if (null == input || input.isEmpty() || null == test || test.isEmpty())
            return Collections.EMPTY_LIST;

        //Build list of string that need to traverse recursively
        List<List<String>> considerList = buildConsiderList(input, test);

        return considerList.stream().map(list -> generateCombination(list)).flatMap(Collection::stream).collect(Collectors.toList());

    }

    private static List<List<String>> buildConsiderList(Map<String, Character[]> input, String pattern) throws InvalidArgument {

        List<List<String>> toConsider = new LinkedList<>();

        //Consider of key;
        /**
         * Here we use key a loop runner because we could have case where from pattern multiple (off different size) string present in input map,
         * then if we traverse over pattern then we need to generate n^2 sub string and check all of them in map, that corresponding list present or not
         */
        for (String key : input.keySet()) {

            List<String> subListsToConsider = new ArrayList<>();

            //If this key does not include in the first place of the pattern list, then don't consider all list by it
            //Example: Key = 2, pattern=123 -> since 123 does not start with 2, which means all the list by 2 we can't consider since it has to be like 123
            if (!pattern.startsWith(key))
                continue;
//
            //     String.valueOf(Arrays.stream(current).map(Objects::toString).collect(Collectors.joining()));

            //process for current key array
            Character[] current = input.get(key);
            subListsToConsider.add(toString(current));

            //Take the remaining length to consider
            //example key = 1, pattern=123, then remaining is = 23 which we'll consider one by one
            String remainingTest = pattern.substring(key.length());


            //process remaining
            for (int i = 0; i < remainingTest.length(); i++) {

                //if this does not present in input; input is corrupt
                if (!input.containsKey(String.valueOf(remainingTest.charAt(i))))
                    throw new InvalidArgument("Input " + pattern + "is invalid");

                Character[] temp = input.get(String.valueOf(remainingTest.charAt(i)));
                subListsToConsider.add(toString(temp));


            }
            toConsider.add(subListsToConsider);


        }

        return toConsider;
    }


    private static List<String> generateCombination(List<String> input) {
        List<String> output = new LinkedList<>();

        generateCombination(input, 0, output, "");
        return output;
    }

    private static void generateCombination(List<String> input, int index, List<String> output, String temp) {

        if (temp.length() == input.size()) {
            output.add(temp);
            return;
        }

        String current = input.get(index);

        for (int i = 0; i < current.length(); i++) {
            temp = temp + current.charAt(i);
            generateCombination(input, index + 1, output, temp);
            temp = temp.substring(0, temp.length() - 1);
        }
    }


    private static String toString(Character[] characters) {
        StringBuilder str = new StringBuilder(characters.length);
        for (Character c : characters)
            str.append(c);

        return str.toString();
    }

}
