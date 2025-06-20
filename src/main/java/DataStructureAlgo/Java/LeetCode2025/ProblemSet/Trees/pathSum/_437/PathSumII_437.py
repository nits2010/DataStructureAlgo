from collections import defaultdict
from typing import List, Optional



# O(n)
class SolutionOneFold:
    def __init__(self):
        self.sum_count_map = None
        self.paths = None

    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> int:
        """
            As we need to get how many paths exists whose sum=targetSum. We can scan the entire tree and for each branch, we can calculate how many times we have seen
            sum including current root. This can be stored in a map. The count from that map for targetSum is our answer.
            However, as we move and include current root to in our sum, we need to remove this sum as well once we leave that node (backtracking). 
            Hence, as soon as we see a sum=targetSum, we count the counts of it.
        """

        self.paths = 0  # define the total paths found for targetSum
        self.sum_count_map = defaultdict(int)  # define how many times we have seen the sum, key=sum, value=count
        self.sum_count_map[0] = 1  # sum=0 always possible to get without taking any nodes, hence 1 

        def dfs(current, curr_sum):
            if not current:
                return

            # add current root to sum
            curr_sum += current.val

            # add count of paths for targetSum
            self.paths += self.sum_count_map[curr_sum - targetSum]

            # update current sum paths  
            self.sum_count_map[curr_sum] += 1

            # find in left and right
            if current.left:
                dfs(current.left, curr_sum)

            if current.right:
                dfs(current.right, curr_sum)

            # backtrack, remove curr sum from a path as this node is leaving
            self.sum_count_map[curr_sum] -= 1

        dfs(root, 0)
        return self.paths


# O(n^2)
class SolutionThreeFold:
    def pathSum(self, root: Optional[TreeNode], targetSum: int) -> int:
        paths = [0]
        self.path_sum(root, targetSum, paths)
        return paths[0]

    def path_sum(self, root: Optional[TreeNode], targetSum: int, paths: List[int]) -> None:
        if not root:
            return

        # include current root in a path sum
        self.dfs(root, targetSum, 0, paths)  # O(n)

        # exclude current root and try left and right
        self.path_sum(root.left, targetSum, paths)  # O(n)
        self.path_sum(root.right, targetSum, paths)  # O(n)

    def dfs(self, root: Optional[TreeNode], targetSum: int, sum: int, paths: List[int]) -> None:

        if not root:
            return

        if targetSum == sum + root.val:
            paths[0] += 1

        self.dfs(root.left, targetSum, sum + root.val, paths)
        self.dfs(root.right, targetSum, sum + root.val, paths)




def test(input_data, targetSum, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True

    root:TreeNode = TreeBuilder.build_tree_from_level_order(input_data)

    solution_three_fold = SolutionThreeFold()
    output = solution_three_fold.pathSum(root, targetSum)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["SolutionThreeFold", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test


    solution_one_fold = SolutionOneFold()
    output = solution_one_fold.pathSum(root, targetSum)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["SolutionOneFold", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    # Add test cases here
    tests: List = [test([10, 5, -3, 3, 2, None, 11, 3, -2, None, 1], 8, 3),
                   test([5, 4, 8, 11, None, 13, 4, 7, 2, None, None, 5, 1], 22, 3),
                   test([1000000000, 1000000000, None, 294967296, None, 1000000000, None, 1000000000, None, 1000000000],
                        0, 0)]

    CommonMethods.print_all_test_results(tests)

