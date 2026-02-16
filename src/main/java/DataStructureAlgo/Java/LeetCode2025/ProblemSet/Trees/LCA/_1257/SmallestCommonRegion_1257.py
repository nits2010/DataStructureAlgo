"""
Author: Nitin Gupta
Date: 16/02/2026
Question Title: 1257. Smallest Common Region 🔒
Link: https://leetcode.com/problems/smallest-common-region/
https://github.com/doocs/leetcode/blob/main/solution/1200-1299/1257.Smallest%20Common%20Region/README_EN.md
Description: 
You are given some lists of regions where the first region of each list directly contains all other regions in that list.

If a region x contains a region y directly, and region y contains region z directly, then region x is said to contain region z indirectly. Note that region x also indirectly contains all regions indirectly containd in y.

Naturally, if a region x contains (either directly or indirectly) another region y, then x is bigger than or equal to y in size. Also, by definition, a region x contains itself.

Given two regions: region1 and region2, return the smallest region that contains both of them.

It is guaranteed the smallest region exists.

 

Example 1:

Input:
regions = [["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]],
region1 = "Quebec",
region2 = "New York"
Output: "North America"
Example 2:

Input: regions = [["Earth", "North America", "South America"],["North America", "United States", "Canada"],["United States", "New York", "Boston"],["Canada", "Ontario", "Quebec"],["South America", "Brazil"]], region1 = "Canada", region2 = "South America"
Output: "Earth"
 

Constraints:

2 <= regions.length <= 104
2 <= regions[i].length <= 20
1 <= regions[i][j].length, region1.length, region2.length <= 20
region1 != region2
regions[i][j], region1, and region2 consist of English letters.
The input is generated such that there exists a region which contains all the other regions, either directly or indirectly.
A region cannot be directly contained in more than one region.

File reference
-----------
Duplicate {@link}
Similar {@link LowestCommonAncestorOfABinaryTree_236}
extension {@link LowestCommonAncestorOfABinaryTree_236}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@Tree
@Depth-FirstSearch
@Breadth-FirstSearch
@Array
@HashTable
@String

<p><p>
Company Tags
-----
@Airbnb
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

class SmallestCommonRegion_1257:
    def findSmallestRegion(self, regions: List[List[str]], region1: str, region2: str) -> str:
        if not regions:
            return ""

        child_parent_map = {} # child -> parent
        
        for regiion_list in regions:
            parent = regiion_list[0]
            
            for child in regiion_list[1:]:
                child_parent_map[child] = parent 
        
        
        # move upwords from first region1 and build parent set 
        ancestors  = set()
        while region1 in child_parent_map :
            ancestors .add(region1)
            region1 = child_parent_map[region1]
        
        # find lca
        lca = region2
        while lca in child_parent_map and lca not in ancestors :
            lca = child_parent_map[lca]
        
        return lca
        
        

def test(regions, region1, region2, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["regions", "region1", "region2", "Expected"], True, regions, region1, region2, expected)
    pass_test, final_pass = True, True
    output = SmallestCommonRegion_1257().findSmallestRegion(regions=regions, region1=region1, region2=region2)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]], "Quebec", "New York", "North America"),
                   ]

    CommonMethods.print_all_test_results(tests)
