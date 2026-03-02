class Solution {
    int[] best, memo;
    int numLaps, changeTime, maxLaps=0;
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        this.memo=new int[numLaps];
        this.best=new int[numLaps];
        this.numLaps=numLaps;
        this.changeTime=changeTime;
        int minChangeTime=Integer.MAX_VALUE;
        for(int[] t: tires) if(t[0]<minChangeTime) minChangeTime=t[0];
        minChangeTime+=changeTime;
        // Pre-process black vertices
        Arrays.fill(best, Integer.MAX_VALUE/2);
        for (int i = 0; i < tires.length; i++) {
            int lapTime=tires[i][0], totalTime=lapTime;
            for (int contLaps=0; contLaps<numLaps && lapTime<(contLaps+1)*minChangeTime; contLaps++) {
                if(totalTime<best[contLaps]) best[contLaps]=totalTime;
                if(contLaps>maxLaps) maxLaps=contLaps;
                lapTime*=tires[i][1];
                totalTime+=lapTime;
            }
        }
        // Top-down DP with memoization on orange cells
        // Removing one change time as we can start the race with
        // the tire we want without needing to change it
        return helper(0)-changeTime;
    }

    private int helper(int lap) {
        if(lap==numLaps) return 0;
        if (memo[lap]>0) return memo[lap];
        int min = Integer.MAX_VALUE/2;
        for (int i=0; i<=maxLaps && i+lap<=numLaps-1; i++) {
            min = Math.min(min,best[i]+helper(i+lap+1));
        }
        min+=changeTime;
        return memo[lap] = min;
    }
}