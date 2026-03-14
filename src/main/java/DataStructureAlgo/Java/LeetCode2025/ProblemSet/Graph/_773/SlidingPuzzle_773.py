from collections import deque
from typing import List


"""
Time: m = rows, n = cols 
    1. compute initial state: O(m*n)
    2. Queue
        1. Finding a index of "0" is O(m*n)
        2. Creating a new state is O(m*n)
        There could be mxn position shifts. Each state can shift to next state generates (mxn)! states.
        Queue processing do visit each state at most once. hence O((mxn)!) 
        total work = total_states * each_state_work
        
        Time: O((mxn)! * (mxn))
        Space: O((mxn)!)
    
"""
class Solution:
    def slidingPuzzle(self, board: List[List[int]]) -> int:
        if not board:
            return 0

        directions = {
            0: [1, 3],  # right , bottom
            1: [0, 2, 4],  # left, right, bottom
            2: [1, 5],  # left, bottom
            3: [0, 4],  # up, right
            4: [3, 5, 1],  # left, right, up
            5: [4, 2], # left, up
        }

        current_state = "".join(str(cell) for row in board for cell in row)
        final_state = "123450"

        if current_state == final_state:
            return 0

        visited = set()
        queue = deque()

        queue.append((current_state, 0))
        visited.add(current_state)

        while queue:

            for _ in range(len(queue)):
                current_state, moves = queue.popleft()

                if current_state == final_state:
                    return moves

                # move "0" based on its pos
                index = current_state.index("0")

                for pos in directions[index]:
                    next_state = list(current_state)
                    next_state[index], next_state[pos] = (
                        next_state[pos],
                        next_state[index],
                    )
                    # print(f"next {next_state}")
                    next_state = "".join(next_state)

                    if next_state not in visited:
                        queue.append((next_state, moves + 1))
                        visited.add(next_state)
        return -1
