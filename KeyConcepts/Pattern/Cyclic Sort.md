# Cyclic Sort

This pattern describes an interesting approach to deal with problems involving arrays containing numbers in a given range.

### For example, take the following problem:

> You are given an unsorted array containing numbers taken from the range `1` to `n` The array can have duplicates, which means that some numbers will be missing. Find all the missing numbers.

To efficiently solve this problem, we can use the fact that the input array contains numbers in the range of `1` to `n`.
For example, to efficiently sort the array, we can try placing each number in its correct place, i.e., placing `1` at index `0`, placing `2` at index `1`, and so on. Once we are done with the sorting, we can iterate the array to find all indices that are missing the correct numbers. These will be our required numbers.

## Cyclic Sort (easy)

We are given an array containing `n` objects. Each object, when created, was assigned a unique number from `1` to `n` based on their creation sequence. This means that the object with sequence number `3` was created just before the object with sequence number `4`.

> Write a function to sort the objects <i>in-place</i> on their creation sequence number in `O(n)` and without any extra space.

```
Most complicated(and smart) part for cycle sort is nums[i] != nums[correctIdx]
It handles two possible cases: 1) when value not in correct position, 2) when duplicate is encountered;
```
For simplicity, let’s assume we are passed an integer array containing only the sequence numbers, though each number is actually an object.
As we know, the input array contains numbers in the range of `1` to `n`. We can use this fact to devise an efficient way to sort the numbers. Since all numbers are unique, we can try placing each number at its correct place, i.e., placing `1` at index `0`, placing `2` at index `1`, and so on.

To place a number (or an object in general) at its correct index, we first need to find that number. If we first find a number and then place it at its correct place, it will take us `O(N²)`, which is not acceptable.

Instead, what if we iterate the array one number at a time, and if the current number we are iterating is not at the correct index, we swap it with the number at its correct index. This way we will go through all numbers and place them in their correct indices, hence, sorting the whole array.

For questions ref [Pattern 05_Cyclic Sort.md](../../DSA_Pattern/Pattern%2005_Cyclic%20Sort.md)