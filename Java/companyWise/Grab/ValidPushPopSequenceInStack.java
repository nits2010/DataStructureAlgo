package Java.companyWise.Grab;

import java.util.Stack;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-18
 * Description:
 * 1. Given push and pop sequence for a stack, need to tell that given pop sequence is valid or not.
 * Example:
 * push: {44, 66, 2, 88, 32, 9 } Stack
 * pop:  {88, 2, 9, 32, 66, 44 } valid
 * pop:  {88, 2, 9, 44, 66, 32 } Not valid
 * Stack push and pop
 * Sequence is valid or not`
 */
public class ValidPushPopSequenceInStack {

    public static void main(String args[]) {

        int push[] = {44, 66, 2, 88, 32, 9};
        int pop[] = {88, 2, 9, 32, 66, 44};

        System.out.println(isValid(push, pop));

        int pop2[] = {88, 2, 9, 32, 44, 66};
        System.out.println(isValid(push, pop2));
    }


    static boolean isValid(int push[], int[] pop) {

        int pushIndex = 0;
        int popIndex = 0;
        Stack<Integer> stack = new Stack<>();

        while (pushIndex < push.length) {

            while (popIndex < pop.length && !stack.isEmpty() && stack.peek() == pop[popIndex]) {

                stack.pop(); //1
                popIndex++;

                //pop sequeuence
            }

            stack.push(push[pushIndex]); //check this pushing 1


            pushIndex++; //check this
        }

        while (popIndex < pop.length && !stack.isEmpty() && stack.peek() == pop[popIndex]) {

            stack.pop();
            popIndex++;

            //pop sequeuence
        }


        if (stack.isEmpty() && pushIndex == push.length && popIndex == pop.length)
            return true;
        else
            return false;
    }
}
