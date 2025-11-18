import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // sentinel to simplify width calculation
        
        long maxArea = 0L;
        
        for (int i = 0; i <= n; i++) {
            int curH = (i == n) ? 0 : heights[i];
            while (stack.peek() != -1 && heights[stack.peek()] > curH) {
                int h = heights[stack.pop()];
                int leftIndex = stack.peek();        // index of previous smaller
                int width = i - leftIndex - 1;      // width where h is minimum
                long area = (long) h * width;
                if (area > maxArea) maxArea = area;
            }
            stack.push(i);
        }
        
        return (int) maxArea;
    }
}
