

"""
Author: Nitin Gupta
Date: 10/02/2026
Question Title: 981. Time Based Key-Value Store
Link: https://leetcode.com/problems/time-based-key-value-store/description/
Description: Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.

Implement the TimeMap class:

TimeMap() Initializes the object of the data structure.
void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 

Example 1:

Input
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
Output
[null, null, "bar", "bar", null, "bar2", "bar2"]

Explanation
TimeMap timeMap = new TimeMap();
timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
timeMap.get("foo", 1);         // return "bar"
timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
timeMap.get("foo", 4);         // return "bar2"
timeMap.get("foo", 5);         // return "bar2"
 

Constraints:

1 <= key.length, value.length <= 100
key and value consist of lowercase English letters and digits.
1 <= timestamp <= 107
All the timestamps timestamp of set are strictly increasing.
At most 2 * 105 calls will be made to set and get.

File reference
-----------
Duplicate {@link TimeBasedKeyValueStore.java}
Similar {@link}
extension {@link }
DP-BaseProblem {@link }
<p><p>
Tags
-----
@medium
@HashTable
@String
@BinarySearch
@Design

<p><p>
Company Tags
-----
@Amazon 
@Apple 
@Atlassian 
@Databricks 
@Facebook 
@Flexport 
@Google 
@Lyft 
@Microsoft 
@Netflix 
@Sumologic 
@Twitter 
@Zillow
<p>
-----

@Editorial <p><p>
-----
@OptimalSolution {@link }
"""


from collections import deque, defaultdict, bisect
from typing import List, Optional, Dict, Any
from sortedcontainers import SortedList

from helpers.common_methods import CommonMethods


# This version of SortedList works regardless the input timestamp is sorted or not.
class TimeMap_SortedList:

    def __init__(self):
        self._map = defaultdict(lambda: SortedList(key=lambda x: x[0]))
        
    def set(self, key: str, value: str, timestamp: int) -> None:
        _list = self._map [key]
        _list.add((timestamp, value))

    def get(self, key: str, timestamp: int) -> str:
        _list = self._map[key]
        if not _list :
            return ""
        
        # _idx is insertion position, always > 0, if 0 means all the element in list is greater than timestamp
        # char(127) is the last entry could be store, beyond ascii
        # chr(127) refers to the ASCII Control Character known as DEL (Delete).
        # The "Highest" Character: In standard 7-bit ASCII, 127 is the maximum value. Even in Unicode/UTF-8, chr(127) sits above all standard keyboard letters, numbers, and symbols.
        _idx = _list.bisect_right((timestamp,chr(127))) - 1

        if _idx < 0:
            return ""

        value = _list[_idx]
        return value[1]
        

# This version has dependency and based on problem statement that the input timestamp is sorted always (increasing order).
class TimeMap_List:

    def __init__(self):
        self._map = defaultdict(list)
        # {key : [(timestamp, value)]}
        
    def set(self, key: str, value: str, timestamp: int) -> None:
        self._map[key].append((timestamp, value))
        
    def get(self, key: str, timestamp: int) -> str:
        if key not in self._map:
            return ""

        entry = self._map[key]

        # _idx is insertion position, always > 0, if 0 means all the element in list is greater than timestamp
        # chr(127) is the last entry could be store, beyond ascii
        # chr(127) refers to the ASCII Control Character known as DEL (Delete).
        # The "Highest" Character: In standard 7-bit ASCII, 127 is the maximum value. Even in Unicode/UTF-8, chr(127) sits above all standard keyboard letters, numbers, and symbols.
        _idx = bisect.bisect_right(entry, (timestamp, chr(127))) - 1

        if _idx >= 0:
            return entry[_idx][1]
        
        return ""
        




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
    tests: List = [test([], None),
                   test([], None)]

    CommonMethods.print_all_test_results(tests)
