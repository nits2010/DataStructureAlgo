

from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

class Solution_Iterative:
    def calculate(self, s: str) -> int:

        stack = []
        sum = 0
        sign = 1
        index = 0
        num = 0

        while index < len(s):

            current = s[index]
            index += 1

            match current:

                case _ if current.isdigit():
                    num = num * 10 + int(current)
                case "(":
                    # new problem is starting, we need to cache the current solution
                    stack.append(sum)
                    stack.append(sign)

                    # reset data for next problem
                    sign = 1
                    sum = 0

                case ")":

                    # current problem ends
                    sum += sign * num

                    # add sum of previously ended
                    sum *= stack.pop()
                    sum += stack.pop()

                    # reset for next iteration
                    num = 0

                case "+":

                    # calculate the sum till previous number formed
                    sum += sign * num

                    sign = 1
                    num = 0

                case "-":

                    # calculate the sum till previous number formed
                    sum += sign * num

                    sign = -1
                    num = 0

        sum = sum + sign * num

        return sum


class Solution_Recursive:
    def calculate(self, s: str) -> int:

        index = 0  # represent the index in the str

        def dfs():
            nonlocal index
            # if string fully traversed
            if index == len(s):
                return 0

            sign = 1  # +1 for '+' and -1 for '-'
            sum = 0
            while index < len(s):
                current = s[index]

                match current:

                    case "(":
                        # this is a start point of a new problem ( ... ) we need to evaluate the bracket first hence move to the operand if found
                        index += 1
                        sum += sign * dfs()
                    case ")":
                        # we have solved the one bracket, hence return from it
                        return sum
                    case "+":
                        sign = 1
                    case "-":
                        sign = -1
                    case _ if current.isdigit():  # default case with is digit logic
                        # we need to build up the number now
                        num = current
                        index += 1
                        while index < len(s):
                            if s[index].isdigit():
                                num += s[index]
                            else:
                                break

                            index += 1
                        # since loop break when it find a character which is not number
                        index -= 1
                        # print(f"index={index}")
                        sum += sign * int(num)

                index += 1
            return sum

        return dfs()

def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution_Recursive().calculate(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Recursive", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    output = Solution_Iterative().calculate(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Iterative", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test("1 + 1", 2),
                   test(" 2-1 + 2 ", 3),
                   test("(1+(4+5+2)-3)+(6+8)", 23),
                   test(" 2-1 + 2 ", 3),
                   test("(1+(4+5+2)-3)+(6+8)", 23),
                   test("1 - (2 - 3) + 4", 6),
                   test("2147483647", 2147483647),
                   test("1-(     -2)", 3)]

    CommonMethods.print_all_test_results(tests)
