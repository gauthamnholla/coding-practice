numMap = {}
for i in range(12):
    numMap[1 << i] = i

class Solution:
    def minMergeCost(self, lists: List[List[int]]) -> int:
        dp, median, length, lengths = [0], [0], len(lists), [0]
        for mask in range(1, 1 << length):
            combined, curLen = [], 0
            if mask in numMap:
                combined = lists[numMap[mask]]
                curLen = len(combined)
                curMedian = combined[(curLen - 1) // 2]
                lengths.append(curLen)
                median.append(curMedian)
                dp.append(0)
                continue

            for index in range(length):
                if mask & (1 << index):
                    combined.extend(lists[index])

            combined.sort()
            curLen = len(combined)
            median.append(combined[(curLen - 1) // 2])
            lengths.append(curLen)
            dp.append(float("inf"))
            cur = mask & (mask - 1)
            while cur > 0:
                other = cur ^ mask
                curMergerCost = dp[cur] + dp[other] + lengths[other] + lengths[cur] + abs(median[other] - median[cur])
                if dp[mask] > curMergerCost:
                    dp[mask] = curMergerCost
                cur = (cur - 1) & mask
            combined = []

        return dp[-1]