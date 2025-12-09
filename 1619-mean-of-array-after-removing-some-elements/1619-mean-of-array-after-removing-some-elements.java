class Solution {
    
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int redundant = (int) (arr.length * 0.05);
        int sum = 0;
        for (int i = redundant; i < (arr.length - redundant); i++) {
            sum += arr[i];
        }
        return sum / (arr.length - redundant * 2.0);
    }
}