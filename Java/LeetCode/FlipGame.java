package Java.LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-23
 * Description:
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -,
 * you and your friend take turns to flip two consecutive "++" into "--".
 * The game ends when a person can no longer make a move and therefore the other person will be the winner.
 * <p>
 * https://www.programcreek.com/2014/04/leetcode-flip-game-java/
 */
public class FlipGame {
}

//Write a function to compute all possible states of the string after one valid move.

class FlipGameI {

    public List<String> generatePossibleNextMoves(String s) {
        if (s == null || s.isEmpty())
            return Collections.EMPTY_LIST;

        List<String> nextMoves = new ArrayList<>();
        char str[] = s.toCharArray();

        for (int i = 0; i < str.length - 1; i++) {

            //if this is ++ then change it
            if (str[i] == '+' && str[i + 1] == '+') {

                str[i] = '-';
                str[i] = '-';
                nextMoves.add(new String(str));

                //revert
                str[i] = '+';
                str[i] = '+';

            }
        }
        return nextMoves;
    }

}


//Write a function to determine if the starting player can guarantee a win.
class FlipGameII {

    public boolean canWin(String s) {
        if (null == s || s.isEmpty())
            return false;

        return canWin(s.toCharArray());

    }

    private boolean canWin(char[] str) {
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == '+' && str[i + 1] == '+') {

                str[i] = '-';
                str[i] = '-';

                if (canWin(str))
                    return true;

                //revert -backtrack
                str[i] = '+';
                str[i] = '+';


            }
        }
        return false;
    }
}