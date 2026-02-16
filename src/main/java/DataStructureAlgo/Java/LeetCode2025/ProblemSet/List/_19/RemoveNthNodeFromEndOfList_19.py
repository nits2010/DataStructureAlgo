# Definition for singly-linked list.
class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

from typing import Optional


class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        """ 
            List length = n
            kth node from end = (n - k + 1) from start
            means two pointer having seperated by k nodes, for +1, when fast reach end, slow would be at n-k+1 the node
            
        """
        if not head:
            return head
        
        if not head.next and k == 1:
            return None

        fast = head
        slow = head

        while fast and k > 0:
            fast = fast.next
            k -=1
        
        if k > 0: # this won't happen, as k<=len
            return None
        
        prev = None
        while fast:
            fast = fast.next
            prev = slow
            slow = slow.next
        
        if not prev:
            result = slow.next
            slow.next = None
            return result
        
        
        prev.next = slow.next
        slow.next = None

        return head
