class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        for (int t = n - 1; t >= k; t--) {
            energy[t - k] += energy[t];
        }
        int maxEnergy = energy[0];
        for (int e : energy) {
            maxEnergy = Math.max(maxEnergy, e);
        }
        return maxEnergy;
    }
}