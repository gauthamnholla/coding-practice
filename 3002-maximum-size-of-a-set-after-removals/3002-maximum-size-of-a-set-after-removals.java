class Solution {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int half = n / 2;
        
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        
        for (int x : nums1) set1.add(x);
        for (int x : nums2) set2.add(x);
        
        Set<Integer> common = new HashSet<>(set1);
        common.retainAll(set2);
        
        Set<Integer> only1 = new HashSet<>(set1);
        only1.removeAll(set2);
        
        Set<Integer> only2 = new HashSet<>(set2);
        only2.removeAll(set1);
        
        // How many unique-to-each-array elements we can keep (capped at half)
        int keep1 = Math.min(only1.size(), half);
        int keep2 = Math.min(only2.size(), half);
        
        // Remaining slots after keeping unique elements
        int slots1 = half - keep1;
        int slots2 = half - keep2;
        
        // Fill remaining slots with common elements
        int commonUsed = Math.min(common.size(), slots1 + slots2);
        
        return keep1 + keep2 + commonUsed;
    }
}