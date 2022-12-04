package DataStructureAlgo.Java.nonleetcode;

import java.util.*;

/**
 * Author: Nitin Gupta
 * Date: 12/04/19
 * Description: https://www.careercup.com/question?id=5690892925534208
 Given a word lenth n , a dice array. find out that this word possible to make usin
 */
public class DiceWordProblem {

    public static void main(String []args) {
        String word = "xniu";
        char dices[][] = 
        {
                {'a', 'b', 'c', 'd', 'x', 'y'},
                {'e', 'f', 'g', 'h', 'i', 'j'},
                {'k', 'l', 'm', 'n', 'o', 'p'},
                {'q', 'r', 's', 't', 'u', 'v'}};

        System.out.println(isPossible(word, dices));
    }

    private static boolean isPossible(String word, char[][] dices) {

        if (word == null)
            return dices == null;

        if (dices.length == 0)
            return word.isEmpty();
        Set<Integer> availableDices = new HashSet<>();
        Map<Integer, List<Character>> diceToCharacterMap = new HashMap<>();

        int m = dices.length;
        int n = dices[0].length;

        for (int i = 0; i < m; i++) {
            availableDices.add(i);
            for (int j = 0; j < n; j++) {
                List<Character> chars = diceToCharacterMap.getOrDefault(i, new ArrayList<>(6));
                chars.add(dices[i][j]);
                diceToCharacterMap.put(i, chars);


            }
        }

        return isPossible(word, availableDices, diceToCharacterMap, "");
    }

    private static boolean isPossible(String word, Set<Integer> availableDices, Map<Integer, List<Character>> diceToCharacterMap, String temp) {

        if (temp.length() > word.length())
            return false;

        if (temp.equals(word))
            return true;

        for (Integer i : diceToCharacterMap.keySet()) {

            if (availableDices.contains(i)) {

                availableDices.remove(i);

                for (Character c : diceToCharacterMap.get(i)) {
                    if (word.contains(String.valueOf(c))) {
                        temp = temp + c;
                        if (isPossible(word, availableDices, diceToCharacterMap, temp))
                            return true;
                        temp = temp.substring(0, temp.length() - 1);
                    }

                }

                availableDices.add(i);
            }
        }
        return false;
    }
}
