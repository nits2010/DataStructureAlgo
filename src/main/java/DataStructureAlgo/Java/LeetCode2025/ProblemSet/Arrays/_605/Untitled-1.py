def calculateWeight(formula: str) -> int:
    weights = {
        'C': 12,
        'H': 1,
        'O': 8
    }
    
    stack = []
    current_sum = 0
    i = 0
    n = len(formula)
    
    while i < n:
        char = formula[i]
        
        if char == '(':
            stack.append(current_sum)
            current_sum = 0
            i +=1
        elif char == ')':
            g_sum = current_sum
            i +=1 
            if i < n and formula[i].isdigit():
                g_sum *= int(formula[i])
                i+=1
            
            current_sum = stack.pop() + g_sum
            
        elif char in weights:
            weight = weights[char]
            
            if i + 1 < n and formula[i+1].isdigit():
                current_sum += weight * int(formula[i+1])
                i +=2
            else:
                i +=1
                current_sum += weight
        
    
    return current_sum

print(calculateWeight("C(C(C4))4"))