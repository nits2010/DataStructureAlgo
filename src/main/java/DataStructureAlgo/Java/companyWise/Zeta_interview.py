"""

1. Non-decreasing array of integer 
    -> Remove duplicate numbers which are repeating. 
[ 1 1 2 2 3 3 4 5 5 5]
=>[1 2 3 4 5]

-----
[ 1 1 2 2 3 3 4 5 5 5]
[1,2,3]


"""


from typing import Counter


class NonDecreasingArray : 
    #O(n)/(n)
    def remove_duplicates(nums: list) -> list: 
        
        if not nums:
            return nums
        
        result = [nums[0]]
        
        for i in range(1, len(nums)):
            if nums[i] != result[-1]:
                result.append(nums[i])
        
        return result

     # O(n)/(1)
    def remove_duplicates_inplace(nums: list) -> list:

        if not nums:
            return nums
        
        write = 1
        
        # [ 1 1 2 2 3 3 4 5 5 5]
        for read in range(1, len(nums)):
            if nums[read] != nums[read-1]:
                nums[write] = nums[read]
                write +=1 
        
        # write tell you the lenght of the resultant array
        return nums[:write]

"""

Maximum number of alphabatic fruits of different types (a,b,c....z) from a array
contraint: 
1. Start from any pos, anywhere. 
2. We can pick max two different type of fruits. Not allowed to pick the third if we exhusted types.
3. Result should be the maximum possible fruits can be collected. Count

fruits: {A,B,C}
input: [A, B , C, A, C]
output: [C, A, C] : 3 return count

-> longest subarray at most 2 distint element
-> Sliding window
    -> increasing / decreasing monotically 
    
    
    => variable(l, r), HashMap 
    
    
"""

class MaxFruits:
    # O(n)/O(n)
    def collect_max_fruits(fruits: list, types_count: int) -> int :
        
        if not fruits:
            return 0
        
        
        # TODO: optimization: see if we have only types_count in the fruits
        freq = Counter(fruits)
        if len(freq) <= types_count:
            return len(fruits)
        
        
        
        left = 0
        fruits_freq = {}
        
        max_len = 0 
        
        
        for right in range(len(fruits)):
            fruit = fruits[right]
            fruits_freq[fruit] = fruits_freq.get(fruit, 0) + 1
            
            while len(fruits_freq) > types_count:
                left_fruit = fruits[left]
                fruits_freq[left_fruit] -= 1
                
                if fruits_freq[left_fruit] == 0:
                    del fruits_freq[left_fruit]
                
                left +=1 
            
            max_len = max(max_len, right - left + 1)
            
    
        return max_len
         

if __name__ == "__main__":
    
    
    #-------- Fruits V1  -----
    max_len = MaxFruits.collect_max_fruits(["A", "B", "C", "A", "C"], 2)
    expected_output = 3
    print (f"max_len={max_len}, Pass = {'Pass' if max_len == expected_output else 'Fail' }")
    
    
    max_len = MaxFruits.collect_max_fruits(["A", "B", "C", "D", "E"], 2)
    expected_output = 2
    print(
        f"max_len={max_len}, Pass = {'Pass' if max_len == expected_output else 'Fail' }")
    
    max_len = MaxFruits.collect_max_fruits(["A", "A", "A", "A", "A"], 2)
    expected_output = 5
    print(
        f"max_len={max_len}, Pass = {'Pass' if max_len == expected_output else 'Fail' }")
    
    max_len = MaxFruits.collect_max_fruits(["A", "A", "A", "A", "B"], 2)
    expected_output = 5
    print(
        f"max_len={max_len}, Pass = {'Pass' if max_len == expected_output else 'Fail' }")
    
    max_len = MaxFruits.collect_max_fruits(["A", "A", "A", "A", "B", "B", "B"], 2)
    expected_output = 7
    print(
        f"max_len={max_len}, Pass = {'Pass' if max_len == expected_output else 'Fail' }")
    
    max_len = MaxFruits.collect_max_fruits(["A"], 2)
    expected_output = 1
    print(
        f"max_len={max_len}, Pass = {'Pass' if max_len == expected_output else 'Fail' }")
    
    max_len = MaxFruits.collect_max_fruits([], 2)
    expected_output = 0
    print(
        f"max_len={max_len}, Pass = {'Pass' if max_len == expected_output else 'Fail' }")
    
    max_len = MaxFruits.collect_max_fruits(None, 2)
    expected_output = 0
    print(
        f"max_len={max_len}, Pass = {'Pass' if max_len == expected_output else 'Fail' }")


    # result = NonDecreasingArray.remove_duplicates([1, 1, 2, 2, 3, 3, 4, 5, 5, 5])
    # print(f"result = {result}")
    
    # result = NonDecreasingArray.remove_duplicates(
    #     [1,5])
    # print(f"result = {result}")
F

    # result = NonDecreasingArray.remove_duplicates(
    #     [1, 2,3,4,5])
    # print(f"result = {result}")
    
    # result = NonDecreasingArray.remove_duplicates(
    #     [1])
    # print(f"result = {result}")
    
    # result = NonDecreasingArray.remove_duplicates(
    #     [])
    # print(f"result = {result}")
    
    # result = NonDecreasingArray.remove_duplicates(
    #     None)
    # print(f"result = {result}")
    
    # result = NonDecreasingArray.remove_duplicates([1, 1,1,1,1,1,1])
    # print(f"result = {result}")
    
    
    
    
    #-------- version 2 ----- 
    
    # result = NonDecreasingArray.remove_duplicates_inplace(
    #     [1, 1, 2, 2, 3, 3, 4, 5, 5, 5])
    # print(f"result = {result}")

    # result = NonDecreasingArray.remove_duplicates_inplace(
    #     [1, 5])
    # print(f"result = {result}")

    # result = NonDecreasingArray.remove_duplicates_inplace(
    #     [1, 2, 3, 4, 5])
    # print(f"result = {result}")

    # result = NonDecreasingArray.remove_duplicates_inplace(
    #     [1])
    # print(f"result = {result}")

    # result = NonDecreasingArray.remove_duplicates_inplace(
    #     [])
    # print(f"result = {result}")

    # result = NonDecreasingArray.remove_duplicates_inplace(
    #     None)
    # print(f"result = {result}")

    # result = NonDecreasingArray.remove_duplicates_inplace(
    #     [1, 1, 1, 1, 1, 1, 1])
    # print(f"result = {result}")

  

        
        
        