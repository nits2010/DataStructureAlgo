# Masks and applications

#### 1. Last k bits set mask 

```
mask = (1 << k) - 1 // which is all 1; 0xfffff (i.e., k bits set to 1).
```
##### Application:
- To extract last k bits from a number