
from collections import defaultdict
from typing import Counter


class Solution_SW_ToRemoveV2:
    def takeCharacters(self, s: str, k: int) -> int:
        PATTERN = "abc"
        freq_map = Counter(s)
        limit = {c: freq_map[c] - k for c in PATTERN}

        if any(limit[c] < 0 for c in PATTERN):
            return -1

        window = defaultdict(int)
        left = 0
        length = 0
        for right, char in enumerate(s):
            window[char] += 1  # take

            while window[char] > limit[char]:  # took more than possible
                window[s[left]] -= 1
                left += 1

            length = max(length, right - left + 1)

        return len(s) - length


class Solution_SW_ToRemoveV1:
    def takeCharacters(self, s: str, k: int) -> int:
        PATTERN = "abc"

        freq_map = Counter(s)

        # don't have sufficient freq
        if any(freq_map[c] < k for c in PATTERN):
            return -1

        allowed = {c: freq_map[c] - k for c in PATTERN}

        # instead of finding what to take, find what to leave. then answer = n - what to leave

        leave_window = Counter()  # collect what to leave for each abc
        left = 0
        max_length = 0
        for right in range(len(s)):

            # take right character to leave
            leave_window[s[right]] += 1

            # if window is invalid
            while left <= right and any(leave_window[c] > allowed[c] for c in PATTERN):
                leave_window[s[left]] -= 1
                left += 1

            max_length = max(max_length, right - left + 1)

        return len(s) - max_length


class Solution:
    def takeCharacters(self, s: str, k: int) -> int:
        freq_map = Counter(s)

        # don't have sufficient freq
        if freq_map["a"] < k or freq_map["b"] < k or freq_map["c"] < k:
            return -1

        allowed = {}
        allowed["a"] = freq_map["a"] - k
        allowed["b"] = freq_map["b"] - k
        allowed["c"] = freq_map["c"] - k

        # instead of finding what to take, find what to leave. then answer = n - what to leave

        leave_window = defaultdict(int)  # collect what to leave for each abc
        left = 0
        max_length = 0
        for right in range(len(s)):

            # take right character to leave
            leave_window[s[right]] += 1

            # if window is invalid
            while left <= right and (
                leave_window["a"] > allowed["a"]
                or leave_window["b"] > allowed["b"]
                or leave_window["c"] > allowed["c"]
            ):
                leave_window[s[left]] -= 1
                left += 1

            max_length = max(max_length, right - left + 1)

        return len(s) - max_length

