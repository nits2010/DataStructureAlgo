"""
Author: Nitin Gupta
Date: 12/02/2026
Question Title: 143. Reorder List
Link: https://leetcode.com/problems/reorder-list/description/
Description: You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

 

Example 1:


Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:


Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]
 

Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000

File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link ReverseLinkedList, FastAndSlowPointer}
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@LinkedList
@TwoPointers
@Stack
@Recursion

<p><p>
Company Tags
-----
@Amazon
@Microsoft
@Adobe
@Facebook
@Bloomberg
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque
from typing import List, Optional, Dict, Any

from helpers.common_methods import CommonMethods

# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
        
class Solution:
    
    def get_second_list(self, head: Optional[ListNode]) -> ListNode:
        fast = head
        slow = head

        while fast and fast.next:
            fast = fast.next.next
            slow = slow.next
        
        list2 = slow.next
        slow.next = None
        return list2
    
    def reverse(self,  head: Optional[ListNode]) -> ListNode:
        if not head or not head.next:
            return head

        n_1_head = head.next
        head.next = None

        new_head = self.reverse(n_1_head)
        
        n_1_head.next = head
        return new_head


    def reorderList(self, head: Optional[ListNode]) -> None:
        """
            1-2-3-4-5-6-7-8-9-10
            => 1-10-2-9-3-8-4-7-5-6
            1-2-3-4-5
            10-9-8-7-6
        """

        if not head:
            return

        list1 = head
        list2 = self.reverse(self.get_second_list(head))
        while list2:
            first, second = list1.next, list2.next
            list1.next = list2
            list2.next = first
            list1,list2 = first, second
        # dummy = ListNode(-1,None)
        # result = dummy
        # turn = 1
        # while list1 and list2:
        #     # print(f"list1 = {list1.val}, list2={list2.val}")
        #     if turn:
        #         result.next = list1
        #         list1 = list1.next
        #     else:
        #         result.next = list2
        #         list2 = list2.next
            
        #     turn = 1-turn
        #     result = result.next
        
        # if list1 :
        #     result.next = list1
        # if list2:
        #     result.next = list2
        


def test(input_data, expected):
    """
    Test function to verify the solution
    """
    CommonMethods.print_test(["Input", "Expected"], True, input_data, expected)
    pass_test, final_pass = True, True
    output = None

    pass_test = CommonMethods.compare_result(output, expected, True)
    CommonMethods.print_test(["Output", "Pass"], False, output, "PASS" if pass_test else "FAIL")
    final_pass &= pass_test

    return final_pass


if __name__ == "__main__":
    tests: List = [test([
        ], None),
                   test([], None)]

    CommonMethods.print_all_test_results(tests)
