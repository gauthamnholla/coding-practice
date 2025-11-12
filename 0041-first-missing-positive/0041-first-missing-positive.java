class Solution {
    public int firstMissingPositive(int[] nums) {
        // Step 1: Keep only positive numbers
        int[] filteredNums = Arrays.stream(nums).filter(n -> n > 0).toArray();

        // Step 2: Sort the filtered numbers
        Arrays.sort(filteredNums);

        int target = 1; // Smallest positive number to find

        // Step 3: Loop through sorted numbers
        for (int n : filteredNums) {
            if (n == target) {
                target++; // Found the current target, move to next
            } else if (n > target) {
                return target; // Found a gap → return missing number
            }
        }

        // Step 4: If no gap, return next positive number
        return target;
    }
}
