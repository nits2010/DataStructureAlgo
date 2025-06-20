"""
Author: Nitin Gupta
Date: 6/20/2025
Question Category: 666 - Path Sum IV
Description: https://leetcode.com/problems/path-sum-iv/ && https://leetcode.ca/2017-09-26-666-Path-Sum-IV/
Description:
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----

<p><p>
Company Tags
-----
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""




class Solution:
    def __init__(self):
        self.all_path_sum = 0
        self.cache : Dict[Tuple[int,int], int] = {}

    def pathSum(self, nums: List[int]) -> int:
        """
        :type nums: List[int]
        :rtype: int
        """
        if not nums:
            return 0
        
        #build cache for traversing the tree
        #for each node in a binary tree; root is at position p, left child is at position 2*p, right child is at position 2*p+1
        # cache will store the [depth,post] -> value
        self.all_path_sum: int = 0
        
        for num in nums:
            #extract the depth of the node, the depth is the first digit of the number
            depth = num // 100
            
            #extract the position of the node, the position is the second and third digits of the number
            position = (num - depth * 100) // 10
            
            #extrac the value of the node, the value is the last digit of the number
            value = num % 10
            
            self.cache[(depth-1,position-1)] = value #depth and pos would be 0 indexed
            
        
        def dfs(depth: int, position: int, current_sum: int) -> None:
            #extract the value of the node at [depth,position]
            root = self.cache.get((depth,position))
            
            #if a tree is complete, return
            if not root:
                return  
            
            #run its left and right child
            left_child = self.cache.get((depth+1, 2*position))
            right_child = self.cache.get((depth+1, 2*position+1))

            if left_child:
                dfs(depth+1, 2*position, current_sum+root)
            
            if right_child:
                dfs(depth+1, 2*position+1, current_sum+root)

            #this is the leaf node, get the path sum
            if not left_child and not right_child:
                current_sum+= root
                self.all_path_sum += current_sum  
        
        dfs(depth=0, position=0, current_sum=0)
        return self.all_path_sum
            
            
        

def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    output = Solution().pathSum(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([113,215,221], 12),
                   test([113,221], 4)
                   ]

    CommonMethods.print_all_test_results(tests)
