"""
Author: Nitin Gupta
Date: 7/18/2025
Question Title: 227. Basic Calculator II
Link: https://leetcode.com/problems/basic-calculator-ii/description/
Description: Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().



Example 1:

Input: s = "3+2*2"
Output: 7
Example 2:

Input: s = " 3/2 "
Output: 1
Example 3:

Input: s = " 3+5 / 2 "
Output: 5


Constraints:

1 <= s.length <= 3 * 105
s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
s represents a valid expression.
All the integers in the expression are non-negative integers in the range [0, 231 - 1].
The answer is guaranteed to fit in a 32-bit integer.
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Math
@String
@Stack

<p><p>
Company Tags
-----
@Facebook
@Amazon
@Microsoft
@ByteDance
@Snapchat

<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


# without single stack
class Solution:
    def calculate(self, s: str) -> int:
        num, last, sign, i = 0, 0, "+", 0
        result = 0  # work like stack
        s = s.replace(" ", "")

        while i < len(s):
            ch = s[i]

            if ch.isdigit():
                num = num * 10 + int(ch)

            if ch in "+-*/" or i == len(s) - 1:

                if sign in "+-":
                    result += last

                    last = num if sign is "+" else -num

                elif sign is "*":
                    last = last * num
                elif sign is "/":
                    last = int(last / num)

                sign = ch
                num = 0

            i += 1

        return result + last


# Using single stack
class Solution_UsingSingleStack:
    def calculate(self, s: str) -> int:
        stack, num, sign, i = [], 0, "+", 0
        s = s.replace(" ", "")

        while i < len(s):
            ch = s[i]

            if ch.isdigit():
                num = num * 10 + int(ch)

            if ch in "+-*/" or i == len(s) - 1:

                # cache the number
                if sign is "+":
                    stack.append(num)
                elif sign is "-":
                    stack.append(-num)
                elif sign is "*":
                    stack[-1] = stack[-1] * num
                elif sign is "/":
                    stack[-1] = int(stack[-1] / num)

                sign = ch
                num = 0

            i += 1

        return sum(stack)


# using two stacks
class Solution_UsingTwoStacks:
    def calculate(self, s: str) -> int:
        def is_higher_precedence(op1, op2):
            return op1 in "*/" and op2 in "+-"

        def apply_operation(op1, op2, operation):
            match operation:
                case "+":
                    return op1 + op2
                case "-":
                    return op1 - op2
                case "*":
                    return op1 * op2
                case "/":
                    return int(op1 / op2)

        operation_stack = []
        operand_stack = []
        num = 0
        index = 0
        s = s.replace(" ", "")  # clean spaces

        while index < len(s):
            ch = s[index]

            if ch.isdigit():
                num = num * 10 + int(ch)

            elif ch in "+-*/":
                operand_stack.append(num)
                num = 0

                while operation_stack and not is_higher_precedence(
                        ch, operation_stack[-1]):
                    op = operation_stack.pop()
                    b = operand_stack.pop()
                    a = operand_stack.pop()
                    operand_stack.append(apply_operation(a, b, op))

                operation_stack.append(ch)

            index += 1

        # Append the last number
        operand_stack.append(num)

        while operation_stack:
            op = operation_stack.pop()
            b = operand_stack.pop()
            a = operand_stack.pop()
            operand_stack.append(apply_operation(a, b, op))

        return operand_stack[-1]


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution_UsingTwoStacks().calculate(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["UsingTwoStacks", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_UsingSingleStack().calculate(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["UsingSingleStack", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution().calculate(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Without Stack", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test("3+2*2", 7),
                   test(" 3/2 ", 1),
                   test(" 3+5 / 2 ", 5),
                   test("3+5*2/5-5-6+3*4", 6),
                   test("0",0),
                   test("14-3/2", 13)]

    CommonMethods.print_all_test_results(tests)
