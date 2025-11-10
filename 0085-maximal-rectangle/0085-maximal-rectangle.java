import java.util.*;

public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, ans = 0;
        int[] h = new int[n];

        for (int i = 0; i < m; i++) {
            // build histogram for row i
            for (int j = 0; j < n; j++) h[j] = (matrix[i][j] == '1') ? h[j] + 1 : 0;

            // largest rectangle in histogram (monotonic stack)
            Deque<Integer> st = new ArrayDeque<>();
            for (int j = 0; j <= n; j++) {
                int curH = (j == n) ? 0 : h[j];
                while (!st.isEmpty() && curH < h[st.peek()]) {
                    int height = h[st.pop()];
                    int left = st.isEmpty() ? -1 : st.peek();
                    ans = Math.max(ans, height * (j - left - 1));
                }
                st.push(j);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] matrix = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        System.out.println(s.maximalRectangle(matrix)); // 6
    }
}
