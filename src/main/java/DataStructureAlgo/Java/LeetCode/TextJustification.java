package DataStructureAlgo.Java.LeetCode;

import  DataStructureAlgo.Java.nonleetcode.WordWrapProblem;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-21
 * Description: https://leetcode.com/problems/text-justification/
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 * <p>
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 * <p>
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 * <p>
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * <p>
 * Note:
 * <p>
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 * Example 1:
 * <p>
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * Output:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Example 2:
 * <p>
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be",
 * because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified becase it contains only one word.
 * Example 3:
 * <p>
 * Input:
 * words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * Output:
 * [
 * "Science  is  what we",
 * "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * <p>
 * For finding the optimal solution see {@link WordWrapProblem}
 *
 * [Amazon]
 */
public class TextJustification {

    public static void main(String[] args) {


        test(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
        test(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16);
        test(new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"}, 20);
    }

    private static void test(String[] words, int maxWidth) {
        System.out.println("Test start\n");
        SolutionTextJustification sol = new SolutionTextJustification();

        List<String> texts = sol.fullJustify(words, maxWidth);

        for (String text : texts) {
            System.out.println(text);
        }


    }

}

class SolutionTextJustification {
    private static final String SPACE = " ";

    /**
     * As question says, we need to put the words "Greedily" until we can put. Once we run out of words or space in line we need to do justification.
     * 1. If this is intermediate line,
     * 1.1 and there is more than 1 word then we need to distribute spaces in between
     * 1.2 if there is only one word then we need to be left justified, means need to put all spaces after word.
     * <p>
     * 2. If this is last line, space after word only.
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        //This holds the line which need to be answered
        final List<String> lines = new ArrayList<>(words.length);

        if (words == null || words.length == 0 || maxWidth == 0)
            return lines;


        int i = 0; // iterator on words array to find the words for the current line;
        int currentLineLength; // finding the current line length while putting words in a line;

        int spaceLeft; //this tell how many space are required to make current line align i.e. word1 SPACE word2 SPACE word3....

        //Try every word
        while (i < words.length) {

            //create a new line
            List<String> currentLine = new ArrayList<>(maxWidth);
            currentLineLength = 0;//new line start

            //find the words that can fit in this line, either we'll run out of words or width of the line
            while (i < words.length && currentLineLength + words[i].length() <= maxWidth) {
                //if this word can be fit into this line, take it

                currentLine.add(words[i]);

                //increase the line length
                currentLineLength += words[i].length();

                //include space
                currentLineLength += 1;

                i++;
            }

            //since while putting words above we have added a space after every word, remove the space after last word
            currentLineLength--;


            //Align ths line in normal format i.e. word1 SPACE word2 SPACE word3....
            //Note we already calculated the currentLineLength using spaces
            alignWords(currentLine);

            spaceLeft = maxWidth - currentLineLength;


            //either we have ran out of words or line width? Or this is our last line; then left justify it
            if (i == words.length || currentLine.size() == 1) {
                leftJustify(currentLine, spaceLeft);

            } else {

                //this means we need to adjust the current line words s.t. they have spaces distributed.
                centerJustify(currentLine, spaceLeft);

            }

            //store this line
            lines.add(buildLine(currentLine));
        }

        return lines;

    }

    private void alignWords(List<String> currentLine) {

        for (int i = 0; i < currentLine.size() - 1; i++) { //we dnt want to put space after last word
            currentLine.set(i, currentLine.get(i) + SPACE); //string is immutable in JAVA
        }
    }


    /**
     * Put all the space after the last word.
     * @param currentWords Contains list of words, mostly 1
     * @param spaces number of spaces to append
     */
    private void leftJustify(List<String> currentWords, int spaces) {

        //till we have spaces, keep adding them to the current line word; notice at this point we'll have to put space only on last word
        int index = currentWords.size() - 1;
        String word = currentWords.get(index);

        while (spaces > 0) {
            word += SPACE;
            spaces--;
        }
        currentWords.set(index, word);

    }

    //In order to distributed them EVENLY, we need to put a space after each word till we run out the spaces in Round robin fashion
    private void centerJustify(List<String> currentLine, int spaces) {

        int indexOfWord = 0; //help us to put space in round robin fashion
        int length = currentLine.size() - 1; //we don't want to put space at the end

        while (spaces > 0) {

            String currentWord = currentLine.get(indexOfWord);

            //add space
            currentLine.set(indexOfWord, currentWord + SPACE); //update line

            indexOfWord++; //prepare for next word

            indexOfWord = indexOfWord % length; //round robin

            spaces--;
        }
    }


    private String buildLine(List<String> currentLine) {
        StringBuilder text = new StringBuilder();

        for (String s : currentLine)
            text.append(s);

        return text.toString();
    }
}

//Copied from https://leetcode.com/problems/text-justification/discuss/337337/0-ms-faster-than-100.00
class TextJustificationUsingCharArray {

    public List<String> fullJustify(String[] words, int maxWidth) {
        int i = 0;
        List<String> fullChs = new ArrayList<>();

        while (i < words.length) {
            int curLen = 0;
            int start = i;
            char[] lineChs = new char[maxWidth];
            while (i < words.length && words[i].length() + curLen + (i - start) <= maxWidth) {
                curLen += words[i].length();
                i++;
            }
            int sep = i > start + 1 ? (maxWidth - curLen) / (i - start - 1) : maxWidth - curLen;
            int rem = i > start + 1 ? (maxWidth - curLen) % (i - start - 1) : 0;
            if (i >= words.length) {
                sep = 1;
                rem = 0;
            }
            int totalEmpty = maxWidth - curLen;
            int empIdx = 0;
            int idx = 0;
            for (int j = start; j < i; j++) {
                int wl = 0;
                while (wl < words[j].length()) {
                    lineChs[idx + wl] = words[j].charAt(wl);
                    wl++;
                }
                idx += wl;
                int len = sep;
                if (rem > 0) {
                    len += 1;
                    rem--;
                }
                int k = 0;
                for (; k < len && empIdx < totalEmpty; k++) {
                    lineChs[idx + k] = ' ';
                    empIdx++;
                }
                idx += k;
            }
            while (empIdx < totalEmpty && idx < maxWidth) {
                lineChs[idx] = ' ';
                empIdx++;
                idx++;
            }

            fullChs.add(new String(lineChs));
        }
        return fullChs;
    }
}