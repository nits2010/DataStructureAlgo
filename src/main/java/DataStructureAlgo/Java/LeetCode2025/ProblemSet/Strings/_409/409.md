### Intuition and Algorithm for `longestPalindrome`

#### Intuition
The goal is to find the length of the longest palindrome that can be constructed from the characters of the given string. A palindrome reads the same forwards and backwards, so we need to consider character frequencies and their arrangements.

1. Characters with even frequencies can always be used in full to construct a palindrome.
2. Characters with odd frequencies can use their maximum even part to contribute to the palindrome length, and one single odd character can be placed in the middle of the palindrome to maximize the length.

#### Algorithm 1: Count Frequencies
1. **Edge Case Handling**:
    - If the input string is `null` or empty, return `0`.

2. **Frequency Counting**:
    - Iterate through the string and count the frequency of each character using a `HashMap`.

3. **Calculate Maximum Palindrome Length**:
    - Initialize counters for `odd` and `even` frequencies.
    - Traverse the frequency map:
        - If a character's count is even, add it to the `even` counter.
        - If a character's count is odd, add its maximum even part to the `odd` counter and mark that there's at least one odd frequency character.

4. **Determine Final Length**:
    - If there's at least one odd frequency character, add `1` to the total length to account for a central odd character.
    - Return the sum of `odd` and `even` 



#### Algorithm 2 : Cancel pairs
1. **Edge Case Handling**:
    - If the input string is `null` or empty, return `0`.

2. **Frequency Counting and Pair Formation**:
    - Traverse the string while keeping track of character frequencies using a `HashMap`.
    - For each character:
        - If it already exists in the map (indicating the formation of a pair), increment the `result` by `2` and remove the character from the map.
        - Otherwise, add the character to the map.

3. **Determine Final Length**:
    - After traversing the string, the `HashMap` will contain at most one character with an odd frequency.
    - The final length of the longest palindrome is the `result` plus `1` if the `HashMap` is not empty (indicating there's a single odd character), otherwise just the `result`.


# Complexity
- Time complexity:
$$O(n) 

- Space complexity:
 O(n)
