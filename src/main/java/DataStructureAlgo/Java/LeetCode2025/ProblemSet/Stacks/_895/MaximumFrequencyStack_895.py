"""
Author: Nitin Gupta
Date: 3/2/2026
Question Title: 895. Maximum Frequency Stack
Link: https://leetcode.com/problems/maximum-frequency-stack/description/


Description:Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.

Implement the FreqStack class:

FreqStack() constructs an empty frequency stack.
void push(int val) pushes an integer val onto the top of the stack.
int pop() removes and returns the most frequent element in the stack.
If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 

Example 1:

Input
["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
[[], [5], [7], [5], [7], [4], [5], [], [], [], []]
Output
[null, null, null, null, null, null, null, 5, 7, 5, 4]

Explanation
FreqStack freqStack = new FreqStack();
freqStack.push(5); // The stack is [5]
freqStack.push(7); // The stack is [5,7]
freqStack.push(5); // The stack is [5,7,5]
freqStack.push(7); // The stack is [5,7,5,7]
freqStack.push(4); // The stack is [5,7,5,7,4]
freqStack.push(5); // The stack is [5,7,5,7,4,5]
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
freqStack.pop();   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack becomes [5,7,5,4].
freqStack.pop();   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
freqStack.pop();   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack becomes [5,7].
 

Constraints:

0 <= val <= 109
At most 2 * 104 calls will be made to push and pop.
It is guaranteed that there will be at least one element in the stack before calling pop.
 
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
@Principal
@HashTable
@Ordered Set


<p><p>
Company Tags
-----
@Amazon 
@Apple 
@Bloomberg 
@Google 
@Salesforce 
@Uber
<p>
-----

@Editorial <p><p>
@OptimalSolution {@link FreqStack_Dict}
"""

from collections import defaultdict
import heapq

class FreqStack_Dict:
    """
    Maintain a mapping of val to freq and a mapping of freq to stack of values.
    On push, increase freq and add to freq stack.
    On pop, get the most frequent stack, pop from it, decrease freq and remove from mapping if freq is 0

    Complexity: O(1) for push and pop,peek
    """

    def __init__(self):
        self.freq = defaultdict(int) # mapping of val to freq
        self.stack = defaultdict(list) # ([freq] ->[val]) its frequency based stack of values 
        self.max_freq = 0 

    def push(self, val: int) -> None:
        self.freq[val] +=1 # increase the frequency of the element
        self.max_freq = max(self.max_freq, self.freq[val])  # get most frequent element, initialize as needed
        self.stack[self.freq[val]].append(val)


    def pop(self) -> int:
        if not self.stack:
            return None
        
        # get the most frequent latest value
        element = self.stack[self.max_freq].pop()

        # reduce its freq
        self.freq[element] -=1

        # if that most_freq is emptied now 
        if not self.stack[self.max_freq] : 
            self.max_freq -=1 
        
        return element


class FreqStack_Heap:
    """
    Maintain a max heap of (freq, id, val) and a mapping of val to freq.
    On push, increase freq and add to heap.
    On pop, pop from heap, decrease freq and remove from mapping if freq is 0

    Heap is ordered by freq desc, id desc (to get most recent element in case of tie)
    Complexity: O(log n) for push and pop, O(1) for peek
    """


    def __init__(self):
        self.freq = defaultdict(int) # mapping of val to freq
        self.stack = [] # (freq, id, val)
        self.id = 0 

    def push(self, val: int) -> None:
        self.freq[val] +=1 # increase the frequency of the element
        heapq.heappush(self.stack, (-self.freq[val], -self.id, val, )) # tuple by default ordered lexigraphically
        self.id +=1 

    def pop(self) -> int:
        if not self.stack:
            return None
        poped = heapq.heappop(self.stack)
        val = poped[2]
        self.freq[val] -=1 

        if self.freq[val] == 0:
            del self.freq[val]

        return val
        


import unittest
from MaximumFrequencyStack_895 import FreqStack_Heap
from MaximumFrequencyStack_895 import FreqStack_Dict


class TestMaxStack(unittest.TestCase):
    def test_example(self):
        stk = FreqStack_Dict()
        stk.push(5)
        stk.push(7)
        stk.push(5)
        stk.push(7)
        stk.push(4)
        stk.push(5)
        self.assertEqual(stk.pop(), 5)
        self.assertEqual(stk.pop(), 7)
        self.assertEqual(stk.pop(), 5)
        self.assertEqual(stk.pop(), 4)

    
    

    def test_example_heap(self):
        stk = FreqStack_Heap()
        stk.push(5)
        stk.push(7)
        stk.push(5)
        stk.push(7)
        stk.push(4)
        stk.push(5)
        self.assertEqual(stk.pop(), 5)
        self.assertEqual(stk.pop(), 7)
        self.assertEqual(stk.pop(), 5)
        self.assertEqual(stk.pop(), 4)



if __name__ == "__main__":
    unittest.main()