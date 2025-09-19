# Python Monkey Patching: The Art of Dynamic Code Modification

## Table of Contents
1. [Introduction](#introduction)
2. [What is Monkey Patching?](#what-is-monkey-patching)
3. [Python's Dynamic Nature](#pythons-dynamic-nature)
4. [Real-World Example: Enhancing a Third-Party Library](#real-world-example-enhancing-a-third-party-library)
5. [How It Works Under the Hood](#how-it-works-under-the-hood)
6. [Advanced Examples](#advanced-examples)
7. [Best Practices and Pitfalls](#best-practices-and-pitfalls)
8. [Comparison with Other Languages](#comparison-with-other-languages)
9. [Conclusion](#conclusion)

## Introduction

Monkey patching is one of Python's most powerful and controversial features. It allows developers to modify the behavior of existing code at runtime, even in third-party libraries, without changing the original source code. This technique, while powerful, should be used judiciously as it can make code harder to understand and debug.

In this article, we'll explore monkey patching through a real-world scenario: enhancing a third-party library's functionality to add custom parameters without modifying the original code.

## What is Monkey Patching?

Monkey patching is the practice of modifying or extending code at runtime by replacing, adding, or modifying attributes, methods, or functions of existing classes or modules. The term comes from the idea of "patching" code like you would patch a piece of clothing - making small modifications to fix or enhance functionality.

### Key Characteristics:
- **Runtime Modification**: Changes happen while the program is running
- **No Source Code Changes**: Original code remains untouched
- **Dynamic**: Can be applied conditionally or based on runtime conditions
- **Global Impact**: Changes affect the entire application

## Python's Dynamic Nature

Python's design philosophy of "we're all consenting adults here" makes monkey patching possible and relatively straightforward. Unlike statically-typed languages like Java or C#, Python treats modules, classes, and functions as first-class objects that can be modified at runtime.

### Core Concepts:

#### 1. Modules are Objects
```python
import mymodule
print(type(mymodule))  # <class 'module'>
print(mymodule.__dict__)  # Dictionary containing all module contents
```

#### 2. Functions are Objects
```python
def my_function():
    return "Hello"

print(type(my_function))  # <class 'function'>
print(id(my_function))   # Memory address of the function object
```

#### 3. Everything is Mutable
```python
# You can modify almost anything at runtime
mymodule.my_function = new_function
MyClass.my_method = new_method
```

## Real-World Example: Enhancing a Third-Party Library

Let's walk through a practical example where we need to enhance a third-party library's functionality.

### The Problem

We're using the `agentCoreSDK` library, which provides an `IPSRetrievalTool` for making API calls. The original tool only supports basic parameters, but we need to add `chat_model` and `temperature` parameters to the API calls.

**Original Library Code:**
```python
# agentcoresdk/persistence/ips_vector.py
def get_metadata(doc_name: str) -> dict:
    metadata: dict = {"max_tokens": 1000, "query_configs": {"size": 5}}
    if doc_name is not None and doc_name.strip():
        pre_filter = {
            "bool": {"should": [{"term": {"domainAttributes.doc_name": doc_name}}]}
        }
        metadata["query_configs"]["pre_filter"] = pre_filter
    return metadata

class IPSVectorDatabaseProvider:
    def read(self, query, doc=None):
        payload = {
            "conversationId": f"{self.knowledge}-{uuid.uuid4()}",
            "messages": get_message(query, doc),  # Uses get_metadata internally
            "experience": self.knowledge,
        }
        # Make API call...
```

**Our Goal:** Add `chat_model` and `temperature` to the metadata without modifying the library.

### The Solution: Monkey Patching

```python
"""
Enhanced IPSRetrievalTool with custom parameters using monkey patching.
"""

from agentcoresdk.tools.retrieval_toolkit import IPSRetrievalTool

def create_enhanced_ips_tool(
    auth_header_retriever,
    knowledge_base: str,
    search_mode: str = "answer",
    chat_model: str = "gpt-5-chat-2025-08-07",
    temperature: float = 0.5,
    **kwargs
) -> IPSRetrievalTool:
    """
    Create an IPSRetrievalTool with chat_model and temperature parameters.
    """
    # Create the original tool
    tool = IPSRetrievalTool()
    tool.metadata = {
        "auth_header_retriever": auth_header_retriever,
        "knowledge": knowledge_base,
        "mode": search_mode,
    }
    
    # Monkey-patch the get_metadata function
    from agentcoresdk.persistence.ips_vector import get_metadata as original_get_metadata
    
    def enhanced_get_metadata(doc_name: str = None) -> dict:
        # Get the original metadata
        metadata = original_get_metadata(doc_name)
        
        # Add our custom parameters
        metadata["chat_model"] = chat_model
        metadata["temperature"] = temperature
        
        return metadata
    
    # Replace the function in the module
    from agentcoresdk.persistence import ips_vector
    ips_vector.get_metadata = enhanced_get_metadata
    
    return tool
```

### How It Works

1. **Function Reference Storage**: We save a reference to the original `get_metadata` function
2. **Enhanced Function Creation**: We create a new function that calls the original and adds our parameters
3. **Module Modification**: We replace the function in the module's namespace
4. **System-Wide Impact**: All code that uses `get_metadata` now gets our enhanced version

### Demonstration

```python
# Before monkey patching
from agentcoresdk.persistence.ips_vector import get_metadata
print(get_metadata("test"))
# Output: {'max_tokens': 1000, 'query_configs': {'size': 5, 'pre_filter': {...}}}

# After monkey patching
tool = create_enhanced_ips_tool(auth_header_retriever, "test_kb")
print(get_metadata("test"))
# Output: {'max_tokens': 1000, 'query_configs': {...}, 'chat_model': 'gpt-5-chat-2025-08-07', 'temperature': 0.5}
```

## How It Works Under the Hood

### Step-by-Step Process

1. **Import Resolution**
   ```python
   from agentcoresdk.persistence.ips_vector import get_metadata
   ```
   - Python loads the module into memory
   - Creates a function object in the module's namespace
   - `get_metadata` becomes a reference to that function object

2. **Reference Storage**
   ```python
   original_get_metadata = ips_vector.get_metadata
   ```
   - We store a reference to the original function object
   - This doesn't copy the function, just creates another reference

3. **Function Replacement**
   ```python
   ips_vector.get_metadata = enhanced_get_metadata
   ```
   - We replace the reference in the module's namespace
   - Now `ips_vector.get_metadata` points to our new function
   - The original function still exists (referenced by `original_get_metadata`)

4. **System-Wide Impact**
   ```python
   # Any code that imports get_metadata gets our enhanced version
   from agentcoresdk.persistence.ips_vector import get_metadata
   # This import now returns our enhanced function!
   ```

### Memory and Reference Management

```python
# Let's explore the memory implications
import sys
from agentcoresdk.persistence import ips_vector

print(f"Original function: {ips_vector.get_metadata}")
print(f"Function ID: {id(ips_vector.get_metadata)}")

# Store original
original = ips_vector.get_metadata

# Create new function
def new_function():
    return "enhanced"

# Replace
ips_vector.get_metadata = new_function

print(f"New function: {ips_vector.get_metadata}")
print(f"Function ID: {id(ips_vector.get_metadata)}")
print(f"Original still exists: {original}")
print(f"Are they the same? {ips_vector.get_metadata is new_function}")
```

## Advanced Examples

### 1. Method Monkey Patching

```python
class Calculator:
    def add(self, a, b):
        return a + b

# Monkey patch the add method
original_add = Calculator.add

def enhanced_add(self, a, b):
    result = original_add(self, a, b)
    print(f"Adding {a} + {b} = {result}")
    return result

Calculator.add = enhanced_add

# Usage
calc = Calculator()
result = calc.add(5, 3)  # Prints: Adding 5 + 3 = 8
```

### 2. Class Monkey Patching

```python
class User:
    def __init__(self, name):
        self.name = name

# Add a new method to the class
def get_display_name(self):
    return f"User: {self.name}"

User.get_display_name = get_display_name

# Usage
user = User("Alice")
print(user.get_display_name())  # Prints: User: Alice
```

### 3. Conditional Monkey Patching

```python
import os

def conditional_patch():
    if os.getenv("DEBUG") == "true":
        # Only patch in debug mode
        from mymodule import my_function as original_function
        
        def debug_function(*args, **kwargs):
            print(f"DEBUG: Calling function with args={args}, kwargs={kwargs}")
            result = original_function(*args, **kwargs)
            print(f"DEBUG: Function returned {result}")
            return result
        
        import mymodule
        mymodule.my_function = debug_function
```

### 4. Temporary Monkey Patching

```python
from contextlib import contextmanager

@contextmanager
def temporary_patch(module, function_name, new_function):
    """Temporarily monkey patch a function."""
    original_function = getattr(module, function_name)
    setattr(module, function_name, new_function)
    try:
        yield
    finally:
        setattr(module, function_name, original_function)

# Usage
def enhanced_function():
    return "enhanced"

with temporary_patch(mymodule, "my_function", enhanced_function):
    result = mymodule.my_function()  # Uses enhanced version
    print(result)  # Prints: enhanced

# Outside the context, original function is restored
result = mymodule.my_function()  # Uses original version
```

### 5. Decorator-Based Monkey Patching

```python
def monkey_patch(module, function_name):
    """Decorator for monkey patching functions."""
    def decorator(new_function):
        original_function = getattr(module, function_name)
        
        def wrapper(*args, **kwargs):
            return new_function(original_function, *args, **kwargs)
        
        setattr(module, function_name, wrapper)
        return wrapper
    return decorator

# Usage
@monkey_patch(mymodule, "my_function")
def enhanced_function(original_function, *args, **kwargs):
    print("Before calling original function")
    result = original_function(*args, **kwargs)
    print("After calling original function")
    return result
```

## Best Practices and Pitfalls

### Best Practices

1. **Document Your Patches**
   ```python
   # Good: Document why and what you're patching
   def create_enhanced_tool():
       """
       Creates an enhanced tool by monkey patching the get_metadata function.
       
       This patch adds chat_model and temperature parameters to the metadata
       without modifying the original library code.
       """
   ```

2. **Store Original References**
   ```python
   # Good: Store original for potential restoration
   original_function = module.function
   
   def enhanced_function():
       # Enhancement logic
       return original_function()
   
   module.function = enhanced_function
   ```

3. **Use Context Managers for Temporary Patches**
   ```python
   # Good: Use context managers for temporary patches
   with temporary_patch(module, "function", enhanced_function):
       # Code that needs the patch
       pass
   # Original function is automatically restored
   ```

4. **Test Your Patches**
   ```python
   def test_monkey_patch():
       # Test that the patch works as expected
       result = patched_function()
       assert "enhanced" in result
       
       # Test that original functionality still works
       original_result = original_function()
       assert original_result is not None
   ```

### Common Pitfalls

1. **Global State Pollution**
   ```python
   # Bad: Patching affects the entire application
   import requests
   requests.get = my_enhanced_get  # Affects ALL requests in the app
   ```

2. **Import Order Issues**
   ```python
   # Bad: Import order can affect patching
   from mymodule import my_function  # Gets original
   # ... later ...
   mymodule.my_function = enhanced_function  # Patch
   # my_function still references the original!
   ```

3. **Memory Leaks**
   ```python
   # Bad: Not cleaning up references
   def patch_function():
       original = module.function
       module.function = enhanced_function
       # original reference is never cleaned up
   ```

4. **Testing Issues**
   ```python
   # Bad: Patches can interfere with tests
   def test_something():
       # This test might be affected by previous patches
       result = module.function()
       assert result == expected  # Might fail due to patches
   ```

## Comparison with Other Languages

### Java Reflection
```java
// Java - complex and limited
Class<?> clazz = Class.forName("com.example.MyClass");
Method method = clazz.getMethod("myMethod", String.class);
Object result = method.invoke(instance, "parameter");
```

### C# Reflection
```csharp
// C# - also complex
Type type = Type.GetType("MyNamespace.MyClass");
MethodInfo method = type.GetMethod("MyMethod");
object result = method.Invoke(instance, new object[] { "parameter" });
```

### Python Monkey Patching
```python
# Python - simple and direct
mymodule.my_function = new_function
# That's it! The entire system now uses the new function
```

### Ruby (Similar to Python)
```ruby
# Ruby - also supports monkey patching
class String
  def reverse
    "enhanced: #{super}"
  end
end
```

## Conclusion

Monkey patching is a powerful technique that showcases Python's dynamic nature. When used responsibly, it can solve complex problems with minimal code, especially when working with third-party libraries that can't be modified directly.

### Key Takeaways:

1. **Power**: Monkey patching allows runtime modification of any code
2. **Simplicity**: Python makes it easy with its dynamic nature
3. **Responsibility**: Use it judiciously and document your changes
4. **Testing**: Always test your patches thoroughly
5. **Alternatives**: Consider if there are better approaches before monkey patching

### When to Use Monkey Patching:

- ✅ Enhancing third-party libraries without modifying source code
- ✅ Adding debugging or logging to existing functions
- ✅ Creating temporary fixes for library bugs
- ✅ Implementing feature flags or conditional behavior
- ✅ Testing and mocking in unit tests

### When NOT to Use Monkey Patching:

- ❌ As a substitute for proper software architecture
- ❌ For permanent changes that should be in the original code
- ❌ When it makes code harder to understand or debug
- ❌ In production code without thorough testing
- ❌ When there are cleaner alternatives available

Monkey patching is a tool in your Python arsenal - powerful when used correctly, dangerous when abused. Use it wisely, and it can solve problems that would otherwise require complex workarounds or library modifications.

---

*This article demonstrates the power of Python's dynamic nature through practical examples. Remember: with great power comes great responsibility!*
