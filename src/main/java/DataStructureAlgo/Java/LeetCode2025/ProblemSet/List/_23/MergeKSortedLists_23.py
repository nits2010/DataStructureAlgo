"""
Author: Nitin Gupta
Date: 2026-03-14
Question Title: Merge K Sorted Lists
Link: https://leetcode.com/problems/merge-ksorted-lists/
Description:
File reference
-----------
Duplicate {@link}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----

<p><p>
Company Tags
-----
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""

# Definition for singly-linked list.
from typing import Optional, List

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:    
    def mergeKLists(self, lists: List[Optional[ListNode]]) -> Optional[ListNode]:
        """

        """
        if not lists:
            return None

        def merge(list1, list2):

            if not list1:
                return list2
            if not list2:
                return list1
            
            if list1.val <= list2.val:
                result = list1
                list1.next = merge(list1.next, list2)
            else:
                result = list2
                list2.next = merge(list1, list2.next)
            
            return result

        size = len(lists)
        height = 1
        
        while height < size :
            i = 0
            while i + height < size :

                lists[i] = merge(lists[i], lists[i+height])
                i = 2*height + i

            height = 2*height
        print(lists)
        return lists[0]

