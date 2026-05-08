from collections import defaultdict, deque

class Solution:
    def minJumps(self, nums: list[int]) -> int:
        n = len(nums)
        if n == 1:
            return 0

        # prime -> all indices j where nums[j] % prime == 0
        prime_to_indices: dict[int, list[int]] = defaultdict(list)
        for i, val in enumerate(nums):
            for p in self._prime_factors(val):
                prime_to_indices[p].append(i)

        visited = [False] * n
        visited[0] = True
        queue = deque([0])
        jumps = 0

        while queue:
            jumps += 1
            for _ in range(len(queue)):
                cur = queue.popleft()

                # Adjacent steps
                for nxt in (cur - 1, cur + 1):
                    if 0 <= nxt < n:
                        if nxt == n - 1:
                            return jumps
                        if not visited[nxt]:
                            visited[nxt] = True
                            queue.append(nxt)

                # Prime teleportation (only if nums[cur] is itself prime)
                if self._is_prime(nums[cur]):
                    p = nums[cur]
                    targets = prime_to_indices.pop(p, [])
                    for nxt in targets:
                        if nxt == cur:
                            continue
                        if nxt == n - 1:
                            return jumps
                        if not visited[nxt]:
                            visited[nxt] = True
                            queue.append(nxt)

        return -1

    def _prime_factors(self, x: int) -> list[int]:
        """Return all distinct prime factors of x."""
        factors = []
        if x < 2:
            return factors
        # Check factor 2
        if x % 2 == 0:
            factors.append(2)
            while x % 2 == 0:
                x //= 2
        # Check odd factors
        i = 3
        while i * i <= x:
            if x % i == 0:
                factors.append(i)
                while x % i == 0:
                    x //= i
            i += 2
        # Remaining prime factor > sqrt(original x)
        if x > 1:
            factors.append(x)
        return factors

    def _is_prime(self, x: int) -> bool:
        if x < 2:
            return False
        if x == 2:
            return True
        if x % 2 == 0:
            return False
        i = 3
        while i * i <= x:
            if x % i == 0:
                return False
            i += 2
        return True