"""
 * T/S :
 * get -> O(log(n))
 * put -> O(log(n))
"""
from sortedcontainers import SortedList

"""

Key observation
--------------------
- we need to track the frequency of each item, a map would do that for us in O(1) time. Map[key,freq]
- We need to remove the item which is LFU, so we can use bucket technique, these bucket will be org based on frequency.
- for any 'get' on any item, will move its frequency up since its recently used, that make move it to tail
- for any 'put' on any item, 
        if exists:
            push it to tail with updated frequency 
        else:
            freq=1 -> than it needs to be sit at the right freq bucket 

- To maintain the order of an item being added / updated would be needed as there could be multiple key with same frequency but we need to evict only the one which was least recently used. 
    Hence, we need an order maintain key, we can use timestamp for the same. 

Data structure selection:
--------------------------------
Since, the frequency of item can be changed any time, we need efficient DS that can update the item efficiently based on access pattern. 

This implies that, we can access that efficiently and also that is ordered. 


Possible Data structures:    
1 ) Min Heap 
            - we can use min heap, which will give access to least frequently item at top which is O(log(n))
            - adding new item, would also O(log(n))
       However
            - Removing an item from anywhere, that will be O(n) in worst case
                - in get/put, it increase the frequency of an item that could be anywhere in the heap.
                
     With the limitation of heap removing item in O(n), we need another DS which maintain the order and also remove item from anywhere would also only O(log(n))
  
get() -> O(n)
put() -> O(n)  
  
2) Tree-Map/SortedList [ Red-Black Tree] 
            - we can use tree Map that will maintain the frequency of each item and additionally add / pop / remove all are O(log(n)) only. 

    Java : Treemap
    Python: SortedList

get() -> O(log(n))
put() -> O(log(n))


3) LinkedList : Even with TreeMap, the efficiency stick to O(log(n)) however, these kind of cache can not have even O(log(n)) complexity as the size of cache would be huge that makes log(n) huge as well. 
    We need to drive it in O(1) time amortized at least. 
    
    Just like LRU, we need to think in direction of CDLL only. 
   
CDLL:
    - tail : we can have MFU item at tail
    - head : we can have LRU item at head
This allow us to delete the LRU item in O(1) time because of pointer switch only. 

Cache [Map]:
    - Key check : To know if a key exists in cache or not , we need map(key : item(value) ) to have O(1) t.c. 
    - freq check : we also need to know the frequency of each key in order to place it right order, that require another map with key as frequency.
        However, since for a single frequency, there could be multiple items, hence we need to maintain a CDLL for that it-self. 
        Which makes its a frequency map = {freq : CDLL}

Eviction:
    - as we always need to evict the lru item, that leads to the point that we need to somehow know the least frequency frequency. For this we can maintain a 
        variable min_freq that help us to know the least frequency item currently our cache has. 
               - whenever we add a new item which is not in cache, the min_freq = 1 
               - whenever we remove the last item from the  min_freq, then it needs to be updated to next freq that is min_freq + 1


Hence we have:
a. map { key : item } -> allow O(1) access for the key in cache
b. map {freq : CDLL } -> allow us to quickly access the min_freq item 
    - CDLL : allow to maintain tail and head of items per frequency
c. min_freq -> the variable to keep track the minimum frequency 


Note: we don't need to maintain the timestamp as we always add new item in tail of the cdll, which makes head always the lru
 
Bonus:
------
While LFU is powerful, it can suffer from "frequency pollution" where an item accessed 1,000 times in the past stays in the cache forever, even if it's never used again.

Advanced Tip: Systems like Redis or Caffeine often use segmented LFU or Aging (gradually decreasing frequency counts over time) to keep the cache relevant.


To implement **Aging**, we need to ensure that historical "hot" items don't block new, relevant items indefinitely. A common senior-level approach is to **decay** frequencies.

### The Aging Strategy: Frequency Reset

Instead of complex math, a simple production strategy is to periodically halve all frequency counts or reset them when a certain threshold is met.

### Updated Logic for Aging

We can add a `total_operations` counter. Every  operations, we "age" the cache:

1. Iterate through all nodes.
2. Move each node from its current `freq_cdll` to `freq_cdll[node.freq // 2]`.
3. Re-calculate the `min_freq`.

### Why this is tricky

Doing this globally is , which breaks our  guarantee for that specific call.

**Senior Design Choice:** Instead of a global reset, you can use **Window TinyLFU**. You maintain a small "admission" LRU. Only if an item proves it’s "hot" in the window does it get promoted to the main LFU segments.

"""

