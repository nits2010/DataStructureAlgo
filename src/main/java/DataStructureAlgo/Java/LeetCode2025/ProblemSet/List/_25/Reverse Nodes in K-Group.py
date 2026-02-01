# Definition for singly-linked list.
from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        
        def reverse(head):
            if not head or not head.next:
                return head
            
            n_1_head = head.next
            head.next = None

            next_head = reverse(n_1_head)
            n_1_head.next = head

            return next_head
        
        def get_k_nodes(head, k):
            k = k - 1
            while k > 0 and head :
                head = head.next
                k -=1
            return head

        
        tail = get_k_nodes(head, k)

        if not tail:
            return head


        next_group = tail.next

        tail.next = None

        tail = reverse(head)

        head.next = self.reverseKGroup(next_group, k)

        return tail

        