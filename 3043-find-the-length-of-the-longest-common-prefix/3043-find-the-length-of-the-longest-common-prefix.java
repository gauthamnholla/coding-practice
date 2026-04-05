class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<Integer> prefixes = new HashSet<>();

        // Store all prefixes of every number in arr1
        for (int num : arr1) {
            while (num > 0) {
                prefixes.add(num);
                num /= 10;
            }
        }

        int best = 0;

        // Check all prefixes of every number in arr2 against the set
        for (int num : arr2) {
            while (num > 0) {
                if (prefixes.contains(num)) {
                    best = Math.max(best, String.valueOf(num).length());
                    break; // longest prefix of this num found, no need to go shorter
                }
                num /= 10;
            }
        }

        return best;
    }
}