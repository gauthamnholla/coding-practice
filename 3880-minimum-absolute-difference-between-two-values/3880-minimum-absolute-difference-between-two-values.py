class Solution:
    def minAbsoluteDifference(self, nums):

        prevNum, prevIdx, mn = 0, 0, inf

        for i, num in enumerate(nums):
            if num == 0: continue
            if prevNum == 3 - num and i - prevIdx < mn: 
                    mn = i - prevIdx
            prevNum, prevIdx = num, i

        return mn if mn != inf else -1