class Solution:
    def __init__(self):
        self.N = 100005
        self.hell = 1000000007
        self.a = [0] * (self.N)
        self.seg = [0] * (4 * self.N)
        self.segsq = [0] * (4 * self.N)
        self.lazy = [0] * (4 * self.N)

    # Build the segment tree.
    def build(self, node, start, end):
        if start == end:
            self.seg[node] = self.a[start]
            self.segsq[node] = (self.a[start] * self.a[start]) % self.hell
            return
        mid = (start + end) >> 1
        self.build(node << 1, start, mid)
        self.build(node << 1 | 1, mid + 1, end)
        self.seg[node] = (self.seg[node << 1] + self.seg[node << 1 | 1]) % self.hell
        self.segsq[node] = (self.segsq[node << 1] + self.segsq[node << 1 | 1]) % self.hell

    # Update the segment tree and handle lazy propagation.
    def update(self, node, start, end, l, r, val):
        if self.lazy[node] != 0:
            self.segsq[node] += ((end - start + 1) * (self.lazy[node] * self.lazy[node]) % self.hell) % self.hell + (2 * self.lazy[node] * self.seg[node]) % self.hell
            self.segsq[node] %= self.hell
            self.seg[node] += ((end - start + 1) * self.lazy[node]) % self.hell
            self.seg[node] %= self.hell
            if start != end:
                self.lazy[node << 1] += self.lazy[node]
                self.lazy[node << 1 | 1] += self.lazy[node]
            self.lazy[node] = 0
        if start > end or start > r or end < l:
            return
        if l <= start and end <= r:
            self.segsq[node] += ((end - start + 1) * (val * val) % self.hell) % self.hell + (2 * val * self.seg[node]) % self.hell
            self.segsq[node] %= self.hell
            self.seg[node] += ((end - start + 1) * val) % self.hell
            self.seg[node] %= self.hell
            if start != end:
                self.lazy[node << 1] += val
                self.lazy[node << 1 | 1] += val
            return
        mid = (start + end) >> 1
        self.update(node << 1, start, mid, l, r, val)
        self.update(node << 1 | 1, mid + 1, end, l, r, val)
        self.seg[node] = (self.seg[node << 1] + self.seg[node << 1 | 1]) % self.hell
        self.segsq[node] = (self.segsq[node << 1] + self.segsq[node << 1 | 1]) % self.hell

    # Main function to find the sum of counts.
    def sumCounts(self, nums):
        n = len(nums)
        for i in range(1, n + 1):
            self.a[i] = 0

        # Build the segment tree.
        self.build(1, 1, n)

        prev_seen_at = {}

        ans = 0

        for i in range(n - 1, -1, -1):
            if nums[i] not in prev_seen_at:
                self.update(1, 1, n, i + 1, n, 1)
            else:
                self.update(1, 1, n, i + 1, prev_seen_at[nums[i]] - 1, 1)

            prev_seen_at[nums[i]] = i + 1

            ans = (ans + self.segsq[1]) % self.hell

        return ans

