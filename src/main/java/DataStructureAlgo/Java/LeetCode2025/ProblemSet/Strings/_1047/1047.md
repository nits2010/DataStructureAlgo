### Problem Explanation:


#### Example 1:
**Input:** `s = "abbaca"`
**Explanation:**
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca""`.

**Approach 1: Brute Force**

We can apply bruteforce to make it work, omitting to discuss here

**Approach 2: Stack**

We can use stack to achieve the same. 
#### Algorithm 
1. Create a stack
2. For each character in the string
3. Push the character in the stack, if stack is empty
4. if stack is not empty, check the top of stack and current character is same or not, if it is, then pop. 
5. else push the current character in the stack
6. return the length of the stack

**Approach 2: Manipulation**

Just like discussed above, we can manipulate the string in place, by maintaining two pointers `i` and `j`.
#### Algorithm 
1. Initialize `i = 0` and `j = 1`
2. Loop while `j` is less than the length of the string
3. If the character at `j` and `i` are same, then `j` is incremented and `i` is decremented
4. If the character at `j` and `i` are different, than copy character from position `j` to position `++i` 
5. Result would be a string from `0` to `i+1`