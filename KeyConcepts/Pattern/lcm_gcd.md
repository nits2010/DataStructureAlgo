# 📘 GCD & LCM – Key Concepts

---

## ✅ GCD (Greatest Common Divisor)

- **Definition**: The largest positive integer that divides both `a` and `b` without leaving a remainder.
- **Example**: `gcd(12, 18) = 6`
- **Euclidean Algorithm** (Recursive):
```java
  int gcd(int a, int b) {
      if (a == 0) return b;
      return gcd(b % a, a);
  }
```
## ✅ LCM (Least Common Multiple)
- **Definition**: The smallest positive integer that is a multiple of both a and b.

- **Example**:  lcm(12, 18) = 36

Relation with GCD:
```java
int lcm(int a, int b) {
    return (a / gcd(a, b)) * b;
}
```


## ✅ Find the common numbers which is divisible by both `a` and `b`

```java
int common  = total / lcm; 
```

### Problems 
- [MinimizeTheMaximumOfTwoArrays_2513](https://leetcode.com/problems/minimize-the-maximum-of-two-arrays/description)