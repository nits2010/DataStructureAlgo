"""
Author: Nitin Gupta
Date: 15/03/2026
Question Title: 339. Nested List Weight Sum 🔒
Link: https://leetcode.com/problems/nested-list-weight-sum/description/ 
    https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0339.Nested%20List%20Weight%20Sum/README_EN.md
Description: Description
You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.

The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.

Return the sum of each integer in nestedList multiplied by its depth.

 

Example 1:



Input: nestedList = [[1,1],2,[1,1]]
Output: 10
Explanation: Four 1's at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
Example 2:



Input: nestedList = [1,[4,[6]]]
Output: 27
Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.
Example 3:

Input: nestedList = [0]
Output: 0
 

Constraints:

1 <= nestedList.length <= 50
The values of the integers in the nested list is in the range [-100, 100].
The maximum depth of any integer is less than or equal to 50.

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
@Depth-FirstSearch
@Breadth-FirstSearch
@PremimumQuestion

<p><p>
Company Tags
-----
@Amazon 
@Cloudera 
@Facebook 
@LinkedIn 
@Uber
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import defaultdict, deque
import heapq
from typing import ItemsView, LiteralString, List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods


"""
This is the interface that allows for creating nested lists.
You should not implement it, or speculate about its implementation
"""


class NestedInteger:
    def __init__(self, value=None):
        """
        If value is not specified, initializes an empty list.
        Otherwise initializes a single integer equal to value.
        """

    def isInteger(self):
        """
        @return True if this NestedInteger holds a single integer, rather than a nested list.
        :rtype bool
        """

    def add(self, elem):
        """
        Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
        :rtype void
        """

    def setInteger(self, value):
        """
        Set this NestedInteger to hold a single integer equal to value.
        :rtype void
        """

    def getInteger(self):
        """
        @return the single integer that this NestedInteger holds, if it holds a single integer
        Return None if this NestedInteger holds a nested list
        :rtype int
        """

    def getList(self):
        """
        @return the nested list that this NestedInteger holds, if it holds a nested list
        Return None if this NestedInteger holds a single integer
        :rtype List[NestedInteger]
        """

# Time: O(N) / O(N)
class Solution_BFS:
    """
        Since we have a helper functions like 'isInteger' which help us to simply know if the current item is integer or not.
        if this is a integer, we can simply multiply it with depth
        otherwise we take this item and increase the depth+1 and repeat.

        We can explore all by level which can be done by queue.
        Algorithm:
            1. Enqueu all the depth 1 item weighter its integer or List. Queue will keep NestedInteger
            2. process all item in queue level by level 
            3. take item and 
                3.1 see if its integer then add up to final sum 
                3.2. if ts not integer, take all its child in queue with parent_depth+1 

        Time: We touch each item at max 1 time, 
            if N = len(nestedList) then its O(N)
        Space: O(N)
    """

    def depthSum(self, nestedList: List[NestedInteger]) -> int:
        if not nestedList:
            return 0

        depth_sum = 0
        queue = deque()  # (NestedInteger, depth)

        # enqueue all the item in the list as depth 1 
        for item in nestedList:
            queue.append((item, 1))

        while queue:
            
            item, depth = queue.popleft()

            if item.isInteger():
                depth_sum += depth * item.getInteger()
            else:
                # enque all childs, with more depth
                for child in item.getList():
                    queue.append((child, depth+1))

        return depth_sum


# Time: O(N) / O(N)
class Solution_DFS:
    """
        Since we have a helper functions like 'isInteger' which help us to simply know if the current item is integer or not. 
        if this is a integer, we can simply multiply it with depth
        otherwise we take this item and increase the depth+1 and repeat. 
        This leads dfs. 
        
        sum += item * depth 
        
        Algorithm:
        1. Start with current NestedInteger list, dfs keep list of NestedInteger it needs to process, with their depth
        2. For each pass, iterate over the list 
            2.1 if current item is integer, compute sum; sum += depth * Item
            2.2 if current item is not integer, then dfs(item.getList(), depth+!)
        

        Time: We touch each item at max 1 time, 
            if N = len(nestedList) then its O(N)
        Space: Impilcit stack O(N) otherwise O(1)
    """

    def depthSum(self, nestedList: List[NestedInteger]) -> int:

        def dfs(nestedList: List[NestedInteger], depth) -> int:
            if not nestedList:
                return 0

            depth_sum = 0
            for item in nestedList:
                if item.isInteger():
                    depth_sum += item.getInteger() * depth
                else:
                    depth_sum += dfs(item.getList(), depth+1)

            return depth_sum

        return dfs(nestedList, 1)


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = None

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([], None),
                   test([], None)]

    CommonMethods.print_all_test_results(tests)
