class Solution {
    public int smallestDistancePair(int[] numbers, int k) {
        Arrays.sort(numbers); // Sort the array first
        int minDistance = 0;
        int maxDistance = numbers[numbers.length - 1] - numbers[0]; // Max possible distance
        
        // Binary search for the smallest distance
        while (minDistance < maxDistance) {
            int midDistance = minDistance + (maxDistance - minDistance) / 2;
            int pairsCount = countPairsWithinDistance(numbers, midDistance);

            // If we have fewer than k pairs, increase distance
            if (pairsCount < k) {
                minDistance = midDistance + 1;
            } else {
                // Otherwise, try to find a smaller valid distance
                maxDistance = midDistance;
            }
        }

        return minDistance; // The k-th smallest pair distance
    }

    // Count number of pairs with distance ≤ targetDistance
    private int countPairsWithinDistance(int[] numbers, int targetDistance) {
        int count = 0;
        int left = 0;

        for (int right = 1; right < numbers.length; right++) {
            // Move left pointer until distance is within target
            while (numbers[right] - numbers[left] > targetDistance) {
                left++;
            }
            count += right - left; // Add number of valid pairs ending at 'right'
        }

        return count;
    }
}
