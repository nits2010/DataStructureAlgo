### Problem Explanation:
[Leetcode](https://leetcode.com/problems/minimum-string-length-after-removing-substrings/solutions/5882992/easy-solution-with-explanation)

#### Example 1:
**Input:** `s = "ABFCACDB"`
**Output:** `2`

**Explanation:**
We can do the following operations:
1. Remove the substring `"ABFCACDB"`, so `s = "FCACDB"`.
2. Remove the substring `"FCACDB"`, so `s = "FCAB"`.
3. Remove the substring `"FCAB"`, so `s = "FC"`.

So, the resulting length of the string is `2`. It can be shown that it is the minimum length that we can obtain.

#### Example 2:
**Input:** `s = "ACBBD"`
**Output:** `5`

## Intuition
We cannot do any operations on the string, so the length remains the same.

#### Example 3:
**Input:** `S = "CCCCDDDD"`
**Output:** `0`

This problem can be solved in many ways, the challenge is to take care of the cumulative condition. For example:
1. `CCCCDDDD`
2. `CCCDDD`
3. `CCDD`
4. `CD`
5. `{}`

If you observed, initially, until index `2`, there is no character to remove. After that, index `3` and index `4` make a pair and get removed. After that, all start making pairs with forward index `j` and another index backward `i`.

| Index | Characters    | Explanation                                |
|-------|---------------|--------------------------------------------|
| 0 1 2 3 4 5 6 7 | C C C C D D D D | `i = 3, j = 4` got removed. |
| 0 1 2 3 4 5 | C C C D D D | Now `i = 2, j = 5` needs to be removed. |
| 0 1 2 3 | C C D D | Now `i = 1, j = 6` needs to be removed. |
| 0 1 | C D | Now `i = 0, j = 7` needs to be removed. |
| {} | | Now `i = -1`.|

This process makes `i` move backward and `j` move forward. Since we are replacing text in place, it moves forward. We just need to maintain these indexes, and we are done. The length of the final string would be `i + 1`.

**Approach 1: Brute Force**

We can apply bruteforce to make it work, omitting to discuss here

**Approach 2: Stack**

We can use stack to achieve the same. 
#### Algorithm 
1. Create a stack
2. For each character in the string
3. Push the character in the stack, if stack is empty
4. if stack is not empty, check the top of stack and current character forming a pair or not, if it form the pair, then pop. 
5. else push the current character in the stack
6. return the length of the stack

**Approach 2: Manipulation**

Just like discussed above, we can manipulate the string in place, by maintaining two pointers `i` and `j`.
#### Algorithm 
1. Initialize `i = 0` and `j = 1`
2. Loop while `j` is less than the length of the string
3. If the character at `j` and `i` are forming a pair, then `j` is incremented and `i` is decremented
4. If the character at `j` and `i` are not forming a pair, than copy character from position `j` to position `++i` 
5. Result would be `i+1`