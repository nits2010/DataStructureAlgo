# Definition for a Node.

from collections import deque
from typing import Optional

class Node:
    def __init__(self, val: int = 0, left: Optional['Node'] = None, right: Optional['Node'] = None, next: Optional['Node'] = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next



class Solution:
    def connect(self, root: 'Optional[Node]') -> 'Optional[Node]':
        if not root:
            return root

        queue = deque([(root, 0)])  # queue root and its level=0
        previous_node, previous_level = None, -1

        while queue:
            current_node, current_lvl = queue.popleft()

            # no more node to process
            if not current_node:
                return root

            if previous_level == current_lvl and previous_node is not None:
                previous_node.next = current_node

            # update previous node and level
            previous_node, previous_level = current_node, current_lvl

            # queue left and right with next level
            if current_node.left:
                queue.append((current_node.left, current_lvl + 1))
            if current_node.right:
                queue.append((current_node.right, current_lvl + 1))

        return root


class SolutionSimplified:
    def connect(self, root: 'Optional[Node]') -> 'Optional[Node]':
        if not root:
            return root

        queue = deque([root])  # queue root and its level=0
        previous_node= None

        while queue:
            size = len(queue)
            previous_node= None
            
            # process all nodes in the current level
            for _ in range(size):
                current_node = queue.popleft()
            
                if previous_node:
                    previous_node.next = current_node
                
                #update previous node
                previous_node = current_node
                
                #queue left and right
                if current_node.left:
                    queue.append(current_node.left)
                if current_node.right:
                    queue.append(current_node.right)
           
        return root

class SolutionRecursive:
    def connect(self, root: 'Optional[Node]') -> 'Optional[Node]':
        if not root:
            return root

        if root.left:
            root.left.next = root.right

        if root.right:
            root.right.next = None if not root.next else root.next.left

        self.connect(root.left)
        self.connect(root.right)
        return root

def print_levels_with_next(root):
    '''Prints each level's nodes and their next pointers.'''
    level = 0
    node = root
    while node:
        curr = node
        line = []
        while curr:
            nxt = curr.next.val if curr.next else None
            line.append(f"{curr.val}(next:{nxt})")
            curr = curr.next
        print(f"Level {level}: ", ' -> '.join(line))
        node = node.left
        level += 1

if __name__ == "__main__":
    # Build a perfect binary tree:
    #        1
    #      /   \
    #     2     3
    #    / \   / \
    #   4  5  6   7
    n4 = Node(4)
    n5 = Node(5)
    n6 = Node(6)
    n7 = Node(7)
    n2 = Node(2, n4, n5)
    n3 = Node(3, n6, n7)
    root = Node(1, n2, n3)

    print("Testing Solution (BFS):")
    sol = Solution()
    connected_root = sol.connect(root)
    print_levels_with_next(connected_root)

    # Rebuild the tree for the recursive solution (since next pointers are now set)
    n4 = Node(4)
    n5 = Node(5)
    n6 = Node(6)
    n7 = Node(7)
    n2 = Node(2, n4, n5)
    n3 = Node(3, n6, n7)
    root = Node(1, n2, n3)

    print("\nTesting SolutionRecursive:")
    sol_rec = SolutionRecursive()
    connected_root_rec = sol_rec.connect(root)
    print_levels_with_next(connected_root_rec)
