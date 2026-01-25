class Solution {
    public static int freq(int[] arr, int n, int k, int th) {
        int c = 0;
        double sum = 0;
        double avg;
        for (int i = 0; i < k; i++) {
            sum = sum + arr[i];
        }
        avg = sum / k;
        if (avg >= th) {
            c = c + 1;
        }
        for (int i = k; i < n; i++) {
            sum = sum - arr[i - k];
            sum = sum + arr[i];
            avg = sum / k;
            if (avg >= th) {
                c = c + 1;
            }

        }
        return c;
    }

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int n = arr.length;
        int res = freq(arr, n, k, threshold);
        return res;
    }
}