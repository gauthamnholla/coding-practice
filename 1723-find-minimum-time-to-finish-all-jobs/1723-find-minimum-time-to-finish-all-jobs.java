import java.util.Arrays;

class Solution {
    private int minMaxTime = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int[] workers = new int[k]; // Array to store the total time assigned to each worker
        backtrack(jobs.length - 1, jobs, workers, k);
        return minMaxTime;
    }

    private void backtrack(int index, int[] jobs, int[] workers, int k) {
        if (index < 0) {
            int currentMax = Integer.MIN_VALUE;
            for (int i = 0; i < k; i++) {
                currentMax = Math.max(currentMax, workers[i]);
            }
            minMaxTime = Math.min(minMaxTime, currentMax);
            return;
        }
        for (int i = 0; i < k; i++) {
            // Prune: skip if the current worker has the same load as the previous worker
            if (i > 0 && workers[i] == workers[i - 1]) continue;
            if(workers[i]+jobs[index]> minMaxTime) continue;// Early termination if the current worker's load exceeds minMaxTime
            
            workers[i] += jobs[index];
            backtrack(index - 1, jobs, workers, k);
            workers[i] -= jobs[index];
            
        }
    }
}

