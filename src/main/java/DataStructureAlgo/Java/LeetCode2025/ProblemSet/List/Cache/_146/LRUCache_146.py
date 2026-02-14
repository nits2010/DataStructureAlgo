from typing import OrderedDict


class Solution_UsingOrderedDict:
    class LRUCache:

        def __init__(self, capacity: int):
            if not capacity:
                return 
            self.cache = OrderedDict[int,int]() # ordered(key:value)
            self.capacity = capacity
            

        def get(self, key: int) -> int:
            if key in self.cache:
                self.cache.move_to_end(key, last=True)
                return self.cache[key]
            return -1


        def put(self, key: int, value: int) -> None:
            if key in self.cache:
                self.cache.move_to_end(key, last=True)

            self.cache[key] = value

            if len(self.cache) > self.capacity:
                self.cache.popitem(last=False)


class LRUCache:
    VALUE = -1
    KEY = -1

    class DLNode:
        
        def __init__(self, key, value):
            self.key, self.value = key, value
            self.next = self.prev = None
            
        
    
    class CDL :
        # circular doubly linked list 
        def __init__(self):
            # head = LRU (least recently)  
            # tail = MRU (most recently used)
            self.dummy =  LRUCache.DLNode(LRUCache.KEY, LRUCache.VALUE)
            self.head = self.tail  = self.dummy
            
            #cicular
            self.head.next = self.tail
            self.tail.prev = self.head
      
        
        # append at the end only
        def append(self, node: DLNode):
            nxt, prev = self.tail.next, self.tail.prev 
            
            self.tail.next = node
            node.prev = self.tail
            node.next = nxt
            nxt.prev = node
            
            self.tail = node

        # pop from anywhere
        def pop(self, node):
            prev, nxt = node.prev, node.next

            prev.next = nxt
            nxt.prev = prev

            # if we are removing tail node it self
            if self.tail == node:
                self.tail = prev

            node.next = node.prev = None
        
        def remove_lru(self):
            """
                remove the Head node
            """
            # if list is empty
            if self.head.next == self.head:
                return None

            node = self.head.next
            self.pop(node)
            return node




    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = {}
        self.cdll = self.CDL()

        

    def get(self, key: int) -> int:
        if key not in self.cache:
            return -1
        
        node = self.cache[key]
        self.cdll.pop(node)
        self.cdll.append(node)
        
        return node.value
        

    def put(self, key: int, value: int) -> None:

        if key in self.cache:
            node = self.cache[key]
            self.cdll.pop(node)
            node.value = value
        else:
            node = self.DLNode(key, value)
        
        self.cdll.append(node)
        self.cache[key] = node

        if len(self.cache) > self.capacity :
            node = self.cdll.remove_lru() # remove the lru key
            if node: # Guard against popping from an empty list or capacity 0
                del self.cache[node.key]



        







