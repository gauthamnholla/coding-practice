import java.util.*;

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int prevTime = 0;
        
        for (String log : logs) {
            String[] parts = log.split(":");
            int id = Integer.parseInt(parts[0]);
            String type = parts[1];
            int time = Integer.parseInt(parts[2]);
            
            if (type.equals("start")) {
                if (!stack.isEmpty()) {
                    // top function has been running from prevTime to time-1
                    res[stack.peek()] += time - prevTime;
                }
                stack.push(id);
                prevTime = time;
            } else { // "end"
                // current function runs from prevTime to time (inclusive)
                int finishedId = stack.pop();
                res[finishedId] += time - prevTime + 1;
                prevTime = time + 1;
            }
        }
        
        return res;
    }
}
