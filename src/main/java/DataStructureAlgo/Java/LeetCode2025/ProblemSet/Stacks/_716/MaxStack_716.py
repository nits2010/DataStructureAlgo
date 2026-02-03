"""
Author: Nitin Gupta
Date: 3/2/2026
Question Title:
Link: https://leetcode.com/problems/max-stack/description/
https://www.hellointerview.com/community/questions/max-stack/cm5eguhac02ka838o22vxy5fk
https://github.com/doocs/leetcode/blob/main/Solution1/0700-0799/0716.Max%20Stack/README_EN.md
https://leetcode.ca/all/716.html


Description: Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.

Implement the MaxStack class:

MaxStack() Initializes the stack object.
void push(int x) Pushes element x onto the stack.
int pop() Removes the element on top of the stack and returns it.
int top() Gets the element on the top of the stack without removing it.
int peekMax() Retrieves the maximum element in the stack without removing it.
int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
You must come up with a Solution1 that supports O(1) for each top call and O(logn) for each other call.

 

Example 1:

Input
["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
[[], [5], [1], [5], [], [], [], [], [], []]
Output
[null, null, null, null, 5, 5, 1, 5, 1, 5]

Explanation
MaxStack stk = new MaxStack();
stk.push(5);   // [5] the top of the stack and the maximum number is 5.
stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
stk.top();     // return 5, [5, 1, 5] the stack did not change.
stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
stk.top();     // return 1, [5, 1] the stack did not change.
stk.peekMax(); // return 5, [5, 1] the stack did not change.
stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
stk.top();     // return 5, [5] the stack did not change.
 

Constraints:

-107 <= x <= 107
At most 105 calls will be made to push, pop, top, peekMax, and popMax.
There will be at least one element in the stack when pop, top, peekMax, or popMax is called.
 
 
File reference
-----------
Duplicate {@link}
Similar {@link MinStack}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@hard
@Stack
@Design
@LinkedList
@Doubly-LinkedList
@OrderedSet
@PremiumQuestion
@LockedProblem


<p><p>
Company Tags
-----
@Amazon 
@Bloomberg 
@Google 
@LinkedIn 
@Lyft 
@Microsoft 
@Twitter
<p>
-----

@Editorial <p><p>
Building Intuition
-----
A naive approach using a single list requires scanning all elements to find the maximum (O(n) for peekMax and popMax). A better approach uses two data structures: a stack for LIFO order and a max heap (or sorted structure) for tracking maximums. However, heaps don't support efficient arbitrary deletion. The optimal solution combines a doubly-linked list (for O(1) deletion at any position) with a TreeMap/balanced BST (for O(log n) max lookup and removal). Each element stores its position in both structures. For example, pushing 5, 1, 5 creates nodes in the list and entries in the TreeMap mapping 5 → [node1, node3], 1 → [node2].
This pattern appears in real-time monitoring systems (tracking current max metric while maintaining history), undo/redo stacks with priority (undo highest-priority action), and trading systems (process orders in LIFO but also need to cancel highest-value orders). Understanding how to maintain multiple orderings efficiently is crucial for building responsive systems that need both temporal and value-based access patterns.

Doubly-Linked List for Stack Storage, sorted list for Max Tracking
-----
Implement a doubly-linked list node and list structure to store stack elements with O(1) insertion and deletion at any position. Each node contains a value, timestamp (for recency tracking), and pointers to previous/next nodes. The list maintains head and tail pointers for efficient stack operations. For example, pushing 5, 1, 5 creates three linked nodes where each can be removed in O(1) time given its reference. This structure serves as the foundation for both stack operations and the max-tracking mechanism in the next section.
-----
@OptimalSolution {@link }
"""

from sortedcontainers import SortedList




# This solution inject id to make sure to maintain the insertion order when there are duplicate max elements.
class MaxStack :

    class Node : 
        def __init__(self, val: int, uid:int):
            self.val    = val
            self.prev   = None
            self.next   = None
            self.id     = uid
        
    class DoublyLinkedList :
         
        def __init__(self):
            self.head  = None
            self.tail  = None
        
        def push(self, node): 

            #santies the node before adding
            node.prev = None
            node.next = None

            if not self.head:
                self.head = node
                self.tail = node
                return 
            self.tail.next = node   
            node.prev = self.tail
            self.tail = node
        

        def remove(self, node):
            if not node:
                return None

            next = node.next
            prev = node.prev

            if prev:
                prev.next = next
            
            if next:
                next.prev = prev
            
            node.prev = None
            node.next = None

            if node == self.head:
                self.head = next
            
            if node == self.tail:
                self.tail = prev
            
            return node
        
        def pop(self):
            if not self.tail:
                 return None
            return self.remove(self.tail)
        
        def top(self):
            return self.tail
        
        def isEmpty(self):
            return self.head is None

       
    def __init__(self):
        self.stack = MaxStack.DoublyLinkedList()
        self.sorted_list = SortedList()  # (val, id, node)
        self._id = 0
        
    def push(self, val:int):
        self._id += 1
        node = MaxStack.Node(val, self._id)
        self.stack.push(node)
        self.sorted_list.add( (node.val, self._id, node) )

    def pop(self) -> int:
        if self.stack.isEmpty():
            return None
        poped = self.stack.pop()
        self.sorted_list.remove((poped.val, poped.id, poped))
        return poped.val
        
    def top(self) -> int:
        top = self.stack.top()
        return top.val if top else None

    def peekMax(self) -> int:
            return self.sorted_list[-1][0] if self.sorted_list else None
        
    def popMax(self) -> int:
        _, _, max_node = self.sorted_list.pop(-1) if self.sorted_list else None
            
        if not max_node:
            return None
            
        self.stack.remove(max_node)
        return max_node.val



import unittest
from MaxStack_716 import MaxStack


class TestMaxStack(unittest.TestCase):
    def test_example(self):
        stk = MaxStack()
        stk.push(5)
        stk.push(1)
        stk.push(5)
        self.assertEqual(stk.top(), 5)
        self.assertEqual(stk.popMax(), 5)
        self.assertEqual(stk.top(), 1)
        self.assertEqual(stk.peekMax(), 5)
        self.assertEqual(stk.pop(), 1)
        self.assertEqual(stk.top(), 5)

    def test_mixed(self):
        stk = MaxStack()
        for v in [2, 1, 2, 3, 3, 0]:
            stk.push(v)
        self.assertEqual(stk.peekMax(), 3)
        self.assertEqual(stk.popMax(), 3)
        self.assertEqual(stk.peekMax(), 3)
        self.assertEqual(stk.popMax(), 3)
        self.assertEqual(stk.peekMax(), 2)
        self.assertEqual(stk.pop(), 0)
        self.assertEqual(stk.pop(), 2)


if __name__ == "__main__":
    unittest.main()