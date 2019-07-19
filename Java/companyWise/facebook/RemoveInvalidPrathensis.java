package Java.companyWise.facebook;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-19
 * Description: https://leetcode.com/problems/remove-invalid-parentheses/
 * https://www.geeksforgeeks.org/remove-invalid-parentheses/
 */
public class RemoveInvalidPrathensis {

    public static void main(String args[]) {

        BackTrackingDFS dfs = new BackTrackingDFS();
        BackTrackingBFS bfs = new BackTrackingBFS();
        BackTrackingEfficientDFS edfs = new BackTrackingEfficientDFS();
        System.out.println("DFS By removing any number of parenthesis : " + dfs.removeInvalidParenthesis("()())()"));
        System.out.println("DFS By removing minimum of parenthesis : " + dfs.removeInvalidParenthesesDFS("()())()"));
        System.out.println("Efficient  DFS By removing minimum of parenthesis : " + edfs.removeInvalidParentheses("()())()"));
        System.out.println("BFS By removing minimum of parenthesis : " + bfs.removeInvalidParenthesis("()())()"));

    }

}

/**
 * Remove as many number of parenthesis
 */
class BackTrackingDFS {


    public Set<String> removeInvalidParenthesis(String str) {

        Set<String> output = new HashSet<>();

        removeInvalidParenthesis(str, output, new HashSet<>());
        return output;

    }

    private void removeInvalidParenthesis(String str, Set<String> output, Set<String> visited) {

        if (isValid(str)) {
            if (!str.isEmpty())
                output.add(str);
        }

        visited.add(str);
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == ')') {

                String temp = str.substring(0, i) + str.substring(i + 1);
                removeInvalidParenthesis(temp, output, visited);
            }

        }

        visited.remove(str);

    }


    private boolean isValid(String str) {

        if (str.isEmpty())
            return true;

        int count = 0;

        for (char c : str.toCharArray()) {
            if (c == '(')
                count++;
            if (c == ')')
                count--;

            if (count < 0) //if there is more close then open found till now
                return false;
        }

        return count == 0; //if equal open and close

    }


    private Set<String> validExpressions = new HashSet<>();
    private int minimumRemoved;

    private void reset() {
        this.validExpressions.clear();
        this.minimumRemoved = Integer.MAX_VALUE;
    }

    private void recurse(
            String s,
            int index,
            int leftCount,
            int rightCount,
            StringBuilder expression,
            int removedCount) {

        // If we have reached the end of string.
        if (index == s.length()) {

            // If the current expression is valid.
            if (leftCount == rightCount) {

                // If the current count of removed parentheses is <= the current minimum count
                if (removedCount <= this.minimumRemoved) {

                    // Convert StringBuilder to a String. This is an expensive operation.
                    // So we only perform this when needed.
                    String possibleAnswer = expression.toString();

                    // If the current count beats the overall minimum we have till now
                    if (removedCount < this.minimumRemoved) {
                        this.validExpressions.clear();
                        this.minimumRemoved = removedCount;
                    }
                    this.validExpressions.add(possibleAnswer);
                }
            }
        } else {

            char currentCharacter = s.charAt(index);
            int length = expression.length();

            // If the current character is neither an opening bracket nor a closing one,
            // simply recurse further by adding it to the expression StringBuilder
            if (currentCharacter != '(' && currentCharacter != ')') {
                expression.append(currentCharacter);
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount);
                expression.deleteCharAt(length);
            } else {

                // Recursion where we delete the current character and move forward
                this.recurse(s, index + 1, leftCount, rightCount, expression, removedCount + 1);
                expression.append(currentCharacter);

                // If it's an opening parenthesis, consider it and recurse
                if (currentCharacter == '(') {
                    this.recurse(s, index + 1, leftCount + 1, rightCount, expression, removedCount);
                } else if (rightCount < leftCount) {
                    // For a closing parenthesis, only recurse if right < left
                    this.recurse(s, index + 1, leftCount, rightCount + 1, expression, removedCount);
                }

                // Undoing the append operation for other recursions.
                expression.deleteCharAt(length);
            }
        }
    }

    public List<String> removeInvalidParenthesesDFS(String s) {

        this.reset();
        this.recurse(s, 0, 0, 0, new StringBuilder(), 0);
        return new ArrayList(this.validExpressions);
    }

}

