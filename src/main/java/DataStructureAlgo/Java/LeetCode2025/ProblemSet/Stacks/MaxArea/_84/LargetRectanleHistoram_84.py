from collections import deque, defaultdict
from typing import List, Optional, Dict, Any
import heapq
from helpers.common_methods import CommonMethods

class Solution_V1:
    def largestRectangleArea(self, heights: List[int]) -> int:
        """
            A bar on left can form a histogram area with bar on right based on their respective heights
            if height(left) > height(right), then left bar can not go beyond the current right bar to build the area as 
            its height sequence already broken. 
            Hence, it means that ok keep all the bar from left to right in incresing order of height will keep building 
            the histogram with more width. 

            To compute all the height in increasing height, we will keep a monotonic increasing height and as soon it breaks, 
            we will compute that bar area
        """
        stack = [-1]
        heights.append(0) # dummy height at the end, to avoid index calculations
        max_area = 0
        for i in range(len(heights)) : 

            while stack[-1] != -1 and heights[stack[-1]] >= heights[i]:
                h = heights[stack.pop()]
                w = i - stack[-1] - 1
                max_area = max(max_area, h*w)
            
            stack.append(i)
        
        heights.pop()
        return max_area

class Solution_V2:
    def largestRectangleArea(self, heights: List[int]) -> int:
        """
            A bar on left can form a histogram area with bar on right based on their respective heights
            if height(left) > height(right), then left bar can not go beyond the current right bar to build the area as 
            its height sequence already broken. 
            Hence, it means that ok keep all the bar from left to right in incresing order of height will keep building 
            the histogram with more width. 

            To compute all the height in increasing height, we will keep a monotonic increasing height and as soon it breaks, 
            we will compute that bar area
        """
        stack = [-1]
        heights.append(0) # dummy height at the end, to avoid index calculations
        max_area = 0
        for i in range(len(heights)) : 

            while heights[stack[-1]] > heights[i]: # when it reaches that stack has only -1, heights[-1] = 0, so loop will break
                h = heights[stack.pop()]
                w = i - stack[-1] - 1
                max_area = max(max_area, h*w)
            
            stack.append(i)
        
        heights.pop()
        return max_area

def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution_V1().largestRectangleArea(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output-V1", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_V2().largestRectangleArea(input_data)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output-V2", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([2,1,5,6,2,3], 10),
                   test([2,4], 4),
                   test([7,1,7,2,2,4], 8),
                   test([1,3,7], 7)]

    CommonMethods.print_all_test_results(tests)

        

