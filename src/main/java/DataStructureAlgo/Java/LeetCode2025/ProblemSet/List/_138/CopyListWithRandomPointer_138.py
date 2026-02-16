"""
# Definition for a Node.

"""
from typing import Optional


class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random

class Solution_InBetweenInsertion:
    def copyRandomList(self, head: "Optional[Node]") -> "Optional[Node]":
        if not head:
            return None

        org = head

        # create clone and set org->next [clone] and clone-next [org.next]
        while org:
            nxt = org.next

            # clone and put in between
            clone = Node(org.val)

            org.next = clone
            clone.next = nxt

            org = nxt

        # fix random
        org = head
        while org:
            clone = org.next
            nxt = clone.next

            clone.random = org.random.next if org.random else None

            org = nxt

        org = head
        clone_head = org.next

        while org:
            clone = org.next
            nxt = clone.next

            clone.next = clone.next.next if clone.next else None
            org.next = nxt

            org = nxt

        return clone_head


class Solution_HashMap2Scan:
    def copyRandomList(self, head: "Optional[Node]") -> "Optional[Node]":
        if not head:
            return None

        map = {}

        org = head
        while org:
            clone = Node(org.val)
            map[org] = clone
            org = org.next

        # set next and random
        org = head
        clone_head = map[org]
        while org:
            clone = map[org]
            clone.next = map[org.next] if org.next else None
            clone.random = map[org.random] if org.random else None
            org = org.next

        return clone_head


class Solution_3PhaseScan:
    def copyRandomList(self, head: "Optional[Node]") -> "Optional[Node]":
        if not head:
            return head

        org = head

        clone_head = None
        clone = None

        # create clone and set org->next [clone] and clone-next [org.next]
        while org:
            nxt = org.next
            clone = Node(org.val)
            clone.next = nxt
            org.next = clone
            org = nxt

        # copy random
        org = head
        clone = org.next
        while org:
            clone = org.next
            if org.random:
                clone.random = org.random.next
            org = clone.next

        # set next and restore
        org = head
        clone_head = org.next
        while org:
            clone = org.next
            nxt = clone.next

            # set clone next
            clone.next = nxt.next if nxt else None

            # restore
            org.next = nxt

            org = nxt

        return clone_head
