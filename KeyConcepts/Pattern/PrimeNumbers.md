# Prime numbers, check if a number is prime
```java
private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
```

# Prime numbers, check if a number is prime if extremely fast primality checks for large numbers (like >10⁹)
```java
// Faster version
private static boolean isPrime(int num) {
    if (num < 2) return false;
    if (num == 2 || num == 3) return true;
    if (num % 2 == 0 || num % 3 == 0) return false;

    for (int i = 5; i * i <= num; i += 6) {
        if (num % i == 0 || num % (i + 2) == 0) return false;
    }
    return true;
}
```

### Why it’s faster:
- Skips all even numbers after 2.
- Skips multiples of 3 as well.
- Checks only of the form 6k ± 1, which covers all primes > 3.


