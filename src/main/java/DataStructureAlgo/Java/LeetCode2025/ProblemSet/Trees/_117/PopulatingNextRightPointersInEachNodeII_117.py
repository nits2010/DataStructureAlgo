# Definition for a Node.


from collections import deque
from typing import Optional

class Node:
    def __init__(self, val: int = 0, left: 'Optional[Node]' = None, right: 'Optional[Node]' = None, next: 'Optional[Node]' = None):
        self.val = val
        self.left = left
        self.right = right
        self.next = next


# using dummy
class Solution:
    """
    This will treat next as next node to be iterate, work like creating a list of Node connected through next
    """

    def connect(self, root: Optional[Node]) -> Optional[Node]:
        original_root = root
        dummy: Node = Node()  # dummy work like a iterator for next
        last_next = dummy

        while root:

            # if ther is a left node
            if root.left:
                last_next.next = root.left
                last_next = last_next.next

            if root.right:
                last_next.next = root.right
                last_next = last_next.next

            root = root.next

            # if there is no next
            if not root:
                root = dummy.next
                last_next = dummy
                dummy.next = None  # this will make sure that for the last node, loop ends as there is no more next left

        return original_root


class SolutionUsingNextTLE:
    def connect(self, root: Optional[Node]) -> Optional[Node]:

        if not root:
            return root

        # connect root to its next if available
        if root.next:
            root.next = self.connect(root.next)

        # connect its left and right
        if root.left:

            if root.right:

                # connect left next to right
                root.left.next = root.right

                # get the next from the root for root.right
                root.right.next = self.getNext(root)

            else:
                # since there is no right, get next from root
                root.left.next = self.getNext(root)

            # process left-subtree
            self.connect(root.left)

        elif root.right:  # process right sub-tree
            root.right.next = self.getNext(root)
            self.connect(root.right)

        elif root.next:  # root is leaf node, process its next now
            self.connect(root.next)

        return root

    def getNext(self, root: Node) -> Optional[Node]:
        next = root.next

        while next:
            if next.left:
                return next.left

            if next.right:
                return next.right

            next = next.next

        return next



class SolutionBFS:
    def connect(self, root: Optional[Node]) -> Optional[Node]:
        if not root:
            return root

        queue = deque()
        previous = None
        queue.append(root)
        queue.append(previous)

        while queue:
            current = queue.popleft()

            # if a node is there, connect to next and its left and right
            if current:
                current.next = queue[0]  # there will always be at least one node in queue, could be None

                if current.left:
                    queue.append(current.left)

                if current.right:
                    queue.append(current.right)

            elif queue:
                queue.append(None)

        return root
