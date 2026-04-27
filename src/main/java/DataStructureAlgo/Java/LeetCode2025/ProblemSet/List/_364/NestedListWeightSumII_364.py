"""
Author: Nitin Gupta
Date: ${DATE}
Question Title: 364. Nested List Weight Sum II 🔒
Link: https://leetcode.com/problems/nested-list-weight-sum-ii/description/ 
https://github.com/doocs/leetcode/blob/main/solution/0300-0399/0364.Nested%20List%20Weight%20Sum%20II/README_EN.md
Description: Description
You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.

The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth. Let maxDepth be the maximum depth of any integer.

The weight of an integer is maxDepth - (the depth of the integer) + 1.

Return the sum of each integer in nestedList multiplied by its weight.

 

Example 1:



Input: nestedList = [[1,1],2,[1,1]]
Output: 8
Explanation: Four 1's with a weight of 1, one 2 with a weight of 2.
1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
Example 2:



Input: nestedList = [1,[4,[6]]]
Output: 17
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1.
1*3 + 4*2 + 6*1 = 17
 

Constraints:

1 <= nestedList.length <= 50
The values of the integers in the nested list is in the range [-100, 100].
The maximum depth of any integer is less than or equal to 50.
There are no empty lists.

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link NestedListWeightSum_339.py}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Depth-FirstSearch
@Breadth-FirstSearch
@stack
@PremimumQuestion

<p><p>
Company Tags
-----
@DiDi 
@Facebook 
@LinkedIn
@Amazon 
@Cloudera 
@Uber
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

from collections import defaultdict, deque
import heapq
from typing import SupportsComplex, is_typeddict, List, Optional, Dict, Any

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


class Solution_BFS :
    """
        In DFS, we compute the max_depth and using mathematical decomposition we compute the sum. 
        
        On the other hand, we can do using BFS as well by computing by level. 
        
        Lets understand the reverse_weighted_sum ( the formula we derive) bit more in level by level case. 
        
        weighted_sum = Sum ( weight * item)
        This `weight * item` means item will be sum up, up to weight time. 
        The more the weight, it will sum more it times otherwise less. 
        
        So 
        if item x = 3 weight with value = 2
            then weithed sum = 2 + 2 + 2  
        if item x = 2 weight with value = 5
            then weithed sum = 5 + 5 
        
        This implies that we are able to sum the item based on their level then we can compute the total sum. 
            total_sum += unweighted_sum
            unweighted_sum = sum(level_sum)
            level_sum = item1 + item2 + .... itemn at current level (depth)
            
           doing such, makes unweighted_sum compute the same level element multiple times (weight time) and the total_sum accumplate all. 
           
           
        Example: 
        item 1 , depth = 1
        item 2, depth = 2
        item 3, depth = 2
        item 4, depth = 1
        
        max_depth = 2, weight = 2 - depthi + 1
        Logical Sum; 
            weighted_sum = (2-1+1) * 1 + (2-2+1)*2 + (2-2+1)*3 + (2-1+1)*4 = 2 + 2 + 3 + 8 = 15
            
        
        Level sum = 
            1 level = 1 + 4
        unweighted_sum = 5, total_sum = 5
            2 level = 2 + 3
        unweighted_sum = 5 + 5 = 10, total_sum = 5 + 10 = 15
            
    Time Complexity: 
        We touch each item at max 1 time, 
            if N = len(nestedList) then its O(N)
    Space Complexity: O(N) queue 
         
    """
    
    def depthSum(self, nestedList: List[NestedInteger]) -> int:
        if not nestedList:
            return 0
      
        queue = deque()
        
        for item in nestedList:
            queue.append(item)
      
        weighted_sum = 0 
        unweighted_sum = 0
      
        while queue:
            # process level by level
            level_sum = 0 
            for _ in range(len(queue)):
                item = queue.popleft()
              
                if item.isInteger():
                    level_sum += item.getInteger()
                else:
                    for child in item.getList():
                        queue.append(child)
            
            unweighted_sum += level_sum
            weighted_sum += unweighted_sum

        return weighted_sum
            

class Solution_DFS:
    """
    weight = max_depth - depthi + 1
    weight_sum = weight * item
        Per item level: 
                = (max_depth - depthi + 1 ) * itemi
                = max_depth*itemi - depthi*itemi + 1*itemi
                = max_depth*itemi + itemi - depthi*itemi
                 = itemi * (max_depth + 1) - depthi*itemi
    
    sum(weighted_sum) = sum(itemi * (max_depth + 1) - depthi*itemi) 
                           = item1 * (max_depth + 1) - depth1*item1 + item2 * (max_depth + 1) - depth2*item2
                           = item1 * (max_depth + 1) + item2 * (max_depth + 1) - depth1*item1 - depth2*item2
                           = (max_depth + 1) * (item1 + item2 ) - (depth1*item1 + depth2*item2 )
                            = (max_depth + 1) * S - (depth_sum)
    
    Hence we can use same approach as NestedListWeightSum_339, we will compute additionally 
        1. item_sum += itemi
        2. depth_sum += itemi*depthi
        2. compute max_depth = max(max_depth, depthi)
    
    then at the end its
        result = (max_depth + 1) * S - (depth_sum)
        
    
    Time Complexity: 
        We touch each item at max 1 time, 
            if N = len(nestedList) then its O(N)
    Space Complexity: 
        Impilcit stack O(N) otherwise O(1)
               
    """

    def depthSum(self, nestedList: List[NestedInteger]) -> int:
        if not nestedList:
            return 0

        # unweighted_sum = depth * item
        # item_sum = sum ( itemi...itemn )
        max_depth, item_sum = 0,0  
        
        def dfs(nestedList: List[NestedInteger], depth) -> int :
            nonlocal max_depth, item_sum
            
            if not nestedList:
                return 0
            unweighted_sum = 0 
            max_depth = max(max_depth, depth)
            for item in nestedList:
                if item.isInteger():
                    val = item.getInteger()
                    item_sum += val
                    unweighted_sum += depth * val
                else:
                    unweighted_sum += dfs(item.getList(), depth+1)
            
            return unweighted_sum
        
        unweighted_sum = dfs(nestedList, 1)
        return  (max_depth + 1) * item_sum - unweighted_sum


class Solution_DFS_Two_pass:
    """
    We do two pass
    1. Get the max_depth using max_depth dfs
    2. Then compute the weighted sum in second DFS. 

    Algorithm:
    1. 1st Passs: max_depth(tem) compute the max_depth overall. 
    2. 2nd Pass: Start doing same as 339. Nested List Weight Sum 🔒, instead of multiply by depth, compute weight of this item (max_depth - depth(i) + 1 ) then then multiply by item(i)
        
    Time Complexity: 
        We touch each item at max 1 time, 
            if N = len(nestedList) then its O(N)
    Space Complexity: 
        Impilcit stack O(N) otherwise O(1)
               
    """
    
    def max_depth(self, nestedList: List[NestedInteger]) -> int:
        max_depth = 0
        
        def dfs(nestedList: List[NestedInteger], depth)  : 
            nonlocal max_depth
            
            if not nestedList:
                return 
            
            max_depth = max(max_depth, depth)
            
            for item in nestedList:
                if not item.isInteger():
                    dfs(item.getList(), depth+1)
        
        dfs(nestedList, 1)
        return max_depth

    def depthSum(self, nestedList: List[NestedInteger]) -> int:
        if not nestedList:
            return 0

        max_depth = self.max_depth(nestedList)
        def dfs(nestedList: List[NestedInteger], depth) -> int:
            
            if not nestedList:
                return 0
            
            total_sum = 0
            for item in nestedList:
                if item.isInteger():
                    weight = max_depth - depth + 1
                    total_sum += weight * item.getInteger()
                else:
                    total_sum += dfs(item.getList(), depth+1)
            
            return total_sum


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
