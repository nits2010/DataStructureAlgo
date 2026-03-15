"""
Author: Nitin Gupta
Date: 14/03/2026
Question Title: 272. Closest Binary Search Tree Value II
Link: https://leetcode.com/problems/closest-binary-search-tree-value-ii/
Description: Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link ClosestBinarySearchTreeValue_270.java}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@Stack
@Tree
@Depth-FirstSearch
@BinarySearchTree
@TwoPointers
@BinaryTree
@Heap(PriorityQueue)

<p><p>
Company Tags
-----
@Amazon 
@Facebook 
@Google 
@LinkedIn
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import defaultdict, deque
import heapq
from typing import WrapperDescriptorType, List, Optional, Dict, Any

from helpers.TreeBuilder import TreeBuilder
from helpers.common_methods import CommonMethods

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

# @ Time : O(k + log(n))
# Space: O(k)
class Solution_TwoStack_BalancedBST:
    """
    We need smallest diff k values from target. 
    Since tree is BST, the inorder traversal will generate a sorted list of values. 
    Additionally, this tree is balanced BST, which means for any value "point" in sorted list, has left and right part of it. 
    
    If we find the "point" with respect to target, then it become a two pointer approach where we move either left or right based on which side element we choose as closest to target. 
    
    In tree the left and right side of a point would be the predecessors and successors respectively. 
    
    Hence we can get all the predecessors and successors of the point=target then, its matter of doing two pointer move (left and right). 
    
    Algorithm:
    1. Get predecessors and successors wrt target. Any root.val <= target would be predecessors otherwise successors. We don't need all predecessors and successors in start
        we just need those nodes which leads predecessors and sucessors ( this way we won't iterate whole tree leading complexity = O(logn)).
        Once we find predecessors we move right side to get successors and similar to successors. 
        Store these nodes in stack, so that we can get predecessors and successors later
        
    2. Now we have two stacks, pred_stack and succes_stack. Now the top of each stack is nothing but left and right of target. 
    3. We start comparing both the value and choose the smallest one and accordingly move either left (get next predecessors) or right (get next successors).
        We will repeate this K times only. 
    4. Any moment there is no predecessors or successors, the remaining stack will give rest of the values. 
    
    
    The Strategy: 
        Two Iterators (Stacks): We use two stacks to act as "iterators"—one for finding the next smallest values (predecessor) and one for finding the next largest values (successor).
        Initialize: Populate both stacks while searching for the target in O(\log N) time.
        Radiate: Compare the top of both stacks. Pick the one closer to the target, add it to your result, and move that specific iterator to its next value.  Repeat $k$ times.
    
    Time Complexity:
        1. Build predecessors and successors: Since we just get in one direction max, we will explore max Log(n) nodes. Hence O(logn). Even if the target sits at the either leftmost or rightmost. 
        2. Getting next predecessors or successors depends on how many times it get called, which is at most O(k). However per call, we still get and push nodes in stack. 
            But we will touch each edge at max 2 times ( 1 time during build next time during next ). 
        
        Time: O(log(n) + K)
    
    Space: O(2k)
    
    """
    

    def build_predecessors_successors(self, root: TreeNode, target:float) :
        predecessors_stack = []  # nodes that are <= target
        successors_stack = []  # nodes that are > target
        
        while root:
            
            if root.val <= target:
                predecessors_stack.append(root)
                root = root.right
            else:
                successors_stack.append(root)
                root = root.left

        return predecessors_stack, successors_stack

    def get_predecessors(self, stack: list[TreeNode]) -> TreeNode:
        if not stack:
            return None

        pred = stack.pop()
        curr = pred.left
        
        while curr:
            stack.append(curr)
            curr = curr.right
        
        return pred
    
    def get_successors(self, stack: list[TreeNode]) -> TreeNode : 
        if not stack:
            return None

        succ = stack.pop()
        curr = succ.right
        
        while curr:
            stack.append(curr)
            curr = curr.left
        
        return succ
    
    def closestKValues(self, root: TreeNode, target: float, k: int) -> List[int]:
        if not root:
            return []

        predecessors_stack, successors_stack = self.build_predecessors_successors(root, target)
        result = [] # result of k closest elements
        
        # set left and right pointers from center and expand towards left and right respectively, like two pointer
        pred, succ = self.get_predecessors(
            predecessors_stack), self.get_successors(successors_stack)
        
        for _ in range(k):
            
            if pred and succ:
                if abs(pred.val - target) <= abs(succ.val - target):
                    result.append(pred.val)
                    pred = self.get_predecessors(predecessors_stack)
                else:
                    result.append(succ.val)
                    succ = self.get_successors(successors_stack)
            elif pred:
                result.append(pred.val)
                pred = self.get_predecessors(predecessors_stack)
            elif succ:
                result.append(succ.val)
                succ = self.get_successors(successors_stack)
                
                    
        return result
                    
                    
# Time O(n) or O(H)
# Space:
#     O(n+k) if implicit stack considered or O(k) for queue
#     O(H+k) if tree is BST and considered implict stack or O(k) for queue
class Solution_Queue_EarlyFinish:
    """
        We need top or smallest diff "k" values. This leads to Heap approach. However this is a BST, we can use In-order for ordering. Hence we can use queue 
        Due to in-order, the first value will always be the largest diff value. 

        Run inorder DFS , store the root val in queue such that it can have max K value and the smallest one. 

        Since we are doing inorder, we can break the dfs early once we start going on bigger value side

        Time: queue operation O(1) done for all nodes in BST (n) worst case (if the target is at the very end of the sorted sequence),
        If tree is balanced BST then O(H) because of early exit
        
        Time O(n) or O(H)
        Space: 
            O(n+k) if implicit stack considered or O(k) for queue
            O(H+k) if tree is BST and considered implict stack or O(k) for queue
    """

    def __init__(self):
        self.queue = None  
        self.finished = False

    # O(1)
    def add_to_queue(self, val, k, target):

        if self.finished:
            return

        diff = abs(val - target)

        if len(self.queue) < k:
            self.queue.append(val)
        elif abs(self.queue[0] - target) > diff:
            self.queue.popleft()
            self.queue.append(val)
        else:
            # means all the value now will be coming will always be bigger then current val. Hence finished
            self.finished = True

    # O(n)
    def inorder(self, root: TreeNode, target: float, k: int):
        if not root or self.finished:
            return

        self.inorder(root.left, target, k)
        self.add_to_queue(root.val, k, target)
        if self.finished:
            return
        self.inorder(root.right, target, k)

    def closestKValues(self, root: TreeNode, target: float, k: int) -> List[int]:
        if not root:
            return []

        self.queue = deque()
        self.finished = False

        self.inorder(root, target, k)
        return self.queue


class Solution_Queue:
    """
        We need top or smallest diff "k" values. This leads to Heap approach. However this is a BST, we can use In-order for ordering. Hence we can use queue 
        Due to in-order, the first value will always be the largest diff value. 

        Run inorder DFS , store the root val in queue such that it can have max K value and the smallest one. 
        Time: queue operation O(1) done for all nodes in BST (n)

        Time O(n)
        Space O(n+k) if implicit stack considered or O(k) for queue
    """

    def __init__(self):
        self.queue = deque()  # ( val)

    # O(1)
    def add_to_queue(self, val, k, target):
        diff = abs(val - target)

        if len(self.queue) < k:
            self.queue.append(val)
        elif abs(self.queue[0] - target) > diff:
            self.queue.popleft()
            self.queue.append(val)

    # O(n)
    def inorder(self, root: TreeNode, target: float, k: int):
        if not root:
            return

        self.inorder(root.left, target, k)
        self.add_to_queue(root.val, k, target)
        self.inorder(root.right, target, k)

    def closestKValues(self, root: TreeNode, target: float, k: int) -> List[int]:
        if not root:
            return []

        self.inorder(root, target, k)
        return self.queue


# Time: Heap operation O(logk) done for all nodes in BST (n)
# Time O(n.logk)
# Space O(n+k) if implicit stack considered or O(k) for heap
class Solution_Heap:
    """
        We need top or smallest diff "k" values. This leads to Heap approach. 
        Since We need smallest, hence keeping largest diff from heap help us to eliminate the maximum difference in O(logk) time. 

        Run Standard DFS (any), store the root val in heap such that it can have max K value and the smallest one. 
        Time: Heap operation O(logk) done for all nodes in BST (n)

        Time O(n.logk)
        Space O(n+k) if implicit stack considered or O(k) for heap
    """

    def __init__(self):
        self.max_heap = []  # (-diff, val) ordered by diff -> val of size k

    # O(logk)
    def add_to_heap(self, val, k, target):
        diff = abs(val - target)

        if len(self.max_heap) < k:
            heapq.heappush(self.max_heap, (-diff, val))
        elif -self.max_heap[0][0] > diff:
            heapq.heapreplace(self.max_heap, (-diff, val))

    # O(n.logk)
    def dfs(self, root: TreeNode, target: float, k: int):
        if not root:
            return

        self.add_to_heap(root.val, k, target)
        self.dfs(root.left, target, k)
        self.dfs(root.right, target, k)

    def closestKValues(self, root: TreeNode, target: float, k: int) -> List[int]:
        if not root:
            return []

        self.dfs(root, target, k)
        return [v for _, v in self.max_heap]


def test(tree, target, k, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(
        ["tree", "target", "k", "Expected"], True, tree, target, k, expected)
    pass_test, final_pass = True, True
    output = Solution_Heap().closestKValues(tree, target, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Heap", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_Queue().closestKValues(tree, target, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Queue", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test
    
    output = Solution_Queue_EarlyFinish().closestKValues(tree, target, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Queue_EarlyFinish", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    output = Solution_TwoStack_BalancedBST().closestKValues(tree, target, k)
    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["TwoStack_BalancedBST", "Pass"], False,
                             output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test(tree=TreeBuilder.build_tree_from_level_order([4, 2, 5, 1, 3]), target=3.714286, k=2, expected=[4, 3]),
                   test(tree=TreeBuilder.build_tree_from_level_order([1]), target=0.000000, k=1, expected=[1])]

    CommonMethods.print_all_test_results(tests)
