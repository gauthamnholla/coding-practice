class Solution {
    public int maxCollectedFruits(int[][] fruits) {
        int n = fruits.length;
        int total = 0;

        // Collect the fruits from the main diagonal: positions (0,0), (1,1), ..., (n-1,n-1)
        for (int i = 0; i < n; i++) {
            total += fruits[i][i];
        }

        // For the path starting from top-right (child 2): path collects [0][n-1] initially
        int[] rightPath = new int[3];
        rightPath[0] = fruits[0][n - 1];

        // For the path starting from bottom-left (child 3): path collects [n-1][0] initially
        int[] bottomPath = new int[3];
        bottomPath[0] = fruits[n - 1][0];

        int window = 2; // Controls the growing "width" for dynamic programming arrays

        // For each movement step except start and end
        for (int step = 1; step < n - 1; step++) {
            int[] newRight = new int[window + 2];   // For current state in rightPath DP
            int[] newBottom = new int[window + 2];  // For current state in bottomPath DP

            // For the "window" sliding dynamic programming: tracks possible distances from start
            for (int dist = 0; dist < window; dist++) {
                // Calculate max of possible previous states for rightPath
                int left = (dist - 1 >= 0) ? rightPath[dist - 1] : 0;
                int mid = rightPath[dist];
                int right = (dist + 1 < rightPath.length) ? rightPath[dist + 1] : 0;
                // DP transition: take max of three possible moves, add current fruit
                newRight[dist] = Math.max(left, Math.max(mid, right)) + fruits[step][n - 1 - dist];

                // Do same for bottomPath
                left = (dist - 1 >= 0) ? bottomPath[dist - 1] : 0;
                mid = bottomPath[dist];
                right = (dist + 1 < bottomPath.length) ? bottomPath[dist + 1] : 0;
                newBottom[dist] = Math.max(left, Math.max(mid, right)) + fruits[n - 1 - dist][step];
            }

            rightPath = newRight;
            bottomPath = newBottom;

            // Window adjustment: controls when to widen or shrink DP arrays (not fully standard, possibly based on geometry)
            if (window - n + 4 + step <= 1) {
                window += 1;
            } else if (window - n + 3 + step > 1) {
                window -= 1;
            }
        }

        // Final answer = sum of main diagonal + best rightPath + best bottomPath
        return total + rightPath[0] + bottomPath[0];
    }
}
