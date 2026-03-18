class Solution:
    def countSubmatrices(self, grid: List[List[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        
        # Step 1: Build 2D prefix sum table
        # prefix[i][j] = sum of all elements from (0,0) to (i-1, j-1)
        prefix = [[0] * (n + 1) for _ in range(m + 1)]
        
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                prefix[i][j] = (grid[i-1][j-1]
                                + prefix[i-1][j]      # sum above
                                + prefix[i][j-1]      # sum to the left
                                - prefix[i-1][j-1])   # subtract double-counted corner
        
        # Step 2: Count submatrices from (0,0) to (i,j) with sum <= k
        count = 0
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                # prefix[i][j] is exactly the sum of submatrix (0,0) -> (i-1,j-1)
                if prefix[i][j] <= k:
                    count += 1
        
        return count