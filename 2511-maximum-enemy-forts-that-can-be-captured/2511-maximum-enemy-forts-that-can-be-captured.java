class Solution {
    public int captureForts(int[] forts) {
        int last = -1;        // index of last non-zero fort
        int zeroCount = 0;    // count of enemy forts
        int maxCaptured = 0;

        for (int i = 0; i < forts.length; i++) {
            if (forts[i] == 0) {
                zeroCount++;
            } else {
                if (last != -1 && forts[last] + forts[i] == 0) {
                    maxCaptured = Math.max(maxCaptured, zeroCount);
                }
                last = i;
                zeroCount = 0;
            }
        }

        return maxCaptured;
    }
}