class BackTrackingEfficientDFS {


    private Set<String> validExpressions = new HashSet<String>();

    private void recurse(
            String s,
            int index,
            int leftCount,
            int rightCount,
            int leftRem,
            int rightRem,
            StringBuilder expression) {

        // If we reached the end of the string, just check if the resulting expression is
        // valid or not and also if we have removed the total number of left and right
        // parentheses that we should have removed.
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                this.validExpressions.add(expression.toString());
            }

        } else {
            char character = s.charAt(index);
            int length = expression.length();

            // The discard case. Note that here we have our pruning condition.
            // We don't recurse if the remaining count for that parenthesis is == 0.
            if ((character == '(' && leftRem > 0) || (character == ')' && rightRem > 0)) {
                this.recurse(
                        s,
                        index + 1,
                        leftCount,
                        rightCount,
                        leftRem - (character == '(' ? 1 : 0),
                        rightRem - (character == ')' ? 1 : 0),
                        expression);
            }

            expression.append(character);

            // Simply recurse one step further if the current character is not a parenthesis.
            if (character != '(' && character != ')') {

                this.recurse(s, index + 1, leftCount, rightCount, leftRem, rightRem, expression);

            } else if (character == '(') {

                // Consider an opening bracket.
                this.recurse(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, expression);

            } else if (rightCount < leftCount) {

                // Consider a closing bracket.
                this.recurse(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, expression);
            }

            // Delete for backtracking.
            expression.deleteCharAt(length);
        }
    }

    public List<String> removeInvalidParentheses(String s) {

        int left = 0, right = 0;

        // First, we find out the number of misplaced left and right parentheses.
        for (int i = 0; i < s.length(); i++) {

            // Simply record the left one.
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                // If we don't have a matching left, then this is a misplaced right, record it.
                right = left == 0 ? right + 1 : right;

                // Decrement count of left parentheses because we have found a right
                // which CAN be a matching one for a left.
                left = left > 0 ? left - 1 : left;
            }
        }

        this.recurse(s, 0, 0, 0, left, right, new StringBuilder());
        return new ArrayList<>(this.validExpressions);
    }

}

/**
 * Remove the minimum number of invalid parentheses
 */
class BackTrackingBFS {


    public List<String> removeInvalidParenthesis(String str) {

        final Set<String> visited = new HashSet<>();
        final List<String> output = new ArrayList<>();

        /**
         * start bfs with current string
         */
        Queue<String> queue = new LinkedList<>();
        queue.offer(str);

        //visit it so that we don't track it again n again
        visited.add(str);
        boolean level = false;

        while (!queue.isEmpty()) {

            String temp = queue.poll();

            //is current is valid
            if (isValid(temp)) {
                //then add in solution and continue
                output.add(temp);
                level = true;
            }
            if (level)
                continue;

            //start removing character one by one from left side and try all possible chars
            for (int i = 0; i < temp.length(); i++) {

                if (temp.charAt(i) == '(' || temp.charAt(i) == ')') {

                    String removeThis = temp.substring(0, i) + temp.substring(i + 1); //remove current char

                    //we have not tried ths string yet, try now
                    if (!visited.contains(removeThis)) {
                        queue.offer(removeThis);

                        //make it visit for the next time
                        visited.add(removeThis);
                    }

                }
            }


        }
        return output;

    }


    private boolean isValid(String str) {

        if (str.isEmpty())
            return true;

        int count = 0;

        for (char c : str.toCharArray()) {
            if (c == '(')
                count++;
            if (c == ')')
                count--;

            if (count < 0) //if there is more close then open found till now
                return false;
        }

        return count == 0; //if equal open and close

    }


}
