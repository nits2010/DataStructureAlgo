
from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods


class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        """
        As we need to know the number of islands within this grid, we can start with any of the land and connect it to all its adj lands.
        Once these lands are connected, they will no longer participate in the next island discovery. Doing such, we can count the number of islands with in this graph.

        To avoid re-discovery of the island which has already been explored earlier, we will mark that land to water [ or we can use a boolean visited array ]
        """

        # go over all the land and discover the island attached with this land
        rows = len(grid)
        cols = len(grid[0])

        def dfs(row: int, col: int):
            if not is_safe(row, col):
                return

            # mark this land to water to avoid re-exploration
            grid[row][col] = "0"

            # explore all four directions; horizontal and vertical

            # horizontally
            dfs(row + 1, col)
            dfs(row - 1, col)

            # vertically
            dfs(row, col + 1)
            dfs(row, col - 1)

        def is_safe(row: int, col: int):
            return 0 <= row < rows and 0 <= col < cols and grid[row][col] == "1"

        islands = 0  # define the number of islands
        for row in range(rows):
            for col in range(cols):
                if grid[row][col] == "1":  # if this the land, then explore
                    dfs(row, col)
                    islands += 1

        return islands


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = Solution().numIslands(input_data)

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([
        ["1", "1", "1", "1", "0"],
        ["1", "1", "0", "1", "0"],
        ["1", "1", "0", "0", "0"],
        ["0", "0", "0", "0", "0"]
    ], 1),
        test([
            ["1", "1", "0", "0", "0"],
            ["1", "1", "0", "0", "0"],
            ["0", "0", "1", "0", "0"],
            ["0", "0", "0", "1", "1"]
        ], 3)]

    CommonMethods.print_all_test_results(tests)
