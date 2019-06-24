package Java.LeetCode;

import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-24
 * Description:
 */
public class LongestValidParentheses {


    public static void main (String args[]){
        System.out.println(longestValidParentheses(")()())"));
    }
    public static int longestValidParentheses(String s) {
        return longestValidParenthesesUsingUsingTwoPointers(s);
    }


    public static int longestValidParenthesesUsingUsingTwoPointers(String s){
        int lVp = 0;
        int open = 0, close =0 ;
        int i = 0;

        while (i<s.length()){

            if(s.charAt(i) == '(')
                open++;
            else close++;

            if (open == close)
                lVp = Math.max(lVp, close+open);

            else if(close > open)
                open=close=0;

            i++;
        }
        open = 0; close =0 ;
        i = s.length()-1;
        while (i>=0){

            if(s.charAt(i) == '(')
                open++;
            else close++;

            if (open == close)
                lVp = Math.max(lVp, close+open);

            else if(open > close)
                open=close=0;
            i--;
        }
        return lVp;
    }
    public static int longestValidParenthesesUsingStacks(String s){
        int lVp = 0;
        Stack<Integer> stack = new Stack<>();

        stack.push(-1);

        int i =0;
        while (i<s.length()){

            if (s.charAt(i) == '(')
                stack.push(i);
            else {
                stack.pop();

                if (!stack.isEmpty())
                    lVp = Math.max(lVp, i-stack.peek());
                else
                    stack.push(i);
            }
            i++;
        }
        return lVp;
    }
}
