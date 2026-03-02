class Solution {
    public int minimumOperations(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        
        // Create frequency maps for both sections
        final Map<Integer, Integer> aFreq = new HashMap<>();
        final Map<Integer, Integer> bFreq = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i % 2 == 0) {
                aFreq.put(num, aFreq.getOrDefault(num, 0) + 1);
            } else {
                bFreq.put(num, bFreq.getOrDefault(num, 0) + 1);
            }
        }
        
        // Create PriorityQueues of the most frequent elements
        PriorityQueue<Integer> aQ = new PriorityQueue<>(10, (a,b) -> Integer.compare(aFreq.get(b), aFreq.get(a)));
        PriorityQueue<Integer> bQ = new PriorityQueue<>(10, (a,b) -> Integer.compare(bFreq.get(b), bFreq.get(a)));
        for (Map.Entry<Integer, Integer> e: aFreq.entrySet()) {
            aQ.offer(e.getKey());
        }
        for (Map.Entry<Integer, Integer> e: bFreq.entrySet()) {
            bQ.offer(e.getKey());
        }
        
        // Get maximums, check if they differ. If they do, check the secondmost frequent element
        int aMaxNum = aQ.poll();
        int aMaxFreq = aFreq.get(aMaxNum);
        int bMaxNum = bQ.poll();
        int bMaxFreq = bFreq.get(bMaxNum);
        if (aMaxNum != bMaxNum) {
            return nums.length - aMaxFreq - bMaxFreq;
        } else {
            int bSecondFreq = 0;
            int aSecondFreq = 0;
            if (!bQ.isEmpty()) {
                bSecondFreq = bFreq.get(bQ.poll());
            }
            if (!aQ.isEmpty()) {
                aSecondFreq = aFreq.get(aQ.poll());
            }
            return Math.min(nums.length - aMaxFreq - bSecondFreq, nums.length - bMaxFreq - aSecondFreq);
        }
    }
};