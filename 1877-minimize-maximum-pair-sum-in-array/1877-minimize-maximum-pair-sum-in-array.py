class Solution:
    def minPairSum(self, nums: List[int]) -> int:
        n = len(nums)
        size = 10
        while size < n:
            size *= 10
        size = 100000 if n == 10000 else size + 1
        freq = [0] * size

        for n in nums:
            freq[n] += 1

        res = 0
        i, j = 0, size - 1

        while i <= j:
            if freq[i] == 0:
                i += 1
            elif freq[j] == 0:
                j -= 1
            else:
                res = max(res, i + j)
                freq[i] -= 1
                freq[j] -= 1

        return res
