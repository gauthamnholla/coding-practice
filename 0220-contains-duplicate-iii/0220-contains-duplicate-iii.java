public class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false; // Invalid input cases
        
        Map<Long, Long> map = new HashMap<>(); // Bucket map

        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE; // Shift to avoid negatives
            long bucket = remappedNum / ((long) t + 1); // Assign bucket ID

            // Check same or neighboring buckets for valid pair
            if (map.containsKey(bucket)
                || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                return true;

            // Keep only last k elements in the window
            if (map.size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }

            // Add current number to its bucket
            map.put(bucket, remappedNum);
        }

        return false; // No such pair found
    }
}
