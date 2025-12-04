class Solution {
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        int total = amount[0] + amount[1] + amount[2];
        int largest = amount[2];

        return Math.max(largest, (total + 1) / 2);
    }
}
