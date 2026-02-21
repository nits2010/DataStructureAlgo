from collections import deque
from sortedcontainers import SortedList

# Time: 
    # add(n) : O(log(m))
    # calcualte() : O(1)
# Space: O(m)

class MKAverage:

    def __init__(self, m: int, k: int):
        self.m, self.k = m, k

        # hold item on left side smallest element based on current element
        self.left = SortedList()
        
        # hold item on right side largest element
        self.right = SortedList()

        # hold m - 2k elements in middle
        self.mid = SortedList()

        # incoming stream element queue, keep only m element of stream
        self.queue = deque()
        
        # sum of elements which is in middle
        self.sum = 0  

    def add(self, num):
        """add elemement in strcuture"""
        if not len(self.left) or self.left[-1] > num:  # this element needs to go left
            self.left.add(num)
        elif not len(self.right) or self.right[0] < num:
            self.right.add(num)
        else:
            self.mid.add(num)
            self.sum += num

    def balance_mid_to_k(self, _from, isFirst):
        """_from:  from to remove to push mid
        isFirst: extract first or last element
        """
        while len(_from) > self.k:
            element = _from.pop(0) if isFirst else _from.pop(-1)
            self.mid.add(element)
            self.sum += element

    def balance_left_right_to_k(self, _to, isFirst):

        while len(_to) < self.k and self.mid:
            element = self.mid.pop(0) if isFirst else self.mid.pop(-1)
            self.sum -= element
            _to.add(element)

    def balance(self):
        """Balance the structure"""

        # make left holds only k element, rest push to mid
        self.balance_mid_to_k(self.left, False)

        # make right holds only k element, rest push to mid
        self.balance_mid_to_k(self.right, True)

        # make left has k element, pull from mid to left
        self.balance_left_right_to_k(self.left, True)

        # make right has k element, pull from mid to right
        self.balance_left_right_to_k(self.right, False)

    def remove(self):
        if len(self.queue) > self.m:
            element = self.queue.popleft()
            if element in self.left:
                self.left.remove(element)
            elif element in self.right:
                self.right.remove(element)
            else:
                self.mid.remove(element)
                self.sum -= element

    def addElement(self, num: int) -> None:
        self.queue.append(num)
        self.add(num)

        if len(self.queue) > self.k:
            self.remove()

        self.balance()

    def calculateMKAverage(self) -> int:
        if len(self.queue) < self.m:
            return -1
        return self.sum // (self.m - 2 * self.k)


# Your MKAverage object will be instantiated and called as such:
# obj = MKAverage(m, k)
# obj.addElement(num)
# param_2 = obj.calculateMKAverage()