class LFUCache_CDLL:

    class DLLNode:
        def __init__(self, key, value, freq):
            """we don't need to maintain the timestamp as we always add new item in tail of the cdll, which makes head always the lru"""
            self.key, self.value, self.freq = key, value, freq
            self.next = self.prev = None

    class CDLL:
        def __init__(self):
            sentinel = LFUCache_CDLL.DLLNode(-1, -1, -1)
            self.head = self.tail = sentinel

            # make it cicular
            self.head.next = self.tail
            self.tail.prev = self.head

        def append(self, node):
            """append to end"""

            nxt, prev = self.tail.next, self.tail.prev

            # add at the end
            self.tail.next = node
            node.prev = self.tail

            # update tail
            self.tail = node

            # make it cicular
            node.next = nxt
            nxt.prev = node

        def pop(self, node):
            """pop the node from list"""
            if self.is_empty():
                return

            nxt, prev = node.next, node.prev

            prev.next = nxt
            nxt.prev = prev

            # if this is the tail node
            if self.tail == node:
                self.tail = prev

            # clean up the node
            node.next = node.prev = None

        def is_empty(self):
            return self.head.next == self.head

        def remove(self):
            """remove head of list"""
            if self.is_empty():
                return None  # no item in list

            node = self.head.next
            self.pop(node)
            return node

    def __init__(self, capacity: int):
        self.cache = {}  # {key : Node} allow O(1) access for the key in cache
        self.freq_cdll = (
            {}
        )  # {freq : CDLL} allow us to quickly access the min_freq item
        self.min_freq = 1  # the variable to keep track the minimum frequency
        self.capacity = capacity

    def _update_min_freq(self):
        # update min_freq
        if self.min_freq in self.freq_cdll and self.freq_cdll[self.min_freq].is_empty():
            self.min_freq += 1

    def _update(self, node):
        """ update the lfu cdll """

        # as we need to increase its frequency, first we remove it from cdll of its current frequency
        old_freq = node.freq
        # 1. Pop from current freq list
        self.freq_cdll[node.freq].pop(node)


        # 2. Update min_freq
        self._update_min_freq()

        # increase its frequency and put it on right cdll or create one
        node.freq += 1
        if node.freq not in self.freq_cdll:
            self.freq_cdll[node.freq] = self.CDLL()

        # add item
        self.freq_cdll[node.freq].append(node)

        # free up the memory
        if self.freq_cdll[old_freq].is_empty():
            del self.freq_cdll[old_freq] 

    def get(self, key: int) -> int:

        if key not in self.cache:
            return -1

        # since its exists, then its frequency going to be increase.
        node = self.cache[key]
        self._update(node)
        return node.value

    def put(self, key: int, value: int) -> None:
        if self.capacity <= 0:
            return

        if key in self.cache:
            # key already exists, hence its need to udpate with its frequency
            node = self.cache[key]
            node.value = value
            self._update(node)
        else:
            # new item
            if len(self.cache) >= self.capacity:
                # cache is full, evict the item
                lru_node = self.freq_cdll[self.min_freq].remove()
                del self.cache[lru_node.key]
                self._update_min_freq()

            node = self.DLLNode(key, value, 1)
            self.cache[key] = node
            self.min_freq = 1
            if self.min_freq not in self.freq_cdll:
                self.freq_cdll[self.min_freq] = self.CDLL()
            self.freq_cdll[self.min_freq].append(node)



class LFUCache_SortedList:
    """
   
    """

    class Item:
        def __init__(self, key, value, freq, time):
            self.key = key
            self.value = value
            self.freq = freq
            self.time = time

        def __lt__(self, other):
            if self.freq == other.freq:
                return self.time < other.time
            return self.freq < other.freq

    def __init__(self, capacity: int):
        self.cache = {}
        self.memory = SortedList()  # of item
        self.current_timestamp = 0
        self.capacity = capacity

    def _update(self, item):
        self.memory.remove(item) # it use sorting condition to remove item
        item.freq += 1
        item.time = self.current_timestamp
        self.memory.add(item)

    def get(self, key: int) -> int:
        self.current_timestamp += 1
        # key is not in cache
        if key not in self.cache or self.capacity == 0:
            return -1

        item = self.cache[key]
        self._update(item)

        return item.value

    def put(self, key: int, value: int) -> None:
        if self.capacity == 0:
            return

        self.current_timestamp += 1

        if key in self.cache:
            item = self.cache[key]
            item.value = value
            self._update(item)
        else:
            # not in cache, remove lfu item first
            if len(self.cache) >= self.capacity:
                lfu_item = self.memory.pop(0)  # remove from memroy
                del self.cache[lfu_item.key]

            # add in memory
            item = self.Item(key=key, value=value, time=self.current_timestamp, freq=1)
            self.cache[key] = item
            self.memory.add(item)


# Your LFUCache object will be instantiated and called as such:
# obj = LFUCache(capacity)
# param_1 = obj.get(key)
# obj.put(key,value)
