package Java.companyWise.facebook;

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


        Map<String, char[]> input = new HashMap<>();

        input.put("1", new char[]{'A', 'B', 'C'});
        input.put("2", new char[]{'D', 'E'});
        input.put("3", new char[]{'P', 'Q'});
        input.put("12", new char[]{'X'});
        input.put("4", new char[]{'T', 'S'});
        input.put("23", new char[]{'Z', 'N'});
        input.put("34", new char[]{'O', 'M'});
        input.put("234", new char[]{'W', 'Y'});
        input.put("1234", new char[]{'@', '#'});

        String test = "1234";
        try {
            List<String> output = generateCombination(input, test);
            System.out.println(output);



        } catch (InvalidArgument invalidArgument) {
            invalidArgument.printStackTrace();
        }


    }

    private static List<String> generateCombination(Map<String, char[]> input, String test) throws InvalidArgument {

        if (null == input || input.isEmpty() || null == test || test.isEmpty())
            return Collections.EMPTY_LIST;

        //Build list of string that need to traverse recursively
        List<List<String>> considerList = buildConsiderList(input, test);

        return considerList.stream().map(list -> generateCombination(list)).flatMap(Collection::stream).collect(Collectors.toList());

    }

    private static List<List<String>> buildConsiderList(Map<String, char[]> input, String pattern) throws InvalidArgument {

        List<List<String>> toConsider = new LinkedList<>();

        /**
         * Consider of key;
         * Here we use key a loop runner because we could have case where from pattern multiple (off different size) string present in input map,
         * then if we traverse over pattern then we need to generate n^2 sub string and check all of them in map, that corresponding list present or not
         */
        for (String key : input.keySet()) {

            List<String> subListsToConsider = new ArrayList<>();

            //If this key does not include in the first place of the pattern list, then don't consider all list by it
            //Example: Key = 2, pattern=123 -> since 123 does not start with 2, which means all the list by 2 we can't consider since it has to be like 123
            if (!pattern.startsWith(key))
                continue;

            //process for current key array
            subListsToConsider.add(new String(input.get(key)));

            //Take the remaining length to consider
            //example key = 1, pattern=123, then remaining is = 23 which we'll consider one by one
            String remainingPattern = pattern.substring(key.length());


            //Case 1: character by character
            //process remaining
            for (int i = 0; i < remainingPattern.length(); i++) {

                String testThis = String.valueOf(remainingPattern.charAt(i));
                //if this does not present in input; input is corrupt
                if (!input.containsKey(testThis))
                    throw new InvalidArgument("Input " + pattern + "is invalid");

                subListsToConsider.add(new String(input.get(testThis)));


            }


            //case 2: whole remaining list
            if (remainingPattern.length() > 1 && input.containsKey(remainingPattern)) {
                List<List<String>> withRemaining = new ArrayList<>();
                List<String> rem = new ArrayList<>();
                rem.add(new String(input.get(key)));
                rem.add(new String(input.get(remainingPattern)));

                withRemaining.add(rem);
                toConsider.addAll(withRemaining);
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



}
