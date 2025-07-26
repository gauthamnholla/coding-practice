class Solution {
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        //we are going to use Arrays to store the two smallest right ends (b) for each left end (a) in conflicting pairs
        int[] smallestConflict = new int[n+1];
        int[] secondSmallestConflict = new int[n+1];

        Arrays.fill(smallestConflict, Integer.MAX_VALUE);
        Arrays.fill(secondSmallestConflict, Integer.MAX_VALUE);

        //we are calculating preprocess to find 2 smallest conflicts for every starting point
        for(int[] pair : conflictingPairs){
            int a = Math.min(pair[0], pair[1]);
            int b = Math.max(pair[0], pair[1]);

            if(smallestConflict[a] > b){
                secondSmallestConflict[a] = smallestConflict[a];
                smallestConflict[a] = b;
            }else if(secondSmallestConflict[a] > b){
                secondSmallestConflict[a] = b;
            }
        }

        long totalSubarrays = 0;
        int currentBestIndex = n; //current position with minimum conflict
        int secondMinConflict = Integer.MAX_VALUE;
        long[] conflictContribution = new long[n + 1];

        //we are traversing from end to start to calculate valid subarrays
        for(int i=n; i>=1; i--){
            if (smallestConflict[currentBestIndex] > smallestConflict[i]) {
                secondMinConflict = Math.min(secondMinConflict, smallestConflict[currentBestIndex]);
                currentBestIndex = i;
            }else{
                secondMinConflict = Math.min(secondMinConflict, smallestConflict[i]);
            }

            //counting for valid subarrays starting from i and ending before the first conflict
            totalSubarrays += Math.min(smallestConflict[currentBestIndex], n+1)-i;

            //we are counting for potential subarrays that could be added if a specific conflict is removed
            conflictContribution[currentBestIndex] +=
                Math.min(Math.min(secondMinConflict, secondSmallestConflict[currentBestIndex]), n+1) -
                Math.min(smallestConflict[currentBestIndex], n+1);
        }

        // Find the best possible conflict to remove
        long maxExtraSubarrays=0;
        for(long extra : conflictContribution){
            maxExtraSubarrays = Math.max(maxExtraSubarrays, extra);
        }

        // Return base result + max gain by removing a single conflict
        return totalSubarrays + maxExtraSubarrays;
    }

    //this is brute force for this to think well
    /*
    public long maxSubarrays(int n, int[][] conflictingPairs) {
        boolean[][] isConflict = new boolean[n + 1][n + 1];
        for (int[] pair : conflictingPairs) {
            isConflict[pair[0]][pair[1]] = true;
            isConflict[pair[1]][pair[0]] = true;
        }

        long count = 0;
        for (int i = 1; i <= n; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i; j <= n; j++) {
                boolean valid = true;
                for (int x : seen) {
                    if (isConflict[x][j]) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) break;
                seen.add(j);
                count++;
            }
        }
        return count;
    }  */
}