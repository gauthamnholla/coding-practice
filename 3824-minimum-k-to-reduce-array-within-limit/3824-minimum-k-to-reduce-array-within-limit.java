class Solution {
    public int minimumK(int[] nums) {
        int n = nums.length;

        java.util.function.LongPredicate ok = (long K) -> {
            long total = 0;
            for(int u : nums){
                total += (u + K - 1) / K;
            }
            return total <= K * K;
        };

        long low = 1, high = 1_000_000_000L;
        while(low < high){
            long mid = (low + high) / 2;
            if(ok.test(mid)){
                high = mid;
            }
            else{
                low = mid + 1;
            }
        }
        return (int)low;
    }
}